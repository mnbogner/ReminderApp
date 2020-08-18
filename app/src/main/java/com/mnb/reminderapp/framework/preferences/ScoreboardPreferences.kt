package com.mnb.reminderapp.framework.preferences

import android.content.Context
import android.content.SharedPreferences

class ScoreboardPreferences(val context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun sharedPreferences(): SharedPreferences {
        return sharedPreferences
    }

    companion object {

        val SHARED_PREFERENCES_NAME = "reminder_preferences"

        @Volatile
        private var scoreboardInstance: ScoreboardPreferences? = null

        fun getInstance(context: Context): ScoreboardPreferences {
            val instance = scoreboardInstance
            if (instance != null) {
                return instance
            }

            synchronized(ScoreboardPreferences::class) {
                val newInstance = ScoreboardPreferences(context)
                scoreboardInstance = newInstance
                return newInstance
            }
        }
    }

}