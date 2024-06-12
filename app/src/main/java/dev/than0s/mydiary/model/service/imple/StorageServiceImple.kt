package dev.than0s.mydiary.model.service.imple

import dev.than0s.mydiary.model.service.StorageService
import dev.than0s.mydiary.screen.diary.Note

class StorageServiceImple:StorageService {
    override val notes: List<Note>
        get() = TODO("Not yet implemented")

    override suspend fun save(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(noteId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun update(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun getTask(noteId: String) {
        TODO("Not yet implemented")
    }
}