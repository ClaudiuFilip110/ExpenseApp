package com.example.android_resources.data.preferences

import android.content.Context
import android.util.Base64
import com.example.android_resources.utils.Constants
import java.lang.Exception
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


class Preferences(context: Context) {
    private var sharedPreferences =
        context.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
}