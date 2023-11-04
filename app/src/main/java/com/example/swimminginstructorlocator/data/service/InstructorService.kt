package com.example.swimminginstructorlocator.data.service

import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.repo.InstructorRepo
import com.example.swimminginstructorlocator.data.service.impl.InstructorServiceImpl
import com.example.swimminginstructorlocator.listener.OnResultListener

class InstructorService(
    private val instructorRepo: InstructorRepo
) : InstructorServiceImpl {
    override fun getInstructors(listener: OnResultListener<MutableList<Instructor>>) {
        instructorRepo.getInstructors(listener)
    }

    override fun searchInstructor(listener: OnResultListener<MutableList<Instructor>>) {
//        TODO("Not yet implemented")
    }

    companion object {
        private var instance: InstructorService? = null

        fun getInstance(instructorRepo: InstructorRepo) = synchronized(this) {
            instance ?: InstructorService(instructorRepo).also {
                instance = it
            }
        }
    }
}