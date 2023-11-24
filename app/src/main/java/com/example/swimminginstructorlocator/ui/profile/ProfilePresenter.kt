package com.example.swimminginstructorlocator.ui.profile

import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.data.service.impl.AuthServiceImpl
import com.example.swimminginstructorlocator.listener.OnResultListener
import java.lang.Exception

class ProfilePresenter(
    private val authService: AuthServiceImpl
) : ProfileContract.Presenter {

    private var view: ProfileContract.View? = null

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun setView(view: ProfileContract.View?) {
        this.view = view
    }

    override fun getCurrentUser() {
        authService.getCurrentUser(
            object : OnResultListener<User>{
                override fun onSuccess(dataResult: User) {
                    view?.onGetCurrentUser(dataResult)
                }

                override fun onError(exception: Exception?) {
                    view?.onError(exception)
                }

            }
        )
    }

    override fun logout() {
        authService.logout(object : OnResultListener<Boolean>{
            override fun onSuccess(dataResult: Boolean) {
                view?.onLogout()
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }

        })
    }
}