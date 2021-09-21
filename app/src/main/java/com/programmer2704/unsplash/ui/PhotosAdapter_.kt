///*
//* Copyright 2021 Wajahat Karim (https://wajahatkarim.com)
//*
//* Licensed under the Apache License, Version 2.0 (the "License");
//* you may not use this file except in compliance with the License.
//* You may obtain a copy of the License at
//*
//*     https://www.apache.org/licenses/LICENSE-2.0
//*
//* Unless required by applicable law or agreed to in writing, software
//* distributed under the License is distributed on an "AS IS" BASIS,
//* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//* See the License for the specific language governing permissions and
//* limitations under the License.
//*/
package com.programmer2704.unsplash.ui
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import coil.load
//import com.programmer2704.imagine.R
//import com.programmer2704.imagine.databinding.PhotoItemLayoutBinding
//import com.programmer2704.imagine.model.PhotoModel
//
//class PhotosAdapter_(val onPhotoSelected: (photo: PhotoModel, position: Int) -> Unit) : RecyclerView.Adapter<PhotosAdapter_.PhotoViewHolder>() {
//
//    private val items: ArrayList<PhotoModel> = arrayListOf()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
//        var binding = PhotoItemLayoutBinding.inflate(
//            LayoutInflater.from(parent.context),
//            parent,
//            false
//        )
//        return PhotoViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
//        holder.bind(items[position], position)
//    }
//
//    override fun getItemCount() = items.size
//
//    fun updateItems(photosList: List<PhotoModel>) {
//        items.clear()
//        items.addAll(photosList)
//        notifyDataSetChanged()
//    }
//
//    inner class PhotoViewHolder(val itemBinding: PhotoItemLayoutBinding)
//        : RecyclerView.ViewHolder(itemBinding.root) {
//
//        fun bind(photoModel: PhotoModel, position: Int) {
////            itemBinding.TVName.text = photoModel.user.name
////            itemBinding.TVDeskripsi.text = photoModel.description
//            itemBinding.imgPhoto.load(photoModel.urls.thumb) {
//                placeholder(R.color.color_box_background)
//                crossfade(true)
//            }
//
//            itemBinding.cardPhoto.setOnClickListener {
//                onPhotoSelected(photoModel, position)
//            }
//
//            /*itemBinding.apply {
//                imgPhoto.load(photoModel.urls.thumb) {
//                    placeholder(R.color.color_box_background)
//                    crossfade(true)
//                }
//                cardPhoto.setOnClickListener {
//                    onPhotoSelected(photoModel, position)
//                }
//            }*/
//        }
//    }
//}
