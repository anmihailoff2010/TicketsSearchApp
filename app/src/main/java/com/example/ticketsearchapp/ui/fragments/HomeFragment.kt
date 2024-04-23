package com.example.ticketsearchapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketsearchapp.model.Offer
import com.example.ticketsearchapp.ui.adapters.OfferAdapter
import com.example.ticketsearchapp.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class HomeFragment : Fragment() {

    private lateinit var editTextFrom: EditText
    private lateinit var editTextTo: EditText
    private lateinit var recyclerViewOffers: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        editTextFrom = view.findViewById(R.id.editTextFrom)
        editTextTo = view.findViewById(R.id.editTextTo)
        recyclerViewOffers = view.findViewById(R.id.recyclerViewOffers)

        // Load last entered "Откуда" value from cache
        val lastFrom = loadLastFrom()
        editTextFrom.setText(lastFrom)

        // Setup RecyclerView with JSON data
        val jsonString = resources.openRawResource(R.raw.offers).bufferedReader().use { it.readText() }
        val offers: List<Offer> = parseJson(jsonString)
        setupRecyclerView(offers)

        // Add listener for "Куда" field to show modal dialog
        editTextTo.setOnClickListener {
            showAdditionalInfoDialog()
        }

        return view
    }

    private fun loadLastFrom(): String {
        // Here you would implement your caching mechanism, for example using SharedPreferences
        // This is a simplified example
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        return sharedPreferences.getString("last_from", "Откуда - Москва") ?: "Откуда - Москва"
    }

    private fun parseJson(json: String): List<Offer> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Offer>>() {}.type
        return gson.fromJson(json, type)
    }

    private fun setupRecyclerView(offers: List<Offer>) {
        val adapter = OfferAdapter(offers)
        recyclerViewOffers.layoutManager = LinearLayoutManager(context)
        recyclerViewOffers.adapter = adapter
    }

    private fun showAdditionalInfoDialog() {
        // Implement your logic for showing additional info dialog
        // This could be a dialog fragment or a custom dialog
        // For simplicity, showing a Toast here
        Toast.makeText(context, "Модальное окно с дополнительной информацией", Toast.LENGTH_SHORT).show()
    }
}