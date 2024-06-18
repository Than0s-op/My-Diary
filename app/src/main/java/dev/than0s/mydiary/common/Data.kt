package dev.than0s.mydiary.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SentimentDissatisfied
import androidx.compose.material.icons.rounded.SentimentNeutral
import androidx.compose.material.icons.rounded.SentimentSatisfied
import androidx.compose.material.icons.rounded.SentimentVeryDissatisfied
import androidx.compose.material.icons.rounded.SentimentVerySatisfied

val sentimentList = Icons.Rounded.run {
    listOf(
        SentimentVeryDissatisfied,
        SentimentDissatisfied,
        SentimentNeutral,
        SentimentSatisfied,
        SentimentVerySatisfied
    )
}