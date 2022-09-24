package com.example.shushufood_wos.screens.loginsUtils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shushufood_wos.network.ApiService
import com.example.shushufood_wos.network.models.LoginResult
import com.example.shushufood_wos.utils.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel(), EventHandler<LoginEvent> {
    private val _viewState: MutableLiveData<LoginViewState> = MutableLiveData(LoginViewState())
    val viewState: LiveData<LoginViewState> = _viewState

    override fun obtainEvent(event: LoginEvent) {
        when (event){
            is LoginEvent.EmailChanged -> emailChanged(event.value)
            LoginEvent.LoginClicked -> loginClicked()
            is LoginEvent.PasswordChanged -> passwordChanged(event.value)
        }
    }
    private fun emailChanged(value: String){
        _viewState.postValue(_viewState.value?.copy(emailValue=value))
    }
    private fun passwordChanged(value: String){
        _viewState.postValue(_viewState.value?.copy(passwordValue=value))
    }

    private val apiService by lazy {
        ApiService.create()
    }

    private fun loginClicked(){
        viewModelScope.launch(Dispatchers.IO) {
            _viewState.postValue(_viewState.value?.copy(isProgress = true))
            val loginResult = apiService.tryLogin(
                email = _viewState.value!!.emailValue,
                password = _viewState.value!!.passwordValue
            )


            when (loginResult){
                is LoginResult.Ok -> {
                    _viewState.postValue(
                        _viewState.value?.copy(
                            isProgress = false,
                            loginAction = LoginAction.OpenDashBoard(_viewState.value!!.emailValue)
                        )
                    )
                }
                else -> {
                    return@launch
                }
            }



        }
    }

}