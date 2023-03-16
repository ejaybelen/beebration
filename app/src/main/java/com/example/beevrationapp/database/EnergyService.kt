package com.example.beevrationapp.database

import android.telecom.Call
import retrofit2.http.GET

interface EnergyService {
    @GET("energy")
    fun getEnergyData(): Call<EnergyData>
}