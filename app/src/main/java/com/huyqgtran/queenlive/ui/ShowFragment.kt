package com.huyqgtran.queenlive.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.huyqgtran.queenlive.R
import com.huyqgtran.queenlive.databinding.ShowFragmentBinding
import com.huyqgtran.queenlive.ui.adapter.ShowAdapter
import com.huyqgtran.queenlive.ui.viewmodels.ShowViewModel
import com.huyqgtran.queenlive.utilities.YT_SEARCH_URL
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber


class ShowFragment : Fragment() {

    private val viewModel by viewModel<ShowViewModel> {
        parametersOf(navArgs.tourName)
    }
    private val navArgs: ShowFragmentArgs by navArgs()
    private lateinit var listAdapter: ShowAdapter
    private var _binding: ShowFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ShowFragmentBinding.inflate(inflater, container, false)
        initUi()
        subscribeObs()
        return binding.root
    }

    private fun initUi() {
        Timber.d("initUi")
        listAdapter = ShowAdapter{id, viewShow -> onMenuItemClick(id, viewShow)}
        binding.showRcv.run {
            adapter = listAdapter
            ContextCompat.getDrawable(context, R.drawable.divider)?.let {
                addItemDecoration(DividerDecorator(it))
            }
        }
        (activity as MainActivity).setToolbarTitle(navArgs.tourName)
    }

    private fun onMenuItemClick(itemId: Int, viewShow: ViewShow): Boolean {
        return when (itemId) {
            R.id.search -> {
                openYoutubeApp(createFullShowText(viewShow))
                true
            }
            R.id.share -> {
                shareShow(createFullShowText(viewShow))
                true
            }
            else -> false
        }
    }

    private fun createFullShowText(viewShow: ViewShow): String {
        return getString(R.string.app_name) + " " + viewShow.name + " " + viewShow.date
    }

    private fun shareShow(song: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, song)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun openYoutubeApp(searchStr: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(YT_SEARCH_URL + searchStr))
        startActivity(browserIntent)
    }

    private fun subscribeObs() {
        viewModel.shows.observe(viewLifecycleOwner, Observer {
            Timber.d("%d", it.size)
            listAdapter.setData(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}