package com.sarafaria.android.github.user.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.layout_adapter_user.view.*

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val mUsername: TextView = itemView.username
    val mImageUrl: CircleImageView = itemView.imageUrl
}