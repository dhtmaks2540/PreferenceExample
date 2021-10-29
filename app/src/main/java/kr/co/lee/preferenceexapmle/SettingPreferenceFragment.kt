package kr.co.lee.preferenceexapmle

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

// PreferenceFragmentCompat을 상속받는 클래스 작성
class SettingPreferenceFragment: PreferenceFragmentCompat() {
    // 저장되는 데이터에 접근하기 위한 SharedPreferences
    lateinit var prefs: SharedPreferences

    // Preference 객체
    var soundPreference: Preference? = null
    var messagePreference: Preference? = null
    var soundListPreference: Preference? = null
    var checkBoxPreference: Preference? = null
    var nicknamePreference: Preference? = null

    // onCreate() 중에 호출되어 Fragment에 preference를 제공하는 메서드
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // preference xml을 inflate하는 메서드
        setPreferencesFromResource(R.xml.settings_preference, rootKey)

        // rootKey가 null 이라면
        if(rootKey == null) {
            // Preference 객체 초기화
            soundPreference = findPreference("sound")
            messagePreference = findPreference("message")
            soundListPreference = findPreference("sound_list")
            checkBoxPreference = findPreference("nickname_flag")
            nicknamePreference = findPreference("nickname")

            // SharedPreferences 객체 초기화
            prefs = PreferenceManager.getDefaultSharedPreferences(activity)
            
            // sound_list라는 key로 저장된 값이 비어있지 않다면
            if(prefs.getString("sound_list","") != "") {
                // ListPreference의 summary를 sound_list key에 해당하는 값으로 설정
                soundListPreference?.summary = prefs.getString("sound_list", "카톡")
            }

            // nickname이라는 key로 저장된 값이 비어있지 않다면
            if(prefs.getString("nickname", "") != "") {
                nicknamePreference?.summary = prefs.getString("nickname", "")
            }
        }

        // 변경이벤트 리스너 달아주기
        prefs.registerOnSharedPreferenceChangeListener(prefListener)
    }

    // 설정 변경 이벤트 처리
    val prefListener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences: SharedPreferences?, key: String? ->
        when(key) {
            "message" -> {
                val value = prefs.getBoolean("message", false)
                showMessage("switch preference : $value")
            }

            "sound_list" -> {
                val summary = prefs.getString("sound_list", "카톡")
                soundListPreference?.summary = summary
                showMessage("list : $summary")
            }
            "nickname" -> {
                val summary = prefs.getString("nickname", "")
                nicknamePreference?.summary = prefs.getString("nickname", "")
                showMessage("edit : $summary")
            }
            "nickname_flag" -> {
                val value = prefs.getBoolean("nickname_flag", false)
                showMessage("check : $value")
            }
            "sound" -> {
                val value = prefs.getBoolean("sound", false)
                showMessage("switch : $value")
            }
        }
    }

    private fun showMessage(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}