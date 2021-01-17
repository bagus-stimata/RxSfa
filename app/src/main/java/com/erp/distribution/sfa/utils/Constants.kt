package com.erp.distribution.sfa.utils


/**
 * Contains fixed values that remains during execution once it is initialized
 */
object Constants {

//     const val BASE_URL = "https://jsonplaceholder.typicode.com/"
     const val BASE_URL = "http://192.168.1.100:8989/rest/"
     const val BASIC_AUTH_USERNAME = "bagus"
     const val BASIC_AUTH_PASSWORD = "hacker"
     /**
      * RETROFIT 2
      * SPRING BASIC AUTH
      */
     val stringBaseAuth: String = BASIC_AUTH_USERNAME + ":" + BASIC_AUTH_PASSWORD
     val authHeader = "Basic " + android.util.Base64.encodeToString(stringBaseAuth.toByteArray(), android.util.Base64.NO_WRAP)


}