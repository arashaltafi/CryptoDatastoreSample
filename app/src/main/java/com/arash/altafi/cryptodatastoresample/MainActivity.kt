package com.arash.altafi.cryptodatastoresample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.arash.altafi.cryptodatastoresample.model.User
import com.arash.altafi.cryptodatastoresample.viewmodel.ActivityViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val activityViewModel: ActivityViewModel by viewModels()
    private lateinit var btnGet: MaterialButton
    private lateinit var btnSave: MaterialButton
    private lateinit var tvNameGet: TextInputEditText
    private lateinit var tvPhoneGet: TextInputEditText
    private lateinit var tvAddressGet: TextInputEditText
    private lateinit var tvNameSave: TextInputEditText
    private lateinit var tvPhoneSave: TextInputEditText
    private lateinit var tvAddressSave: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()
        init()
    }

    private fun bindViews() {
        btnGet = findViewById(R.id.btnGet)
        tvNameGet = findViewById(R.id.tvNameGet)
        tvPhoneGet = findViewById(R.id.tvPhoneGet)
        tvAddressGet = findViewById(R.id.tvAddressGet)
        btnSave = findViewById(R.id.btnSave)
        tvNameSave = findViewById(R.id.tvNameSave)
        tvPhoneSave = findViewById(R.id.tvPhoneSave)
        tvAddressSave = findViewById(R.id.tvAddressSave)
    }

    private fun init() {
        btnGet.setOnClickListener {
            activityViewModel.retrieveData()
        }
        btnSave.setOnClickListener {
            if (
                tvNameSave.text.toString().isEmpty() ||
                tvAddressSave.text.toString().isEmpty() ||
                tvPhoneSave.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Please Fill Inputs", Toast.LENGTH_SHORT).show()
            } else {
                activityViewModel.user = User(
                    username = tvNameSave.text.toString(),
                    phone = tvPhoneSave.text.toString().toLong(),
                    address = tvAddressSave.text.toString()
                )
                activityViewModel.saveData()
                Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show()
            }
        }
        activityViewModel.appSettings.observe(this@MainActivity) {
            tvNameSave.text?.clear()
            tvPhoneSave.text?.clear()
            tvAddressSave.text?.clear()
            tvNameSave.clearFocus()
            tvPhoneSave.clearFocus()
            tvAddressSave.clearFocus()
            tvNameGet.setText(it.user.username)
            tvPhoneGet.setText(it.user.phone.toString())
            tvAddressGet.setText(it.user.address)
        }
    }

}