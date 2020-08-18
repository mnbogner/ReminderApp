package com.mnb.reminderapp.usecase.interactors

import com.mnb.reminderapp.data.ScoreboardRepository

// does it make sense to bake this logic in here? doing so does
// isolate it from both the presentation and framework layers

class ScoreProgress (private val scoreboardRepository: ScoreboardRepository) {

    val progressValue = 1

    suspend operator fun invoke(): Int {
        return scoreboardRepository.increaseScore(progressValue)
    }
}