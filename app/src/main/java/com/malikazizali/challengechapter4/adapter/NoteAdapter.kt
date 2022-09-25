package com.malikazizali.challengechapter4.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.malikazizali.challengechapter4.R
import com.malikazizali.challengechapter4.databinding.ItemNotesBinding
import com.malikazizali.challengechapter4.room.DataNote
import java.util.ArrayList

class NoteAdapter(var listNote: List<DataNote>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    var onEdit : ((DataNote)->Unit)? = null
    var onDelete : ((DataNote)->Unit)? = null

    class ViewHolder(var binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun dataBind(itemData: DataNote) {
            binding.dataNote = itemData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val id = listNote[position].id.toString()
        val title = listNote[position].title
        val content = listNote[position].content

        holder.dataBind(listNote[position])

        holder.binding.btnEdit.setOnClickListener{
            onEdit?.invoke(listNote[position])
        }

        holder.binding.btnDelete.setOnClickListener {
            onDelete?.invoke(listNote[position])
        }

        holder.binding.cvNote.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val bun = Bundle()
                bun.putString("id", id)
                bun.putString("title", title)
                bun.putString("content", content)

                Navigation.findNavController(holder.itemView).navigate(R.id.action_homeFragment_to_detailFragment, bun)
            }
        })
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    fun setNoteData(listNote: ArrayList<DataNote>) {
        this.listNote = listNote
    }
}