package com.example.swimminginstructorlocator.data.repo.source.remote

import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.repo.source.InstructorDataSource
import com.example.swimminginstructorlocator.listener.OnResultListener

class InstructorRemoteDataSource : InstructorDataSource.Remote {
    override fun getInstructorsRemote(listener: OnResultListener<MutableList<Instructor>>) {
//        TODO("Not yet implemented")
    }

    override fun searchInstructor(listener: OnResultListener<MutableList<Instructor>>) {
//        TODO("Not yet implemented")
    }

    companion object {
        private var instance: InstructorRemoteDataSource? = null

        fun getInstance() = synchronized(this) {
            instance ?: InstructorRemoteDataSource().also {
                instance = it
            }
        }
    }
}