package com.example.memeshare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import org.chromium.net.CronetEngine
import org.chromium.net.UrlRequest
import org.chromium.net.UrlResponseInfo
import java.nio.ByteBuffer
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MAINACTIVITY", "This is my message")
        Log.d("MAINACTIVITY", "This is my another message")
        Log.d("MAINACTIVITY", "This is my last message")
        Log.d("MAINACTIVITY", "This is my final last message")
        Log.d("MAINACTIVITY", "Experimental Change")
    }
}