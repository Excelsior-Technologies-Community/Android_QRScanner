package com.ext.smartqrscanner

import android.app.Activity
import android.content.Intent
import com.ext.smartqrscanner.callback.QRScanResult
import com.ext.smartqrscanner.core.QRScannerConfig
import com.ext.smartqrscanner.ui.QRScannerActivity


object SmartQRScanner {

    internal var callback: QRScanResult? = null
    internal var config: QRScannerConfig = QRScannerConfig()

    fun start(
        activity: Activity,
        config: QRScannerConfig = QRScannerConfig(),
        callback: QRScanResult
    ) {
        this.callback = callback
        this.config = config

        val intent = Intent(activity, QRScannerActivity::class.java)
        activity.startActivity(intent)
    }
}
