package com.isoneday.kade3.mvp.view

import com.isoneday.kade3.model.modelleague.EventsItem
import com.isoneday.kade3.model.modelteam.TeamsItem

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showMessage(message: String?)
    fun showList(data: EventsItem?)
    fun showHome(eachTeam: TeamsItem?)
    fun showAway(eachTeam: TeamsItem?)
}