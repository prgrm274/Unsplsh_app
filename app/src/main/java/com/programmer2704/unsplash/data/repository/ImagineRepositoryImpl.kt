package com.programmer2704.unsplash.data.repository

import androidx.annotation.WorkerThread
import com.programmer2704.unsplash.data.DataState
import com.programmer2704.unsplash.data.remote.UnsplashApiService
import com.programmer2704.unsplash.data.remote.message
import com.programmer2704.unsplash.data.remote.onErrorSuspend
import com.programmer2704.unsplash.data.remote.onExceptionSuspend
import com.programmer2704.unsplash.data.remote.onSuccessSuspend
import com.programmer2704.unsplash.model.PhotoModel
import com.programmer2704.unsplash.utils.StringUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class ImagineRepositoryImpl @Inject constructor(
    private val stringUtils: StringUtils,
    private val apiService: UnsplashApiService
) : ImagineRepository {

    @WorkerThread
    override suspend fun loadPhotos(
        pageNumber: Int,
        pageSize: Int,
        orderBy: String
    ): Flow<DataState<List<PhotoModel>>> {
        return flow {
            apiService.loadPhotos(pageNumber, pageSize, orderBy).apply {
                this.onSuccessSuspend {
                    data?.let {
                        emit(DataState.success(it))
                    }
                }
            }.onErrorSuspend {
                emit(DataState.error<List<PhotoModel>>(message()))

            }.onExceptionSuspend {
                if (this.exception is IOException) {
                    emit(DataState.error<List<PhotoModel>>(stringUtils.noNetworkErrorMessage()))
                } else {
                    emit(DataState.error<List<PhotoModel>>(stringUtils.somethingWentWrong()))
                }
            }
        }
    }

    override suspend fun searchPhotos(
        query: String,
        pageNumber: Int,
        pageSize: Int
    ): Flow<DataState<List<PhotoModel>>> {
        return flow {
            apiService.searchPhotos(query, pageNumber, pageSize).apply {
                this.onSuccessSuspend {
                    data?.let {
                        emit(DataState.success(it.photosList))
                    }
                }
            }.onErrorSuspend {
                emit(DataState.error<List<PhotoModel>>(message()))

            }.onExceptionSuspend {
                if (this.exception is IOException) {
                    emit(DataState.error<List<PhotoModel>>(stringUtils.noNetworkErrorMessage()))
                } else {
                    emit(DataState.error<List<PhotoModel>>(stringUtils.somethingWentWrong()))
                }
            }
        }
    }
}
