package me.skrilltrax.rxjavatest

import io.reactivex.Observable

class DeferClass {

    lateinit var value: String

    fun valueObservable(): Observable<String> {
        return Observable.just(value)
    }

    fun valueObservableDefer(): Observable<String> {
        return Observable.defer {
            Observable.just(value)
        }
    }
}