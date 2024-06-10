package dev.than0s.mydiary.screen.diary

import androidx.compose.ui.graphics.vector.ImageVector
import java.util.Date
import java.util.UUID

data class Note(
    val date: Date = Date(),
    val emoji: ImageVector,
    val note: String,
    val uid: String = UUID.randomUUID().toString()
)