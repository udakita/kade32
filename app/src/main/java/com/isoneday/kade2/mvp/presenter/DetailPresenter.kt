package com.isoneday.kade3.mvp.presenter

import com.isoneday.kade3.model.modelleague.ResponseLeague
import com.isoneday.kade3.model.modelteam.ResponseTeam
import com.isoneday.kade3.mvp.view.DetailView
import com.isoneday.kade3.network.MyRetrofit
import com.isoneday.kade3.network.RestApi
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailPresenter(private val view: DetailView) {
    fun getMatch(idEvent: String) {
        view.showLoading()
        val serviceListLiga: RestApi = MyRetrofit.getInstance()
        serviceListLiga.getDataEachTeam(idEvent).enqueue(object : retrofit2.Callback<ResponseLeague> {
            override fun onFailure(call: Call<ResponseLeague>?, t: Throwable?) {
                view.hideLoading()
                view.showMessage(t?.message.toString())
            }

            override fun onResponse(call: Call<ResponseLeague>?, response: Response<ResponseLeague>?) {
                view.hideLoading()
                async(UI) {
                    val dataList = bg { response?.body()?.events?.get(0) }
                    view.showList(dataList.await())
                }

            }

        })
    }

    fun getDataHome(idTeam: String?) {
        view.showLoading()
        val service: RestApi = MyRetrofit.getInstance()
        service.getLookupTeam(idTeam).enqueue(object : Callback<ResponseTeam> {
            override fun onResponse(call: Call<ResponseTeam>?, response: Response<ResponseTeam>?) {
                async(UI) {
                    val data = bg { response?.body()?.teams?.get(0) }
                    view.showHome(data.await())
                }
            }

            override fun onFailure(call: Call<ResponseTeam>?, t: Throwable?) {
                view.hideLoading()
                view.showMessage(t?.message)
            }


        })

    }

    fun getDataAway(idTeam: String?) {
        view.showLoading()
        val service: RestApi = MyRetrofit.getInstance()
        service.getLookupTeam(idTeam).enqueue(object : Callback<ResponseTeam> {
            override fun onResponse(call: Call<ResponseTeam>?, response: Response<ResponseTeam>?) {
                async(UI) {
                    val data = bg { response?.body()?.teams?.get(0) }
                    view.showAway(data.await())
                }
            }

            override fun onFailure(call: Call<ResponseTeam>?, t: Throwable?) {
                view.hideLoading()
                view.showMessage(t?.message)
            }
        })

    }

}