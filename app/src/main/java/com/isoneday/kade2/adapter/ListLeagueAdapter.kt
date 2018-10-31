package com.isoneday.kade3.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.isoneday.kade3.R
import com.isoneday.kade3.model.modelleague.EventsItem
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick


class ListLeagueAdapter(
    private val events: List<EventsItem>,
    private val listener: (EventsItem) -> Unit
) : RecyclerView.Adapter<ListLeagueAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.listliga, parent, false)
        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }

    override fun getItemCount(): Int = events.size

    class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateLiga: TextView = view.find(R.id.event_date)
        val scoreHome: TextView? = view.find(R.id.home_team_score)
        val scoreAway: TextView? = view.find(R.id.away_team_score)
        val teamHomeLiga: TextView = view.find(R.id.home_team)
        val teamAwayLiga: TextView = view.find(R.id.away_team)
        fun bindItem(event: EventsItem, listener: (EventsItem) -> Unit) {
            dateLiga.text = event.strDate
            teamHomeLiga.text = event.strHomeTeam
            teamAwayLiga.text = event.strAwayTeam
            scoreHome?.text = event.intHomeScore
            scoreAway?.text = event.intAwayScore
            itemView.onClick { listener(event) }

        }
    }
}
