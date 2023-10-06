package com.example.structureskotlin.remote

data class UpdateProfileResponse(
    val code: String = "",
    val `data`: Data = Data(),
    val message: String = "",
    val status: String = ""
) {
    data class Data(
        val dBirthDate: String = "",
        val iRoleId: String = "",
        val vFullName: String = ""
    )
}