package com.example.myapplication.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.HistoryitemBinding
import com.example.myapplication.modle.HistoryModelClass

class HistoryAdapter(var ListHistory:ArrayList<HistoryModelClass>):RecyclerView.Adapter<HistoryAdapter.HistoryCoinViewHolder>() {
    class HistoryCoinViewHolder(var binding:HistoryitemBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryCoinViewHolder {
        return HistoryCoinViewHolder(HistoryitemBinding.inflate(LayoutInflater.from(parent.context),parent,false    ))
    }

    override fun getItemCount()= ListHistory.size

    override fun onBindViewHolder(holder: HistoryCoinViewHolder, position: Int) {
        holder.binding.Time.text=ListHistory[position].timeAndDate
        holder.binding.Coins.text=ListHistory[position].coin
    }


}