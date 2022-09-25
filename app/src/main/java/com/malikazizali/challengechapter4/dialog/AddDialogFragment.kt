package com.malikazizali.challengechapter4.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.malikazizali.challengechapter4.R
import com.malikazizali.challengechapter4.databinding.FragmentAddDialogBinding
import com.malikazizali.challengechapter4.room.DataNote
import com.malikazizali.challengechapter4.viewmodel.NoteViewModel
import kotlinx.coroutines.GlobalScope

class AddDialogFragment : DialogFragment() {
    lateinit var binding : FragmentAddDialogBinding
    lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)

        binding.btnAddSave.setOnClickListener {
            val title = binding.etAddTitle.text.toString()
            val content = binding.etAddContent.text.toString()

            noteViewModel.insertNote(DataNote(0,title,content))

            dismiss()
            Toast.makeText(context, "Success adding new note", Toast.LENGTH_LONG).show()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}