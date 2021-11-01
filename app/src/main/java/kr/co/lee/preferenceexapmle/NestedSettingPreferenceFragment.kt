package kr.co.lee.preferenceexapmle

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

// PreferenceFragmentCompat을 상속받는 클래스 정의
class NestedSettingPreferenceFragment : PreferenceFragmentCompat() {
    lateinit var prefs: SharedPreferences

    var keywordPreference: Preference? = null
    var keywordListPreference: Preference? = null

    // onCreate() 중에 호출되어 Fragment에 preference를 제공하는 메서드
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // preference xml을 inflate하는 메서드
        setPreferencesFromResource(R.xml.nested_setting_preference, rootKey)

        // rootkey가 null이라면
        if (rootKey == null) {
            // Preference 객체들 초기화
            keywordPreference = findPreference("keyword")
            keywordListPreference = findPreference("keyword_sound_list")
        }

        // SharedPreferences 객체 초기화
        prefs = PreferenceManager.getDefaultSharedPreferences(activity)

        // SharedPreferences에 저장된 값 불러오기
        if (prefs.getString("keyword_sound_list", "카톡") != "") {
            keywordListPreference?.summary = prefs.getString("keyword_sound_list", "카톡")
        }
    }

    // 설정 변경 이벤트 처리
    val prefListener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences: SharedPreferences?, key: String? ->
            // key는 xml에 등록된 key에 해당
            when (key) {
                "keyword_sound_list" -> {
                    // SharedPreferences에 저장된 값을 가져와서 summary 설정
                    val summary = prefs.getString("keyword_sound_list", "카톡")
                    keywordListPreference?.summary = summary
                }
                "keyword" -> {
                    val value = prefs.getBoolean("keyword", false)
                }
            }
        }

    // 리스너 등록
    override fun onResume() {
        super.onResume()
        prefs.registerOnSharedPreferenceChangeListener(prefListener)
    }

    // 리스너 해제
    override fun onPause() {
        super.onPause()
        prefs.unregisterOnSharedPreferenceChangeListener(prefListener)
    }
}