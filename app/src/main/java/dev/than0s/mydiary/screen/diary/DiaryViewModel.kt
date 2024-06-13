package dev.than0s.mydiary.screen.diary

import dev.than0s.mydiary.model.service.StorageService
import dev.than0s.mydiary.screen.MyDiaryViewModel

class DiaryViewModel(storageService:StorageService): MyDiaryViewModel() {
    val notes = storageService.notes
}