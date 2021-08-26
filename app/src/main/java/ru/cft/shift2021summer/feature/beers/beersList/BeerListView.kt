package ru.cft.shift2021summer.feature.beers.beersList

import ru.cft.shift2021summer.base.BaseView
import ru.cft.shift2021summer.feature.beers.domain.BeerModel

interface BeerListView: BaseView {

    fun getBeersList (list: ArrayList<BeerModel>)

    fun openBeerDetailScreen (beerModelId: Int)

}