package com.example.btt2screen.fragmentscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.btt2screen.R
import kotlinx.android.synthetic.main.fragment_well_come.*

class FragmentWellCome : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_well_come, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Signup.setOnClickListener {
            val controller =  findNavController()
            controller.navigate(R.id.action_fragmentWellCome_to_fragmentSignUp)
        }
        textSignIn.setOnClickListener{
            val controller =  findNavController()
            controller.navigate(R.id.action_fragmentWellCome_to_fragmentLogin)
        }
//        s.setOnClickListener{
//            val controller =  findNavController()
//            Toast.makeText(this,"Đăng nhập bằng Facebook", Toast.LENGTH_SHORT).show()
//
//        }
//        f.setOnClickListener{
//            Toast.makeText(this,"Đăng nhập bằng Google", Toast.LENGTH_SHORT).show()
//
//        }
    }
}