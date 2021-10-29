package kr.co.lee.preferenceexapmle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var showSetting: TextView
    lateinit var showResult: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showSetting = findViewById(R.id.show_setting)
        showResult = findViewById(R.id.show_result)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SettingPreferenceFragment(), "setting_fragment")
            .commit()

        showSetting.setOnClickListener(textClickListener)
        showResult.setOnClickListener(textClickListener)
    }

    val textClickListener = View.OnClickListener {
        when(it) {
            showSetting -> {
                if(supportFragmentManager.findFragmentByTag("setting_fragment") == null) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, SettingPreferenceFragment(), "setting_fragment")
                        .commit()
                }
            }
        }
    }
}