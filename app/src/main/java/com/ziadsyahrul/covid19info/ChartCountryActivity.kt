package com.ziadsyahrul.covid19info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_chart_country.*

class ChartCountryActivity : AppCompatActivity() {


    companion object{
        const val EXTRA_COUNTRY = "country"
        const val EXTRA_DATE = "date"
        const val EXTRA_COUNTRY_CODE = "country_code"
        const val EXTRA_NEW_DEATH = "new_death"
        const val EXTRA_NEW_CONFIRMED = "new_confirmed"
        const val EXTRA_NEW_RECOVERED = "new_recovered"
        const val EXTRA_TOTAL_CONFIRMED = "total_confirmed"
        const val EXTRA_TOTAL_DEATHS = "total_deaths"
        const val EXTRA_TOTAL_RECOVERED = "total_recovered"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart_country)

        val country = intent.getStringExtra(EXTRA_COUNTRY)
        val date = intent.getStringExtra(EXTRA_DATE)
        val countryCode = intent.getStringExtra(EXTRA_COUNTRY_CODE)
        val newDeath = intent.getStringExtra(EXTRA_NEW_DEATH)
        val newConfirmed = intent.getStringExtra(EXTRA_NEW_CONFIRMED)
        val newRecovered = intent.getStringExtra(EXTRA_NEW_RECOVERED)
        val totalDeath = intent.getStringExtra(EXTRA_TOTAL_DEATHS)
        val totalConfirmed = intent.getStringExtra(EXTRA_NEW_CONFIRMED)
        val totalRecovered = intent.getStringExtra(EXTRA_TOTAL_RECOVERED)

        txt_country_chart.text = country
        txt_current.text = date
        txt_total_confirmed_current.text = totalConfirmed
        txt_new_confirmed_current.text = newConfirmed
        txt_total_deaths_current.text = totalDeath
        txt_new_deaths_current.text = newDeath
        txt_total_recovered_current.text = totalRecovered
        txt_new_recovered_current.text = newRecovered
    }
}