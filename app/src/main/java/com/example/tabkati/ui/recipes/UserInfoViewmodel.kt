package com.example.tabkati.ui.recipes

import android.util.Log
import androidx.lifecycle.*
import com.example.tabkati.domain.use_case.GetUserUseCase
import com.example.tabkati.domain.use_case.UserUseCase
import com.example.tabkati.model.User
import com.example.tabkati.utils.RecipesApiStatus
import com.example.tabkati.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {

    private val _UserInfoState = MutableLiveData<User>()
    val UserInfoState: LiveData<User> = _UserInfoState

    // The internal MutableLiveData that stores the status of the most recent request.
    private val _status = MutableLiveData<RecipesApiStatus>()

    // The external immutable LiveData for the request status.
    val status: LiveData<RecipesApiStatus> = _status

    // The internal MutableLiveData that stores the status of the most recent request.
    private val _userName = MutableLiveData<String>()

    // The external immutable LiveData for the request status.
    val userName: LiveData<String> = _userName

    private var _profileUIState = MutableStateFlow(UserProfileScreenUiState())
    val profileUIState = _profileUIState.asStateFlow()




    init {
        getUser()
        Log.e("init", "getUser:${_UserInfoState.value?.name} ", )
    }

    @ExperimentalCoroutinesApi
    private  fun getUser(){
        viewModelScope.launch {
              _status.value = RecipesApiStatus.LOADING
            try {
                userUseCase.getUserInfo().collect {user->

                    _profileUIState.update {
                        it.copy(
                            name = user.name,
                            email = user.email,
                            loading = false
                        )
                    }
                    Log.e("PP", "getUser:${user.name} ", )
                } }catch (e: Exception) {
            _status.value = RecipesApiStatus.ERROR


        }


        }}

    fun setUserName(name: String){
        _userName.value = name
        editUserName()
    }

    @ExperimentalCoroutinesApi
   private fun editUserName(){
        viewModelScope.launch {
           try{
               userUseCase.editUserName(_userName.value).collect {
                           _profileUIState.update {
                               it.copy(loading = true)
                           }
                               getUser()
               }}
           catch (e: Exception){
               Log.e("roa", "editUserName: ${e.message}", )
           }
        }
    }


    }