package com.sarafaria.android.github.user.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import coil.Coil
import coil.api.load
import com.sarafaria.android.github.R
import com.sarafaria.android.github.user.adapter.viewholder.UserViewHolder
import com.sarafaria.android.github.user.domain.model.User
import com.sarafaria.android.github.utils.RecyclerViewAdapter
import java.util.ArrayList

class UserAdapter(
    private val mActivity: Activity,
    private val mUsers: ArrayList<User>) : RecyclerViewAdapter<UserViewHolder, User>(mUsers) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_adapter_user, viewGroup, false)

        return createItemClick(view,
            UserViewHolder(
                view
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = mUsers[position]

        holder.mUsername.text = user.userName

        mActivity.apply {
            Coil.load(this, user.imageUrl) {
                placeholder(R.drawable.profile)
                crossfade(false)
                target { drawable ->
                    holder.mImageUrl.load(drawable)
                }
            }
        }
    }
}