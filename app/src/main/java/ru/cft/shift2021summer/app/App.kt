package ru.cft.shift2021summer.app

import android.app.Application
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.create
import ru.cft.shift2021summer.feature.beers.domain.BeersRepository
import ru.cft.shift2021summer.feature.beers.data.BeerRepositoryImp
import ru.cft.shift2021summer.feature.beers.data.BeersDataSourceImp

class App: Application() {

    var BASE_URL = "https://api.punkapi.com"
    lateinit var beersRepository: BeersRepository
    lateinit var retrofit: Retrofit

    override fun onCreate() {
        super.onCreate()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

        beersRepository = BeerRepositoryImp(BeersDataSourceImp(retrofit.create()))

    }

}