package ru.cft.shift2021summer.base

open class BasePresenter<V : BaseView> {

    var view: V? = null

    fun attachView(view: V) {
        this.view = view
        onAttachView()
    }

    open fun onAttachView () {

    }

    fun destroy() {
        view = null
    }
}