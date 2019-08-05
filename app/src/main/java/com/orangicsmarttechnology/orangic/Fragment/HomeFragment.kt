package com.orangicsmarttechnology.orangic.Fragment


import android.annotation.TargetApi
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.orangicsmarttechnology.orangic.Activity.AboutActivity
import com.orangicsmarttechnology.orangic.MainActivity

import com.orangicsmarttechnology.orangic.R
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment(), View.OnClickListener  {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)



        val newsLayout = view.findViewById<View>(R.id.idNews) as LinearLayout
        newsLayout.setOnClickListener(this)

        val cinterestLayout = view.findViewById<View>(R.id.idCompoundInterest) as LinearLayout
        cinterestLayout.setOnClickListener(this)

        val slcLayout = view.findViewById<View>(R.id.idSlc) as LinearLayout
        slcLayout.setOnClickListener(this)

        val emergencyLayout = view.findViewById<View>(R.id.idEmergencyNumber) as LinearLayout
        emergencyLayout.setOnClickListener(this)

        val aboutLayout = view.findViewById<View>(R.id.idAbout) as LinearLayout
        aboutLayout.setOnClickListener(this)



        // Inflate the layout for this fragment
        return view
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    override fun onClick(v: View) {
        when (v.id) {

            R.id.idNews -> ((activity as MainActivity).replaceNewsFragment())

            R.id.idCompoundInterest -> ((activity as MainActivity).replaceCompoundInterestFragment())

            R.id.idSlc -> ((activity as MainActivity).replaceSlcFragment())

            R.id.idEmergencyNumber -> ((activity as MainActivity).replaceEmergencyNumberFragment())

            R.id.idAbout -> {
                var intent = Intent(activity, AboutActivity::class.java)
                startActivity(intent)
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //you can set the title for your toolbar here for different fragments different titles
        activity!!.title = "Orangic"

    }


}
