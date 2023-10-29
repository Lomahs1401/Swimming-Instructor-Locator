package com.example.swimminginstructorlocator.data.service.impl

import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.listener.OnResultListener

interface InstructorServiceImpl {
    /**
     * Local
     */
    interface Local {
//        TODO("Not yet implemented")
    }

    /**
     * Remote
     */
    interface Remote {
        fun getInstructors(listener: OnResultListener<MutableList<Instructor>>)
        fun searchInstructor(listener: OnResultListener<MutableList<Instructor>>)
    }
}