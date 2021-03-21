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
import androidx.recyclerview.widget.DividerItemDecoration
import com.huyqgtran.queenlive.R
import com.huyqgtran.queenlive.databinding.SongFragmentBinding
import com.huyqgtran.queenlive.ui.adapter.SongAdapter
import com.huyqgtran.queenlive.ui.viewmodels.SongViewModel
import com.huyqgtran.queenlive.utilities.YT_SEARCH_URL
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class SongFragment : Fragment() {
    private val viewModel by viewModel<SongViewModel> {
        parametersOf(getShowDate(navArgs.showDate))
    }
    private val navArgs: SongFragmentArgs by navArgs()
    private lateinit var listAdapter: SongAdapter
    private var _binding: SongFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = SongFragmentBinding.inflate(inflater, container, false)
        initUi()
        subscribeObs()
        return binding.root
    }

    private fun getShowDate(showInfo: String): String {
        return showInfo.split("\\|".toRegex())[1]
    }

    private fun initUi() {
        listAdapter = SongAdapter{id, viewSong -> onMenuItemClick(viewSong, id)}
        binding.songRcv.apply {
            adapter = listAdapter
            ContextCompat.getDrawable(context, R.drawable.divider)?.let {
                addItemDecoration(DividerDecorator(it))
            }
        }
        (activity as MainActivity).setToolbarTitle(navArgs.showDate.replace("|", " "))
    }

    private fun onMenuItemClick(viewSong: ViewSong, itemId: Int): Boolean {
        return when (itemId) {
            R.id.search -> {
                openYoutubeApp(createFullSongText(viewSong))
                true
            }
            R.id.share -> {
                shareSong(createFullSongText(viewSong))
                true
            }
            else -> false
        }
    }

    private fun createFullSongText(viewSong: ViewSong): String {
        return getString(R.string.app_name) + " " + viewSong.name + " " + viewSong.showName + " " + removeAbundantZero(
            viewSong.date
        )
    }

    private fun shareSong(song: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, song)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun removeAbundantZero(date: String): String {
        val arr = date.split("/")
        var editedDate = ""
        editedDate += arr[0].replace("0", "")
        editedDate += "/"
        editedDate += arr[1].replace("0", "")
        editedDate += "/"
        editedDate += arr[2]
        return editedDate
    }

    private fun openYoutubeApp(searchStr: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(YT_SEARCH_URL + searchStr))
        startActivity(browserIntent)
    }

    private fun subscribeObs() {
        viewModel.songs.observe(viewLifecycleOwner, Observer {
            listAdapter.setData(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}