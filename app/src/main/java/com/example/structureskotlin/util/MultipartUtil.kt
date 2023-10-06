package com.example.structureskotlin.util

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

object MultipartUtil {
    fun getImagePart(apiKey: String, filePath: String): MultipartBody.Part {

        val imagePart: MultipartBody.Part

        if (filePath.isEmpty()) {

            val emptyBody = "".toRequestBody("text/plain".toMediaTypeOrNull())
            imagePart = MultipartBody.Part.createFormData(apiKey, "", emptyBody)

        } else {
            val file = File(filePath)

            val requestFile = when (file.extension) {
                "jpg" -> file.asRequestBody("image/jpg".toMediaTypeOrNull())
                "png" -> file.asRequestBody("image/png".toMediaTypeOrNull())
                "jpeg" -> file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                else -> file.asRequestBody("image/jpg".toMediaTypeOrNull())
            }

            imagePart = MultipartBody.Part.createFormData(apiKey, file.name, requestFile)
        }

        return imagePart
    }

    fun getMediaPart(apiKey: String, filePath: String): MultipartBody.Part {

        val imagePart: MultipartBody.Part

        if (filePath.isEmpty()) {

            val emptyBody = "".toRequestBody("text/plain".toMediaTypeOrNull())
            imagePart = MultipartBody.Part.createFormData(apiKey, "", emptyBody)

        } else {
            val file = File(filePath)

            val requestFile = when (file.extension) {
                "jpg" -> file.asRequestBody("image/jpg".toMediaTypeOrNull())
                "png" -> file.asRequestBody("image/png".toMediaTypeOrNull())
                "jpeg" -> file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                "pdf" -> file.asRequestBody("application/pdf".toMediaTypeOrNull())
                "mp4" -> file.asRequestBody("video/mp4".toMediaTypeOrNull())
                "xls" -> file.asRequestBody("application/vnd.ms-excel".toMediaTypeOrNull())
                "xlsx" -> file.asRequestBody("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".toMediaTypeOrNull())
                "rar" -> file.asRequestBody("application/vnd.rar".toMediaTypeOrNull())
                "gif" -> file.asRequestBody("image/gif".toMediaTypeOrNull())
                "zip" -> file.asRequestBody("application/zip".toMediaTypeOrNull())
                "mp3" -> file.asRequestBody("audio/mpeg".toMediaTypeOrNull())
                "txt" -> file.asRequestBody("text/plain".toMediaTypeOrNull())
                else -> file.asRequestBody("image/jpg".toMediaTypeOrNull())
            }

            imagePart = MultipartBody.Part.createFormData(apiKey, file.name, requestFile)
        }

        return imagePart
    }

    fun getStringPart(data: String) = data.toRequestBody("multipart/form-data".toMediaTypeOrNull())

}