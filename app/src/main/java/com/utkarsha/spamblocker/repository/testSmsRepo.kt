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

object TestSmsRepo {

    suspend fun readAllSms(context: Context): List<Sms> {
        val messages = mutableListOf<Sms>()
        val spamModel = SpamModel(context)

        runBlocking {
            try {
                val contentResolver: ContentResolver = context.contentResolver
                val uri: Uri = Telephony.Sms.Conversations.CONTENT_URI
                val projection = arrayOf(
                    Telephony.Sms.Conversations.SNIPPET, // This represents the latest message snippet in the conversation
                    Telephony.Sms.Conversations.THREAD_ID // This represents the conversation ID
                )

                val cursor = contentResolver.query(
                    uri,
                    projection,
                    null,
                    null,
                    null
                )

                cursor?.use {
                    val snippetIndex = it.getColumnIndex(Telephony.Sms.Conversations.SNIPPET)
                    val threadIdIndex = it.getColumnIndex(Telephony.Sms.Conversations.THREAD_ID)

                    while (it.moveToNext()) {
                        val snippet = it.getString(snippetIndex)
                        val threadId = it.getLong(threadIdIndex)

                        // Fetch the most recent SMS for the conversation
                        val recentSms = getRecentSmsForConversation(context, threadId)

                        // Check if recentSms is not null before adding to messages
                        recentSms?.let { sms ->
                            messages.add(sms)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("Testlog SmsRepo", "Error reading SMS: ${e.message}")
            }
        }

        return messages
    }

    private suspend fun getRecentSmsForConversation(context: Context, threadId: Long): Sms? {
        val contentResolver: ContentResolver = context.contentResolver
        val uri = Telephony.Sms.CONTENT_URI
        val projection = arrayOf(
            Telephony.Sms.ADDRESS,
            Telephony.Sms.BODY,
            Telephony.Sms.DATE
        )
        val selection = "${Telephony.Sms.THREAD_ID} = ?"
        val selectionArgs = arrayOf(threadId.toString())
        val sortOrder = "${Telephony.Sms.DATE} DESC" // Sort by date in descending order to get the most recent SMS

        val cursor = contentResolver.query(
            uri,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )

        cursor?.use {
            if (it.moveToFirst()) {
                // Check if the column indices are valid
                val addressIndex = it.getColumnIndex(Telephony.Sms.ADDRESS)
                val bodyIndex = it.getColumnIndex(Telephony.Sms.BODY)
                val dateIndex = it.getColumnIndex(Telephony.Sms.DATE)

                if (addressIndex >= 0 && bodyIndex >= 0 && dateIndex >= 0) {
                    val sender = it.getString(addressIndex)
                    val body = it.getString(bodyIndex)
                    val timestamp = it.getLong(dateIndex)
                    val isSpam = SpamModel(context).classify(body) // Classify spam based on message body

                    return Sms(sender, body, "https://i.pravatar.cc/300", isSpam)
                } else {
                    Log.e("Error", "Column index not found in cursor")
                }
            }
        }

        return null // Return null if no SMS found for the conversation or if column indices are not valid
    }




}