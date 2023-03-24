package com.example.btt2screen.fragmentscreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.btt2screen.ProfileViewModel
import com.example.btt2screen.R
import com.example.btt2screen.databinding.FragmentProfileBinding
import kotlinx.android.synthetic.main.fragment_profile.*


class FragmentProfile : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.backsu.setOnClickListener {
           val controller = findNavController()
            controller.navigate(R.id.fragmentLogin)
        }
        binding.signout.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.fragmentWellCome)
        }
        val editText = view.findViewById<TextView>(R.id.full_name)
        val textView = view.findViewById<TextView>(R.id.textV5)
        val editText1 = view.findViewById<TextView>(R.id.email)

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                i: Int, i1: Int, i2: Int
            ) {
                //Gọi trước khi text thay đổi
            }
            override fun onTextChanged(
                charSequence: CharSequence,
                i: Int, i1: Int, i2: Int
            ) {
                //Gọi khi thay đổi
            }
            override fun afterTextChanged(editable: Editable) {
                //Gọi sau khi thay đổi
                textView.text = editText.text.toString()
            }
        })
        showEditTextDialog()
        listenerSuccessEvent()
        listenerErrorEvent()
    }
    private fun showEditTextDialog(){
        full_name.setOnClickListener {
            val builder = activity?.let { it1 -> AlertDialog.Builder(it1) }
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.dialog_edit,null)
            val editText = dialogLayout.findViewById<EditText>(R.id.et_editText)

            with(builder) {
                this?.setPositiveButton("Ok"){ dialog, which -> full_name.text = editText.text}
                this?.setNegativeButton("Cancel"){ dialog, which -> Log.d( "Main", "Negative button clicked")}
                this?.setView(dialogLayout)
                this?.show()
            }
        }
        binding.email.setOnClickListener {
            val builder = activity?.let { it1 -> AlertDialog.Builder(it1) }
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.dialog_edit,null)
            val editText = dialogLayout.findViewById<EditText>(R.id.et_editText)

            with(builder) {
                this?.setPositiveButton("Ok"){ dialog, which -> email.text = editText.text}
                this?.setNegativeButton("Cancel"){ dialog, which -> Log.d( "Main", "Negative button clicked")}
                this?.setView(dialogLayout)
                this?.show()
            }
        }
        binding.phoneNumber.setOnClickListener {
            val builder = activity?.let { it1 -> AlertDialog.Builder(it1) }
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.dialog_edit,null)
            val editText = dialogLayout.findViewById<EditText>(R.id.et_editText)

            with(builder) {
                this?.setPositiveButton("Ok"){ dialog, which -> phone_number.text = editText.text}
                this?.setNegativeButton("Cancel"){ dialog, which -> Log.d( "Main", "Negative button clicked")}
                this?.setView(dialogLayout)
                this?.show()
            }
        }
    }
    private fun listenerErrorEvent() {
        viewModel.isErrorEvent.observe(viewLifecycleOwner) { errMsg ->
            Toast.makeText(activity, errMsg, Toast.LENGTH_SHORT).show()
        }
    }

    private fun listenerSuccessEvent() {
        viewModel.isSuccessEvent.observe(viewLifecycleOwner) { successUserKey ->
            (successUserKey)
        }
    }
}