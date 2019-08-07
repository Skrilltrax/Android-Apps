package me.skrilltrax.rxjavatest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    val stringList: List<String> = listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J")

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*createObservable()
        createSimple()
        createDefer()
        observableFrom()
        observableInterval()
        observableRange()*/
        observableTimer()
    }

    @SuppressLint("CheckResult")
    private fun observableTimer() {
     Observable.timer(40, TimeUnit.SECONDS, Schedulers.io())
         .subscribe({
             Log.d("RXTIMER", "onNext: $it")
         }, {
             Log.d("RXTIMER", "onError ${it.stackTrace}")
         }, {
             Log.d("RXTIMER", "onComplete")
         })
    }

    @SuppressLint("CheckResult")
    private fun observableRange() {
        Observable.range(1, 10)
            .repeat(3)
            .subscribe({
                Log.d("RXRANGE", "onNext: $it")
            }, {
                Log.d("RXRANGE", "onError ${it.stackTrace}")
            })
    }

    @SuppressLint("CheckResult")
    private fun observableInterval() {
        Observable.interval(1, TimeUnit.SECONDS, Schedulers.io())
            .subscribe({
                Log.d("RXINTERVAL", "onNext: $it")
            }, {
                Log.d("RXINTERVAL", "onError ${it.stackTrace}")
            })
    }

    private fun createSimple() {
        val observable = Observable.just("A", "B", "C", "D", "E")
        val observer: Observer<String> = object: Observer<String> {
            override fun onComplete() {
                Log.d("RX", "Completed")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d("RX", "onSubscribe + $d")
            }

            override fun onNext(t: String) {
                Log.d("RX", "onNext + $t")
            }

            override fun onError(e: Throwable) {
                Log.d("RX", "onError + $e")
            }

        }
        observable
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    @SuppressLint("CheckResult")
    fun createDefer() {
        val defer = DeferClass()
        defer.value = "IDNSJDBAOUDDFSN"
        defer.valueObservableDefer().subscribe({
            Log.d("RX", it)
        }, {
            Throwable(it)
        })
    }

    fun createObservable() {
        val observable: Observable<String> = Observable.create(ObservableOnSubscribe {emitter ->
                try {
                    for (alphabet in stringList) {
                        emitter.onNext(alphabet)
                    }
                    emitter.onComplete()
                } catch(e: Exception) {
                    emitter.onError(e)
                }
        })

        val observer: Observer<String> = object: Observer<String> {
            override fun onComplete() {
                Log.d("RX", "onCompleted")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d("RX", "onSubscribe")
            }

            override fun onNext(t: String) {
                Log.d("RX", "onNext : $t")
            }

            override fun onError(e: Throwable) {
                Log.d("RX", "onError : $e")
            }
        }

        observable.subscribe(observer)
    }

    @SuppressLint("CheckResult")
    fun observableFrom() {
//        val observable: Observable<Int> = Observable.fromArray(intArrayOf(1, 2, 3, 4, 5))
    }
}
