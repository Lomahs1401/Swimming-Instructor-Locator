package com.example.swimminginstructorlocator.data.service.local

import android.content.ContentResolver
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.repo.InstructorRepo
import com.example.swimminginstructorlocator.data.service.impl.InstructorServiceImpl
import com.example.swimminginstructorlocator.data.service.remote.InstructorServiceRemote
import com.example.swimminginstructorlocator.listener.OnResultListener

class InstructorServiceLocal(
    private val instructorRepo: InstructorRepo
) : InstructorServiceImpl.Local {

    companion object {
        private var instance: InstructorServiceLocal? = null

        fun getInstance(instructorRepo: InstructorRepo) = synchronized(this) {
            instance ?: InstructorServiceLocal(instructorRepo).also {
                instance = it
            }
        }
    }
}