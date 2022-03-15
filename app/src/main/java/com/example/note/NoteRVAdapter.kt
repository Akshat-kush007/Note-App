package com.example.note

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(private val context: Context, private val listenInterface:IonClick):RecyclerView.Adapter<NoteRVAdapter.NoteViewHolder>() {

    val allNotes=ArrayList<Note>()

    inner class NoteViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val itemText=itemView.findViewById<TextView>(R.id.txtItem_text)
        val itemImage:ImageView=itemView.findViewById(R.id.imgItem_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val ItemView=LayoutInflater.from(context).inflate(R.layout.item_note,parent,false)
        val holder=NoteViewHolder(ItemView)

        //Handling the clicks-----------------------
        holder.itemImage.setOnClickListener {
            listenInterface.onClickListeer(allNotes[holder.adapterPosition])
        }
        return  holder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote=allNotes[holder.adapterPosition]
        holder.itemText.text=currentNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }


    fun updateList(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}

interface IonClick{
    fun onClickListeer(item:Note)
}