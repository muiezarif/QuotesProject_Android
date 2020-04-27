package com.muiezarif.quotesproject.interfaces

import android.view.View
import com.muiezarif.quotesproject.databinding.CustomAuthorItemBinding

interface AuthorClickListener {
    fun onClick(view: CustomAuthorItemBinding?, position: Int,authorName:String)
}