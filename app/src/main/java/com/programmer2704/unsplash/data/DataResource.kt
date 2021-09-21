package com.programmer2704.unsplash.data

data class DataResource<out T>(
    val status: DataStatus,
    val data: T?,
    val message: String?
) {
    companion object {
        fun <T> success(data: T?): DataResource<T> {
            return DataResource(DataStatus.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T? = null): DataResource<T> {
            return DataResource(DataStatus.ERROR, data, msg)
        }
    }
}

enum class DataStatus {
    SUCCESS,
    ERROR
}
