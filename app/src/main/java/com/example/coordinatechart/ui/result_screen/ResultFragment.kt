package com.example.coordinatechart.ui.result_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coordinatechart.App
import com.example.coordinatechart.databinding.FragmentResultBinding
import com.example.coordinatechart.domen.entity.PointItem
import com.example.coordinatechart.ui.result_screen.adapter.CoordinateRecyclerViewAdapter
import com.example.coordinatechart.util.ViewModelFactory
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import javax.inject.Inject


class ResultFragment @Inject constructor() : Fragment() {

    private var binding: FragmentResultBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<ResultFragmentViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as App).appComponent.injectResultFragment(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cordinateList = viewModel.getList()
        initRecyclerView(cordinateList)
        val entryList = cordinateList.map {
            Entry(it.coordinateX.toFloat(), it.coordinateY.toFloat())
        }
        val dataset = LineDataSet(entryList, null)
        dataset.mode = LineDataSet.Mode.CUBIC_BEZIER
        val data = LineData(dataset)
        binding?.lineChart?.let {
            it.setTouchEnabled(false)
            it.description.isEnabled = false
            it.setDrawGridBackground(false)
            it.data = data
            it.invalidate()
        }
    }

    private fun initRecyclerView(coordinateList: List<PointItem>) {
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding?.let {
            it.coordinateListContainer.layoutManager = layoutManager
            it.coordinateListContainer.adapter = CoordinateRecyclerViewAdapter(coordinateList)
        }
    }
}