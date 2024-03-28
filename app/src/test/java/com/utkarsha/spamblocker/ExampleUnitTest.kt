package com.utkarsha.spamblocker

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.utkarsha.spamblocker.repository.TestSmsRepo
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.*
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun testReadAllSms() {
        // Mock the context
        val context = mock(Context::class.java)

        // Set up any necessary mock behavior for the context

        val smsRepository = TestSmsRepo

        runBlocking {
            val smsList = smsRepository.readAllSms(context)

            // Print the list of SMS
            smsList.forEachIndexed { index, sms ->
                println("SMS ${index + 1}: Sender = ${sms.sender}, Body = ${sms.body}")
            }

            // Assert that the SMS list is not empty
            assertFalse(smsList.isEmpty())
        }
    }

}