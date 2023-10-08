package com.utkarsha.spamblocker.utils

import android.content.Context
import android.os.SystemClock
import android.util.Log
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.tensorflow.lite.support.label.Category
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.text.nlclassifier.NLClassifier
import java.util.concurrent.ScheduledThreadPoolExecutor

class SpamModel(context: Context) {

    private var nlClassifier: NLClassifier
    //private lateinit var executor: ScheduledThreadPoolExecutor

    init {
        val baseOptionsBuilder = BaseOptions.builder()
        val baseOptions = baseOptionsBuilder.build()

        val options = NLClassifier.NLClassifierOptions.builder()
            .setBaseOptions(baseOptions)
            .build()

        nlClassifier = NLClassifier.createFromFileAndOptions(
            context,
            EPOCH_40_MODEL,
            options
        )

        val list = listOf(
            "Sent Rs.120.00 from Kotak Bank AC X6306 to q847856739@ybl on 07-10-23.UPI Ref 328034302323. Not you, kotak.com/fraud",
            "Received Rs.5000.00 in your Kotak Bank AC X6306 from ramjageshwarmahto@okicici on 04-10-23.Bal:5,160.28.UPI Ref:327714324439",
            "Attend FREE online IELTS Masterclass by Vidyalankar & know expected questions, tips to score 7+ Band & more. 7 Oct, Sat, 6:30 PM. Register: bit.ly/3F2L1iS",
            "Dear Customer, Last 12 hours to get 50% cashback + Buy1Get1 with Gold membership in Lenskart's Freedom Rush. Shop now! TnC\n" + "\n" + "App lskt.me/k9\n" + "\n" + "Store lskt.me/m8",
            "Dear Customer" + "Rs.4,50,000 is credited to your loan a/c no - xxx343. Check eligibility.\n" + "Complete KYC now- http://t9x.in/J64lS9 NU Finance"
        )

    }

    suspend fun classify(text: String): String {
        var isSpam = ""

        runBlocking {
            val result = async { nlClassifier.classify(text) }
            isSpam = if (result.await()[0].score >= 0.50) "Not Spam" else "Spam"

            Log.d("Testlog", "${text.substring(0, minOf(text.length, 15))} Result :    $isSpam")
        }

        return isSpam

        //            executor = ScheduledThreadPoolExecutor(1)
//            executor.execute {
//                val results: List<Category> = nlClassifier.classify(text)
//
//                isSpam = if(results[0].score >= 0.50) "Not Spam" else "Spam"
//
//                Log.d("Testlog", "${text.substring(0, minOf(text.length, 15))} Result :    $isSpam")
//
//            }
    }

    companion object {
        const val EPOCH_40_MODEL = "epoch_40_sst3.tflite"
    }

}

