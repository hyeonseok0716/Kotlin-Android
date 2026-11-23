package com.androidbook.bookmarket.chapter06

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import androidx.core.content.IntentCompat
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.androidbook.bookmarket.R
import java.util.concurrent.atomic.AtomicReference
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IntentFilterProjectTest {
    @Test
    fun clickingComposePassesCurrentFieldsToTheChooser() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val captured = AtomicReference<Intent>()
        val monitor = object : Instrumentation.ActivityMonitor() {
            override fun onStartActivity(intent: Intent): Instrumentation.ActivityResult? {
                if (intent.action != Intent.ACTION_CHOOSER) return null
                captured.set(intent)
                return Instrumentation.ActivityResult(Activity.RESULT_CANCELED, null)
            }
        }
        instrumentation.addMonitor(monitor)
        try {
            ActivityScenario.launch(IntentFilterProject::class.java).use { scenario ->
                scenario.onActivity { activity ->
                    activity.findViewById<EditText>(R.id.editText1).setText("old@example.com")
                    activity.findViewById<EditText>(R.id.editText1).setText(" reader@example.com ")
                    activity.findViewById<EditText>(R.id.editText2).setText("메일보내기")
                    activity.findViewById<EditText>(R.id.editText3).setText("안녕하세요~~\n좋은 하루되세요~~")
                    activity.findViewById<Button>(R.id.button).performClick()
                }
                val chooser = captured.get()
                assertNotNull(chooser)
                val email = IntentCompat.getParcelableExtra(chooser!!, Intent.EXTRA_INTENT, Intent::class.java)
                assertNotNull(email)
                assertEquals(Intent.ACTION_SEND, email!!.action)
                assertEquals("message/rfc822", email.type)
                assertArrayEquals(arrayOf("reader@example.com"), email.getStringArrayExtra(Intent.EXTRA_EMAIL))
                assertEquals("메일보내기", email.getStringExtra(Intent.EXTRA_SUBJECT))
                assertEquals("안녕하세요~~\n좋은 하루되세요~~", email.getStringExtra(Intent.EXTRA_TEXT))
            }
        } finally {
            instrumentation.removeMonitor(monitor)
        }
    }
}
