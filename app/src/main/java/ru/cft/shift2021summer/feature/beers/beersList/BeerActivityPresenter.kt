package ru.cft.shift2021summer.feature.beers.beersList


import io.reactivex.disposables.CompositeDisposable
import ru.cft.shift2021summer.base.BasePresenter
import ru.cft.shift2021summer.feature.beers.domain.BeerModel
import ru.cft.shift2021summer.feature.beers.domain.BeersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy


class BeerActivityPresenter(private val beerRepository: BeersRepository): BasePresenter<BeerListView>() {

    private val compositeDisposable = CompositeDisposable()

    fun onScreenResumed() {
        val disposable = beerRepository.getAll()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    view?.getBeersList(it)
                },
            )
        compositeDisposable.add(disposable)
    }


    fun openDetailsScreen (beerModel: BeerModel) {
        view?.openBeerDetailScreen(beerModel.id)
    }

}