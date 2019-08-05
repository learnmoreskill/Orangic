package com.orangicsmarttechnology.orangic.Fragment


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.orangicsmarttechnology.orangic.Activity.PdfviewActivity
import com.orangicsmarttechnology.orangic.Constants

import com.orangicsmarttechnology.orangic.R
import com.orangicsmarttechnology.orangic.RequestInterface
import com.orangicsmarttechnology.orangic.Utils.InternetCheck
import com.orangicsmarttechnology.orangic.adapter.NotesListAdapter
import com.orangicsmarttechnology.orangic.models.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SlcFragment : Fragment(), NotesListAdapter.Listener {

    private var mySwipeRefreshLayout : SwipeRefreshLayout? = null
    private var list_progress: ProgressBar? = null
    private var list_error_card :CardView? = null
    private var recyclerViewList : RecyclerView ? = null
    private var notesListAdapter: NotesListAdapter? = null
    private var myCompositeDisposable: CompositeDisposable? = null
    private val BASE_URL = "https://orangicsmarttechnology.com.np/"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_slc, container, false)

        activity!!.title = "SEE / SLC Notes"

        myCompositeDisposable = CompositeDisposable()

        initRecyclerView(view)

        readyForLoad(view)

        // Set Pull Down to refresh and reload
        mySwipeRefreshLayout!!.setOnRefreshListener(

                SwipeRefreshLayout.OnRefreshListener {
                    readyForLoad(view)
                    mySwipeRefreshLayout!!.setRefreshing(false)
                }
        )

        return view
    }




    //Initialise the RecyclerView

    private fun initRecyclerView(view: View) {

        //Use a layout manager to position your items to look like a standard ListView//

        list_progress = view.findViewById(R.id.list_progress)
        list_error_card = view.findViewById(R.id.list_error_card)

        mySwipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.listPullToRefresh)

        recyclerViewList = view.findViewById(R.id.recyclerViewList)
        recyclerViewList!!.layoutManager = LinearLayoutManager(view.context)

    }

    private fun readyForLoad(view: View){

        InternetCheck(object : InternetCheck.Consumer {
            override fun accept(internet: Boolean?) {
                if(internet==true){
                    loadData(view)
                }else{

                    list_progress!!.visibility = View.GONE
                    list_error_card!!.visibility = View.VISIBLE
                    Snackbar.make(view, "Please check internet connection!!", Snackbar.LENGTH_LONG).show()
                }

            }
        })
    }


    //Implement loadData//

    private fun loadData(view: View) {

        list_progress!!.visibility = View.VISIBLE
        list_error_card!!.visibility = View.GONE
        //recyclerViewList!!.visibility = View.GONE

        //Define the Retrofit request//

        val requestInterface = Retrofit.Builder()

                 //Set the API’s base URL//
                .baseUrl(BASE_URL)

                //Specify the converter factory to use for serialization and deserialization//
                .addConverterFactory(GsonConverterFactory.create())

                //Add a call adapter factory to support RxJava return types//
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                //Build the Retrofit instance//
                .build().create(RequestInterface::class.java)

        val request = ServerRequest()
        request.setOperation("getDetails")
        request.setType("slcNotes")

        //Add all RxJava disposables to a CompositeDisposable//
        myCompositeDisposable?.add(requestInterface.operation(request)

                //Send the Observable’s notifications to the main UI thread//
                .observeOn(AndroidSchedulers.mainThread())

                //Subscribe to the Observer away from the main UI thread//
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError))

    }


    private fun handleResponse(resp: ServerResponse) {


        if (resp.getStatus() == "200") {

            notesListAdapter = NotesListAdapter(resp.getNotesList(), this )

            list_progress!!.visibility = View.GONE
            list_error_card!!.visibility = View.GONE
            recyclerViewList!!.visibility = View.VISIBLE

            //Set the adapter//
            recyclerViewList!!.adapter = notesListAdapter


        } else{

            list_progress!!.visibility = View.GONE
            recyclerViewList!!.visibility = View.GONE
            list_error_card!!.visibility = View.VISIBLE

//            tv_brd_error_msg!!.text=resp.getMessage()
//            brd_error_card!!.visibility = View.VISIBLE
            Snackbar.make(view!!, resp.getMessage(), Snackbar.LENGTH_LONG).show()


        }

    }

    private fun handleError(error: Throwable) {

        list_progress!!.visibility = View.GONE
        recyclerViewList!!.visibility = View.GONE
        list_error_card!!.visibility = View.VISIBLE
        Log.d(Constants.TAG, "Failed"+error.localizedMessage)
        Snackbar.make(view!!, "Sorry, Failed to load list..", Snackbar.LENGTH_LONG).show()


    }


    override fun onItemClick(responseList: ResponseMapNotesList) {

        //If the user clicks on an item, then display a Toast//

        //Toast.makeText(context, "You clicked: ${responseList.url}", Toast.LENGTH_LONG).show()

        if (responseList.file_type.equals("pdf")){
            val intent = Intent(context, PdfviewActivity::class.java)
            intent.putExtra("title",responseList.title)
            intent.putExtra("pdfUrl",responseList.url)
            startActivity(intent)
        }else{
            Snackbar.make(view!!, "Sorry, File format is unknown!!", Snackbar.LENGTH_LONG).show()
        }



    }

    override fun onDestroy() {
        super.onDestroy()

        //Clear all your disposables//

        myCompositeDisposable?.clear()

    }


}
