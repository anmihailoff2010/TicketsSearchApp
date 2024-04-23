package com.example.ticketsearchapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketsearchapp.R
import com.example.ticketsearchapp.ui.adapters.TicketAdapter

class ViewAllTicketsFragment : Fragment() {

    private lateinit var ticketAdapter: TicketAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_all_tickets, container, false)
        val recyclerViewTickets: RecyclerView = view.findViewById(R.id.recyclerViewTickets)

        ticketAdapter = TicketAdapter(requireContext())
        recyclerViewTickets.adapter = ticketAdapter
        recyclerViewTickets.layoutManager = LinearLayoutManager(context)

        // Загрузка билетов из JSON файла в ресурсах
        ticketAdapter.loadTicketsFromJson("tickets.json")

        return view
    }
}
