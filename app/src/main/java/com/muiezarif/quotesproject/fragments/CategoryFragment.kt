package com.muiezarif.quotesproject.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager

import com.muiezarif.quotesproject.R
import com.muiezarif.quotesproject.activities.ListQuotesActivity
import com.muiezarif.quotesproject.adapters.CategoryRecyclerAdapter
import com.muiezarif.quotesproject.databinding.CustomCategoryItemBinding
import com.muiezarif.quotesproject.databinding.FragmentCategoryBinding
import com.muiezarif.quotesproject.db.DbQueries
import com.muiezarif.quotesproject.db.models.CategoryModel
import com.muiezarif.quotesproject.interfaces.CategoryClickListener
import com.muiezarif.quotesproject.utils.AdHelper

/**
 * A simple [Fragment] subclass.
 */
class CategoryFragment : Fragment(), CategoryClickListener {
    private lateinit var binding:FragmentCategoryBinding
    private lateinit var dbQuery: DbQueries
    private lateinit var quoteList:ArrayList<CategoryModel>
    private var adapter:CategoryRecyclerAdapter?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.fragment_category, container, false)
        AdHelper.loadBannerAd(binding.adView)
        dbQuery= DbQueries(context)
        quoteList=dbQuery.getAllCategories()
        adapter= context?.let { CategoryRecyclerAdapter(it,quoteList,this) }
        binding.rvCategories.layoutManager= GridLayoutManager(context,2)
        binding.rvCategories.adapter=adapter
        return binding.root
    }

    override fun onClick(view: CustomCategoryItemBinding?, position: Int, catName: String) {
        var intent= Intent(context, ListQuotesActivity::class.java)
        intent.putExtra("from","category")
        intent.putExtra("catName",catName)
        Log.i("CheckLog",catName)
        startActivity(intent)
    }


}
