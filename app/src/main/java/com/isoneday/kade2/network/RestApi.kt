package com.isoneday.kade3.network

import com.isoneday.kade3.model.modelleague.ResponseLeague
import com.isoneday.kade3.model.modelteam.ResponseTeam
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {
    @GET("api/v1/json/1/eventspastleague.php")
    fun getLastMatch(@Query("id") leagueId: Int): Call<ResponseLeague>

    @GET("api/v1/json/1/eventsnextleague.php")
    fun getNextMatch(@Query("id") leagueId: Int): Call<ResponseLeague>

    @GET("api/v1/json/1/lookupteam.php")
    fun getLookupTeam(@Query("id") idTeam: String?): Call<ResponseTeam>

    @GET("api/v1/json/1/lookupevent.php")
    fun getDataEachTeam(@Query("id") idEvent: String): Call<ResponseLeague>


}