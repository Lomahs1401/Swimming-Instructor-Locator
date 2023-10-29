package com.example.swimminginstructorlocator.data.service.remote

import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.repo.InstructorRepo
import com.example.swimminginstructorlocator.data.service.impl.InstructorServiceImpl
import com.example.swimminginstructorlocator.listener.OnResultListener

class InstructorServiceRemote private constructor(
    private val instructorRepo: InstructorRepo
) : InstructorServiceImpl.Remote {
    override fun getInstructors(listener: OnResultListener<MutableList<Instructor>>) {
        instructorRepo.getInstructors()
    }

    override fun searchInstructor(listener: OnResultListener<MutableList<Instructor>>) {
//        TODO("Not yet implemented")
    }

    companion object {
        private var instance: InstructorServiceRemote? = null

        fun getInstance(instructorRepo: InstructorRepo) = synchronized(this) {
            instance ?: InstructorServiceRemote(instructorRepo).also {
                instance = it
            }
        }
    }
}