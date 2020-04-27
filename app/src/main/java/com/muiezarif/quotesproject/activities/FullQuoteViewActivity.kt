package com.muiezarif.quotesproject.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.muiezarif.quotesproject.R
import com.muiezarif.quotesproject.adapters.QuotesVPAdapter
import com.muiezarif.quotesproject.databinding.ActivityFullQuoteViewBinding
import com.muiezarif.quotesproject.db.DbQueries
import com.muiezarif.quotesproject.db.models.QuotesModel
import com.muiezarif.quotesproject.utils.AdHelper

class FullQuoteViewActivity : AppCompatActivity() {
    private lateinit var binding:ActivityFullQuoteViewBinding
    private lateinit var dbQuery: DbQueries
    private lateinit var quoteList:ArrayList<QuotesModel>
    private var adapter:QuotesVPAdapter?=null
    private var position=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_full_quote_view)
        binding.fullQuoteHeader.ivBack.visibility= View.VISIBLE
        binding.fullQuoteHeader.tvHomeTitle.text= "Quotes"
        binding.fullQuoteHeader.ivBack.setOnClickListener {
            finish()
        }
        AdHelper.loadBannerAd(binding.adView)
        position= intent.extras?.getInt("quotePos",0)!!
        quoteList= intent.getSerializableExtra("list") as ArrayList<QuotesModel>
        adapter=  QuotesVPAdapter(this,quoteList)
        binding.vpQuotes.adapter=adapter
        binding.vpQuotes.currentItem = position
    }
}
