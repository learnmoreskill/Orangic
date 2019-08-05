package com.orangicsmarttechnology.orangic


import android.Manifest
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.orangicsmarttechnology.orangic.Activity.AboutActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.jetbrains.anko.startActivity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.orangicsmarttechnology.orangic.Fragment.*
import com.orangicsmarttechnology.orangic.helper.ForceUpdateChecker


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ForceUpdateChecker.OnUpdateNeededListener {

    lateinit var mAdView : AdView
    private val mDrawerHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //INITIALIZE APP ID
        MobileAds.initialize(this, getString(R.string.ad_app_id))

        // Find Banner ad
        mAdView = findViewById(R.id.myBannerAdView)
        val adRequest = AdRequest.Builder().build()
        // Display Banner ad
        mAdView.loadAd(adRequest)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        displayScreen(-1)


        //Check update from firebase remote config on each start of app
        ForceUpdateChecker.with(this).onUpdateNeeded(this).check()

        validatePermission()
    }


    private fun validatePermission() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        // Toast.makeText(this@MainActivity, "Permission Granted", Toast.LENGTH_LONG).show()
                    }

                    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {
                        AlertDialog.Builder(this@MainActivity)
                                .setTitle("Storage permission required")
                                .setMessage("In order to take a picture, you must grant this permission")
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
                        Toast.makeText(this@MainActivity, "Permission denied", Toast.LENGTH_LONG).show()
                    }
                }
                ).check()
    }

    override fun onUpdateNeeded() {
        val dialog = AlertDialog.Builder(this)
                .setTitle("New version available")
                .setMessage("Please, update app to new version to continue use.")
                .setPositiveButton("Update"
                ) { dialog, which -> redirectStore() }.setNegativeButton("No, thanks"
                ) { dialog, which ->
                    //finish();
                    dialog.dismiss()
                }.create()
        dialog.show()
    }



    override fun onUpdateNeededManually(yesno: Boolean?) {

        if (yesno!!) {
            val dialog = AlertDialog.Builder(this)
                    .setTitle("New version available")
                    .setMessage("Please, update app to new version to get new features.")
                    .setPositiveButton("Update"
                    ) { dialog, which -> redirectStore() }.setNegativeButton("Cancel"
                    ) { dialog, which -> dialog.dismiss() }.create()
            dialog.show()
        } else {
            val dialog = AlertDialog.Builder(this)
                    .setTitle("Update Version")
                    .setMessage("This is the newest version")
                    .setPositiveButton("Ok"
                    ) { dialog, which -> }.create()
            dialog.show()
        }

    }

    private fun redirectStore() {
        val appPackageName = packageName // getPackageName() from Context or Activity object
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
        } catch (anfe: android.content.ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
        }
    }

    fun replaceHomeFragment() {
        val fragment: Fragment
        fragment = HomeFragment()
            mDrawerHandler.postDelayed({
                val ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.container_body, fragment)
                ft.commit()
            },250)
    }

    fun replaceNewsFragment() {
        val fragment: Fragment
        fragment = NewsFragment()
            mDrawerHandler.postDelayed({
                val ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.container_body, fragment)
                ft.commit()
            },250)
    }

    fun replaceSlcFragment() {
        val fragment: Fragment
        fragment = SlcFragment()
        mDrawerHandler.postDelayed({
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.container_body, fragment)
            ft.commit()
        },250)
    }

    fun replaceCompoundInterestFragment() {
        val fragment: Fragment
        fragment = InterestFragment()
            mDrawerHandler.postDelayed({
                val ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.container_body, fragment)
                ft.commit()
            },250)
    }

    fun replaceEmergencyNumberFragment() {
        val fragment: Fragment
        fragment = EmergencyFragment()
            mDrawerHandler.postDelayed({
                val ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.container_body, fragment)
                ft.commit()
            },250)
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {

        val currentFragment =  getSupportFragmentManager().findFragmentById(R.id.container_body).toString().split("{")[0]

        val homeFragment = HomeFragment().toString().split("{")[0]

        // First check whether drawer is open or not
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else if (currentFragment.equals(homeFragment)) {

            if (doubleBackToExitPressedOnce) {
                super.onBackPressed() //Close App
                return
            }
            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)

        }
//        else if (fragment is NewsPageFragment) {
//            displayInterstitial()
//        }
        else {

            replaceHomeFragment()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            //R.id.action_settings -> return true
            R.id.action_exit -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun displayScreen(id: Int){

        var fragment: Fragment?=null

        when (id){
            R.id.nav_id_first -> {

                fragment = HomeFragment()

            }
            R.id.nav_id_second -> {

                fragment = NewsFragment()

            }
            R.id.nav_id_third -> {

                fragment = InterestFragment()



//                val intent = Intent(this@MainActivity,PdfviewActivity::class.java)
//                intent.putExtra("title","SEE Notes")
//                intent.putExtra("pdfUrl","http://unec.edu.az/application/uploads/2014/12/pdf-sample.pdf")
//                startActivity(intent)

            }
            R.id.nav_id_four -> {

                fragment = EmergencyFragment()

            }
            R.id.nav_sub_id_first -> {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, resources.getText(R.string.share_link))
                startActivity(Intent.createChooser(shareIntent, resources.getText(R.string.app_name)))

            }
            R.id.nav_sub_id_second -> {

                ForceUpdateChecker.with(this).onUpdateNeeded(this).checkManually()

            }
            R.id.nav_sub_id_third -> {

                mDrawerHandler.postDelayed({
                    startActivity<AboutActivity>()
                },250)

            }
            else -> {

                fragment = HomeFragment()

            }
        }
        if(fragment != null){
            mDrawerHandler.postDelayed({
                val ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.container_body, fragment)
                ft.commit()
            },250)
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        displayScreen(item.itemId)

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
