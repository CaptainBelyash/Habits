package com.example.habitsapp.Screens

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.spinner.KSpinner
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.example.habitsapp.R

class EditorScreen: Screen<EditorScreen>(){
    val deleteHabitButton = KButton{
        withId(R.id.delete_button)
    }

    val nameField = KEditText{
        withId(R.id.name_input_field)
    }

    val descriptionField = KEditText{
        withId(R.id.description_et)
    }

    val saveHabitButton = KButton{
        withId(R.id.save_fab)
    }
}