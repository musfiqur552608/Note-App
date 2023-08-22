package com.example.noteapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Adapter(val dataList:ArrayList<Data>, val context:Context):
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val noteTxt = itemView.findViewById<TextView>(R.id.noteTxt)
        val delete = itemView.findViewById<TextView>(R.id.deleteBtn)
        val update = itemView.findViewById<TextView>(R.id.updateBtn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_items, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTxt.setText(dataList.get(position).data)
        holder.delete.setOnClickListener {
            val key=dataList.get(position).key
            val dbRef = Firebase.database.getReference("Notes").child(key!!)
            dbRef.removeValue().addOnCompleteListener{
                if(it.isSuccessful){
                    notifyDataSetChanged()
                    Toast.makeText(context, "Data Removed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}