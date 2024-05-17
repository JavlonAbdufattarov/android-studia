package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val editTextName = findViewById<TextInputEditText>(R.id.editTextName)
        val textViewNameCounter = findViewById<TextView>(R.id.textViewNameCounter)
        val editTextPhone = findViewById<TextInputEditText>(R.id.editTextPhone)
        val checkBox1 = findViewById<CheckBox>(R.id.checkBoxSMS)
        val checkBox2 = findViewById<CheckBox>(R.id.checkBoxEmail)
        val switchMaterial = findViewById<SwitchMaterial>(R.id.switchNotifications)
        val buttonSave  = findViewById<Button>(R.id.buttonSave)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupGender)
        val progressBar = findViewById<ProgressBar>(R.id.progressBarPoints)
        checkBox1.isEnabled = false
        checkBox2.isEnabled = false
        buttonSave.isEnabled =false

        fun updateProgress() {
            val randomProgress = Random.nextInt(101)
            progressBar.progress = randomProgress
        }

        updateProgress()



        buttonSave.setOnClickListener {
            Toast.makeText(this@MainActivity, "Данные сохранены", Toast.LENGTH_SHORT).show()
        }




        fun updateButtonState() {
            val isTextNotEmpty = editTextName.text.toString().trim().isNotEmpty()
            val isRadioButtonChecked = radioGroup.checkedRadioButtonId != -1
            val isTextNotEmptyPhone = editTextPhone.text.toString().trim().isNotEmpty()
            buttonSave.isEnabled = isTextNotEmpty && isRadioButtonChecked && isTextNotEmptyPhone
        }


        switchMaterial.setOnCheckedChangeListener { _, isChecked ->
            // Включаем или выключаем чекбоксы в зависимости от состояния переключателя
            checkBox1.isEnabled = isChecked
            checkBox2.isEnabled = isChecked

            // Автоматически выбираем первый чекбокс, если переключатель активирован
            if (isChecked) {
                checkBox1.isChecked = true
            } else {
                // При деактивации переключателя также сбрасываем состояния чекбоксов
                checkBox1.isChecked = false
                checkBox2.isChecked = false
            }
        }

        editTextName.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateButtonState()
            }

            override fun afterTextChanged(s: android.text.Editable?) {
                if (s != null) {
                    if (s.isEmpty()) {
                        editTextName.error = "Поле не может быть пустым"
                    } else if (s.length > 40) {
                        editTextName.error = "Максимальная длина имени 40 символов"
                    } else {
                        editTextName.error = null // Очистить ошибку, если все в порядке
                    }
                }
            }
        })

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            updateButtonState()
        }

        editTextName.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Ничего не делаем
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Ничего не делаем
            }

            override fun afterTextChanged(s: android.text.Editable?) {
                val textLength = s?.length ?: 0
                val maxLength = 40
                textViewNameCounter.text = "$textLength/$maxLength"
            }
        })

        editTextPhone.addTextChangedListener(object : android.text.TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateButtonState()
            }

            override fun afterTextChanged(s: android.text.Editable?) {
                if (s != null) {
                    if (s.isEmpty()) {
                        editTextPhone.error = "Поле не может быть пустым"
                    } else if (s.length > 40) {
                        editTextPhone.error = "Максимальная длина имени 40 символов"
                    } else {
                        editTextPhone.error = null // Очистить ошибку, если все в порядке
                    }
                }
            }
        })






    }




}