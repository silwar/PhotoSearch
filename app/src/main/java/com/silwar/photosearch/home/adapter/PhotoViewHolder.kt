package com.silwar.photosearch.home.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions
import com.silwar.photosearch.R
import com.silwar.photosearch.home.model.Photo

class PhotoViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    var mPhotoView: ImageView = itemView.findViewById(R.id.image_photo)

    fun bind(photo: Photo) {
        val photoUrl = getPhotoUrl(photo)
        val requestOptions = RequestOptions().apply {
            placeholder(R.drawable.default_image)
            error(R.drawable.image_not_found)
        }
        Glide.with(itemView.context)
            .setDefaultRequestOptions(requestOptions)
            .load(photoUrl)
            .downsample(DownsampleStrategy.FIT_CENTER)
            .into(mPhotoView)

    }

    private fun getPhotoUrl(photo: Photo): String {
        return "https://farm${photo.farm}.static.flickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"
    }
}