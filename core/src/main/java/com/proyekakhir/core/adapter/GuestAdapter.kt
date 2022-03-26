package com.proyekakhir.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.proyekakhir.core.R
import com.proyekakhir.core.data.source.local.UserEntity
import com.proyekakhir.core.databinding.GuestItemBinding
import com.proyekakhir.core.domain.model.Events

class GuestAdapter : RecyclerView.Adapter<GuestAdapter.ViewHolder>() {
    private var listUser = ArrayList<UserEntity>()
    private lateinit var guestCallback: GuestCallback

    fun setData(listEvent: List<UserEntity>) {
        this.listUser.clear()
        this.listUser.addAll(listEvent)
    }

    fun setListener(guestCallback: GuestCallback){
        this.guestCallback = guestCallback
    }


    class ViewHolder(binding: GuestItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val bin = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = GuestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user= listUser[position]
        with(holder.bin){
            tvName.text = user.fullName
            root.setOnClickListener {
                guestCallback.onClick(user)
            }
            Glide.with(root.context)
                .load(user.avatar)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(profileImage)
        }
    }

    override fun getItemCount(): Int = listUser.size

    interface GuestCallback {
        fun onClick(guest: UserEntity)
    }
}