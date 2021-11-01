package kr.co.lee.preferenceexapmle

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class MainActivity : AppCompatActivity(), PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {
    lateinit var showSetting: TextView
    lateinit var showResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // FragmentManager를 얻어온 후 transaction 시작
        // contatiner에 Fragment 변경하고 commit을 호출하여 transaction 적용
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SettingPreferenceFragment(), "setting_fragment")
            .commit()

        showSetting = findViewById(R.id.show_setting)
        showResult = findViewById(R.id.show_result)


        showSetting.setOnClickListener(textClickListener)
        showResult.setOnClickListener(textClickListener)
    }

    // Preference에 새로운 화면을 연결하기 위하여 호출하는 메서드
    override fun onPreferenceStartFragment(caller: PreferenceFragmentCompat, pref: Preference): Boolean {
        // Instantiate the new Fragment
        val args = pref.extras
        val fragment = supportFragmentManager.fragmentFactory.instantiate(
            classLoader,
            pref.fragment)
        fragment.arguments = args
        fragment.setTargetFragment(caller, 0)
        // Replace the existing Fragment with the new Fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, "nested_fragment")
            .commit()
        return true
    }

    // 버튼 클릭 리스너
    val textClickListener = View.OnClickListener {
        when(it) {
            showSetting -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SettingPreferenceFragment(), "setting_fragment")
                    .commit()
            }
            showResult -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ShowResultFragment(), "show_fragment")
                    .commit()
                }
        }
    }
}