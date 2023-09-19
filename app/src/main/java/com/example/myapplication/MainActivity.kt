package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.Fragment.HomeFragment
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.modle.Users
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.signUp.setOnClickListener{
            if (binding.name.text.toString().equals("") ||
                binding.email.text.toString().equals("") ||
                binding.password.text.toString().equals("")
                ){
                Toast.makeText(this,"Заполните все поля",Toast.LENGTH_SHORT).show()
        }else{
                Firebase.auth.createUserWithEmailAndPassword(binding.email.text.toString(),binding.password.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                            var user=Users(binding.name.text.toString(),
                                binding.email.text.toString(),
                                binding.password.text.toString()

                                )
                        Firebase.database.reference.child("Users").child(Firebase.auth.currentUser!!.uid).setValue(user).addOnSuccessListener {
                            startActivity(Intent(this,HomeActivity::class.java))
                            finish()

                        }



                    } else{
                        Toast.makeText(this,it.exception?.localizedMessage,Toast.LENGTH_SHORT)
                    }                   }
                }
        }



    }

    override fun onStart() {
        super.onStart()
        if (Firebase.auth.currentUser!=null){

            startActivity(Intent(this,HomeActivity::class.java))
                finish()

        }
    }

}