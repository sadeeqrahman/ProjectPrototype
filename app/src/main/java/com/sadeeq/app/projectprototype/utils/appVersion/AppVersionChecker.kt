package com.sadeeq.app.projectprototype.utils.appVersion
import android.content.pm.PackageManager
import com.sadeeq.app.projectprototype.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class AppVersionChecker {

    fun getInstalledAppVersion( packageManager: PackageManager): String? {
        try {
            val packageInfo = packageManager.getPackageInfo(BuildConfig.APPLICATION_ID, 0)
            return packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return null
    }

    suspend fun getPlayStoreAppVersion(): String? {
        val url = "https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}"
        return try {
            val doc = Jsoup.connect(url).get()
            val versionElement = doc.select(".htlgb > div").first()
            versionElement?.text()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}