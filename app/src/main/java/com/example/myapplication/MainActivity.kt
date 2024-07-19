package com.example.myapplication

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun test(view: View) {
        binding.textView.text = "bbb"
        login("","");
    }

    /**
     * 测试 携程
     */
    fun login(username: String, token: String) {
        // Create a new coroutine to move the execution off the UI thread
        println("login start Thread :"+Thread.currentThread().name)
        GlobalScope.launch(Dispatchers.Main) {
            val jsonBody = "{ username: \"$username\", token: \"$token\"}"
            println("GlobalScope Thread :"+Thread.currentThread().name)
            val result = makeLoginRequest(jsonBody)
            println("result: "+result+" Thread: "+Thread.currentThread().name)
        }
        println("login end Thread :"+Thread.currentThread().name)
    }

    suspend fun makeLoginRequest(
        jsonBody: String
    ):String {
        // Move the execution of the coroutine to the I/O dispatcher
        return withContext(Dispatchers.IO) {
            println("withContext Thread :"+Thread.currentThread().name)
            // Blocking network request code
            "结果";
        }
    }
}