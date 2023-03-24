package com.example.btt2screen.fragmentscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.btt2screen.DataStore
import com.example.btt2screen.R
import com.example.btt2screen.SignUpViewModel
import com.example.btt2screen.databinding.FragmentSignUpBinding

class FragmentSignUp : Fragment() {
    private lateinit var viewModel: SignUpViewModel
    private lateinit var  binding: FragmentSignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        binding.viewModel = viewModel

        binding.textlogin.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.fragmentLogin)
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
                DataStore.userDataStore.add(
                    mutableMapOf("fullName" to binding.inputFullname.text.toString(),
                        "email" to binding.inputEmail.text.toString(),
                        "password" to binding.inputPassword1.text.toString())
                )
                Toast.makeText(activity, "SignUp Success", Toast.LENGTH_SHORT).show()
            }
        }
    }
}