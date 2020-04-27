package com.muiezarif.quotesproject.adapters

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.muiezarif.quotesproject.R
import com.muiezarif.quotesproject.adapters.viewholders.MyQuoteViewHolder
import com.muiezarif.quotesproject.databinding.CustomQuoteItemBinding
import com.muiezarif.quotesproject.db.models.QuotesModel
import com.muiezarif.quotesproject.interfaces.QuoteClickListener
import java.util.*


class QuoteRecyclerAdapter(var context: Context,var quoteList:ArrayList<QuotesModel>,var quoteClickListener: QuoteClickListener): RecyclerView.Adapter<MyQuoteViewHolder>() {
    private var colors:Array<Int> = arrayOf(R.color.colorCombo1,R.color.colorCombo2,R.color.colorCombo3,R.color.colorCombo4,R.color.colorCombo5,R.color.colorCombo6,R.color.colorCombo7,R.color.colorCombo8,R.color.colorCombo9,R.color.colorCombo10)
    val min = 0
    val max = 9

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyQuoteViewHolder {
        var binding:CustomQuoteItemBinding= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_quote_item,parent,false)
        return MyQuoteViewHolder(binding,quoteClickListener)
    }

    override fun getItemCount(): Int {
        return quoteList.size
    }

    override fun onBindViewHolder(holder: MyQuoteViewHolder, position: Int) {
        val model= quoteList[position]
        holder.binding.tvQuote.text=model.quote
        holder.binding.tvAuthor.text=model.author
        holder.binding.tvCategory.text=model.category
        val random = Random().nextInt(max - min + 1) + min
        Log.i("COLOR",colors[random].toString())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.binding.cvQuoteItem.setCardBackgroundColor(context.getColor(colors[random]))
        }
    }

    fun filterList(filteredList:ArrayList<QuotesModel>){
        quoteList=filteredList
        notifyDataSetChanged()
    }
    fun getList():ArrayList<QuotesModel>{
        return quoteList
    }
}