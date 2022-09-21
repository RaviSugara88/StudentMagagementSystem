package com.ravitech.ignounew.ui.subject

import android.content.res.AssetManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ravitech.ignounew.R
import com.ravitech.ignounew.databinding.SubjectFragmentBinding
import com.ravitech.ignounew.model.SemData
import com.ravitech.ignounew.ui.dashboard.adapter.DashboardAdapter
import com.ravitech.ignounew.ui.viewPdf.ViewPdfActivity
import com.ravitech.ignounew.uital.xtnNavigate
import com.studentmanagement.ui.subject.SubjectViewModel
import com.ravitech.ignounew.uital.SUBJECT_ID
import java.io.IOException


class SubjectFragment : Fragment() {

    private  val viewModel: SubjectViewModel by viewModels()
    private lateinit var binding : SubjectFragmentBinding
    private lateinit var dashboardAdapter: DashboardAdapter
    private val args:SubjectFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (this::binding.isInitialized.not()){
            binding = SubjectFragmentBinding.inflate(inflater)
            handleView()
        }
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun handleView() {
        val id = args.id
        var subList:Array<String>? =null
        binding.apply {
            dashboardAdapter = DashboardAdapter(requireContext()){
                entity, type, po ->
                when(type){
                    0->{
                        requireContext().xtnNavigate<ViewPdfActivity>(
                            Bundle().apply {
                                putInt(SUBJECT_ID,entity.id)
                            })
                    }
                }

            }
            val list = mutableListOf<SemData>()
            when(id){
                1->subList = resources.getStringArray(R.array.subject_sem_1)
                2->subList = resources.getStringArray(R.array.subject_sem_2)
                3->subList = resources.getStringArray(R.array.subject_sem_3)
                4->subList = resources.getStringArray(R.array.subject_sem_4)
                5->subList = resources.getStringArray(R.array.subject_sem_5)
            }

            subList!!
                .forEachIndexed { index, s ->
                    list.add(SemData(index, s))
                }

            /*val layoutManager = GridLayoutManager(this, 3, RecyclerView.HORIZONTAL, false)
layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
    override fun getSpanSize(position: Int) = when (position % 2) {
        0 -> 1 // 50dp item
        else -> 2 // 100dp item
    }
}*/
            rvSubject.recyclerView.apply {
                setHasFixedSize(true)
                val layoutM = GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)
                layoutManager = layoutM
                adapter = dashboardAdapter
                dashboardAdapter.swapData(list)
            }

        }

    }

    /**
     * This method will return all pdf files found in the assets folder. Note: will not traverse
     * nested directory. Returned list is not sorted
     * @return List of file names
     */
    private fun getPDFFromAssets(): List<String>? {
        val pdfFiles: MutableList<String> = ArrayList()
        val assetManager: AssetManager = requireActivity().getAssets()
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
            Toast.makeText(requireContext(),
                mesg,
                Toast.LENGTH_LONG
            )
                .show()
        }
        return pdfFiles
    }
}