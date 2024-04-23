package com.example.ticketsearchapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketsearchapp.R
import com.example.ticketsearchapp.model.Price
import com.example.ticketsearchapp.model.TicketOffer
import com.example.ticketsearchapp.model.TimeRange
import com.google.gson.Gson
import com.google.gson.JsonObject

class CountrySearchFragment : Fragment() {

    private lateinit var recyclerViewTicketOffers: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_country_search, container, false)

        recyclerViewTicketOffers = view.findViewById(R.id.recyclerViewTicketOffers)

        // Read JSON from resources
        val jsonString = requireContext().resources.openRawResource(R.raw.tickets_offers).bufferedReader().use { it.readText() }
        val ticketOffers: List<TicketOffer> = parseJson(jsonString)
        setupRecyclerView(ticketOffers)

        return view
    }

    private fun parseJson(json: String): List<TicketOffer> {
        val gson = Gson()
        val jsonObject = gson.fromJson(json, JsonObject::class.java)
        val jsonArray = jsonObject.getAsJsonArray("tickets_offers")

        val ticketOffers = mutableListOf<TicketOffer>()
        for (jsonElement in jsonArray) {
            val ticketObject = jsonElement.asJsonObject
            val id = ticketObject.getAsJsonPrimitive("id").asInt
            val title = ticketObject.getAsJsonPrimitive("title").asString
            val priceObject = ticketObject.getAsJsonObject("price")
            val priceValue = priceObject.getAsJsonPrimitive("value").asInt
            val timeRangeArray = ticketObject.getAsJsonArray("time_range")

            val timeRanges = mutableListOf<TimeRange>()
            for (i in 0 until timeRangeArray.size()) {
                val timeRangeString = timeRangeArray[i].asString
                val times = timeRangeString.split(" ")
                if (times.size == 2) {
                    val startTime = times[0]
                    val endTime = times[1]
                    timeRanges.add(TimeRange(startTime, endTime))
                }
            }

            val ticketOffer = TicketOffer(id, title, Price(priceValue), timeRanges)
            ticketOffers.add(ticketOffer)
        }
        return ticketOffers
    }





    private fun setupRecyclerView(ticketOffers: List<TicketOffer>) {
        val adapter = TicketOfferAdapter(ticketOffers)
        recyclerViewTicketOffers.layoutManager = LinearLayoutManager(context)
        recyclerViewTicketOffers.adapter = adapter
    }

    inner class TicketOfferAdapter(private val ticketOffers: List<TicketOffer>) :
        RecyclerView.Adapter<TicketOfferAdapter.TicketOfferViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketOfferViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.ticket_offer_item, parent, false)
            return TicketOfferViewHolder(view)
        }

        override fun onBindViewHolder(holder: TicketOfferViewHolder, position: Int) {
            val ticketOffer = ticketOffers[position]
            holder.bind(ticketOffer)
        }

        override fun getItemCount(): Int {
            return ticketOffers.size
        }

        inner class TicketOfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val titleTextView: TextView = itemView.findViewById(R.id.textTitle)
            private val priceTextView: TextView = itemView.findViewById(R.id.textPrice)
            private val timeRangeTextView: TextView = itemView.findViewById(R.id.textTimeRange)

            fun bind(ticketOffer: TicketOffer) {
                titleTextView.text = ticketOffer.title
                priceTextView.text = "${ticketOffer.price.value} ₽"

                val timeRangeStringBuilder = StringBuilder()
                for (i in ticketOffer.timeRange.indices) {
                    val timeRange = ticketOffer.timeRange[i]
                    val formattedTimeRange = formatTimeRange(timeRange)
                    timeRangeStringBuilder.append(formattedTimeRange)
                    if (i < ticketOffer.timeRange.size - 1) {
                        timeRangeStringBuilder.append(", ")
                    }
                }
                timeRangeTextView.text = timeRangeStringBuilder.toString()
            }

            private fun formatTimeRange(timeRange: TimeRange): String {
                val startTime = timeRange.startTime
                val endTime = timeRange.endTime
                return "$startTime - $endTime"
            }

        }
    }
}
