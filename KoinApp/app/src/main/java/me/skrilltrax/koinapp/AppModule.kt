package me.skrilltrax.koinapp

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single {
        androidContext().getSharedPreferences(androidContext().getString(R.string.shared_prefs), Context.MODE_PRIVATE)
    }
    factory { (view: View) -> Presenter(view, get()) }
}