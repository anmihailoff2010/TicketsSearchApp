package com.example.ticketsearchapp.model

data class TicketOffer(
    val id: Int,
    val title: String,
    val price: Price,
    val timeRange: List<TimeRange>
)

data class Price(
    val value: Int
)

data class TimeRange(
    val startTime: String,
    val endTime: String
)