package com.programmer2704.unsplash.data.repository

import com.programmer2704.unsplash.data.DataState
import com.programmer2704.unsplash.model.PhotoModel
import kotlinx.coroutines.flow.Flow

interface ImagineRepository {
    suspend fun loadPhotos(pageNumber: Int, pageSize: Int, orderBy: String): Flow<DataState<List<PhotoModel>>>
    suspend fun searchPhotos(query: String, pageNumber: Int, pageSize: Int): Flow<DataState<List<PhotoModel>>>
}
