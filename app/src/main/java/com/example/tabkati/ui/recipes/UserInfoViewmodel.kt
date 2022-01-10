package com.example.tabkati.ui.recipes

import android.util.Log
import androidx.lifecycle.*
import com.example.tabkati.domain.use_case.GetUserUseCase
import com.example.tabkati.domain.use_case.UserUseCase
import com.example.tabkati.model.User
import com.example.tabkati.utils.RecipesApiStatus
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


    private var _profileUIState = MutableStateFlow(UserProfileScreenUiState())
    val profileUIState = _profileUIState.asStateFlow()

    private var _respicesUIState = MutableStateFlow(RecipesScreenUiState())
    val respicesUIState = _respicesUIState.asStateFlow()


    init {
        getUser()
        Log.e("init", "getUser:${_UserInfoState.value?.name} ", )
    }



//    viewModelScope.launch {
//        try {
//            val result = repository.getRandomRecipes()
//            val list = result?.map {
//                RecipesItemUiState(
//                    title = it?.title!!,
//                    image = it?.image!!,
//                    id = it?.id!!,
//                    mintus =  it?.readyInMinutes!!.toString(),
//                    serving = it?.servings.toString()
//
//                )
//            }
//            _recipesList.value = list
//           //        _status.value = RecipesApiStatus.LOADING _status.value = RecipesApiStatus.DONE
//        } catch (e: Exception) {
//            _status.value = RecipesApiStatus.ERROR
//            // to clear the RecyclerView.
//
//        }
//
//    @ExperimentalCoroutinesApi
//    fun getUserInfo() {
//        viewModelScope.launch {
//            _status.value = RecipesApiStatus.LOADING
//
//            userUseCase.getUserInfo().collect { response ->
//                _UserInfoState.value = getUser().value
//
//
//                   }
//            }
//        }
//
//






    @ExperimentalCoroutinesApi
    private  fun getUser(){
        viewModelScope.launch {
              _status.value = RecipesApiStatus.LOADING
            try {
                userUseCase.getUserInfo().collect {user->

                    _profileUIState.update {
                        it.copy(
                            name = user.name,
                            email = user.email
                        )
                    }
                    Log.e("PP", "getUser:${user.name} ", )
                } }catch (e: Exception) {
            _status.value = RecipesApiStatus.ERROR


        }


        }}


    }