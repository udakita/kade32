package com.isoneday.kade3.mvp.view

import com.isoneday.kade3.model.modelleague.EventsItem

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<EventsItem>?)
    fun showMessage(message: String?)
}