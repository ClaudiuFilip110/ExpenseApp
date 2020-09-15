package com.example.android_resources.data.preferences

import android.content.Context
import com.example.android_resources.utils.Constants

class Preferences(context: Context) {
    private var sharedPreferences =
        context.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
}