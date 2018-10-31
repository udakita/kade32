package com.isoneday.kade3.model.modelteam

import com.google.gson.annotations.SerializedName

data class ResponseTeam(

	@field:SerializedName("teams")
	val teams: List<TeamsItem?>? = null
)