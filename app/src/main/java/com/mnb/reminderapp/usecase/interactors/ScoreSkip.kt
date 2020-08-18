package com.mnb.reminderapp.usecase.interactors

import com.mnb.reminderapp.data.ScoreboardRepository

// does it make sense to bake this logic in here? doing so does
// isolate it from both the presentation and framework layers

class ScoreSkip (private val scoreboardRepository: ScoreboardRepository) {

    val skipValue = 3

    suspend operator fun invoke(value: Int): Int {
        return scoreboardRepository.decreaseScore(skipValue - value)
    }
}