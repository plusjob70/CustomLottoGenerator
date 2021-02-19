package com.example.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import kotlinx.android.synthetic.main.activity_statistics.*

class StatisticsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val period = intent.getIntExtra("period", 0)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        webView.apply {
            settings.javaScriptEnabled = true
            settings.builtInZoomControls = true
            settings.displayZoomControls = false
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            webViewClient = WebViewClient()
        }

        when (period) {
            0 -> webView.loadUrl("https://dhlottery.co.kr/gameResult.do?method=statByNumber")
            5 -> webView.loadUrl("https://dhlottery.co.kr/gameResult.do?method=noViewNumber&sltPeriod=5")
            8 -> webView.loadUrl("https://dhlottery.co.kr/gameResult.do?method=noViewNumber&sltPeriod=8")
            10 -> webView.loadUrl("https://dhlottery.co.kr/gameResult.do?method=noViewNumber&sltPeriod=10")
            15 -> webView.loadUrl("https://dhlottery.co.kr/gameResult.do?method=noViewNumber&sltPeriod=15")
        }
    }

    override fun onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack()
        }else{
            super.onBackPressed()
        }
    }
}