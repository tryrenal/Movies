package com.redveloper.movies.domain.usecase.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.redveloper.movies.utils.RxSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class BaseUseCaseImpl @Inject constructor(
    private val schedulers: RxSchedulers,
    private val context: Context
): IBaseUseaCase {

    val disposeables = CompositeDisposable()

    override fun addDisposable(disposable: Disposable) {
        disposeables.add(disposable)
    }

    override fun clear() {
        disposeables.clear()
    }

    override fun allowExecute(allow: IBaseUseaCase.AllowExecuteCallback) {
        if (isInternetAvailable()){
            allow.allow()
        } else {
            allow.notInternet()
        }
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

            return actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
}