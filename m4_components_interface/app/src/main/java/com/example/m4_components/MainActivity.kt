package com.example.m4_components

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import com.example.m4_components.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val progress = kotlin.random.Random.nextInt(101)
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() {
            return _binding!!
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //назначаем события на элементы интерфейса
        startProgram();
    }


    private fun startProgram() {
        checkButtonSave()
        checkNotification(false)
        binding.progressScores.progress = progress
        binding.countScores.text = progress.toString() + resources.getString(R.string.count_score)
        changeEditNameText()
        changeEditPhoneText()
        changeChoiceNotifications()
        changeRadioButton()
        changeNotificationAuthorization()
    }

    private fun changeEditNameText() {
        binding.editNameText.doOnTextChanged { text, _, _, _ ->
            if (text != null) {
                binding.textInputNameLayout.isErrorEnabled = text.length > 40
            }
            checkButtonSave()
        }
    }

    private fun changeNotificationAuthorization() {
        binding.notificationAuthorization.setOnCheckedChangeListener { _, _ ->
            checkButtonSave()
        }

        binding.notificationNewProducts.setOnCheckedChangeListener { _, _ ->
            checkButtonSave()
        }
    }

    private fun changeRadioButton() {
        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            checkButtonSave()
        }
    }

    private fun changeEditPhoneText() {
        binding.editPhoneText.doOnTextChanged{ _, _, _, _ ->
            checkButtonSave()
        }
    }

    private fun changeChoiceNotifications() {
        binding.enableNotifications.setOnCheckedChangeListener { _, isChecked ->
            checkNotification(isChecked)
            checkButtonSave()
        }
    }

    private fun checkNotification(isChecked: Boolean) {
        binding.notificationAuthorization.isEnabled = isChecked
        binding.notificationNewProducts.isEnabled = isChecked
        if (!isChecked) {
            binding.notificationAuthorization.isChecked = false
            binding.notificationNewProducts.isChecked = false
        }
        checkButtonSave()
    }

    private fun checkInputName(): Boolean {
        return !(binding.textInputNameLayout.isErrorEnabled || binding.editNameText.text.isNullOrEmpty())
    }

    private fun checkRadioButton(): Boolean {
        if (binding.radioButtonMan.isChecked || binding.radioButtonWoman.isChecked) return true
        return false
    }

    private fun checkNotifications(): Boolean {
        if (binding.enableNotifications.isChecked && binding.notificationAuthorization.isChecked
            || binding.enableNotifications.isChecked && binding.notificationNewProducts.isChecked) {
            return true
        } else if (!binding.enableNotifications.isChecked) {
            return true
        }
        return false
    }

    private fun checkButtonSave() {
        binding.saveButton.isEnabled =
            checkInputName() && !binding.editPhoneText.text.isNullOrEmpty() && checkRadioButton() && checkNotifications()
        if (binding.saveButton.isEnabled) pressButtonSave()
    }

    private fun pressButtonSave() {
        binding.saveButton.setOnClickListener {
            Toast.makeText(this, resources.getText(R.string.toast_save_text), Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}