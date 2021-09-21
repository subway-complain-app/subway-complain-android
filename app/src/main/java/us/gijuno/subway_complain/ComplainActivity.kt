package us.gijuno.subway_complain

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ComplainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complain)
        val selectedLine = intent.getStringExtra("selectedLine")

        findViewById<Button>(R.id.complain_button).setOnClickListener {
            val line = selectedLine.toString()
            val boundTo = findViewById<EditText>(R.id.bound_to).text.toString()
            val nextStation = findViewById<EditText>(R.id.next_station).text.toString()
            val trainNum = findViewById<EditText>(R.id.train_num).text.toString()
            val unit = findViewById<EditText>(R.id.unit).text.toString()
            val content = findViewById<EditText>(R.id.content).text.toString()

            sendMessage(phoneNumberFromLine(line),
                setMessageContent(line, boundTo, nextStation, trainNum, unit, content))
        }
    }

    private fun setMessageContent(
        line: String,
        boundTo: String,
        nextStation: String,
        trainNum: String,
        unit: String,
        content: String,
    ): String {
        val _unit = when (unit) {
            "" -> ""
            else -> " ${unit}호칸"
        }

        return "$line ${boundTo}행 $nextStation 가는 ${trainNum}편성${_unit}에 $content"
    }

    private fun phoneNumberFromLine(line: String): String {
        val lines = MainActivity().lines

        return when (line) {
            lines[0], lines[2], lines[4], lines[6], lines[7], lines[8], lines[9], lines[10] -> "1577-1234"
            lines[1], lines[3], lines[5], lines[12], lines[13] -> "1544-7769"
            lines[11] -> "1544-4009"
            lines[14] -> "032-451-2114"
            lines[15] -> "031-8018-7777"
            else -> ""
        }
    }

    private fun sendMessage(phoneNumber: String, content: String) {
        startActivity(Intent(Intent.ACTION_SENDTO,
            Uri.parse("smsto:${phoneNumber}")).putExtra("sms_body",
            content))
    }
}