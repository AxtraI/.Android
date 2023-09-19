package com.example.myapplication.Fragment

import android.os.Bundle
import android.renderscript.Sampler.Value
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProfileBinding
import com.example.myapplication.modle.Users
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment() {
    val binding by lazy{
        FragmentProfileBinding.inflate(layoutInflater)
    }
    var isExpand=true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.imageButton.setOnClickListener{if (isExpand)
        {
            binding.expandable.visibility=View.VISIBLE
            binding.imageButton.setImageResource(R.drawable.arrowup)
        }
        else{
            binding.expandable.visibility=View.GONE
            binding.imageButton.setImageResource(R.drawable.downarrow)

        }
            isExpand=! isExpand
        }

            /*Firebase.database.reference.child("Users").child(Firebase.auth.currentUser!!.uid).addListenerForSingleValueEvent(
            object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                  for (datasnap in snapshot.children){
                      var user=datasnap.getValue<Users>(Users::class.java)
                      binding.Profilename.text=user?.name
                      binding.password.text=user?.password
                      binding.Email.text=user?.email

                  }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            }
        )*/

        return binding.root
    }

    companion object {

    }
}