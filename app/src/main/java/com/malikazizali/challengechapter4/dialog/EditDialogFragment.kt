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
import com.malikazizali.challengechapter4.databinding.FragmentEditDialogBinding
import com.malikazizali.challengechapter4.room.DataNote
import com.malikazizali.challengechapter4.viewmodel.NoteViewModel

class EditDialogFragment : DialogFragment() {
    lateinit var binding : FragmentEditDialogBinding
    lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments?.getSerializable("noteData") as DataNote
        binding.dataNote = DataNote(data.id,data.title,data.content)

        noteViewModel = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)

        binding.btnUpdate.setOnClickListener {
            val title = binding.etEditTitle.text.toString()
            val content = binding.etEditContent.text.toString()

            noteViewModel.updateNote(DataNote(data.id,title,content))

            dismiss()
            Toast.makeText(context, "Update note succeed", Toast.LENGTH_LONG).show()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}