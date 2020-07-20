package com.ziadsyahrul.covid19info.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ziadsyahrul.covid19info.R
import com.ziadsyahrul.covid19info.network.Countries
import kotlinx.android.synthetic.main.list_country.view.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CountryAdapter(val country: ArrayList<Countries>, val clickListener: (Countries) -> Unit) :
// recyclerview is a class so there must be a constructor
// when called there must be a sign () at the end
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(),

    // Firterable: interface to create a filter in recyclerview
    // because filterable is an interface it doesn't need a sign () at the end
    //when a class is called, it's different from other classes
    Filterable {

    var countryFilterList = ArrayList<Countries>()

    init {
        countryFilterList = country
    }

    // onCreateViewHolder to be used to inflate or apply
    // operations that have been made into the model layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_country, parent, false)
    )

    override fun getItemCount() = countryFilterList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindItem(countryFilterList[position], clickListener)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                countryFilterList = if (charSearch.isEmpty()) {
                    country
                } else {
                    val resultList = ArrayList<Countries>()
                    for (row in country) {
                        if (row.Country.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = countryFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countryFilterList = results?.values as ArrayList<Countries>
                notifyDataSetChanged()
            }
        }
    }

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCountry: TextView = itemView.txt_country_name
        val tvTotalCase: TextView = itemView.txt_total_case
        val tvTotalRecovered: TextView = itemView.txt_total_recovered
        val tvTotalDeath: TextView = itemView.txt_total_deaths
        val flag: ImageView = itemView.img_flag_country

        fun bindItem(countries: Countries, clickListener: (Countries) -> Unit) {

            val formatter: NumberFormat = DecimalFormat("#,###")
            tvCountry.txt_country_name.text = countries.Country
            tvTotalCase.txt_total_case.text = formatter.format(countries.TotalConfirmed.toDouble())
            tvTotalRecovered.txt_total_recovered.text =
                formatter.format(countries.TotalRecovered.toDouble())
            tvTotalDeath.txt_total_deaths.text = formatter.format(countries.TotalDeaths.toDouble())

            Glide.with(itemView)
                .load("https://www.countryflags.io/" + countries.CountryCode + "/flat/16.png")
                .into(flag)

            itemView.setOnClickListener { clickListener(countries) }
        }
    }
}