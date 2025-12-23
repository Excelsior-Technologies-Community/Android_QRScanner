package com.ext.smartqrscanner.callback

interface QRScanResult {
    fun onSuccess(result: String)
    fun onFailure(error: String)
}