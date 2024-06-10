package dev.than0s.mydiary

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import dev.than0s.mydiary.screen.NavHost

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, NavHost::class.java))
    }
}