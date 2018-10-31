package com.isoneday.kade3.mvp.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.isoneday.kade3.R
import com.isoneday.kade3.adapter.ListLeagueAdapter
import com.isoneday.kade3.model.modelleague.EventsItem
import com.isoneday.kade3.mvp.presenter.MainPresenter
import com.isoneday.kade3.mvp.view.MainView
import com.isoneday.kade3.mvp.view.activity.DetailListLeagueActivity
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 *
 */
class NextMatchFragment : Fragment(), MainView {
    var loadProgress: ProgressBar? = null
    var presentMain: MainPresenter? = null
    var listLeagueAdapter: ListLeagueAdapter? = null
    var liga: MutableList<EventsItem> = mutableListOf()
    var ligaID: Int = 4335
    var nextLiga: String = "Next Liga"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_match, container, false)
        val layoutLiga: RecyclerView = v.findViewById(R.id.layoutLiga)
        layoutLiga.layoutManager = LinearLayoutManager(activity)
        return v
         }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presentMain = MainPresenter(this)
        presentMain?.getNextMatch(ligaID)

        listLeagueAdapter = ListLeagueAdapter(liga) {
            startActivity<DetailListLeagueActivity>(getString(R.string.idevent) to "${it.idEvent}")
        }
        layoutLiga.adapter = listLeagueAdapter
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

    override fun showTeamList(data: List<EventsItem>?) {
        liga.clear()
        if (data != null) {
            liga.addAll(data)
            Log.d(getString(R.string.check),nextLiga)
        }else{
            Log.d(getString(R.string.check),getString(R.string.nulldata))

        }
        listLeagueAdapter?.notifyDataSetChanged()

    }


}
