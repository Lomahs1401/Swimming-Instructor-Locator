package com.example.swimminginstructorlocator.data.repo

import android.content.ContentResolver
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.repo.source.InstructorDataSource
import com.example.swimminginstructorlocator.listener.OnResultListener

class InstructorRepo private constructor(
    private val local: InstructorDataSource.Local?,
    private val remote: InstructorDataSource.Remote?,
) : InstructorDataSource.Local, InstructorDataSource.Remote {
    override fun getInstructorsLocal(
        contentResolver: ContentResolver,
        listener: OnResultListener<MutableList<Instructor>>
    ) {
        local?.getInstructorsLocal(contentResolver, listener)
    }

    override fun getInstructorsRemote(listener: OnResultListener<MutableList<Instructor>>) {
        remote?.getInstructorsRemote(listener)
    }

    override fun searchInstructor(listener: OnResultListener<MutableList<Instructor>>) {
        remote?.searchInstructor(listener)
    }

    companion object {
        private var instance: InstructorRepo? = null

        fun getInstanceInstructorLocalRepo(local: InstructorDataSource.Local): InstructorRepo {
            return synchronized(this) {
                instance ?: InstructorRepo(local, null).also {
                    instance = it
                }
            }
        }

        fun getInstanceInstructorRemoteRepo(remote: InstructorDataSource.Remote): InstructorRepo {
            return synchronized(this) {
                instance ?: InstructorRepo(null, remote).also {
                    instance = it
                }
            }
        }
    }
}