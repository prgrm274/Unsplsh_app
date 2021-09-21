package com.programmer2704.unsplash.data.usecases

import com.programmer2704.unsplash.data.repository.ImagineRepository
import com.programmer2704.unsplash.utils.AppConstants
import javax.inject.Inject

class FetchPopularPhotosUsecase @Inject constructor(private val repository: ImagineRepository) {
    suspend operator fun invoke(
        pageNum: Int = 1,
        pageSize: Int = AppConstants.API.PHOTOS_PER_PAGE,
        orderBy: String = "popular"
    ) = repository.loadPhotos(
        pageNumber = pageNum,
        pageSize = pageSize,
        orderBy = orderBy
    )
}
