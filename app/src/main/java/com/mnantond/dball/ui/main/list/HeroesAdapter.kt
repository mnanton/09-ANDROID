package com.mnantond.dball.ui.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mnantond.dball.databinding.CellHeroesBinding

class HeroesAdapter: RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>() {

    class HeroesViewHolder(private val binding: CellHeroesBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        return HeroesViewHolder(
            CellHeroesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}