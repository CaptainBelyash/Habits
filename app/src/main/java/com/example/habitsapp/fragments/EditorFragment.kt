package com.example.habitsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.domainmodule.HabitEntity
import com.example.habitsapp.MainActivity
import com.example.habitsapp.R
import com.example.habitsapp.viewmodels.EditorViewModel
import kotlinx.android.synthetic.main.fragment_editor.*


class EditorFragment : Fragment() {
    private lateinit var editorViewModel: EditorViewModel
    private var habitId: String? = null
    private var habitUId: String? = null
    private var habit: HabitEntity? = null

    companion object {
        @JvmStatic
        fun newInstance() = EditorFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val usecases = (requireActivity() as MainActivity).appComponent.getHabitsUseCases()
        super.onCreate(savedInstanceState)
        editorViewModel = ViewModelProvider(activity!!, object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return EditorViewModel(usecases) as T
            }
        }).get(EditorViewModel::class.java)

        arguments?.let {
            habitId = it.getString("habitId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_editor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
        if (habitId != null){
            fitExistedData()
            delete_button.visibility = View.VISIBLE
        }
    }

    private fun getFields(): Map<String, String?> {
        val result = mutableMapOf<String, String?>()
        result["name"] = name_et.text.toString()
        result["description"] = description_et.text.toString()
        result["priority"] = priority_sp.selectedItem.toString()
        result["periodicity"] = periodicity_et.text.toString()
        result["type"] = activity?.findViewById<RadioButton>(type_rg.checkedRadioButtonId)?.text.toString()
        return result
    }

    private fun initButtons(){
        initSaveButton()
        initDeleteButtons()
    }

    private fun initSaveButton(){
        save_fab.setOnClickListener {
            editorViewModel.insertHabit(editorViewModel.getHabitFromFields(getFields(), habitId, habitUId))
            findNavController().popBackStack()
        }
    }

    private fun initDeleteButtons(){
        delete_button.setOnClickListener{
            editorViewModel.deleteHabit(habit!!)
            findNavController().popBackStack()
        }
    }

    private fun fitExistedData(){
        editorViewModel.GetHabitById(habitId!!).observe(viewLifecycleOwner){
            habit = it
            habitUId = it.uid
            name_et.setText(it.name)
            description_et.setText(it.description)
            priority_sp.setSelection(it.priority.ordinal)
            periodicity_et.setText(it.frequency.toString())
            type_rg.check(type_rg.getChildAt(it.type.ordinal).id)
        }
    }
}