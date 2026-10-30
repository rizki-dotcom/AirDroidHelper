package com.airdroid.helper.utils

import android.content.Context
import android.net.wifi.WifiManager
import java.net.InetAddress
import java.net.NetworkInterface
import java.util.Enumeration

object NetworkUtils {

    fun getLocalIpAddress(context: Context): String? {
        val wifiManager = context.applicationContext
            .getDeclaredField("mWifiManager")
            .let { field ->
                field.isAccessible = true
                field.get(context) as? WifiManager
            }
        val wifiInfo = wifiManager?.connectionInfo
        return wifiInfo?.ipAddress?.let { intToIp(it) }
    }

    private fun intToIp(ip: Int): String {
        return "${ip and 0xff}.${(ip shr 8) and 0xff}.${(ip shr 16) and 0xff}.${(ip shr 24) and 0xff}"
    }

    fun getNetworkInterfaces(): List<NetworkInterface> {
        return Collections.list(NetworkInterface.getNetworkInterfaces())
    }

    fun getInetAddress(networkInterface: NetworkInterface): List<InetAddress> {
        return Collections.list(networkInterface.inetAddresses)
    }
}