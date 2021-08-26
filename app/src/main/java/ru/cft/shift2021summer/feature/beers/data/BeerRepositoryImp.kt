package ru.cft.shift2021summer.feature.beers.data

import io.reactivex.Single
import retrofit2.Response
import ru.cft.shift2021summer.feature.beers.domain.BeerModel
import ru.cft.shift2021summer.feature.beers.domain.BeersRepository

class BeerRepositoryImp(private val beersDataSource: BeersDataSource): BeersRepository {

    override fun getAll(): Single<ArrayList<BeerModel>> = beersDataSource.getAll()

    override fun getFromId(id: Int): Single<BeerModel> = beersDataSource.getFromId(id = id)


}