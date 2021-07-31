package com.example.marvelapp.app.ui.characters.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelapp.R
import com.example.marvelapp.app.base.recycler.PaginationAdapter
import com.example.marvelapp.data.model.character.Character
import com.example.marvelapp.data.model.character.CharacterComics

class CharacterComicsAdapter(val context: Context) : RecyclerView.Adapter<CharacterComicsAdapter.MyViewHolder>() {

    var characterList : List<CharacterComics> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character_comics,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.name.text = characterList.get(position).title

    }

    fun setMovieListItems(characterList: List<CharacterComics>){
        this.characterList = characterList
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val name: TextView = itemView!!.findViewById(R.id.tvCharacterName)

    }
}