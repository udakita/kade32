package com.isoneday.kade3.model.modelleague

import com.google.gson.annotations.SerializedName

data class ResponseLeague(

	@field:SerializedName("events")
	val events: List<EventsItem>? = null
)