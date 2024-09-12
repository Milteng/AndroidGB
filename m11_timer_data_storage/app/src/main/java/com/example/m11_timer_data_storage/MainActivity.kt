package com.example.m11_timer_data_storage

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.m11_timer_data_storage.databinding.ActivityMainBinding


private const val PREFERENCE_NAME = "prf_name";
private const val KEY_VAL_NAME = "val_name";

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var prefs: SharedPreferences;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository(this)
        val editText = binding.editText
        binding.textView.text = repository.getText()

        binding.saveButton.setOnClickListener {
            repository.saveText(editText.text.toString())
            binding.textView.text = repository.getText()
        }
        binding.resetButton.setOnClickListener {
            repository.clearText()
            binding.textView.text = repository.getText()
        }




        /*prefs = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)

        val editor : SharedPreferences.Editor = prefs.edit()

        editor.putString(KEY_VAL_NAME, "test_string")
        //editor.remove(KEY_VAL_NAME)

        //val isSuccess = editor.commit() //синхронное

        editor.apply() // асинхронное

        //считываем

        prefs.getString(KEY_VAL_NAME,"")

        //проверить есть ли ключ
        prefs.contains(KEY_VAL_NAME)*/


    }
}