package com.example.swimminginstructorlocator.listener

import java.lang.Exception

interface OnResultListener<T> {
    fun onSuccess(dataResult: T)
    fun onError(exception: Exception?)
}