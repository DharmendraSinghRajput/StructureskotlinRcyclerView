package com.example.structureskotlin.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.structureskotlin.R
import com.example.structureskotlin.remote.RemoteService
import com.example.structureskotlin.remote.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class AddUniversityViewModel @Inject constructor(private val workService: RemoteService) :
    ViewModel() {

    private var _getAllEducationDataByiUserIdAPIResponse = MutableLiveData<Resource<Any>>()
    var getAllEducationDataByiUserIdAPIResponse: LiveData<Resource<Any>> =
        _getAllEducationDataByiUserIdAPIResponse

    fun callGetAllEducationDataByiUserAPI(context: Context, params: HashMap<String, Any>) =
        viewModelScope.launch {
            _getAllEducationDataByiUserIdAPIResponse.value = Resource.Loading()
            try {
                val response = workService.getAllPostData(params)
                if (response.isSuccessful)
                    _getAllEducationDataByiUserIdAPIResponse.value =
                        Resource.Success(response.body()!!)
                else
                    _getAllEducationDataByiUserIdAPIResponse.value =
                        Resource.Error(response.message())
            } catch (e: Exception) {
                e.printStackTrace()
                _getAllEducationDataByiUserIdAPIResponse.value =
                    Resource.Error(e.message ?: context.getString(R.string.unexpected_error))
            }
        }

    private var _updateProfileAPIResponse = MutableLiveData<Resource<Any>>()
    var updateProfileAPIResponse: LiveData<Resource<Any>> = _updateProfileAPIResponse
    fun callUpdateProfileAPI(context: Context, params: HashMap<String, RequestBody>, profileImage: MultipartBody.Part, bannerImage: MultipartBody.Part) = viewModelScope.launch {
        _updateProfileAPIResponse.value = Resource.Loading()

        try {
            val response = workService.updateProfile(params, profileImage, bannerImage)
            if (response.isSuccessful)
                _updateProfileAPIResponse.value = Resource.Success(response.body()!!)
            else
                _updateProfileAPIResponse.value = Resource.Error(response.message())
        } catch (e: Exception) {
            e.printStackTrace()
            _updateProfileAPIResponse.value = Resource.Error(e.message ?: context.getString(R.string.unexpected_error))
        }
    }

 /*   private var _createUserHobbiesResponse = MutableLiveData<Resource<Any>>()
    var createUserHobbiesResponse: LiveData<Resource<Any>> = _createUserHobbiesResponse
    fun callCreateUserHobbiesAPI(context: Context, params: HashMap<String, Any>) = viewModelScope.launch {

        _createUserHobbiesResponse.value = Resource.Loading()

        if (!NetworkUtil.isNetworkConnected(context))
            _createUserHobbiesResponse.value = Resource.NoInternet(context.getString(R.string.no_internet_connection))

        try {
            val response = remoteService.createUserHobbies(params)
            if (response.isSuccessful)
                _createUserHobbiesResponse.value = Resource.Success(response.body()!!)
            else
                _createUserHobbiesResponse.value = Resource.Error(response.message())
        } catch (e: Exception) {
            e.printStackTrace()
            _createUserHobbiesResponse.value = Resource.Error("UnExpected Error.")
        }
    }*/


/*    private var _updateProfiledBirthDate = MutableLiveData<Resource<Any>>()
    var updateProfiledBirthDate: LiveData<Resource<Any>> = _updateProfiledBirthDate
    fun updateProfiledBirthDate(context: Context, params: HashMap<String, Any>) = viewModelScope.launch {
        _updateProfiledBirthDate.value = Resource.Loading()
        if (!NetworkUtil.isNetworkConnected(context))
            _updateProfiledBirthDate.value = Resource.NoInternet(context.getString(R.string.no_internet_connection))
        try {
            val response = workService.profileProfileUpdate(params)
            if (response.isSuccessful)
                _updateProfiledBirthDate.value = Resource.Success(response.body()!!)
            else
                _updateProfiledBirthDate.value = Resource.Error(response.message())
        } catch (e: Exception) {
            e.printStackTrace()
            _updateProfiledBirthDate.value = Resource.Error(e.message ?: context.getString(R.string.unexpected_error))
        }
    }*/


}