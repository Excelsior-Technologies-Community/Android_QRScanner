## SmartQRScanner
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9-blue?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-green)](LICENSE)
[![API](https://img.shields.io/badge/API-24%2B-orange)](#)
---

A lightweight, modern QR Code Scanner library for Android (Kotlin) built using CameraX and Google ML Kit, with a clean API and GPay-style scanning UI.

### Features

- CameraX-based scanning
- ML Kit QR / Barcode detection
- GPay-style overlay UI
- Flashlight toggle
- Beep + vibration on success
- Prevents double scan
- Fast & lightweight
- Minimal API – one-line start
- Easy to test in app module
- Library-friendly & reusable

---

## Preview

<img src="assets/demo.gif" width="320"/>

---

## Installation (JitPack)

### 1️⃣ Add JitPack to your **root `settings.gradle` or `build.gradle`**

```gradle
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```
### Add Dependency
```
dependencies {
	        implementation 'com.github.Excelsior-Technologies-Community:Android_QRScanner:1.0.0'
	}
```

---

## Permissions


Add These permissions in manifest File
```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.VIBRATE" />

<uses-feature
    android:name="android.hardware.camera"
    android:required="true" />
```

### Usage
```kotlin
SmartQRScanner.start(
    this,
    QRScannerConfig(),
    object : QRScanResult {
        override fun onSuccess(result: String) {
            // Handle scanned result
            Toast.makeText(this@MainActivity, result, Toast.LENGTH_LONG).show()
        }

        override fun onFailure(error: String) {
            Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
        }
    }
)
```

**Open Url's**
```kotlin
if (result.startsWith("http")) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(result)))
}
```


### Example Usage

**XML**
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/main"
    android:gravity="center"
    android:orientation="vertical">

    <Button
        android:id="@+id/btnScan"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Scan QR Code" />

</LinearLayout>
```

**Kotlin**
```kotlin

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ext.android_qrscanner.databinding.ActivityMainBinding
import com.ext.smartqrscanner.SmartQRScanner
import com.ext.smartqrscanner.core.QRScannerConfig
import com.ext.smartqrscanner.callback.QRScanResult

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val cameraPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                startScanner()
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnScan.setOnClickListener {
            checkCameraPermission()
        }

    }
    private fun checkCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                startScanner()
            }

            else -> {
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }
    private fun startScanner() {
        SmartQRScanner.start(
            this,
            QRScannerConfig(),
            object : QRScanResult {
                override fun onSuccess(result: String) {
                    Toast.makeText(this@MainActivity, result, Toast.LENGTH_LONG).show()
                }

                override fun onFailure(error: String) {
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}
```

---

## License

```
MIT License

Copyright (c) 2025 Excelsior Technologies 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
