package dev.than0s.mydiary

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import dev.than0s.mydiary.common.emojiList

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        for (index in 2..100) {
            val resourceName = "emoji_$index"
            emojiList.add(
                applicationContext.resources.getIdentifier(
                    resourceName,
                    "drawable",
                    packageName
                )
            )
        }
    }
}
