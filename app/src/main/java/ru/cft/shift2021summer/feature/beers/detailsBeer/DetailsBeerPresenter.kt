package ru.cft.shift2021summer.feature.beers.detailsBeer

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import ru.cft.shift2021summer.base.BasePresenter
import ru.cft.shift2021summer.feature.beers.domain.BeersRepository


class DetailsBeerPresenter(
    private val beerId: Int,
    private val repository: BeersRepository
) : BasePresenter<DetailsBeerView>() {

    private val compositeDisposable = CompositeDisposable()

    override fun onAttachView() {
        val disposable = repository.getFromId(beerId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    view?.viewAllInfo(it)
                },
            )
        compositeDisposable.add(disposable)
    }

}