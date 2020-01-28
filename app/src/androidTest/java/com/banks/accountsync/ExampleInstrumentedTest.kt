package com.banks.accountsync

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    companion object{
        private const val TAG = "ExampleInstrumentedTest"
    }
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.banks.accountsync", appContext.packageName)


        val externalFilesDir = appContext.getExternalFilesDir("record")
        if (BuildConfig.DEBUG) {
            Log.v(TAG,"externalFilesDir $externalFilesDir")
        }

        val internalFilesDir = appContext.filesDir
        if (BuildConfig.DEBUG) {
            Log.v(TAG,"internalFilesDir $internalFilesDir")
        }

       /* val file = File(externalFilesDir, "recordSync.txt")
        if (!file.exists()) {
            file.createNewFile()
        }
        val bufferWriter = BufferedWriter(OutputStreamWriter(FileOutputStream(file, true)))
        bufferWriter.write("hello, recordSync1\n")
        bufferWriter.flush()
        bufferWriter.close()*/



    }
}
