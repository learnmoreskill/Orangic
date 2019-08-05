package com.orangicsmarttechnology.orangic.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import kotlinx.android.synthetic.main.fragment_interest.*
import kotlinx.android.synthetic.main.fragment_interest.view.*
import android.text.Editable
import android.text.TextWatcher


import com.orangicsmarttechnology.orangic.R




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class InterestFragment : Fragment() {

    private var startYear : Int? = 0
    private var startMonth : Int? = 0
    private var startDay : Int? = 0
    private var lastYear : Int? = 0
    private var lastMonth : Int? = 0
    private var lastDay : Int? = 0
    private var principal : Float? = 0f
    private var rate : Float? = 0f

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_interest, container, false)


        view.editTextStartYear?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {    }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {   }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (editTextStartYear.text.toString().trim().length == 4) {
                    editTextStartMonth.requestFocus()
                }
            }
        })
        view.editTextStartMonth?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {    }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {   }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (editTextStartMonth.text.toString().trim().length == 2) {
                    editTextStartDay.requestFocus()
                }
            }
        })
        view.editTextStartDay?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {    }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {   }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (editTextStartDay.text.toString().trim().length == 2) {
                    editTextLastYear.requestFocus()
                }
            }
        })
        view.editTextLastYear?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {    }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {   }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (editTextLastYear.text.toString().trim().length == 4) {
                    editTextLastMonth.requestFocus()
                }
            }
        })
        view.editTextLastMonth?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {    }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {   }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (editTextLastMonth.text.toString().trim().length == 2) {
                    editTextLastDay.requestFocus()
                }
            }
        })
        view.editTextLastDay?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {    }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {   }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (editTextLastDay.text.toString().trim().length == 2) {
                    editTextPrincipal.requestFocus()
                }
            }
        })


        view.buttonInterest.setOnClickListener {


            if (editTextStartYear!!.text.toString().trim().isEmpty() || editTextStartYear.text.toString().toInt()<2000 || editTextStartYear.text.toString().toInt()>3000 ){
                editTextStartYear?.error="रकम लिएको बर्ष लेख्नुहोस(2000 देखि 3000)"
                editTextStartYear.requestFocus()
                return@setOnClickListener
            }
            if (editTextStartMonth!!.text.toString().trim().isEmpty() || editTextStartMonth.text.toString().toInt()<1 || editTextStartMonth.text.toString().toInt()>12 ){
                editTextStartMonth?.error="रकम लिएको महिना लेख्नुहोस(1 देखि 12)"
                editTextStartMonth.requestFocus()
                return@setOnClickListener
            }
            if (editTextStartDay!!.text.toString().trim().isEmpty() || editTextStartDay.text.toString().toInt()<1 || editTextStartDay.text.toString().toInt()>31 ){
                editTextStartDay?.error="रकम लिएको दिन लेख्नुहोस(1 देखि 31)"
                editTextStartDay.requestFocus()
                return@setOnClickListener
            }
            if (editTextLastYear!!.text.toString().trim().isEmpty() || editTextLastYear.text.toString().toInt()<2000 || editTextLastYear.text.toString().toInt()>3000 ){
                editTextLastYear?.error="रकम बुझाउने बर्ष लेख्नुहोस(2000 देखि 3000)"
                editTextLastYear.requestFocus()
                return@setOnClickListener
            }
            if (editTextLastMonth!!.text.toString().trim().isEmpty() || editTextLastMonth.text.toString().toInt()<1 || editTextLastMonth.text.toString().toInt()>12 ){
                editTextLastMonth?.error="रकम बुझाउने महिना लेख्नुहोस(1 देखि 12)"
                editTextLastMonth.requestFocus()
                return@setOnClickListener
            }
            if (editTextLastDay!!.text.toString().trim().isEmpty() || editTextLastDay.text.toString().toInt()<1 || editTextLastDay.text.toString().toInt()>31 ){
                editTextLastDay?.error="रकम बुझाउने दिन लेख्नुहोस(1 देखि 31)"
                editTextLastDay.requestFocus()
                return@setOnClickListener
            }
            if (editTextPrincipal!!.text.toString().trim().isEmpty() || editTextPrincipal.text.toString().toFloat()<1.00 || editTextPrincipal.text.toString().toFloat()>9999999999.00 ){
                editTextPrincipal?.error="मूलधन लेख्नुहोस(1 देखि 9999999999)"
                editTextPrincipal.requestFocus()
                return@setOnClickListener
            }
            if (editTextRate!!.text.toString().trim().isEmpty() || editTextRate.text.toString().toFloat()<0.01 || editTextRate.text.toString().toFloat()>100.00 ){
                editTextRate?.error="ब्याज दर लेख्नुहोस(0.01 देखि 100)"
                editTextRate.requestFocus()
                return@setOnClickListener
            }

            startYear = editTextStartYear!!.text.toString().toInt()
            startMonth = editTextStartMonth!!.text.toString().toInt()
            startDay = editTextStartDay!!.text.toString().toInt()
            lastYear = editTextLastYear!!.text.toString().toInt()
            lastMonth = editTextLastMonth!!.text.toString().toInt()
            lastDay = editTextLastDay!!.text.toString().toInt()
            principal = editTextPrincipal!!.text.toString().toFloat()
            rate = editTextRate!!.text.toString().toFloat()


            var dDay : Int? = 0
            var saptiMonth : Int? = 0
            var dMonth : Int? = 0
            var saptiYear : Int? = 0
            var dYear : Int? = 0
            var totalDays : Int? = 0

            if (lastDay!!>startDay!!){
                dDay = lastDay!! - startDay!!
            }else if (lastDay!! == startDay!!){
                dDay = 0
            }else{
                dDay = (lastDay!!+30)-startDay!!
                saptiMonth = 1
            }

            lastMonth = lastMonth!!-saptiMonth!!

            if (lastMonth!! > startMonth!!){
                dMonth = lastMonth!! - startMonth!!
            }else if (lastMonth!! == startMonth!!){
                dMonth = 0
            }else{
                dMonth = (lastMonth!!+12)-startMonth!!
                saptiYear = 1
            }

            lastYear = lastYear!! - saptiYear!!

            if (lastYear!! > startYear!!){
                dYear = lastYear!! - startYear!!
            }else if (lastYear!! == startYear!!){
                dYear = 0
            }else{
                Toast.makeText(context, "रकम लिएको मितिभन्दा रकम बुझाउने मिति कम छ, कृपया जांच गर्नुहोस", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

           totalDays = dDay+(dMonth*30)+(dYear*360)


            fun interest(principal : Float, rate : Float, totalDays : Int): Float{

                var tdays = totalDays
                var interest : Float = 0f
                var total : Float = 0f
                var months = 0
                var days = 0

                total += principal

                while (tdays >= 360){

                    total += (total*12*rate)/100

                    tdays = tdays-360
                }

                months = tdays/30
                days = tdays%30

                if (months >0){
                    interest += (total*months*rate)/100
                }

                if (days >0){

                    interest+= (((total*rate)/100)/30)*days

                }

                total += interest

                return total

            }

            var showYear = totalDays/360
            var showMonth = (totalDays%360)/30
            var showDays = (totalDays%360)%30

            var ci = interest(principal!!,rate!!,totalDays)
            ci = Math.round(ci*100.0f)/100.0f

            var i = ci-principal!!
            i = Math.round(i*100.0f)/100.0f


            textViewDuration!!.setText(""+showYear+" साल "+showMonth+" महिना "+showDays+" दिन")
            textViewInterest!!.setText(""+i)
            textViewTotal!!.setText(""+ci)

        }
        view.buttonClear.setOnClickListener {
            editTextStartYear.text.clear()
            editTextStartMonth.text.clear()
            editTextStartDay.text.clear()
            editTextLastYear.text.clear()
            editTextLastMonth.text.clear()
            editTextLastDay.text.clear()
            editTextPrincipal.text.clear()
            editTextRate.text.clear()
            textViewDuration!!.setText("")
            textViewInterest!!.setText("")
            textViewTotal!!.setText("")

            editTextStartYear.requestFocus()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //you can set the title for your toolbar here for different fragments different titles
        activity!!.title = "घरायसी ब्याज"

    }

}