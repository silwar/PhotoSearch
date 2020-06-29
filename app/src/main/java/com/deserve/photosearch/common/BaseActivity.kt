package com.deserve.photosearch.common

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.deserve.photosearch.util.NetworkConnectionCompat


open class BaseActivity : AppCompatActivity() {

    protected val networkCompat = NetworkConnectionCompat(this)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            networkCompat.registerNetworkCallback()
        }
    }
}