package com.programmer2704.unsplash.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.programmer2704.unsplash.model.PhotoModel
import programmer2704.unsplash.R
import programmer2704.unsplash.databinding.PhotoItemLayoutBinding

class PhotosAdapter(private val items: ArrayList<PhotoModel>)
    : RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        var binding = PhotoItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PhotoViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(items[position], position)
        holder.b.root.setOnClickListener(mOnClickListener)
    }

    fun updateItems(photosList: List<PhotoModel>) {
        items.clear()
        items.addAll(photosList)
        notifyDataSetChanged()
    }

    inner class PhotoViewHolder(val b: PhotoItemLayoutBinding)
        : RecyclerView.ViewHolder(b.root) {

        fun bind(photoModel: PhotoModel, position: Int) {
            b.TVName.text = photoModel.user.name
            b.TVDeskripsi.text = photoModel.description
            b.imgPhoto.load(photoModel.urls.thumb) {
                placeholder(R.color.color_box_background)
                crossfade(true)
            }

            b.imgPhoto.setOnClickListener {
                var bundle = bundleOf("photo" to items[position])
                b.root.findNavController().navigate(R.id.action_homeFragment_to_photoDetailsFragment, bundle)
            }
        }
    }

    //? Another way of setOnClickListener in Adapter
    private val mOnClickListener: View.OnClickListener = View.OnClickListener { v ->
    }

}
