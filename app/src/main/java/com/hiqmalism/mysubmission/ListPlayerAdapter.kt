package com.hiqmalism.mysubmission

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListPlayerAdapter(private val listPlayer: ArrayList<Player>) : RecyclerView.Adapter<ListPlayerAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_football, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, profile, card) = listPlayer[position]
        holder.imgCard.setImageResource(card)
        holder.tvName.text = name
        holder.tvStats.text = profile

        holder.btnPlayer.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_player", listPlayer[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = listPlayer.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgCard: ImageView = itemView.findViewById(R.id.item_card)
        val tvName: TextView = itemView.findViewById(R.id.item_name)
        val tvStats: TextView = itemView.findViewById(R.id.item_stats)
        val btnPlayer: Button = itemView.findViewById(R.id.item_button)
    }
}