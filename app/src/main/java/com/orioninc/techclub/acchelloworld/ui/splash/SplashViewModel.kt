package com.orioninc.techclub.acchelloworld.ui.splash

import android.app.Application
import androidx.lifecycle.*
import com.orioninc.techclub.acchelloworld.data.repository.UserRepository
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState: MutableLiveData<UIState> = MutableLiveData(UIState.IDLE)
    val uiState: LiveData<UIState> = _uiState

    private val userRepository = UserRepository(getApplication<Application>().applicationContext)

    fun autoLogin() {
        viewModelScope.launch {
            val isAutoLoginSuccessful = userRepository.autoLoginNew()

            if (isAutoLoginSuccessful)
                _uiState.postValue(UIState.LOGGED_IN)
            else
                _uiState.postValue(UIState.LOGIN_FAILED)
        }
    }


    enum class UIState {
        IDLE,
        LOGGED_IN,
        LOGIN_FAILED
    }
}