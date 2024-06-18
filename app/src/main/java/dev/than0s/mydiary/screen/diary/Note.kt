package dev.than0s.mydiary.screen.diary

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Note(
    @DocumentId val id: String = "",
    @ServerTimestamp val date: Date = Date(),
    val userId: String = FirebaseAuth.getInstance().currentUser!!.uid,
    val title: String = "",
    val emoji: Int = 2,
    val description: String = "",
)