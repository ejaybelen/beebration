package com.example.beevrationapp.database

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import com.example.beevration.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class EnergyData(val energy: Int) {
    private lateinit var energyService: EnergyService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                //hindi ko alam ilalagay dito, siguro manggagaling to sa prototype?
            .baseUrl("http://<IP_ADDRESS>:<PORT>/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        energyService = retrofit.create(EnergyService::class.java)

        refreshButton.setOnClickListener {
            getEnergyData()
        }
    }

    private fun getEnergyData() {
        val call = energyService.getEnergyData()
        call.enqueue(object : Callback<EnergyData> {
            override fun onResponse(call: Call<EnergyData>, response: Response<EnergyData>) {
                if (response.isSuccessful) {
                    val energyData = response.body()
                    energyTextView.text = energyData?.energy.toString()
                }
            }

            override fun onFailure(call: Call<EnergyData>, t: Throwable) {
                Toast.makeText(applicationContext, "Error fetching energy data", Toast.LENGTH_SHORT).show()
            }
        })
    }

}