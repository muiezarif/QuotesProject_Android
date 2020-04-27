package com.muiezarif.quotesproject.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.muiezarif.quotesproject.databinding.CustomQuoteItemBinding
import com.muiezarif.quotesproject.db.models.QuotesModel
import com.muiezarif.quotesproject.interfaces.QuoteClickListener

class MyQuoteViewHolder(var binding: CustomQuoteItemBinding, var quoteClickListener: QuoteClickListener): RecyclerView.ViewHolder(binding.root),
    View.OnClickListener {
    init {
        binding.clQuote.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        quoteClickListener.onClick(binding,adapterPosition)
    }
}