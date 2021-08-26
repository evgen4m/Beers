package ru.cft.shift2021summer.feature.beers.detailsBeer

import ru.cft.shift2021summer.base.BaseView
import ru.cft.shift2021summer.feature.beers.domain.BeerModel

interface DetailsBeerView: BaseView {

    fun viewAllInfo (beerModel: BeerModel)

}