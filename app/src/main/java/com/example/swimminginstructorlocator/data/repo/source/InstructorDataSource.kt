package com.example.swimminginstructorlocator.data.repo.source

import android.content.ContentResolver
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.listener.OnResultListener

interface InstructorDataSource {
    /**
     * Local
     */
    interface Local {
        fun getInstructorsLocal(
            contentResolver: ContentResolver,
            listener: OnResultListener<MutableList<Instructor>>
        )
    }

    /**
     * Remote
     */
    interface Remote {
        fun getInstructorsRemote(listener: OnResultListener<MutableList<Instructor>>)
        fun searchInstructor(listener: OnResultListener<MutableList<Instructor>>)
    }
}