package dev.than0s.mydiary.screen.edit_note

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import dev.than0s.mydiary.ID
import dev.than0s.mydiary.model.service.StorageService
import dev.than0s.mydiary.screen.MyDiaryViewModel
import dev.than0s.mydiary.screen.diary.Note
import java.util.Date

class EditNoteViewModel(
    private val toast: (String) -> Unit,
    private val storageService: StorageService,
    savedStateHandle: SavedStateHandle
) :
    MyDiaryViewModel() {
    val note = mutableStateOf(Note())

    init {
        val taskId = savedStateHandle.get<String>(ID)
        println("ID: $taskId")
        taskId?.let {
            launchCatching(errorMassage = toast) {
                storageService.getTask(taskId)?.let {
                    note.value = it
                }
            }
        }
    }

    fun onTitleChange(title: String) {
        note.value = note.value.copy(title = title)
    }

    fun onDescriptionChange(description: String) {
        note.value = note.value.copy(description = description)
    }

    fun onDateChange(date: Long) {
        note.value = note.value.copy(date = Date(date))
    }

    fun onEmojiChange(emoji: Int) {
        note.value = note.value.copy(emoji = emoji)
    }

    fun onDoneClick(popUpScreen: () -> Unit) {
        launchCatching(errorMassage = toast) {
            val editedNote = note.value
            if (editedNote.id.isBlank()) {
                storageService.save(editedNote)
            } else {
                storageService.update(editedNote)
            }
            popUpScreen()
        }
    }

}