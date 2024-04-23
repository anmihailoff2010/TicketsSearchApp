package com.example.ticketsearchapp.model

data class Ticket(
    val id: Int,
    val badge: String,
    val price: Int,
    val providerName: String,
    val company: String,
    val departureTown: String,
    val departureDate: String,
    val departureAirport: String,
    val arrivalTown: String,
    val arrivalDate: String,
    val arrivalAirport: String,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val hasLuggage: Boolean,
    val luggagePrice: Int,
    val hasHandLuggage: Boolean,
    val handLuggageSize: String,
    val isReturnable: Boolean,
    val isExchangable: Boolean
)
