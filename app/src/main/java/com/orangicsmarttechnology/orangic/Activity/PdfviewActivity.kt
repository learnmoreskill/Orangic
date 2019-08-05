package com.orangicsmarttechnology.orangic.Activity

import android.Manifest
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.krishna.fileloader.FileLoader
import com.krishna.fileloader.listener.FileRequestListener
import com.krishna.fileloader.pojo.FileResponse
import com.krishna.fileloader.request.FileLoadRequest
import com.orangicsmarttechnology.orangic.R
import kotlinx.android.synthetic.main.activity_pdfview.*
import java.io.File

class PdfviewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdfview)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        storagePermission()


        if (intent != null){

            supportActionBar!!.title = intent.getStringExtra("title")


            val pdfUrl = intent.getStringExtra("pdfUrl")
            if (!TextUtils.isEmpty(pdfUrl) || pdfUrl != null){


                progress_bar.visibility = View.VISIBLE

                FileLoader.with(this)
                        .load(pdfUrl,false)
                        .fromDirectory("Orangic/files/",FileLoader.DIR_EXTERNAL_PUBLIC)
                        .asFile(object:FileRequestListener<File>{
                            override fun onLoad(p0: FileLoadRequest?, p1: FileResponse<File>?) {
                                progress_bar.visibility = View.GONE

                                val pdffile = p1!!.body

                                pdf_view.fromFile(pdffile)
                                        .password("krishnahackster@orangic")
                                        .defaultPage(0)
                                        .enableSwipe(true) // allows to block changing pages using swipe
                                        .swipeHorizontal(false)
                                        .enableDoubletap(true)
                                        //.onDraw(canvas,pageWidh)
                                        //.onPageChange(onPageChangeListener)
                                        //.onTap( false )
                                        //.onRender(onRenderListener) // called after document is rendered for the first time

                                        //.pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
                                        // allows to draw something on the current page, usually visible in the middle of the screen
                                        // allows to draw something on all pages, separately for every page. Called only for visible pages
                                        //.onDrawAll(onDrawListener)
                                        //.onLoad(onLoadCompleteListener) // called after document is loaded and starts to be rendered
                                        //.onPageScroll(onPageScrollListener)
                                        //.onError(onErrorListener)
                                        //.onPageError(onPageErrorListener)
                                        // called on single tap, return true if handled, false to toggle scroll handle visibility
                                        //.onLongPress(onLongPressListener)
                                        .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)

                                        .scrollHandle(null)
                                        .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                                        // spacing between pages in dp. To define spacing color, set view background
                                        .spacing(0)
                                        //.autoSpacing(false) // add dynamic spacing to fit each page on its own on the screen
                                        //.linkHandler(DefaultLinkHandler)
                                        //.pageFitPolicy(FitPolicy.WIDTH)
                                        //.pageSnap(true) // snap pages to screen boundaries
                                        //.pageFling(false) // make a fling change only a single page like ViewPager
                                        //.nightMode(false) // toggle night mode
                                        .load()

                            }

                            override fun onError(p0: FileLoadRequest?, p1: Throwable?) {

                                Toast.makeText(this@PdfviewActivity,""+p1!!.message,Toast.LENGTH_SHORT).show()

                                progress_bar.visibility = View.GONE

                            }

                        })
            }
        }

    }

    private fun storagePermission() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        //Toast.makeText(this@PdfviewActivity, "Permission Granted", Toast.LENGTH_LONG).show()
                    }

                    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {
                        AlertDialog.Builder(this@PdfviewActivity)
                                .setTitle("Storage permission required")
                                .setMessage("In order to read notes,Storage permission required")
                                .setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener {
                                    dialogInterface, i ->
                                    dialogInterface.dismiss()
                                    token?.cancelPermissionRequest()
                                })
                                .setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener {
                                    dialogInterface, i ->
                                    dialogInterface.dismiss()
                                    token?.continuePermissionRequest()
                                })
                                .show()
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                        onBackPressed()
                        //Toast.makeText(this@PdfviewActivity, "Storage permission is required in order to take a photo with the camera", Toast.LENGTH_LONG).show()
                    }
                }
                ).check()
    }



    override fun onBackPressed() {
        super.onBackPressed()
        finish()
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
