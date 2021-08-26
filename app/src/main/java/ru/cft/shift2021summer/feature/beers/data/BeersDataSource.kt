package ru.cft.shift2021summer.feature.beers.data

import io.reactivex.Single
import retrofit2.Response
import ru.cft.shift2021summer.feature.beers.domain.BeerModel

interface BeersDataSource {
     fun getAll(): Single<ArrayList<BeerModel>>
     fun getFromId(id: Int): Single<BeerModel>
}

class BeersDataSourceImp (private val JSONPlaceHplderApi: JSONPlaceHolderApi) : BeersDataSource {

    private val beers = ArrayList<BeerModel>()

    override fun getAll(): Single<ArrayList<BeerModel>> =
        JSONPlaceHplderApi.getAllPosts()
            .map {
                it.body()?: beers
            }

    override fun getFromId(id: Int): Single<BeerModel> =
        JSONPlaceHplderApi.getPostWithID(id = id)
            .map {
                it.body()?.get(0)?: beers[0]
            }

}
