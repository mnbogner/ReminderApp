package com.mnb.reminderapp.data

class ScoreboardRepository(private val dataSource: ScoreboardDataSource) {
    suspend fun increaseScore(value: Int): Int {
        return dataSource.increaseScore(value)
    }
    suspend fun decreaseScore(value: Int): Int {
        return dataSource.decreaseScore(value)
    }
    suspend fun getScore(): Int {
        return dataSource.getScore()
    }
}