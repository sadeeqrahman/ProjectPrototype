package com.sadeeq.app.projectprototype.firebase.models


import com.google.gson.annotations.SerializedName

data class UserLogin(
    @SerializedName("data")
    var `data`: Data?,
    @SerializedName("isSuccess")
    var isSuccess: Boolean?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
) {
    data class Data(
        @SerializedName("email")
        var email: String?,
        @SerializedName("expires")
        var expires: String?,
        @SerializedName("firstName")
        var firstName: String?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("lastName")
        var lastName: String?,
        @SerializedName("mobile")
        var mobile: String?,
        @SerializedName("refreshToken")
        var refreshToken: String?,
        @SerializedName("roleId")
        var roleId: Int?,
        @SerializedName("roleName")
        var roleName: String?,
        @SerializedName("token")
        var token: String?,
        @SerializedName("userName")
        var userName: String?
    )
}