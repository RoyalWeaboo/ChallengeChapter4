package com.malikazizali.challengechapter4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.malikazizali.challengechapter4.databinding.FragmentDetailBinding
import com.malikazizali.challengechapter4.room.DataNote

class DetailFragment : Fragment() {
    lateinit var binding : FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString("id")?.toInt()
        val title = arguments?.getString("title")
        val content = arguments?.getString("content")

        binding.dataNote = DataNote(id!!,title!!,content!!)

        binding.ivBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_detailFragment_to_homeFragment)
        }
    }
}