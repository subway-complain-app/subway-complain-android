package us.gijuno.subway_complain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    val lines = arrayOf("서울 1호선 (서울역 ~ 청량리)",
        "서울 1호선 (비서울권)",
        "서울 2호선",
        "서울 3호선 (대화 ~ 지축)",
        "서울 3호선 (구파발 ~ 오금)",
        "서울 4호선 (선바위 ~ 오이도)",
        "서울 4호선 (당고개 ~ 경마공원)",
        "서울 5호선",
        "서울 6호선",
        "서울 7호선",
        "서울 8호선",
        "서울 9호선",
        "분당선",
        "경의중앙선",
        "인천지하철",
        "신분당선")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val picker = findViewById<NumberPicker>(R.id.picker)
        findViewById<Button>(R.id.line_select_button).setOnClickListener {
            Log.d("selected", lines[picker.value])
            lines[picker.value]
            startActivity(Intent(this, ComplainActivity::class.java).putExtra("selectedLine",lines[picker.value]))
        }
    }

    override fun onStart() {
        super.onStart()
        initNumberPicker()
        numberPickerListener()
    }

    // 넘버 픽커 초기화
    private fun initNumberPicker() {
        val picker = findViewById<NumberPicker>(R.id.picker)
        findViewById<TextView>(R.id.selected_line).text = lines[0]
        picker.minValue = 0
        picker.maxValue = lines.size-1
        picker.wrapSelectorWheel = false
        picker.displayedValues = lines
    }

    // 넘버 픽커 리스너
    private fun numberPickerListener() {
        val picker = findViewById<NumberPicker>(R.id.picker)
        picker.setOnValueChangedListener { picker, _, _ ->
            findViewById<TextView>(R.id.selected_line).text = lines[picker.value]
        }
    }
}