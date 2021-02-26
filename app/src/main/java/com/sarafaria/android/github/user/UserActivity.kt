package com.sarafaria.android.github.user

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.sarafaria.android.github.R
import com.sarafaria.android.github.user.adapter.UserAdapter
import com.sarafaria.android.github.user.domain.model.User
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_user.*
import java.util.*
import javax.inject.Inject

class UserActivity : AppCompatActivity(), UserContract.View {
    private val mLinearLayoutManager: LinearLayoutManager by lazy { LinearLayoutManager(this) }

    @Inject
    lateinit var mPresenter: UserPresenter

    private var mAdapter: UserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        setupDI()
        initRecyclerView()
        setListeners()

        mPresenter.searchUsers()
    }

    private fun setListeners() {
        searchView.setOnClickListener { searchView.isIconified = false }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mPresenter.searchUserByUsername(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        searchView.setOnCloseListener {
            mPresenter.searchUsers()
            false
        }
    }

    private fun initRecyclerView() {
        recyclerViewUsers.setHasFixedSize(true)
        mLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerViewUsers.layoutManager = mLinearLayoutManager
    }

    private fun setupDI() {
        AndroidInjection.inject(this)
        if (!mPresenter.isBinded) mPresenter.bind(this)
    }

    override fun showToastMessage(resMessage: Int) {
        Toast.makeText(this, getString(resMessage), Toast.LENGTH_LONG).show()
    }

    override fun dismissContainerMessage() {
        containerErrorLoad.visibility = View.GONE
    }

    override fun showContainerMessage() {
        containerErrorLoad.visibility = View.VISIBLE
    }

    override fun showRecyclerView() {
        recyclerViewUsers.visibility = View.VISIBLE
    }

    override fun dismissProgressbar() {
        progressBar.visibility = View.GONE
    }

    override fun showMessage(message: String) {
        textViewMessage.text = message
    }

    override fun showMessage(resMessage: Int) {
        textViewMessage.text = getString(resMessage)
    }

    override fun loadUsers(user: List<User>) {
        mAdapter = UserAdapter(
            this,
            user as ArrayList<User>,
        )
        recyclerViewUsers.adapter = mAdapter
    }

    override fun onDestroy() {
        mPresenter.unbind()
        super.onDestroy()
    }
}