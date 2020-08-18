package com.mnb.reminderapp.framework

import android.content.Context
import com.mnb.reminderapp.data.ScoreboardDataSource
import com.mnb.reminderapp.framework.preferences.ScoreboardPreferences

class ScoreboardPreferencesDataSource (context: Context) : ScoreboardDataSource {

    private val SCORE_PREFERENCE_KEY = "score_key"

    private val sharedPreferences = ScoreboardPreferences.getInstance(context).sharedPreferences()

    override suspend fun increaseScore(value: Int): Int {
        var currentScore = sharedPreferences.getInt(SCORE_PREFERENCE_KEY, 0)
        currentScore = currentScore + value
        sharedPreferences.edit().putInt(SCORE_PREFERENCE_KEY, currentScore).apply()
        return currentScore
    }

    override suspend fun decreaseScore(value: Int): Int {
        var currentScore = sharedPreferences.getInt(SCORE_PREFERENCE_KEY, 0)
        currentScore = currentScore - value
        sharedPreferences.edit().putInt(SCORE_PREFERENCE_KEY, currentScore).apply()
        return currentScore
    }

    override suspend fun getScore(): Int {
        var currentScore = sharedPreferences.getInt(SCORE_PREFERENCE_KEY, 0)
        return currentScore
    }
}