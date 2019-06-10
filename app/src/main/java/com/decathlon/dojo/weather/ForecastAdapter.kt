package com.decathlon.dojo.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.decathlon.dojo.R
import com.decathlon.dojo.databinding.ForecastListItemBinding
import com.decathlon.dojo.data.model.DailyForecast

class ForecastAdapter(private var dailyForecastList: List<DailyForecast>) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = DataBindingUtil.inflate<ForecastListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.forecast_list_item,
            parent,
            false
        )
        return ForecastViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dailyForecastList.size
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(dailyForecastList[position])
    }

    inner class ForecastViewHolder(private val itemBinding: ForecastListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(forecast: DailyForecast) {
            itemBinding.forecast = forecast
            itemBinding.executePendingBindings()
        }
    }


    fun replaceData(items: List<DailyForecast>?) {
        if (items != null) {
            this.dailyForecastList = items
            notifyDataSetChanged()
        }
    }
}