package com.orioninc.techclub.acchelloworld.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.orioninc.techclub.acchelloworld.domain.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState: MutableLiveData<UIState> = MutableLiveData(UIState.IDLE)
    val uiState: LiveData<UIState> = _uiState

    private val loginUseCase = LoginUseCase(getApplication<Application>().applicationContext)

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _uiState.postValue(UIState.LOGIN_IN_PROGRESS)

            val isLoginSuccessful = loginUseCase(username, password)

            if (isLoginSuccessful)
                _uiState.postValue(UIState.LOGGED_IN)
            else
                _uiState.postValue(UIState.LOGIN_FAILED)
        }
    }

    enum class UIState {
        IDLE,
        LOGIN_IN_PROGRESS,
        LOGGED_IN,
        LOGIN_FAILED
    }
}