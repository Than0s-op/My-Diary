package dev.than0s.mydiary.screen.diary

import androidx.compose.ui.graphics.vector.ImageVector
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date
import java.util.UUID

data class Note(
    @DocumentId val id:String = "",
    @ServerTimestamp val date: Date = Date(),
    val userID:String = "",
    val title:String = "",
    val emoji: ImageVector,
    val description: String = "",
)