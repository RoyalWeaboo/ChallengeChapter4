package com.malikazizali.challengechapter4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.malikazizali.challengechapter4.adapter.NoteAdapter
import com.malikazizali.challengechapter4.databinding.ActivityMainBinding
import com.malikazizali.challengechapter4.databinding.FragmentHomeBinding
import com.malikazizali.challengechapter4.databinding.FragmentRegisterBinding
import com.malikazizali.challengechapter4.dialog.AddDialogFragment
import com.malikazizali.challengechapter4.dialog.EditDialogFragment
import com.malikazizali.challengechapter4.room.DataNote
import com.malikazizali.challengechapter4.room.NoteDatabase
import com.malikazizali.challengechapter4.viewmodel.NoteViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var sp: SharedPreferences
    lateinit var adapterNote: NoteAdapter
    lateinit var noteViewModel: NoteViewModel
    var noteDb : NoteDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sp = requireActivity().getSharedPreferences("login_ch_4", Context.MODE_PRIVATE)
        val username = sp.getString("username", "user")
        binding.tvUser.text = "Welcome, " + username

        getAllNote()

        noteViewModel = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)

        noteViewModel.getAllNoteObservers().observe(viewLifecycleOwner, Observer {
            adapterNote.setNoteData(it as ArrayList<DataNote>)
            adapterNote.notifyDataSetChanged()
        })

        noteDb = NoteDatabase.getInstance(requireActivity())

        if (noteDb?.noteDao()?.checkData() != 0 ) {
            binding.tvEmpty.visibility = View.GONE
        } else {
            binding.tvEmpty.visibility = View.VISIBLE
        }

        binding.fabAdd.setOnClickListener {
            val addDialogFragment = AddDialogFragment()
            addDialogFragment.show(parentFragmentManager, "addDialog")
            binding.tvEmpty.visibility = View.GONE
        }

        binding.ivLogout.setOnClickListener {
            val editor = sp.edit()
            editor.putString("status", "notloggedin")
            editor.apply()
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_loginFragment)
        }

    }

    fun getAllNote() {
        adapterNote = NoteAdapter(ArrayList())
        binding.rvNote.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvNote.adapter = adapterNote

        adapterNote.onEdit = {
            val bun = Bundle()
            bun.putSerializable("noteData", it)

            val addDialogFragment = EditDialogFragment()
            addDialogFragment.arguments = bun
            addDialogFragment.show(parentFragmentManager, "addDialog")
        }

        adapterNote.onDelete = {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Delete Note")
            builder.setMessage("Confirm delete note with id number : ${it.id} ?")
            builder.setIcon(R.drawable.ic_baseline_warning_24)
            builder.setPositiveButton("Confirm") { _, _ ->
                noteViewModel.deleteNote(it)
                Toast.makeText(context, "Success Deleting", Toast.LENGTH_LONG).show()
            }
            builder.setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()

            if (noteDb?.noteDao()?.checkData() != 0 ) {
                binding.tvEmpty.visibility = View.GONE
            }else {
                binding.tvEmpty.visibility = View.VISIBLE
            }
        }

    }

}
