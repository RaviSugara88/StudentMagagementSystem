package com.ravitech.ignounew.ui.notifications

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ravitech.ignounew.R
import com.ravitech.ignounew.databinding.FragmentNotificationsBinding
import com.ravitech.ignounew.uital.xtnLog

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        handleView()
        return root
    }

    private fun handleView() {
        binding.apply {
            val eventF: Spannable = SpannableString(resources.getString(R.string.event_f))
            eventF.setSpan(myClickableSpan(0),0,eventF.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            eventF.setSpan( ForegroundColorSpan(resources.getColor(R.color.purple_500)), 0,eventF.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            event1.text=eventF
            event1.movementMethod = LinkMovementMethod.getInstance()
            val eventS: Spannable = SpannableString(resources.getString(R.string.event_s))
            eventS.setSpan(myClickableSpan(1),0,eventS.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            eventS.setSpan( ForegroundColorSpan(resources.getColor(R.color.purple_500)), 0,eventS.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            event2.text=eventS
            event2.movementMethod = LinkMovementMethod.getInstance()

            val eventT: Spannable = SpannableString(resources.getString(R.string.event_th))
            eventT.setSpan(myClickableSpan(2),0,eventT.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            eventT.setSpan( ForegroundColorSpan(resources.getColor(R.color.purple_500)), 0,eventT.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            event3.text=eventT
            event3.movementMethod = LinkMovementMethod.getInstance()

            val eventFo: Spannable = SpannableString(resources.getString(R.string.event_fo))
            eventFo.setSpan(myClickableSpan(3),0,eventFo.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            eventFo.setSpan( ForegroundColorSpan(resources.getColor(R.color.purple_500)), 0,eventFo.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            event4.text=eventFo
            event4.movementMethod = LinkMovementMethod.getInstance()

            val eventFi: Spannable = SpannableString(resources.getString(R.string.event_fi))
            eventFi.setSpan(myClickableSpan(4),0,eventFi.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            eventFi.setSpan( ForegroundColorSpan(resources.getColor(R.color.purple_500)), 0,eventFi.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            event5.text=eventFi
            event5.movementMethod = LinkMovementMethod.getInstance()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class myClickableSpan(var pos: Int) : ClickableSpan() {
        override fun onClick(p0: View) {
            xtnLog("fgxffg")
            when(pos){
                0->{
                    val openURL = Intent(Intent.ACTION_VIEW)
                    openURL.data = Uri.parse("https://exam.ignou.ac.in/")
                    startActivity(openURL)
                    // xtnLog("clickrd $pos")
                }
                1->{
                    //  xtnLog("clickrd $pos")
                    val openURL = Intent(Intent.ACTION_VIEW)
                    openURL.data = Uri.parse("https://projects.ignou.ac.in/PROJECTJUN22/")
                    startActivity(openURL)


                }
                2->{
                    val openURL = Intent(Intent.ACTION_VIEW)
                    openURL.data = Uri.parse("http://www.ignou.ac.in/userfiles/datesheet.pdf")
                    startActivity(openURL)
                }
                3->{
                    val openURL = Intent(Intent.ACTION_VIEW)
                    openURL.data = Uri.parse("http://www.ignou.ac.in/ignou/bulletinboard/news/latest/detail/Admission_Portals_for_Online_and_ODL_Distance_Programmmes_for_July_2022_Session-853")
                    startActivity(openURL)
                }
                4->{
                    val openURL = Intent(Intent.ACTION_VIEW)
                    openURL.data = Uri.parse("http://www.ignou.ac.in/ignou/bulletinboard/news/latest/detail/Declaration_of_PhD_result_2021-_Computer_ScienceNursingManagementCommerceChild_DevelopmentEducationAnthropologyWomen_StudiesEnglish_and_Sanskrit_-849")
                    startActivity(openURL)
                }
            }
            //  TODO("Not yet implemented")
        }
    }

}