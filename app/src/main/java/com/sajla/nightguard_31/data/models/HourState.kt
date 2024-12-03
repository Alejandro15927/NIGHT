package com.sajla.nightguard_31.data.models

data class HourState(
    val hour: Int = 0,
    val minute: Int = 0
) {
    val formattedTime: String
        get() = String.format("%02d:%02d", hour, minute)
}