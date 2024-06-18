package dev.than0s.mydiary.screen.edit_note

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.than0s.mydiary.ID
import dev.than0s.mydiary.model.service.StorageService
import dev.than0s.mydiary.screen.MyDiaryViewModel
import dev.than0s.mydiary.screen.diary.Note
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val storageService: StorageService,
    savedStateHandle: SavedStateHandle
) :
    MyDiaryViewModel() {
    val note = mutableStateOf(Note())

    init {
        val taskId = savedStateHandle.get<String>(ID)
        taskId?.let {
            launchCatching(errorMassage = {}) {
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

    fun onDateChange(localDate: LocalDate) {
        val date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
        note.value = note.value.copy(date = date)
    }

    fun onEmojiChange(emoji: Int) {
        note.value = note.value.copy(emoji = emoji)
    }

    fun onDoneClick(popUpScreen: () -> Unit) {
        launchCatching(errorMassage = {}) {
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