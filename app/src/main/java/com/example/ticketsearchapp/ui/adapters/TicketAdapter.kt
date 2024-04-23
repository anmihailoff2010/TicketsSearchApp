package com.example.ticketsearchapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketsearchapp.R
import com.example.ticketsearchapp.model.Ticket
import org.json.JSONObject

class TicketAdapter(private val context: Context) :
    RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {

    private var tickets: MutableList<Ticket> = mutableListOf()

    inner class TicketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val providerName: TextView = itemView.findViewById(R.id.textProviderName)
        val company: TextView = itemView.findViewById(R.id.textCompany)
        val price: TextView = itemView.findViewById(R.id.textPrice)
        val departureInfo: TextView = itemView.findViewById(R.id.textDepartureInfo)
        val arrivalInfo: TextView = itemView.findViewById(R.id.textArrivalInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ticket, parent, false)
        return TicketViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val currentTicket = tickets[position]

        holder.providerName.text = currentTicket.providerName
        holder.company.text = currentTicket.company
        holder.price.text = "Price: ${currentTicket.price} RUB"
        holder.departureInfo.text = "Departure: ${currentTicket.departureTown} " +
                "(${currentTicket.departureAirport}) at ${currentTicket.departureDate}"
        holder.arrivalInfo.text = "Arrival: ${currentTicket.arrivalTown} " +
                "(${currentTicket.arrivalAirport}) at ${currentTicket.arrivalDate}"
    }

    override fun getItemCount(): Int {
        return tickets.size
    }

    fun setTickets(ticketList: List<Ticket>) {
        tickets.clear()
        tickets.addAll(ticketList)
        notifyDataSetChanged()
    }

    private fun getJsonFromAssets(fileName: String): String {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use {
                it.readText()
            }
        } catch (ioException: Exception) {
            ioException.printStackTrace()
            return ""
        }
        return jsonString
    }

    fun loadTicketsFromJson(fileName: String) {
        val jsonString = getJsonFromAssets(fileName)
        val jsonObject = JSONObject(jsonString)
        val ticketsArray = jsonObject.getJSONArray("tickets")

        val ticketList = mutableListOf<Ticket>()
        for (i in 0 until ticketsArray.length()) {
            val ticketObject = ticketsArray.getJSONObject(i)
            val id = ticketObject.getInt("id")
            val badge = ticketObject.optString("badge", "")
            val price = ticketObject.getJSONObject("price").getInt("value")
            val providerName = ticketObject.getString("provider_name")
            val company = ticketObject.getString("company")

            val departureObj = ticketObject.getJSONObject("departure")
            val departureTown = departureObj.getString("town")
            val departureDate = departureObj.getString("date")
            val departureAirport = departureObj.getString("airport")

            val arrivalObj = ticketObject.getJSONObject("arrival")
            val arrivalTown = arrivalObj.getString("town")
            val arrivalDate = arrivalObj.getString("date")
            val arrivalAirport = arrivalObj.getString("airport")

            val hasTransfer = ticketObject.getBoolean("has_transfer")
            val hasVisaTransfer = ticketObject.getBoolean("has_visa_transfer")

            val luggageObj = ticketObject.optJSONObject("luggage")
            val hasLuggage = luggageObj?.optBoolean("has_luggage") ?: false
            val luggagePrice = luggageObj?.optJSONObject("price")?.getInt("value") ?: 0

            val handLuggageObj = ticketObject.optJSONObject("hand_luggage")
            val hasHandLuggage = handLuggageObj?.optBoolean("has_hand_luggage") ?: false
            val handLuggageSize = handLuggageObj?.getString("size") ?: ""

            val isReturnable = ticketObject.optBoolean("is_returnable")
            val isExchangable = ticketObject.optBoolean("is_exchangable")

            val ticket = Ticket(
                id, badge, price, providerName, company,
                departureTown, departureDate, departureAirport,
                arrivalTown, arrivalDate, arrivalAirport,
                hasTransfer, hasVisaTransfer,
                hasLuggage, luggagePrice,
                hasHandLuggage, handLuggageSize,
                isReturnable, isExchangable
            )

            ticketList.add(ticket)
        }

        setTickets(ticketList)
    }
}
