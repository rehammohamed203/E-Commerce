package com.reham11203.e_commerce.activities.auth.fragments.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reham11203.domain.usecases.LoginUseCase
import com.reham11203.domain.utils.ApiResult
import com.reham11203.e_commerce.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    val loginApiState = MutableLiveData<Resource<Unit>>(Resource.IdleState())
    var emailLiveData = MutableLiveData<String>()
    var passwordLiveData = MutableLiveData<String>()
    var emailErrorLiveData = MutableLiveData<String>()
    var passwordErrorLiveData = MutableLiveData<String>()

    fun login(){
        viewModelScope.launch {
            when(val result = loginUseCase.execute(emailLiveData.value!!, passwordLiveData.value!!)) {
                is ApiResult.ErrorApiResult<*> -> {
                    loginApiState.postValue(Resource.ErrorState(result.error))
                }
                is ApiResult.SuccessApiResult<*> -> {
                    loginApiState.postValue(Resource.SuccessState(Unit))
                }
            }
        }
    }
}