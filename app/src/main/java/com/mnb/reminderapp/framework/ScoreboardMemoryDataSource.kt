package com.mnb.reminderapp.framework

import com.mnb.reminderapp.data.ScoreboardDataSource
import com.mnb.reminderapp.domain.Scoreboard

class ScoreboardMemoryDataSource : ScoreboardDataSource {

    private var scoreboard = Scoreboard(0)

    override suspend fun increaseScore(value: Int): Int {
        scoreboard.score += value
        return scoreboard.score
    }

    override suspend fun decreaseScore(value: Int): Int {
        scoreboard.score -= value
        return scoreboard.score
    }

    override suspend fun getScore(): Int {
        return scoreboard.score
    }
}