package com.example.ticketsearchapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketsearchapp.model.Offer
import com.example.ticketsearchapp.R

class OfferAdapter(private val offers: List<Offer>) :
    RecyclerView.Adapter<OfferAdapter.OfferViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_offer, parent, false)
        return OfferViewHolder(view)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer = offers[position]
        holder.bind(offer)
    }

    override fun getItemCount(): Int {
        return offers.size
    }

    inner class OfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.textTitle)
        private val townTextView: TextView = itemView.findViewById(R.id.textTown)
        private val priceTextView: TextView = itemView.findViewById(R.id.textPrice)

        fun bind(offer: Offer) {
            titleTextView.text = offer.title
            townTextView.text = offer.town
            priceTextView.text = "${offer.price.value} ₽"
        }
    }
}