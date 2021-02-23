package com.huyqgtran.queenlive.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.huyqgtran.queenlive.databinding.TourFragmentBinding
import com.huyqgtran.queenlive.ui.adapter.TourAdapter
import com.huyqgtran.queenlive.ui.viewmodels.TourViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class TourFragment : Fragment() {

    private val viewModel by viewModel<TourViewModel>()

    private lateinit var listAdapter: TourAdapter

    private var _binding: TourFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TourFragmentBinding.inflate(inflater, container, false)
        initUi()
        subscribeObs()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUi() {
        Timber.d("initUi")
        listAdapter = TourAdapter(viewModel)
        binding.tourRcv.adapter = listAdapter
    }

    private fun subscribeObs() {
        viewModel.tours.observe(viewLifecycleOwner, Observer {
            Timber.d("update tours%s", it.size)
            listAdapter.setData(it)
            (activity as MainActivity).updateMenuDrawer(it)
        })
    }
}