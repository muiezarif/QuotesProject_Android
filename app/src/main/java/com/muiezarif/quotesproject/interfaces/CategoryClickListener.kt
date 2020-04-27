package com.muiezarif.quotesproject.interfaces

import android.view.View
import com.muiezarif.quotesproject.databinding.CustomCategoryItemBinding

interface CategoryClickListener {
    fun onClick(view: CustomCategoryItemBinding?, position: Int,catName:String)
}