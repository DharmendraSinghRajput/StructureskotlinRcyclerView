package com.example.structureskotlin.remote

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap

interface RemoteService {

    @FormUrlEncoded
    @POST("post/getAllPostData")
    suspend fun getAllPostData(@FieldMap params: HashMap<String, Any>): Response<GetAllPostResponse>

    @Multipart
    @POST("profile/profileUpdate")
    suspend fun updateProfile(
        @PartMap params: HashMap<String, RequestBody>, @Part profileImage: MultipartBody.Part, @Part bannerImage: MultipartBody.Part
    ): Response<UpdateProfileResponse>


  /*  @FormUrlEncoded
    @POST("profile/profileUpdate")
    suspend fun profileProfileUpdate(@FieldMap params: HashMap<String, Any>): Response<Status>*/


}