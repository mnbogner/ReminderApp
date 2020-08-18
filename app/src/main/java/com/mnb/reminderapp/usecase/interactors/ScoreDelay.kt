package com.mnb.reminderapp.usecase.interactors

import com.mnb.reminderapp.data.ScoreboardRepository

// does it make sense to bake this logic in here? doing so does
// isolate it from both the presentation and framework layers

class ScoreDelay (private val scoreboardRepository: ScoreboardRepository) {

    val delayValue = 1

    suspend operator fun invoke(): Int {
        return scoreboardRepository.decreaseScore(delayValue)
    }
}