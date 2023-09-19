package com.example.myapplication.Fragment

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.Payment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSpinBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.Random


class SpinFragment : Fragment() {
    private lateinit var binding: FragmentSpinBinding
    private lateinit var timer: CountDownTimer
    private val itemTitles = arrayOf("100", "Try Again Later", "500", "Try Again Later", "200", "Try Again Later")
    var currentChance=1
    var temp=1
    var currentCoins=650L
    var playerCoins=0L
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSpinBinding.inflate(inflater, container, false)
        binding.Coin.text= currentCoins.toString()
        /*Firebase.database.reference.child("PlayChance").child(Firebase.auth.currentUser!!.uid)
            .addValueEventListener(object :ValueEventListener){
                override fun onDataChange(snapshot: DataSnapshot){
                    if (snapshot.exists()){

                    }
                }
            }*/


        return binding.root
    }

    private fun showResult(itemTitle: String, spin:Int) {
        if (spin%2==0){
            var winCoins=itemTitle.toInt()
            playerCoins+=winCoins
            binding.Coin.text= (winCoins+currentCoins).toString()
        }
        Toast.makeText(requireContext(), itemTitle, Toast.LENGTH_SHORT).show()
        binding.spinChance.text= temp.toString()
        temp=currentChance-1
        binding.spinChance.text= temp.toString()
        if (temp==0){
            binding.Spin.isEnabled=false
        }
        binding.Spin.isEnabled = true

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.CoinPayment.setOnClickListener{
            val bottomSheetDialog: BottomSheetDialogFragment = Payment()
            bottomSheetDialog.show(requireActivity().supportFragmentManager,"TEST")
            bottomSheetDialog.enterTransition
        }
        binding.Coin.setOnClickListener{
            val bottomSheetDialog: BottomSheetDialogFragment = Payment()
            bottomSheetDialog.show(requireActivity().supportFragmentManager,"TEST")
            bottomSheetDialog.enterTransition
        }
        binding.Spin.setOnClickListener {
            binding.Spin.isEnabled = false
            val spin = Random().nextInt(6)
            val degrees = 60f * spin
            timer = object : CountDownTimer(5000, 50) {
                var rotation = 0f
                override fun onTick(millisUntilFinished: Long) {
                    rotation += 5f
                    if (rotation >= degrees) {
                        rotation = degrees
                        timer.cancel()
                        showResult(itemTitles[spin],spin)
                    }
                    binding.ivWheel.rotation=rotation
                }

                override fun onFinish() {}
            }.start()
        }

    }
}






