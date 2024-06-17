package dev.than0s.mydiary.model.service.imple

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import dev.than0s.mydiary.model.service.StorageService
import dev.than0s.mydiary.screen.diary.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class StorageServiceImple @Inject constructor(private val auth: FirebaseAuth) : StorageService {
    private val store = Firebase.firestore
    override val notes: Flow<List<Note>>
        get() = store.collection(NOTE_COLLECTION)
            .whereEqualTo(USER_ID_FIELD, auth.currentUser?.uid)
            .dataObjects()

    override suspend fun save(note: Note) {
        store.collection(NOTE_COLLECTION).add(note).await()
    }

    override suspend fun delete(noteId: String) {
        store.collection(NOTE_COLLECTION).document(noteId).delete().await()
    }

    override suspend fun update(note: Note) {
        store.collection(NOTE_COLLECTION).document(note.id).set(note).await()
    }

    override suspend fun getTask(noteId: String): Note? {
        return store.collection(NOTE_COLLECTION).document(noteId).get().await().toObject()
    }

    companion object {
        private const val NOTE_COLLECTION = "notes"
        private const val USER_ID_FIELD = "userId"
    }
}