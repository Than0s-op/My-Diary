package dev.than0s.mydiary.model.service

import dev.than0s.mydiary.screen.diary.Note
import kotlinx.coroutines.flow.Flow

interface StorageService {
    val notes: Flow<List<Note>>
    suspend fun save(note: Note)
    suspend fun delete(noteId: String)
    suspend fun update(note: Note)
    suspend fun getTask(noteId: String): Note?
}