package com.muiezarif.quotesproject.db

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.muiezarif.quotesproject.db.models.AuthorModel
import com.muiezarif.quotesproject.db.models.CategoryModel
import com.muiezarif.quotesproject.db.models.QuotesModel
import java.io.IOException
import java.util.*

class DbQueries(context: Context?) {

    private val TAG = "DataAdapter"
    private var mContext: Context? = context
    private var mDb: SQLiteDatabase? = null
    private var mDbHelper: DatabaseHelper? = mContext?.let { DatabaseHelper(it) }
    init {
        createDatabase()
    }

    @Throws(SQLException::class)
    fun createDatabase(): DbQueries? {
        try {
            mDbHelper?.createDataBase()
        } catch (mIOException: IOException) {
            Log.e(TAG, "$mIOException  UnableToCreateDatabase")
            throw Error("UnableToCreateDatabase")
        }
        return this
    }

    @Throws(SQLException::class)
    fun open(): DbQueries? {
        mDb = try {
            mDbHelper?.openDataBase()
            mDbHelper?.close()
            mDbHelper?.readableDatabase
        } catch (mSQLException: SQLException) {
            Log.e(TAG, "open >>$mSQLException")
            throw mSQLException
        }
        return this
    }

    fun close() {
        mDbHelper?.close()
    }

    fun getAllQuotes():ArrayList<QuotesModel>{
        createDatabase()
        open()
        val cursor: Cursor?
        val operatorArray: ArrayList<QuotesModel> = ArrayList<QuotesModel>()
        try {
            cursor = mDb?.rawQuery("select * from quote", null)
            if (cursor != null) {
                val idCol = cursor.getColumnIndex("_id")
                val authorNameCol = cursor.getColumnIndex("author_name")
                val categoryCol = cursor.getColumnIndex("category_name")
                val quoteCol = cursor.getColumnIndex("qte")
                for (i in 0 until cursor.count) {
                    cursor.moveToPosition(i)
                    val id = cursor.getInt(idCol)
                    val author = cursor.getString(authorNameCol)
                    val category = cursor.getString(categoryCol)
                    val quote = cursor.getString(quoteCol)
                    operatorArray.add(QuotesModel(id,quote,category,author))
                }
            } else {
                Log.e("query", "getAuthorData" + "Cursor is null")
            }
        } catch (e: Exception) {
            Log.e("query", " getEventFromDB$e")
        }
        close()
        return operatorArray
    }

    fun getQuotesByAuthor(authorName:String):ArrayList<QuotesModel>{
        createDatabase()
        open()
        val cursor: Cursor?
        val operatorArray: ArrayList<QuotesModel> = ArrayList<QuotesModel>()
        try {
            cursor = mDb?.rawQuery("select * from quote where author_name='"+authorName+"'", null)
            if (cursor != null) {
                val idCol = cursor.getColumnIndex("_id")
                val authorNameCol = cursor.getColumnIndex("author_name")
                val categoryCol = cursor.getColumnIndex("category_name")
                val quoteCol = cursor.getColumnIndex("qte")
                for (i in 0 until cursor.count) {
                    cursor.moveToPosition(i)
                    val id = cursor.getInt(idCol)
                    val author = cursor.getString(authorNameCol)
                    val category = cursor.getString(categoryCol)
                    val quote = cursor.getString(quoteCol)
                    operatorArray.add(QuotesModel(id,quote,category,author))
                }
            } else {
                Log.e("query", "getAuthorData" + "Cursor is null")
            }
        } catch (e: Exception) {
            Log.e("query", " getEventFromDB$e")
        }
        close()
        return operatorArray
    }

    fun getQuotesByCategory(category:String):ArrayList<QuotesModel>{
        createDatabase()
        open()
        val cursor: Cursor?
        val operatorArray: ArrayList<QuotesModel> = ArrayList<QuotesModel>()
        Log.i("CheckLog",category)
        try {
            cursor = mDb?.rawQuery("select * from quote where category_name='"+category+"'", null)
            if (cursor != null) {
                val idCol = cursor.getColumnIndex("_id")
                val authorNameCol = cursor.getColumnIndex("author_name")
                val categoryCol = cursor.getColumnIndex("category_name")
                val quoteCol = cursor.getColumnIndex("qte")
                for (i in 0 until cursor.count) {
                    cursor.moveToPosition(i)
                    val id = cursor.getInt(idCol)
                    val author = cursor.getString(authorNameCol)
                    val category = cursor.getString(categoryCol)
                    val quote = cursor.getString(quoteCol)
                    operatorArray.add(QuotesModel(id,quote,category,author))
                }
            } else {
                Log.e("query", "getAuthorData" + "Cursor is null")
            }
        } catch (e: Exception) {
            Log.e("query", " getEventFromDB$e")
        }
        close()
        Log.i("CheckLog",operatorArray.toString())
        return operatorArray
    }
    fun getAllCategories():ArrayList<CategoryModel>{
        createDatabase()
        open()
        val cursor: Cursor?
        val operatorArray: ArrayList<CategoryModel> = ArrayList<CategoryModel>()
        try {
            cursor = mDb?.rawQuery("select * from category", null)
            if (cursor != null) {
                val idCol = cursor.getColumnIndex("_id_cat")
                val catNameCol = cursor.getColumnIndex("name")
                val catImgCol = cursor.getColumnIndex("file_name")
                for (i in 0 until cursor.count) {
                    cursor.moveToPosition(i)
                    val id = cursor.getInt(idCol)
                    val catName = cursor.getString(catNameCol)
                    val catImage = cursor.getString(catImgCol)
                    operatorArray.add(CategoryModel(id,catName,catImage))
                }
            } else {
                Log.e("query", "getAuthorData" + "Cursor is null")
            }
        } catch (e: Exception) {
            Log.e("query", " getEventFromDB$e")
        }
        close()
        return operatorArray
    }

    fun getAllAuthors():ArrayList<AuthorModel>{
        createDatabase()
        open()
        val cursor: Cursor?
        val operatorArray: ArrayList<AuthorModel> = ArrayList<AuthorModel>()
        try {
            cursor = mDb?.rawQuery("select * from author", null)
            if (cursor != null) {
                val idCol = cursor.getColumnIndex("_id_author")
                val authorNameCol = cursor.getColumnIndex("name")
                val authorImageCol = cursor.getColumnIndex("file_name")
                for (i in 0 until cursor.count) {
                    cursor.moveToPosition(i)
                    val id = cursor.getInt(idCol)
                    val author = cursor.getString(authorNameCol)
                    val authorImg = cursor.getString(authorImageCol)
                    operatorArray.add(AuthorModel(id,author,authorImg))
                }
            } else {
                Log.e("query", "getAuthorData" + "Cursor is null")
            }
        } catch (e: Exception) {
            Log.e("query", " getEventFromDB$e")
        }
        close()
        return operatorArray
    }



}