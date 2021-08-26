package ru.cft.shift2021summer.feature.beers.domain

import io.reactivex.Single
import retrofit2.Response

interface BeersRepository {

    fun getAll(): Single<ArrayList<BeerModel>>

    fun getFromId(id: Int): Single<BeerModel>

}