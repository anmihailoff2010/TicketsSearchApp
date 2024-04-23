package com.example.ticketsearchapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ticketsearchapp.R

class SearchFragment : Fragment() {

    private lateinit var editTextSearch: EditText
    private lateinit var imageViewClear: ImageView
    private lateinit var layoutHints: LinearLayout
    private lateinit var layoutRecommendations: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        editTextSearch = view.findViewById(R.id.editTextSearch)
        imageViewClear = view.findViewById(R.id.imageViewClear)
        layoutHints = view.findViewById(R.id.layoutHints)
        layoutRecommendations = view.findViewById(R.id.layoutRecommendations)

        // Add click listener to clear button
        imageViewClear.setOnClickListener {
            editTextSearch.setText("")
        }

        return view
    }

    // Called when a hint is clicked
    fun onHintClicked(view: View) {
        val hint = (view as TextView).text.toString()
        // Handle hint click, for example:
        // showStubOrReturn(hint)
        // updateEditText(hint)
    }

    // Called when a recommendation is clicked
    fun onRecommendationClicked(view: View) {
        val city = (view as TextView).text.toString()
        editTextSearch.setText(city)
    }

    private fun showStubOrReturn(hint: String) {
        // Implement logic to show stub or return to previous screen based on hint
    }

    private fun updateEditText(hint: String) {
        editTextSearch.setText(hint)
    }
}
