package com.muiezarif.quotesproject.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.muiezarif.quotesproject.R
import com.muiezarif.quotesproject.adapters.viewholders.MyCategoryViewHolder
import com.muiezarif.quotesproject.databinding.CustomCategoryItemBinding
import com.muiezarif.quotesproject.db.models.CategoryModel
import com.muiezarif.quotesproject.interfaces.CategoryClickListener
import java.io.IOException

class CategoryRecyclerAdapter(var context: Context, var catList:ArrayList<CategoryModel>, var catClickListener: CategoryClickListener): RecyclerView.Adapter<MyCategoryViewHolder>() {
    var assetManager=context.assets
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCategoryViewHolder {
        var binding:CustomCategoryItemBinding= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_category_item,parent,false)
        return MyCategoryViewHolder(binding,catClickListener)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(holder: MyCategoryViewHolder, position: Int) {
        var model=catList[position]
        holder.binding.tvCatName.text=model.name
        try {
            val ims = assetManager.open("categories/" + model.file_name+".jpg")
            val drawable = Drawable.createFromStream(ims, null)
            holder.binding.ivCatImage.setImageDrawable(drawable)
        }catch (ex: IOException){
            return
        }
    }
}