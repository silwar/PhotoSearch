package com.silwar.photosearch.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silwar.photosearch.R
import com.silwar.photosearch.home.model.Photo


class PhotoListAdapter(private var photos: ArrayList<Photo>?) :
    RecyclerView.Adapter<PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(itemView)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return photos?.run { size } ?: 0
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos?.get(position)
        photo?.let {
            holder.bind(it)
        }
    }

    fun updateData(photoList: ArrayList<Photo>?) {
        if (photos == null) {
            photos = arrayListOf()
        }
        photoList?.run { photos?.addAll(this) }
        notifyDataSetChanged()
    }

    fun reset() {
        photos = arrayListOf()
        notifyDataSetChanged()
    }
}