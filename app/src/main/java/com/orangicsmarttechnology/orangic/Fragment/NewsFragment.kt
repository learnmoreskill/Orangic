package com.orangicsmarttechnology.orangic.Fragment


import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.orangicsmarttechnology.orangic.R
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_news.view.*
import android.content.Intent
import com.orangicsmarttechnology.orangic.Activity.NewsPageActivity


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NewsFragment : Fragment() {

    private val mDrawerHandler = Handler()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_news, container, false)

//        var fragment: Fragment?=null
//        var urlOfNews: String?=null


        view.ekantipur.setOnClickListener { view ->

            goToNewsPage("https://learnmoreskill.github.io/orangic/newsredirect.html?newsurl=ekantipur")


            /*fragment = NewsPageFragment()
            if(fragment != null){
                mDrawerHandler.postDelayed({
                    val ft = fragmentManager?.beginTransaction()

                   // urlOfNews = 'aaa'.toString()

                    val args = Bundle()
                    args.putString("newsUrl", "http://www.appantv.com.np/")
                    fragment?.setArguments(args)

                    ft?.replace(R.id.container_body, fragment as NewsPageFragment )
                    //ft?.addToBackStack("tag")
                    ft?.commit()
                },250)
            }*/

        }

        view.appantv.setOnClickListener { view ->
            goToNewsPage("https://learnmoreskill.github.io/orangic/newsredirect.html?newsurl=appantv")
        }
        view.ratopati.setOnClickListener { view ->
            goToNewsPage("https://learnmoreskill.github.io/orangic/newsredirect.html?newsurl=ratopati")
        }
        view.nepalsamaya.setOnClickListener { view ->
            goToNewsPage("https://learnmoreskill.github.io/orangic/newsredirect.html?newsurl=nepalsamaya")
        }
        view.thahakhabar.setOnClickListener { view ->
            goToNewsPage("https://learnmoreskill.github.io/orangic/newsredirect.html?newsurl=thahakhabar")
        }
        view.onlinekhabar.setOnClickListener { view ->
            goToNewsPage("https://learnmoreskill.github.io/orangic/newsredirect.html?newsurl=onlinekhabar")
        }
        view.ujyaalo.setOnClickListener { view ->
            goToNewsPage("https://learnmoreskill.github.io/orangic/newsredirect.html?newsurl=unn")
        }
        view.newsbureau.setOnClickListener { view ->
            goToNewsPage("https://learnmoreskill.github.io/orangic/newsredirect.html?newsurl=enewsbureau")
        }
        view.setopati.setOnClickListener { view ->
            goToNewsPage("https://learnmoreskill.github.io/orangic/newsredirect.html?newsurl=setopati")
        }
        view.gorkhapatra.setOnClickListener { view ->
            goToNewsPage("https://learnmoreskill.github.io/orangic/newsredirect.html?newsurl=gorkhapatra")
        }
        view.annapurnapost.setOnClickListener { view ->
            goToNewsPage("https://learnmoreskill.github.io/orangic/newsredirect.html?newsurl=annapurnapost")
        }
        view.nagriknews.setOnClickListener { view ->
            goToNewsPage("https://learnmoreskill.github.io/orangic/newsredirect.html?newsurl=nagriknews")
        }
        view.thehimalayan.setOnClickListener { view ->
            goToNewsPage("https://learnmoreskill.github.io/orangic/newsredirect.html?newsurl=thehimalayan")
        }
        view.myrepublica.setOnClickListener { view ->
            goToNewsPage("https://learnmoreskill.github.io/orangic/newsredirect.html?newsurl=myrepublica")
        }
        view.mysansar.setOnClickListener { view ->
            goToNewsPage("https://learnmoreskill.github.io/orangic/newsredirect.html?newsurl=mysansar")
        }
        view.bbcnepali.setOnClickListener { view ->
            goToNewsPage("https://learnmoreskill.github.io/orangic/newsredirect.html?newsurl=bbcnepali")
        }






        // Inflate the layout for this fragment
        return view
    }

    fun goToNewsPage(newsUrl:String){

        val intent = Intent(activity, NewsPageActivity::class.java)
        // To pass any data to next activity
        intent.putExtra("newsUrl", newsUrl)
        // start your next activity
        startActivity(intent)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //you can set the title for your toolbar here for different fragments different titles
        activity!!.title = "News"

    }





}
