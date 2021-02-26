package com.sarafaria.android.github.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewAdapter<T : RecyclerView.ViewHolder, O> protected constructor(var mObjectList: List<O>) : RecyclerView.Adapter<T>() {

    private var mOnItemClickListener: OnItemClickListener<O>? = null

    override fun getItemCount(): Int {
        return mObjectList.size
    }

    fun getItem(position: Int): O {
        return mObjectList[position]
    }

    fun createItemClick(view: View, holder: T): T {
        view.setOnClickListener { onItemClick(holder) }
        return holder
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<O>) {
        this.mOnItemClickListener = onItemClickListener
    }

    fun onItemClick(holder: T) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener!!.onItemClick(mObjectList[holder.adapterPosition], holder.adapterPosition)
        }
    }

    interface OnItemClickListener<O> {
        fun onItemClick(item: O, position: Int)
    }

}