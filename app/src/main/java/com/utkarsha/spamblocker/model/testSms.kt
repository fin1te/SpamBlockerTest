package com.utkarsha.spamblocker.model

data class testSms(
    val sender: String,
    val title: String,
    val body: String,
    val image: String = "https://i.pravatar.cc/300",
    val isSpam: String = "Not Spam"
)
