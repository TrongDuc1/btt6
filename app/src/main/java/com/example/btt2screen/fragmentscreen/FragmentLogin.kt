package com.example.btt2screen.fragmentscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.btt2screen.LoginInViewModel
import com.example.btt2screen.MainActivity
import com.example.btt2screen.R
import com.example.btt2screen.databinding.FragmentLoginBinding

class FragmentLogin : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginInViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.textsignup.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.fragmentSignUp)
        }
        binding.back.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.fragmentSignUp)
        }

        listenerSuccessEvent()
        listenerErrorEvent()
    }
    private fun listenerErrorEvent() {
        viewModel.isErrorEvent.observe(viewLifecycleOwner) { errMsg ->
            Toast.makeText(activity, errMsg, Toast.LENGTH_SHORT).show()
        }
    }

    private fun listenerSuccessEvent() {
        viewModel.isSuccessEvent.observe(viewLifecycleOwner) { isSuccess ->
            if(isSuccess)
            {
                // Success
                Toast.makeText(activity, "LogIn Success", Toast.LENGTH_SHORT).show()
                val controller = findNavController()
                controller.navigate(R.id.action_fragmentLogin_to_tabView2)
            }
        }
    }

}
