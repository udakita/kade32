package com.isoneday.kade3.mvp.presenter

import com.isoneday.kade3.model.modelleague.ResponseLeague
import com.isoneday.kade3.mvp.view.MainView
import com.isoneday.kade3.network.MyRetrofit
import com.isoneday.kade3.network.RestApi
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import retrofit2.Call
import retrofit2.Response

class MainPresenter(private val view: MainView) {

    fun getLastMatch(ligaId: Int) {
        view.showLoading()
        val serviceListLiga: RestApi = MyRetrofit.getInstance()
        serviceListLiga.getLastMatch(ligaId).enqueue(object : retrofit2.Callback<ResponseLeague> {
            override fun onFailure(call: Call<ResponseLeague>?, t: Throwable?) {
                view.hideLoading()
                view.showMessage(t?.localizedMessage.toString())
            }

            override fun onResponse(call: Call<ResponseLeague>?, response: Response<ResponseLeague>?) {
                view.hideLoading()
                async(UI) {
                    val dataList = bg { response?.body()?.events }
                    view.showTeamList(dataList.await())
                }

            }

        })
    }

    fun getNextMatch(ligaId: Int) {
        view.showLoading()
        val serviceListLiga: RestApi = MyRetrofit.getInstance()
        serviceListLiga.getNextMatch(ligaId).enqueue(object : retrofit2.Callback<ResponseLeague> {
            override fun onFailure(call: Call<ResponseLeague>?, t: Throwable?) {
                view.hideLoading()
                view.showMessage(t?.message.toString())
            }

            override fun onResponse(call: Call<ResponseLeague>?, response: Response<ResponseLeague>?) {
                view.hideLoading()
                async(UI) {
                    val dataList = bg { response?.body()?.events }
                    view.showTeamList(dataList.await())
                }

            }

        })
    }

}
