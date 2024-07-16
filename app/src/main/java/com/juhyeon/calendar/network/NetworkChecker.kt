package com.juhyeon.calendar.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@SuppressLint("MissingPermission")
class NetworkChecker @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val networkMutableStateFlow = MutableStateFlow<NetworkContract.State.NetworkState>(NetworkContract.State.NetworkState.None)
    private val validTransportTypes = listOf(
        NetworkCapabilities.TRANSPORT_WIFI,
        NetworkCapabilities.TRANSPORT_CELLULAR
    )
    val networkStateFlow = networkMutableStateFlow.asStateFlow()

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            networkMutableStateFlow.value = NetworkContract.State.NetworkState.Connected
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            networkMutableStateFlow.value = NetworkContract.State.NetworkState.DisConnected
        }
    }

    init {
        val connectivityManager: ConnectivityManager? = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        connectivityManager?.run {
            initiateNetworkState(this)
        }
    }

    private fun initiateNetworkState(manager: ConnectivityManager) {
        networkMutableStateFlow.value = manager.activeNetwork?.let {
            manager.getNetworkCapabilities(it)
        }?.let { networkCapabilities ->
            if (validTransportTypes.any { networkCapabilities.hasTransport(it) }) {
                NetworkContract.State.NetworkState.Connected
            } else {
                NetworkContract.State.NetworkState.DisConnected
            }
        } ?: NetworkContract.State.NetworkState.None
    }

    private fun registerNetworkCallback(manager: ConnectivityManager) {
        NetworkRequest.Builder().apply {
            validTransportTypes.onEach { addTransportType(it) }
        }.let {
            manager.registerDefaultNetworkCallback(networkCallback)
        }
    }
}