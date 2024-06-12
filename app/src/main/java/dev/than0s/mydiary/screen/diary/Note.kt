package dev.than0s.mydiary.screen.diary

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SentimentNeutral
import androidx.compose.ui.graphics.vector.ImageVector
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Note(
    @DocumentId val id: String = "",
    @ServerTimestamp val date: Date = Date(),
    val userId: String = "",
    val title: String = "",
    val emoji: ImageVector = Icons.Rounded.SentimentNeutral,
    val description: String = "",
)