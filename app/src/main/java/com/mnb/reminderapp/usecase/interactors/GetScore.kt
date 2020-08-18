package com.mnb.reminderapp.usecase.interactors

import com.mnb.reminderapp.data.ScoreboardRepository

class GetScore (private val scoreboardRepository: ScoreboardRepository) {
    suspend operator fun invoke(): Int {
        return scoreboardRepository.getScore()
    }
}