package me.skrilltrax.rxjavatest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
}
