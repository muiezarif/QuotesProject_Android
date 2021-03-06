package com.muiezarif.quotesproject.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.muiezarif.quotesproject.R
import com.muiezarif.quotesproject.activities.FullQuoteViewActivity
import com.muiezarif.quotesproject.adapters.QuoteRecyclerAdapter
import com.muiezarif.quotesproject.databinding.CustomQuoteItemBinding
import com.muiezarif.quotesproject.databinding.FragmentQuotesBinding
import com.muiezarif.quotesproject.db.DbQueries
import com.muiezarif.quotesproject.db.models.QuotesModel
import com.muiezarif.quotesproject.interfaces.QuoteClickListener
import com.muiezarif.quotesproject.utils.AdHelper

/**
 * A simple [Fragment] subclass.
 */
class QuotesFragment : Fragment(), QuoteClickListener {
    private lateinit var binding:FragmentQuotesBinding
    private lateinit var dbQuery: DbQueries
    private lateinit var quoteList:ArrayList<QuotesModel>
    private var adapter:QuoteRecyclerAdapter?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.fragment_quotes, container, false)
        AdHelper.loadBannerAd(binding.adView)
        init()
        return binding.root
    }
    private fun init(){
        dbQuery= DbQueries(context)
        quoteList=dbQuery.getAllQuotes()
        quoteList.shuffle()
        adapter= context?.let { QuoteRecyclerAdapter(it,quoteList,this) }
        binding.rvQuotes.adapter=adapter
        binding.etSearchQuotes.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(e: Editable?) {
                filter(e.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
    }
    private fun filter(s:String){
        var filteredList:ArrayList<QuotesModel> = ArrayList()
        quoteList.forEach {
            if (it.quote.toLowerCase().contains(s.toLowerCase()) || it.category.toLowerCase().contains(s.toLowerCase()) || it.author.toLowerCase().contains(s.toLowerCase())) {
                filteredList.add(it)
            }
        }
        adapter?.filterList(filteredList)
    }

    override fun onClick(view: CustomQuoteItemBinding?, position: Int) {
        var intent=Intent(context,FullQuoteViewActivity::class.java)
        intent.putExtra("quotePos",position)
        intent.putExtra("list",adapter?.getList())
        startActivity(intent)
        Log.i("TESTING",""+adapter?.getList()?.size)
    }



}
