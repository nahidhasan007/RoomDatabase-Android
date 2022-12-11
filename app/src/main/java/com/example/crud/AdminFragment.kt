package com.example.crud

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.crud.admin.AdminViewModel
import com.example.crud.admin.Superadmin

class AdminFragment : Fragment() {
    lateinit var adminViewModel: AdminViewModel
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_admin, container, false)
        adminViewModel = ViewModelProvider(this)[AdminViewModel::class.java]
        var check = 0
        adminViewModel.readAdmin.observe(viewLifecycleOwner){
            check+=it.size
        }
        /*val admin = Superadmin(0,"Admin","Admin")
        if(check<=2){
            adminViewModel.addAdmin(admin)
        }
        ?
         */
        val loginBtn = view.findViewById<Button>(R.id.LoginBtn)
        val username = view.findViewById<EditText>(R.id.userName)
        val password = view.findViewById<EditText>(R.id.password)
        loginBtn.setOnClickListener()
        {
            val uname = username.text.toString()
            val pass = password.text.toString()
            if(uname=="Admin" && pass=="Admin")
            {
                findNavController().navigate(R.id.action_adminFragment_to_userListFragment)
            }
            else
            {
                Toast.makeText(view.context, "Wrong Username or Password!", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

}