package com.reham11203.data.utils

import android.content.Context
import com.reham11203.data.model.response.User
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import androidx.core.content.edit
import com.google.gson.Gson

class PrefsHelper @Inject constructor(@ApplicationContext private val context: Context) {
    companion object{
        const val SHARED_PREFS_NAME = "app_prefs"
        const val TOKEN_KEY = "token"
        const val USER_KEY = "user"
    }
    val sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)


    fun saveToken(token: String?){
        token?.let{ token ->
            sharedPrefs.edit { putString(TOKEN_KEY, token) }

        }
    }

    fun saveUser(user: User?){
        user?.let{ user ->
            val gson = Gson()
            val userAsString: String = gson.toJson(user)
            sharedPrefs.edit { putString(USER_KEY, userAsString) }
        }

    }

    fun getToken(): String?{
        return sharedPrefs.getString(TOKEN_KEY, null)
    }

    fun getUser(): User?{
        val gson = Gson()
        val userAsString: String? = sharedPrefs.getString(USER_KEY, null)
        userAsString?.let {
            val user = gson.fromJson(it, User::class.java)
            return user
        }
        return null

    }
}