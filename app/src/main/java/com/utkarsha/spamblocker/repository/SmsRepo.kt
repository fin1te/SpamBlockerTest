package com.utkarsha.spamblocker.repository

import com.utkarsha.spamblocker.model.Sms

object SmsRepo {

    fun getMockSms() : List<Sms> {
        return listOf(
            Sms("Rishabh", "How are you?", "https://i.pravatar.cc/306"),
            Sms("Utkarsha", "How about meeting tomorrow?", "https://i.pravatar.cc/293"),
            Sms("Rishabh", "I am good, how are you?", "https://i.pravatar.cc/298"),
            Sms("Simran", "I am good too, let's meet tomorrow.", "https://i.pravatar.cc/297"),
            Sms("Simran", "I am good too, let's meet tomorrow.", "https://i.pravatar.cc/296"),
            Sms("Rishabh", "How are you?", "https://i.pravatar.cc/295"),
            Sms("Utkarsha", "How about meeting tomorrow?", "https://i.pravatar.cc/301"),
            Sms("Rishabh", "How are you?", "https://i.pravatar.cc/302"),
            Sms("Utkarsha", "How about meeting tomorrow?", "https://i.pravatar.cc/303"),
            Sms("Rishabh", "How are you?", "https://i.pravatar.cc/299"),
            Sms("Utkarsha", "How about meeting tomorrow?", "https://i.pravatar.cc/300"),
            Sms("Rishabh", "I am good, how are you?", "https://i.pravatar.cc/298"),
            Sms("Simran", "I am good too, let's meet tomorrow.", "https://i.pravatar.cc/297"),
            Sms("Simran", "I am good too, let's meet tomorrow.", "https://i.pravatar.cc/296"),
            Sms("Rishabh", "How are you?", "https://i.pravatar.cc/295"),
            Sms("Utkarsha", "How about meeting tomorrow?", "https://i.pravatar.cc/301"),
            Sms("Rishabh", "How are you?", "https://i.pravatar.cc/302"),
            Sms("Utkarsha", "How about meeting tomorrow?", "https://i.pravatar.cc/303"),
        ).shuffled()
    }
}