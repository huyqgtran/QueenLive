package com.huyqgtran.queenlive.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.huyqgtran.queenlive.R
import com.huyqgtran.queenlive.databinding.TourFragmentBinding
import com.huyqgtran.queenlive.ui.adapter.TourAdapter
import com.huyqgtran.queenlive.ui.viewmodels.TourViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.lang.ClassCastException


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
        binding.tourRcv.apply {
            adapter = listAdapter
            ContextCompat.getDrawable(context, R.drawable.divider)?.let {
                addItemDecoration(DividerDecorator(it))
            }
        }

        try {
            (activity as MainActivity).setToolbarTitle(getString(R.string.app_name))
        } catch (e: ClassCastException) {
            Timber.e(e)
        }
    }

    private fun subscribeObs() {
        viewModel.tours.observe(viewLifecycleOwner, Observer {
            Timber.d("update tours%s", it.size)
            listAdapter.setData(it)
            try {
                (activity as MainActivity).updateMenuDrawer(it)
            } catch (e: ClassCastException) {
                Timber.e(e)
            }
        })
    }
}