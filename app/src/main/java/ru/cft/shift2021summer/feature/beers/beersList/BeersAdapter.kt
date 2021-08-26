package ru.cft.shift2021summer.feature.beers.beersList

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.cft.shift2021summer.R
import ru.cft.shift2021summer.databinding.BeersItemBinding
import ru.cft.shift2021summer.feature.beers.domain.BeerModel
import java.util.*
import kotlin.collections.ArrayList

class BeersAdapter(private val onItemClick: (BeerModel) -> Unit): RecyclerView.Adapter<BeersAdapter.ViewBeersInfo>(), Filterable {

    private lateinit var context: Context
    lateinit var beerList: ArrayList<BeerModel>

    var beers: ArrayList<BeerModel> = ArrayList()
    set(value) {
        field = value
        beerList = ArrayList(beers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewBeersInfo {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.beers_item, parent, false)
        return ViewBeersInfo(view)
    }

    override fun onBindViewHolder(holder: ViewBeersInfo, position: Int) {
        holder.bind(beers[position])
        val url = beers[position].image_url
        if(url.isNotEmpty()) {
            Glide
                .with(context)
                .load(url).placeholder(R.drawable.ic_launcher_background)
                .into(holder.binding.ivBeer)
        }
    }

    override fun getItemCount(): Int {
        return beers.size
    }

    inner class ViewBeersInfo(view: View): RecyclerView.ViewHolder(view) {

        val binding = BeersItemBinding.bind(view)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind (item: BeerModel) {
            binding.tvBeerName.text =  item.name
            binding.tvFirstBrewed.text = item.first_brewed
            binding.tvTagline.text = item.tagline
            binding.tvBeerAbv.text = item.abv.toString()

            when (item.abv) {
                in 1.0..5.0 -> binding.tvBeerAbv.background = context.getDrawable(R.drawable.beer_abv_background_blue)
                in 5.0..8.0 -> binding.tvBeerAbv.background = context.getDrawable(R.drawable.beer_abv_background_green)
                in 8.0..13.0 -> binding.tvBeerAbv.background = context.getDrawable(R.drawable.beer_abv_background_red)
            }

            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun getFilter(): Filter {
        return filterList
    }

    private val filterList: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<BeerModel> = ArrayList()
            if (constraint.isEmpty() || constraint == null) {
                filteredList.addAll(beerList)
            } else {
                val filterPattern = constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
                for (item in beerList) {
                    if (item.name.lowercase(Locale.getDefault()).contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            beers.clear()
            beers.addAll(results.values as ArrayList<BeerModel>)
            notifyDataSetChanged()
        }
    }

}
