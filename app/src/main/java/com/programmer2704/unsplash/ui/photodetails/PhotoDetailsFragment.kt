/*
* Copyright 2021 Wajahat Karim (https://wajahatkarim.com)
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     https://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.programmer2704.unsplash.ui.photodetails

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.programmer2704.unsplash.ui.base.BaseFragment
import com.programmer2704.unsplash.model.PhotoModel
import dagger.hilt.android.AndroidEntryPoint
import programmer2704.unsplash.databinding.PhotoDetailsFragmentBinding
import kotlin.math.max
import kotlin.math.min

@AndroidEntryPoint
class PhotoDetailsFragment : BaseFragment<PhotoDetailsFragmentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> PhotoDetailsFragmentBinding
        get() = PhotoDetailsFragmentBinding::inflate

    private val viewModel: PhotoDetailsViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var photo = arguments?.getParcelable<PhotoModel>("photo")
        if (photo == null) {
            findNavController().popBackStack()
            return
        }

        setupViews()
        initObservations()

        viewModel.initPhotoModel(photo)
    }

    fun setupViews() {
        sgd = ScaleGestureDetector(context, ScaleListener(b.IMGPhoto))

        b.IMGPhoto.setOnClickListener {

            it.setOnTouchListener { v, event ->
                sgd.onTouchEvent(event)
                v.performClick()

                when (event?.action) {
                    MotionEvent.ACTION_UP -> {

                    }
                    MotionEvent.ACTION_DOWN -> {

                    }
                }

                return@setOnTouchListener true
            }
        }
    }

    fun initObservations() {
        viewModel.photoModelLiveData.observe(viewLifecycleOwner) { photo ->
            b.IMGPhoto.load(photo.urls.full)
        }
    }

    private lateinit var sgd: ScaleGestureDetector
    private var scaleFactor = 1.0F

    private inner class ScaleListener(
        private val img: ImageView
    ) : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            scaleFactor *= sgd.scaleFactor
            scaleFactor = max(.1F, min(scaleFactor, 10.0F))
            img.scaleX = scaleFactor
            img.scaleY = scaleFactor
            return true
        }
    }
}
