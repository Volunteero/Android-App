package com.alexprodrom.volunteero.data.remote.event

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.alexprodrom.volunteero.model.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Response

class EventRepository {

    private val eventApi: EventApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://volunteero-events.herokuapp.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        eventApi = retrofit.create(EventApi::class.java)
    }

    fun getEvents(): LiveData<List<Event>> {
        val data = MutableLiveData<List<Event>>()
        eventApi.getEvents().enqueue(object : Callback<List<Event>> {
            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                data.setValue(response.body())
            }

            override fun onFailure(call: Call<List<Event>>?, t: Throwable?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        return data
    }
}