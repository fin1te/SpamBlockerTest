package com.utkarsha.spamblocker.model

data class Sms(
    val title : String,
    val body : String,
    val image : String = "https://i.pravatar.cc/300"
)
