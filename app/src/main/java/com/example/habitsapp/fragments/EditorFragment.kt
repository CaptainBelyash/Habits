package com.example.habitsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habitsapp.R
import com.example.habitsapp.model.HabitsAppModel
import com.example.habitsapp.model.database.HabitEntity
import com.example.habitsapp.model.database.HabitsDB
import com.example.habitsapp.viewmodels.EditorViewModel
import kotlinx.android.synthetic.main.fragment_editor.*


class EditorFragment : Fragment() {
    private lateinit var editorViewModel: EditorViewModel
    private var habitId: Int? = null
    private var habit: HabitEntity? = null

    companion object {
        @JvmStatic
        fun newInstance() = EditorFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editorViewModel = ViewModelProvider(activity!!, object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return EditorViewModel(HabitsAppModel.withDao(HabitsDB.getDatabase(requireContext()).habitsDao())) as T
            }
        }).get(EditorViewModel::class.java)

        arguments?.let {
            habitId = it.getInt("habitId")
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
            editorViewModel.addOrReplaceHabit(editorViewModel.getHabitFromFields(getFields()), habit)
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
            name_et.setText(it.name)
            description_et.setText(it.description)
            priority_sp.setSelection(spinnerFitValue(priority_sp, it.priority))
            periodicity_et.setText(it.periodicity)
            radioGroupFitValue(type_rg, it.type)
        }
    }

    private fun spinnerFitValue(spinner: Spinner, myString: String?): Int {
        for (i in 0 until spinner.count)
            if (spinner.getItemAtPosition(i).toString().equals(myString, ignoreCase = true))
                return i
        return 0
    }

    private fun radioGroupFitValue(rg: RadioGroup, myString: String?){
        rg.check(rg.getChildAt(0).id)
        for (i in 0 until rg.childCount) {
            val rb = activity!!.findViewById<RadioButton>(rg.getChildAt(i).id)
            if (rb.text.toString().equals(myString, ignoreCase = true)) {
                rg.check(rb.id)
            }
        }
    }
}