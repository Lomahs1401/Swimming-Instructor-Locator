package com.example.swimminginstructorlocator.data.repo.source.local

import android.content.ContentResolver
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.repo.source.InstructorDataSource
import com.example.swimminginstructorlocator.listener.OnResultListener

class InstructorLocalDataSource : InstructorDataSource.Local {
    override fun getInstructorsLocal(
        contentResolver: ContentResolver,
        listener: OnResultListener<MutableList<Instructor>>
    ) {
//        TODO("Not yet implemented")
    }

    companion object {
        private var instance: InstructorLocalDataSource? = null

        fun getInstance() = synchronized(this) {
            instance ?: InstructorLocalDataSource().also {
                instance = it
            }
        }
    }
}