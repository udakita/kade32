package com.isoneday.kade3.mvp.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.isoneday.kade3.R
import com.isoneday.kade3.model.modelleague.EventsItem
import com.isoneday.kade3.model.modelteam.TeamsItem
import com.isoneday.kade3.mvp.presenter.DetailPresenter
import com.isoneday.kade3.mvp.view.DetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_list_league.*
import kotlinx.android.synthetic.main.content_detail_list_league.*
import org.jetbrains.anko.toast

class DetailListLeagueActivity : AppCompatActivity(), DetailView {


    private var imageHomeTeam: ImageView? = null
    private var imageAwayTeam: ImageView? = null
    private var textDate: TextView? = null
    private var textScoreHomeTeam: TextView? = null
    private var textScoreAwayTeam: TextView? = null
    private var textGoalHomeTeam: TextView? = null
    private var textGoalAwayTeam: TextView? = null
    private var idEvent: String? = null
    private var loadProgress: ProgressBar? = null
    private var presentDetail: DetailPresenter? = null
    private var idHomeTeam: String? = null
    private var idAwayTeam: String? = null
    private var eventMatch: EventsItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_list_league)
        setSupportActionBar(toolbar)
        initView()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initView() {
        idEvent = intent.getStringExtra(getString(R.string.idevent))
        presentDetail = DetailPresenter(this)
        presentDetail?.getMatch(idEvent!!)
        imageHomeTeam = img_home_team
        imageAwayTeam = img_away_team
        textScoreHomeTeam = text_score_home_team
        textScoreAwayTeam = text_score_away_team
        textDate = tv_date
        textGoalHomeTeam = text_goal_home_team
        textGoalAwayTeam = text_goal_away_team
    }


    override fun showLoading() {
        loadProgress?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadProgress?.visibility = View.INVISIBLE
    }

    override fun showMessage(message: String?) {
        toast(message.toString())
    }

    override fun showList(data: EventsItem?) {
        eventMatch = data
        idHomeTeam = data?.idHomeTeam
        idAwayTeam = data?.idAwayTeam
        textScoreHomeTeam!!.text = data?.intHomeScore
        textScoreAwayTeam!!.text = data?.intAwayScore
        textGoalHomeTeam!!.text = data?.strHomeGoalDetails
        textDate!!.text = data?.strDate
        textGoalAwayTeam!!.text = data?.strAwayGoalDetails
        presentDetail?.getDataHome(idHomeTeam)
        presentDetail?.getDataAway(idAwayTeam)
    }

    override fun showHome(eachTeam: TeamsItem?) {
        val imgHome = eachTeam?.strTeamBadge
        Picasso.get().load(imgHome).placeholder(R.drawable.emptyclub).error(R.drawable.emptyclub).into(imageHomeTeam)
    }

    override fun showAway(eachTeam: TeamsItem?) {
        val imgAway = eachTeam?.strTeamBadge
        Picasso.get().load(imgAway).placeholder(R.drawable.emptyclub).error(R.drawable.emptyclub).into(imageAwayTeam)
    }
}
