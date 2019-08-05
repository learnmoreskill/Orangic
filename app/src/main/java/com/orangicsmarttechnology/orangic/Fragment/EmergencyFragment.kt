package com.orangicsmarttechnology.orangic.Fragment


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast


import com.orangicsmarttechnology.orangic.R
import kotlinx.android.synthetic.main.fragment_emergency.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class EmergencyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_emergency, container, false)


        view.callPoliceControl.setOnClickListener {
            dialNumber("100")
        }
        view.callPoliceEmergencyNumber.setOnClickListener {
            dialNumber("4228435")
        }
        view.callAmbulance.setOnClickListener {
            dialNumber("102")
        }
        view.callFireTruck.setOnClickListener {
            dialNumber("101")
        }
        view.callTrafficPolice.setOnClickListener {
            dialNumber("103")
        }
        view.callBloodBank.setOnClickListener {
            dialNumber("4225344")
        }

        view.callNepalTourism.setOnClickListener {
            dialNumber("4256909")
        }

        view.callNepalTelecom.setOnClickListener {
            dialNumber("1498")
        }

        view.callNcell.setOnClickListener {
            dialNumber("9809005000")
        }

        view.callWorldlink.setOnClickListener {
            dialNumber("8449675354")
        }

        view.callEsewa.setOnClickListener {
            dialNumber("16600102121")
        }



        return view
    }

    fun dialNumber(number: String){

        try {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()

            Toast.makeText(context, "Sorry,Permission denied", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //you can set the title for your toolbar here for different fragments different titles
        activity!!.title = "Emergency Number"

    }


}
