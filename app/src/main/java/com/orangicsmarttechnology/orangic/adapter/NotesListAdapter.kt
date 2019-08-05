package com.orangicsmarttechnology.orangic.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import com.orangicsmarttechnology.orangic.R
import com.orangicsmarttechnology.orangic.models.ResponseMapNotesList
import kotlinx.android.synthetic.main.post_item_layout.view.*

//Pass the ArrayList and a listener, and add a variable to hold your data//

class NotesListAdapter (private val cryptoList : ArrayList<ResponseMapNotesList>, private val listener :

//Extend RecyclerView.Adapter//

Listener) : RecyclerView.Adapter<NotesListAdapter.ViewHolder>() {

    interface Listener {
        fun onItemClick(responseList : ResponseMapNotesList){

        }

    }

    //Define an array of colours//
    private val colors : Array<String> = arrayOf("#7E57C2", "#42A5F5", "#26C6DA", "#66BB6A", "#FFEE58", "#FF7043" , "#EC407A" , "#d32f2f")

    //Bind the ViewHolder//
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //Pass the position where each item should be displayed//

        holder.bind(cryptoList[position], listener, colors, position)

    }

    //Check how many items you have to display//
    override fun getItemCount(): Int = cryptoList.count()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item_layout, parent, false)
        return ViewHolder(view)

    }

    //Create a ViewHolder class for your RecyclerView items//

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        //Assign values from the data model, to their corresponding Views//

        fun bind(responseList: ResponseMapNotesList, listener: Listener, colors : Array<String>, position: Int) {

            //Listen for user input events//
            itemView.setOnClickListener{ listener.onItemClick(responseList) }

            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
            itemView.idTitle.text = responseList.title
            itemView.IdUploadedBy.text = responseList.uploaded_by
            itemView.IdUploadedDate.text = responseList.uploaded_date

        }

    }

}