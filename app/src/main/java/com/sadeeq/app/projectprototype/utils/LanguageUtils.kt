package com.sadeeq.app.projectprototype.utils

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

object LanguageUtils {

    fun changeLanguage(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val resources = context.resources
        val configuration = Configuration(resources.configuration)
        configuration.setLocale(locale)

        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
}