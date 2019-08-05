package com.orangicsmarttechnology.orangic.Activity

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.orangicsmarttechnology.orangic.R
import kotlinx.android.synthetic.main.activity_news_page.*
import org.jetbrains.anko.toast
import android.view.KeyEvent.KEYCODE_BACK
import android.support.v4.widget.SwipeRefreshLayout


class NewsPageActivity : AppCompatActivity() {


    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_page)

        supportActionBar!!.title = "News"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        var newsUrl: String = intent.getStringExtra("newsUrl")

//        Toast.makeText(this,newsUrl,Toast.LENGTH_LONG).show()

        val webView = findViewById<WebView>(R.id.webView_news)

        val mySwipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.pullToRefresh)

        // Get the web view settings instance
        val webSettings = webView.settings

        // Enable java script in web view
        webSettings.javaScriptEnabled = true

        //scrolll overlay
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)


        // Enable and setup web view cache
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK)
        webSettings.setAppCacheEnabled(true)


        webSettings.setDomStorageEnabled(true)
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS)
        webSettings.setUseWideViewPort(true)
        webSettings.setEnableSmoothTransition(true)

        // Enable zooming in web view
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false


        //Improve webView performance
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webSettings.setSupportMultipleWindows(true)
        webSettings.loadWithOverviewMode = true
        webSettings.allowContentAccess = true
        webSettings.setGeolocationEnabled(true)
        webSettings.allowUniversalAccessFromFileURLs = true
        webSettings.allowFileAccess = true

        // WebView settings
        webView.fitsSystemWindows = true




        //important to open url in your app
        webView.webViewClient = object : WebViewClient() {
            //No internet message handle
            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                webView.loadUrl("file:///android_asset/error.html")
            }
        }
        webView.loadUrl(newsUrl)

        // Set Pull Down to refresh and reload
        mySwipeRefreshLayout!!.setOnRefreshListener(

                SwipeRefreshLayout.OnRefreshListener {
                    webView.reload()
                    mySwipeRefreshLayout.setRefreshing(false)
                }
        )



        // Set web view client
        webView.webViewClient = object: WebViewClient(){
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                // Page loading started
                // Do something
                //toast("Page loading.")

                // Enable disable back forward button
                //button_back.isEnabled = web_view.canGoBack()
                //button_forward.isEnabled = web_view.canGoForward()
            }

            override fun onPageFinished(view: WebView, url: String) {
                // Page loading finished
                // Display the loaded page title in a toast message
                //toast("Page loaded: ${view.title}")
                supportActionBar!!.title =" ${view.title}"

                // Enable disable back forward button
                //button_back.isEnabled = web_view.canGoBack()
                //button_forward.isEnabled = web_view.canGoForward()
            }
        }

        // Set web view chrome client
        webView.webChromeClient = object: WebChromeClient(){
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                progress_bar.progress = newProgress
            }
        }




        webView.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                    webView.goBack()
                    return true
                }
                return false
            }
        })

    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                //NavUtils.navigateUpFromSameTask(this)
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
