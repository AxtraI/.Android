package com.example.myapplication.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.Payment
import com.example.myapplication.R
import com.example.myapplication.adaptor.categoryAdaptor
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.modle.categoryModelClass
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class HomeFragment : Fragment() {

    private val binding:FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    private var categoryList = ArrayList<categoryModelClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryList.add(categoryModelClass(R.drawable.quantum,"quantum"))

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.CoinPayment.setOnClickListener{
            val bottomSheetDialog:BottomSheetDialogFragment=Payment()
            bottomSheetDialog.show(requireActivity().supportFragmentManager,"TEST")
            bottomSheetDialog.enterTransition
        }
        binding.Coin.setOnClickListener{
            val bottomSheetDialog:BottomSheetDialogFragment=Payment()
            bottomSheetDialog.show(requireActivity().supportFragmentManager,"TEST")
            bottomSheetDialog.enterTransition
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoryRecyclerView.layoutManager=GridLayoutManager(requireContext(),2)
        var adapter= categoryAdaptor(categoryList,requireActivity())
        binding.categoryRecyclerView.adapter=adapter
        binding.categoryRecyclerView.setHasFixedSize(true)
    }
    companion object {

    }
}