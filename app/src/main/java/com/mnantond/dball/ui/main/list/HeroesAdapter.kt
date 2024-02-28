package com.mnantond.dball.ui.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mnantond.dball.databinding.CellHeroesBinding
import com.mnantond.dball.domain.models.Hero
import com.mnantond.dball.ui.main.SharedViewModel

class HeroesAdapter: RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>() {

    private var heroes = mutableListOf<Hero>()

    class HeroesViewHolder(private val binding: CellHeroesBinding): RecyclerView.ViewHolder(binding.root) {
        fun loadHeroes(hero: Hero){
            Glide.with(binding.cellPhoto.context).load(hero.photo).into(binding.cellPhoto)
            binding.cellNameHero.text = hero.name
            binding.cellProgressBar.progress = hero.HealthState
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        return HeroesViewHolder(
            CellHeroesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}
