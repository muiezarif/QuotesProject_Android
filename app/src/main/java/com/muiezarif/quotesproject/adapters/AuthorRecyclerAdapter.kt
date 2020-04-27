package com.muiezarif.quotesproject.adapters

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.muiezarif.quotesproject.R
import com.muiezarif.quotesproject.adapters.viewholders.MyAuthorViewHolder
import com.muiezarif.quotesproject.databinding.CustomAuthorItemBinding
import com.muiezarif.quotesproject.db.models.AuthorModel
import com.muiezarif.quotesproject.interfaces.AuthorClickListener
import java.io.IOException
import java.io.InputStream


class AuthorRecyclerAdapter(var context: Context, var authorList:ArrayList<AuthorModel>, var authorClickListener: AuthorClickListener): RecyclerView.Adapter<MyAuthorViewHolder>() {
    var assetManager=context.assets
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAuthorViewHolder {
        val binding:CustomAuthorItemBinding= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_author_item,parent,false)
        return MyAuthorViewHolder(binding,authorClickListener)
    }

    override fun getItemCount(): Int {
        return authorList.size
    }

    override fun onBindViewHolder(holder: MyAuthorViewHolder, position: Int) {
        val model=authorList[position]
        holder.binding.tvAuthorName.text=model.name
        try {
            val ims = assetManager.open("authors/" + model.file_name+".jpg")
            val drawable = Drawable.createFromStream(ims, null)
            holder.binding.ivAuthorImage.setImageDrawable(drawable)
        }catch (ex:IOException){
            return
        }
    }

    fun filterList(filteredList:ArrayList<AuthorModel>){
        authorList=filteredList
        notifyDataSetChanged()
    }
}