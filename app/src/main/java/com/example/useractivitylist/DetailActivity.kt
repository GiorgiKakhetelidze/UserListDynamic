package com.example.useractivitylist

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.useractivitylist.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        setListeners()
    }

    private fun init(){
        val data = intent.getParcelableExtra<User>(UsersActivity.USER_KEY)
        binding.nameEditTxt.setText(data?.name)
        binding.lastNameEditTxt.setText(data?.lastName)
        binding.mailEditTxtView.setText(data?.mail)
    }

    private fun setListeners(){
        binding.btnSave.setOnClickListener{
            saveAndGoToProfile()
        }
    }

    private fun saveAndGoToProfile() {
        val user = User(
            name = binding.nameEditTxt.text.toString(),
            lastName = binding.lastNameEditTxt.text.toString(),
            mail = binding.mailEditTxtView.text.toString()
        )
        val resultIntent = Intent()
        resultIntent.putExtra(KEY_EDITED_USER, user)
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    companion object{
        const val KEY_EDITED_USER = "EDITED_USER"
    }

}