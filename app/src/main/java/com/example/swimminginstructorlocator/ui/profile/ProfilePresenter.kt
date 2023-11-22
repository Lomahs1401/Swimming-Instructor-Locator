package com.example.swimminginstructorlocator.ui.profile

import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.data.service.impl.AuthServiceImpl
import com.example.swimminginstructorlocator.data.service.impl.InstructorServiceImpl
import com.example.swimminginstructorlocator.listener.OnResultListener
import com.example.swimminginstructorlocator.ui.instructor.detail.InstructorDetailContract
import java.lang.Exception

class ProfilePresenter(
    private val authService: AuthServiceImpl
) : ProfileContract.Presenter {
    private var view: ProfileContract.View? = null
    override fun getCurrentUser() {
        TODO("Not yet implemented")
        authService.getCurrentUser(
            object : OnResultListener<User>{
                override fun onSuccess(dataResult: User) {
                    view?.onGetCurrentUser(dataResult)
                }

                override fun onError(exception: Exception?) {
                    TODO("Not yet implemented")
                    view?.onError(exception)
                }

            }
        )
    }

    override fun onStart() {
        TODO("Not yet implemented")
    }

    override fun onStop() {
        TODO("Not yet implemented")
    }

    override fun setView(view: ProfileContract.View?) {
        TODO("Not yet implemented")
        this.view = view
    }
}