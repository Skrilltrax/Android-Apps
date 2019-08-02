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

class MainActivity : AppCompatActivity() {

    val stringList: List<String> = listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J")

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*val observable = Observable.just("A", "B", "C", "D", "E")
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


        createObservable()*/

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
}
