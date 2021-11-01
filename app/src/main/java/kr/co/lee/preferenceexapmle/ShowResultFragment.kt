package kr.co.lee.preferenceexapmle

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager

// 단지 SharedPreferences에 저장된 결과 보여주기위한 Fragment
class ShowResultFragment: Fragment(R.layout.fragment_show_result) {
    lateinit var songListView: TextView
    lateinit var nickNameVIew: TextView
    lateinit var keywordSongListView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // View 객체 초기화
        songListView = view.findViewById(R.id.song_list)
        nickNameVIew = view.findViewById(R.id.nickname)
        keywordSongListView = view.findViewById(R.id.keyword_song_list)

        // Activity에 저장된 SharedPreferences 얻어오기
        val prefs = PreferenceManager.getDefaultSharedPreferences(activity)

        // 값 얻어와서 TextView에 셋팅
        songListView.text = prefs.getString("sound_list","카톡")
        nickNameVIew.text = prefs.getString("nickname", "")
        keywordSongListView.text = prefs.getString("keyword_sound_list", "카톡")
    }
}