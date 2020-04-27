package com.muiezarif.quotesproject.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.muiezarif.quotesproject.databinding.CustomCategoryItemBinding
import com.muiezarif.quotesproject.interfaces.CategoryClickListener

class MyCategoryViewHolder(var binding: CustomCategoryItemBinding, var catClickListener: CategoryClickListener): RecyclerView.ViewHolder(binding.root),
    View.OnClickListener {
    init {
        binding.clCatLayout.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        catClickListener.onClick(binding,adapterPosition, binding.tvCatName.text.toString())
    }
}