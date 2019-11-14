

package com.example.android.marsrealestate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.marsrealestate.network.MarsApi
import com.example.android.marsrealestate.network.MarsProperty
import okhttp3.Call
import okhttp3.Response
import javax.security.auth.callback.Callback

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the request status String
    val response: LiveData<String>
        get() = _response

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties()
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getMarsRealEstateProperties() {
        // TODO (05) Call the MarsApi to enqueue the Retrofit request, implementing the callbacks
        MarsApi.retrofitService.getProperties().enqueue( object: retrofit2.Callback<List<MarsProperty>> {
            override fun onFailure(call: retrofit2.Call<List<MarsProperty>>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

            override fun onResponse(call: retrofit2.Call<List<MarsProperty>>, response: retrofit2.Response<List<MarsProperty>>) {
                _response.value ="Success: ${response.body()?.size} Mars properties retrieved"
            }
        })
    }


    }

