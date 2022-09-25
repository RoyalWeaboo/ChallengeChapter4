package com.malikazizali.challengechapter4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.malikazizali.challengechapter4.databinding.FragmentRegisterBinding
import com.malikazizali.challengechapter4.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {
    lateinit var binding : FragmentSplashScreenBinding
    lateinit var sp : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sp = requireContext().getSharedPreferences("login_ch_4", Context.MODE_PRIVATE)
        val status = sp.getString("status","notloggedin")

        Handler().postDelayed({
            if(status=="notloggedin")
                Navigation.findNavController(view).navigate(R.id.action_splashScreenFragment_to_loginFragment)
            else if(status=="loggedin")
                Navigation.findNavController(view).navigate(R.id.action_splashScreenFragment_to_homeFragment)
        }, 3000)
    }

}