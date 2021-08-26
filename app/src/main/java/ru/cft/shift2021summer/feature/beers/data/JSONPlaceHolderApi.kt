package ru.cft.shift2021summer.feature.beers.data

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*
import ru.cft.shift2021summer.feature.beers.domain.BeerModel
import java.util.ArrayList


interface JSONPlaceHolderApi {

    @GET("/v2/beers/{id}")
    fun getPostWithID(@Path("id") id: Int): Single<Response<ArrayList<BeerModel>>>

    @GET("/v2/beers")
    fun getAllPosts(): Single<Response<ArrayList<BeerModel>>>

}