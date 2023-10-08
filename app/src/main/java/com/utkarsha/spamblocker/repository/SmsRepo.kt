package com.utkarsha.spamblocker.repository

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.Telephony
import android.provider.Telephony.*
import android.util.Log
import com.utkarsha.spamblocker.model.Sms
import com.utkarsha.spamblocker.utils.SpamModel
import kotlinx.coroutines.runBlocking

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

    suspend fun readAllSms(context: Context): List<Sms> {
        val messages = mutableListOf<Sms>()
        val spamModel = SpamModel(context)

        runBlocking {
            try {
                val contentResolver: ContentResolver = context.contentResolver
                val uri: Uri = Telephony.Sms.Inbox.CONTENT_URI
                val projection = arrayOf(
                    Telephony.Sms.Inbox.ADDRESS,
                    Telephony.Sms.Inbox.BODY,
                    Telephony.Sms.Inbox.DATE
                )

                val cursor = contentResolver.query(
                    uri,
                    projection,
                    null,
                    null,
                    Telephony.Sms.Inbox.DEFAULT_SORT_ORDER
                )

                cursor?.use {
                    val senderIndex = it.getColumnIndex(Telephony.Sms.Inbox.ADDRESS)
                    val bodyIndex = it.getColumnIndex(Telephony.Sms.Inbox.BODY)
                    val dateIndex = it.getColumnIndex(Telephony.Sms.Inbox.DATE)

                    while (it.moveToNext()) {
                        val sender = it.getString(senderIndex)
                        val messageBody = it.getString(bodyIndex)
                        val timestamp = it.getLong(dateIndex)
                        val isSpam = spamModel.classify(messageBody)

                        messages.add(Sms(sender, messageBody, randomAvatarUrl(), isSpam))
                    }
                }
            } catch (e: Exception) {
                Log.e("Testlog SmsRepo", "Error reading SMS: ${e.message}")
            }
        }

        return messages
    }

    suspend fun readSpamSms(context: Context): List<Sms> {
        val messages = mutableListOf<Sms>()
        val spamModel = SpamModel(context)

        runBlocking {
            try {
                val contentResolver: ContentResolver = context.contentResolver
                val uri: Uri = Telephony.Sms.Inbox.CONTENT_URI
                val projection = arrayOf(
                    Telephony.Sms.Inbox.ADDRESS,
                    Telephony.Sms.Inbox.BODY,
                    Telephony.Sms.Inbox.DATE
                )

                val cursor = contentResolver.query(
                    uri,
                    projection,
                    null,
                    null,
                    Telephony.Sms.Inbox.DEFAULT_SORT_ORDER
                )

                cursor?.use {
                    val senderIndex = it.getColumnIndex(Telephony.Sms.Inbox.ADDRESS)
                    val bodyIndex = it.getColumnIndex(Telephony.Sms.Inbox.BODY)
                    val dateIndex = it.getColumnIndex(Telephony.Sms.Inbox.DATE)

                    while (it.moveToNext()) {
                        val sender = it.getString(senderIndex)
                        val messageBody = it.getString(bodyIndex)
                        val timestamp = it.getLong(dateIndex)
                        val isSpam = spamModel.classify(messageBody)

                        if(isSpam == "Spam") {
                            messages.add(Sms(sender, messageBody, randomAvatarUrl(), isSpam))
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("Testlog SmsRepo", "Error reading SMS: ${e.message}")
            }
        }

        return messages
    }

    suspend fun readCleanSms(context: Context): List<Sms> {
        val messages = mutableListOf<Sms>()
        val spamModel = SpamModel(context)

        runBlocking {
            try {
                val contentResolver: ContentResolver = context.contentResolver
                val uri: Uri = Telephony.Sms.Inbox.CONTENT_URI
                val projection = arrayOf(
                    Telephony.Sms.Inbox.ADDRESS,
                    Telephony.Sms.Inbox.BODY,
                    Telephony.Sms.Inbox.DATE
                )

                val cursor = contentResolver.query(
                    uri,
                    projection,
                    null,
                    null,
                    Telephony.Sms.Inbox.DEFAULT_SORT_ORDER
                )

                cursor?.use {
                    val senderIndex = it.getColumnIndex(Telephony.Sms.Inbox.ADDRESS)
                    val bodyIndex = it.getColumnIndex(Telephony.Sms.Inbox.BODY)
                    val dateIndex = it.getColumnIndex(Telephony.Sms.Inbox.DATE)

                    while (it.moveToNext()) {
                        val sender = it.getString(senderIndex)
                        val messageBody = it.getString(bodyIndex)
                        val timestamp = it.getLong(dateIndex)
                        val isSpam = spamModel.classify(messageBody)

                        if(isSpam != "Spam") {
                            messages.add(Sms(sender, messageBody, randomAvatarUrl(), isSpam))
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("Testlog SmsRepo", "Error reading SMS: ${e.message}")
            }
        }

        return messages
    }

    private fun randomAvatarUrl(): String {
        val random = (280..320).random()
        return "https://i.pravatar.cc/$random"
    }
}