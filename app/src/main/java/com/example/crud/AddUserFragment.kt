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
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crud.data.User
import com.example.crud.data.UserViewModel

class AddUserFragment : Fragment() {
    lateinit var userViewModel: UserViewModel
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_user, container, false)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        val name = view.findViewById<EditText>(R.id.Name)
        val phone = view.findViewById<EditText>(R.id.Phone)
        val address = view.findViewById<EditText>(R.id.Address)
        val user_data = arguments?.getParcelable<User>("UserInfo")
        val addbtn = view.findViewById<Button>(R.id.addbutton)
        val deletebtn = view.findViewById<Button>(R.id.deleteButton)
        deletebtn.visibility = View.GONE
        if (user_data!=null) {
                name.setText(user_data.name)
                phone.setText(user_data.phone)
                address.setText(user_data.address)
                addbtn.setText("Update")
                deletebtn.visibility = View.VISIBLE
                deletebtn.setOnClickListener(){
                    val uid = user_data.id
                    val uName = name.text.toString()
                    val uPhone = phone.text.toString()
                    val uAddress = address.text.toString()
                    val user = User(uid, uName, uPhone, uAddress)
                    userViewModel.userDelete(user)
                    findNavController().navigate(R.id.action_addUserFragment2_to_userListFragment)
                }
                addbtn.setOnClickListener() {
                    val uid = user_data.id
                    val uName = name.text.toString()
                    val uPhone = phone.text.toString()
                    val uAddress = address.text.toString()
                    val newUser = User(uid, uName, uPhone, uAddress)
                    userViewModel.userUpdate(newUser)
                    findNavController().navigate(R.id.action_addUserFragment2_to_userListFragment)
                }
            }
        else {
            addbtn.setOnClickListener() {
                val Name = name.text.toString()
                val Phone = phone.text.toString()
                val Address = address.text.toString()
                val user = User(0, Name, Phone, Address)
                // val recyclerView = view.findViewById<RecyclerView>(R.id.UserInfo)
                userViewModel.addUser(user)
                //val adapter = Adapter()
                //val mLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                //recyclerView.layoutManager = mLayoutManager
                //adapter.setData(listOf(user))
                findNavController().navigate(R.id.action_addUserFragment2_to_userListFragment)
                //val db = userViewModel.readAllUserData()
                //Log.i(TAG, db.toString())


            }
        }

        return view
    }
}

