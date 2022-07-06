package pl.kaemo.recipefinder

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var counter = 0
    var greeting = MutableLiveData<String>("Hello app")

    fun onButtonClicked(){
        counter++
        greeting.value = "Button was clicked $counter times"
        Log.d("TAG", "Counter is $counter")
    }
}