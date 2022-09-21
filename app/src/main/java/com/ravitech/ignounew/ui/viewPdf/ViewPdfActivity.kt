package com.ravitech.ignounew.ui.viewPdf

import android.annotation.SuppressLint
import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.ravitech.ignounew.R
import com.ravitech.ignounew.databinding.ActivityViewPdfBinding
import com.ravitech.ignounew.ui.LoginActivity
import com.ravitech.ignounew.uital.xtnLog
import com.ravitech.ignounew.uital.xtnNavigate2
import com.ravitech.ignounew.uital.SUBJECT_ID
import java.io.IOException

class ViewPdfActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewPdfBinding
    private var id = -1
    private var url =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPdfBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pdfPath = getPDFFromAssets()//assets.list("uuuu.pdf")
        xtnLog(pdfPath.toString(),"ViewPdfActivity")
        binding.toolbar.apply {
            textTitle.setText(resources.getString(R.string.subject))
            btnLogout.setOnClickListener {
                xtnNavigate2<LoginActivity>()
            }

        }

        try {
            intent?.extras?.let {
                id = it.getInt(SUBJECT_ID,-1)
            }

        }catch (e:Exception){

        }


        when(id){
            0->{
                url = "https://drive.google.com/file/d/1fXH_9Z2bbec1yr9q0L_AFPb4DqJP1U2U/view?usp=sharing"
            }
            1->{
               url = "https://drive.google.com/file/d/1MheQP2WxvQP5fJA9wWaiHnLBnMhpaMjT/view?usp=sharing"
            }
            2->{
              url ="https://drive.google.com/file/d/15qHFPUTUMoa2WnkALqlKVbXfhqXTBxFe/view?usp=sharing"
            }
            else ->{
                url ="https://drive.google.com/file/d/1XXI74jmRf7pF8EqxdC3jLvMAmbpalfs2/view?usp=sharing"

            }
        }
        binding.isVisiableProgress = true
        loadUrl(url)
            // pdfView=findViewById(R.id.pdfv);
       // viewPdf.fromAsset("uuuu.pdf").load();
    }
    @SuppressLint("SetJavaScriptEnabled")
    private fun loadUrl(url: String) {
        binding.webView.apply {
            clearCache(true)
            clearHistory()
            setWebViewClient(AppWebViewClients())
            settings.javaScriptEnabled = true
            settings.useWideViewPort = true
            settings.loadWithOverviewMode = true
            settings.builtInZoomControls = true
            settings.displayZoomControls = false
            fitsSystemWindows = true
            webViewClient = AppWebViewClients()
            binding.isVisiableProgress = true
            loadUrl(url)

        }

    }

    inner class AppWebViewClients : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            // TODO Auto-generated method stub
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            // TODO Auto-generated method stub
            // function recall
            if (view.title.equals(""))
                view.reload()
            else
                binding.isVisiableProgress = false

            super.onPageFinished(view, url)
        }

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            binding.isVisiableProgress = false
            super.onReceivedError(view, request, error)
        }
    }

    private fun getPDFFromAssets(): List<String>? {
        val pdfFiles: MutableList<String> = ArrayList()
        val assetManager: AssetManager = assets
        try {
            for (name in assetManager.list("")!!) {
                // include files which end with pdf only
                if (name.toLowerCase().endsWith("pdf")) {
                    pdfFiles.add(name)
                }
            }
        } catch (ioe: IOException) {
            val mesg = "Could not read files from assets folder"
            //  Log.e(TAG, mesg)
            Toast.makeText(this,
                mesg,
                Toast.LENGTH_LONG
            )
                .show()
        }
        return pdfFiles
    }

}