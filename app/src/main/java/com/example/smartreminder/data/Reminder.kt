package com.example.smartreminder.data

data class Reminder(
    val title: String,
    val notes: String,
    val dateTime: Long = System.currentTimeMillis(),
)