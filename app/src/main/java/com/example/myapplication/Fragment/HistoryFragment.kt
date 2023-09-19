package com.example.myapplication.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Payment
import com.example.myapplication.R
import com.example.myapplication.adaptor.HistoryAdapter
import com.example.myapplication.databinding.FragmentHistoryBinding
import com.example.myapplication.modle.HistoryModelClass
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class HistoryFragment : Fragment() {
    val binding by lazy{
        FragmentHistoryBinding.inflate(layoutInflater)
    }
private  var ListHistory=ArrayList<HistoryModelClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ListHistory.add(HistoryModelClass("12:00","50"))
        ListHistory.add(HistoryModelClass("18:00","50"))
        ListHistory.add(HistoryModelClass("00:00","50"))
        ListHistory.add(HistoryModelClass("06:00","500"))


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        binding.HistoryRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        var adapter=HistoryAdapter(ListHistory)
        binding.HistoryRecyclerView.adapter=adapter
        binding.HistoryRecyclerView.setHasFixedSize(true)
        return binding.root
    }

    companion object{

    }
}