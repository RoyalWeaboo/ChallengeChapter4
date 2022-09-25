package com.malikazizali.challengechapter4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.malikazizali.challengechapter4.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    lateinit var binding : FragmentRegisterBinding
    lateinit var sp : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sp = requireContext().getSharedPreferences("login_ch_4", Context.MODE_PRIVATE)

        binding.btnReg.setOnClickListener {
            registerUser(view)
        }

        binding.tvLogin.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    fun registerUser(view : View){
        val inputUsername = binding.etRegUsername.text.toString()
        val inputEmail = binding.etRegEmail.text.toString()
        val inputPass = binding.etRegPassword.text.toString()
        val inputConPass = binding.etRegConPassword.text.toString()

        if(inputPass == inputConPass){
            val reg = sp.edit()
            reg.putString("username", inputUsername)
            reg.putString("email", inputEmail)
            reg.putString("pass", inputPass)
            reg.putString("status", "notloggedin")
            reg.apply()
            Toast.makeText(context, "Registration Succeed", Toast.LENGTH_LONG).show()
            Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
        }
        else if(inputPass!=inputConPass){
            Toast.makeText(context, "Password do not match", Toast.LENGTH_LONG).show()
        }

    }



}