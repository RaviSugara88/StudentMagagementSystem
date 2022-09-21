package com.ravitech.ignounew.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ravitech.ignounew.databinding.FragmentHomeBinding
import com.ravitech.ignounew.model.SemData
import com.ravitech.ignounew.ui.dashboard.adapter.DashboardAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var sem = mutableListOf<SemData>()
    private lateinit var dashboardAdapter: DashboardAdapter


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        handleView()
        return root
    }
    private fun handleView() {
        sem.add(SemData(1,name = "First Semester"))
        sem.add(SemData(2,name = "Second Semester"))
        sem.add(SemData(3,name = "Third Semester"))
        sem.add(SemData(4,name = "Fourth Semester"))
        sem.add(SemData(5,name = "Fifth Semester"))
        dashboardAdapter = DashboardAdapter(requireContext()){
                entity, type, po ->
            when (type){
                0->{
                      val action = HomeFragmentDirections.actionNavigationHomeToSubjectFragment(entity.id)
                       findNavController().navigate(action)

                }
            }

        }

        binding.apply {
            rvSem.recyclerView.apply {
                setHasFixedSize(true)
                adapter = dashboardAdapter
                dashboardAdapter.swapData(sem)
            }
        }
        // sem.add(SemData(name = ""))

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}