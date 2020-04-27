package com.muiezarif.quotesproject.interfaces

import android.view.View
import com.muiezarif.quotesproject.databinding.CustomQuoteItemBinding
import com.muiezarif.quotesproject.db.models.QuotesModel

interface QuoteClickListener {
    fun onClick(view: CustomQuoteItemBinding?, position: Int)
}