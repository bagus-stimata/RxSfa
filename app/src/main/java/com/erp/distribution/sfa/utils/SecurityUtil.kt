package com.erp.distribution.sfa.utils

class SecurityUtil {
    companion object{
        fun getAuthHeader(username: String, password: String): String {
            val stringBaseAuth: String = username + ":" + password
            val authHeader = "Basic " + android.util.Base64.encodeToString(stringBaseAuth.toByteArray(), android.util.Base64.NO_WRAP)
            return authHeader
        }
    }
}