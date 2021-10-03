package com.example.useractivitylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.useractivitylist.databinding.UserItemBinding

class UserAdapter(private val onItemClick: (user: User, position: Int) -> Unit) :
    RecyclerView.Adapter<UserAdapter.ItemViewHolder>() {

    var list = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        binding = UserItemBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = list.size

    inner class ItemViewHolder(
        val binding: UserItemBinding

    ) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var curData: User

        init {
            binding.btnUpdateImgView.setOnClickListener{
                onItemClick.invoke(list[adapterPosition],adapterPosition)
            }
            binding.btnDeleteImgView.setOnClickListener {
                list.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }

        fun bind() {
            curData = list[adapterPosition]
            binding.nameTxtView.text = curData.name
            binding.lastNameTxtView.text = curData.lastName
            binding.mailTxtView.text = curData.mail
        }

    }
}