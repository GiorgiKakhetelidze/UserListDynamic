package com.example.useractivitylist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.useractivitylist.databinding.ActivityUserBinding

class UsersActivity : AppCompatActivity() {

    private var position = -1
    private lateinit var binding: ActivityUserBinding
    private val adapter = UserAdapter { user, position->
        navigateToDetailsScreenForResult(user)
        this.position = position
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecycler()
    }

    private fun navigateToDetailsScreenForResult(user: User) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(USER_KEY, user)
        }
        resultLauncher.launch(intent)
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val user = result.data?.getParcelableExtra<User>(DetailActivity.KEY_EDITED_USER)
                setDataFromResult(user)
            }
        }

    private fun setDataFromResult(user: User?) {
        adapter.list[position] = user!!
        adapter.notifyItemChanged(position)
    }

    private fun setRecycler() {
        binding.recycleView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycleView.adapter = adapter

        adapter.list = mutableListOf(
            User("Giorgi", "Kakhetelidze", "kakhetelidzegio@gmail.com"),
            User("Giorgi", "Kakhetelidze", "kakhetelidzegio@gmail.com"),
            User("Giorgi", "Kakhetelidze", "kakhetelidzegio@gmail.com"),
            User("Giorgi", "Kakhetelidze", "kakhetelidzegio@gmail.com"),
            User("Giorgi", "Kakhetelidze", "kakhetelidzegio@gmail.com"),
            User("Giorgi", "Kakhetelidze", "kakhetelidzegio@gmail.com")
        )
    }

    companion object {
        const val USER_KEY = "USER"
    }
}