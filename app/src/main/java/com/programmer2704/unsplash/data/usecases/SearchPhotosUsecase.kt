package com.programmer2704.unsplash.data.usecases

import com.programmer2704.unsplash.data.repository.ImagineRepository
import com.programmer2704.unsplash.utils.AppConstants
import javax.inject.Inject

class SearchPhotosUsecase @Inject constructor(private val repository: ImagineRepository) {
    suspend operator fun invoke(
        query: String,
        pageNum: Int = 1,
        pageSize: Int = AppConstants.API.PHOTOS_PER_PAGE
    ) = repository.searchPhotos(
        query = query,
        pageNumber = pageNum,
        pageSize = pageSize
    )
}
