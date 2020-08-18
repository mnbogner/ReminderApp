package com.mnb.reminderapp.data

interface ScoreboardDataSource {
    suspend fun increaseScore(value: Int): Int
    suspend fun decreaseScore(value: Int): Int
    suspend fun getScore(): Int
}