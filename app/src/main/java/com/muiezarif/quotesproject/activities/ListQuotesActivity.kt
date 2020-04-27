package com.muiezarif.quotesproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.muiezarif.quotesproject.R
import com.muiezarif.quotesproject.adapters.QuoteRecyclerAdapter
import com.muiezarif.quotesproject.databinding.ActivityListQuotesBinding
import com.muiezarif.quotesproject.databinding.CustomQuoteItemBinding
import com.muiezarif.quotesproject.db.DbQueries
import com.muiezarif.quotesproject.db.models.QuotesModel
import com.muiezarif.quotesproject.interfaces.QuoteClickListener
import com.muiezarif.quotesproject.utils.AdHelper

class ListQuotesActivity : AppCompatActivity(), QuoteClickListener {
    private lateinit var binding:ActivityListQuotesBinding
    private lateinit var dbQuery: DbQueries
    private lateinit var quoteList:ArrayList<QuotesModel>
    private var adapter:QuoteRecyclerAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_list_quotes)
        AdHelper.loadBannerAd(binding.adView)
        AdHelper.loadInterstitialAd(this,getString(R.string.admob_full_ad))
        binding.quoteListHeader.ivBack.visibility= View.VISIBLE
        binding.quoteListHeader.ivBack.setOnClickListener {
            finish()
        }
        if (intent.hasExtra("from")){
            if (intent.extras?.getString("from").equals("category")){
                var catName=intent.extras?.get("catName")
                binding.quoteListHeader.tvHomeTitle.text=catName.toString()
                dbQuery= DbQueries(this)
                quoteList=dbQuery.getQuotesByCategory(catName as String)
                quoteList.shuffle()
                adapter= QuoteRecyclerAdapter(this,quoteList,this)
                binding.rvQuotesList.adapter=adapter
            }else if(intent.extras?.getString("from").equals("author")){
                var authorName=intent.extras?.get("authorName")
                binding.quoteListHeader.tvHomeTitle.text=authorName.toString()
                dbQuery= DbQueries(this)
                quoteList=dbQuery.getQuotesByAuthor(authorName as String)
                quoteList.shuffle()
                adapter= QuoteRecyclerAdapter(this,quoteList,this)
                binding.rvQuotesList.adapter=adapter
            }
        }
    }

    override fun onClick(view: CustomQuoteItemBinding?, position: Int) {
        var intent= Intent(this,FullQuoteViewActivity::class.java)
        intent.putExtra("quotePos",position)
        intent.putExtra("list",adapter?.getList())
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        AdHelper.showInterstitialAd()
    }

    override fun onStop() {
        super.onStop()
        AdHelper.reloadInterstitialAd()
    }
}
