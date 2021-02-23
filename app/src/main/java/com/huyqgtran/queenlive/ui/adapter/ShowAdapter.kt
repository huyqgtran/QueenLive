package com.huyqgtran.queenlive.ui.adapter

import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.huyqgtran.queenlive.R
import com.huyqgtran.queenlive.databinding.ShowItemBinding
import com.huyqgtran.queenlive.ui.ShowFragmentDirections
import com.huyqgtran.queenlive.ui.ViewShow
import timber.log.Timber

class ShowAdapter(val onItemClick: (Int, ViewShow) -> Boolean)  : ListAdapter<ViewShow, ShowAdapter.ShowViewHolder>(ShowDiffCallBack()) {

    private val shows: MutableList<ViewShow> = mutableListOf()

    fun setData(list: List<ViewShow>) {
        shows.clear()
        shows.addAll(list)
        notifyDataSetChanged()
    }

    inner class ShowViewHolder (private val binding: ShowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private fun navigateToSongFragment(showDate: String, view: View) {
            val action = ShowFragmentDirections.showFragmentToSongFragment(showDate)
            view.findNavController().navigate(action)
        }

        fun bind(item: ViewShow) {
            binding.run {
                titleText.text = item.name
                showDate.text = item.date
                showItem.setOnClickListener {
                    navigateToSongFragment(item.date, it)
                }
                moreBtn.setOnClickListener {
                    addPopupMenu(it, item)
                }
            }
        }

        private fun addPopupMenu(v: View, item: ViewShow) {
            val popup = PopupMenu(v.context, v)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.actions, popup.menu)
            popup.setOnMenuItemClickListener {
                onItemClick(it.itemId, item)
            }
            popup.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ShowItemBinding.inflate(layoutInflater, parent, false)
        return ShowViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(shows[position])
    }

    override fun getItemCount(): Int {
        Timber.d("item count: %d", shows.size)
        return shows.size
    }
}

class ShowDiffCallBack: DiffUtil.ItemCallback<ViewShow>() {
    override fun areItemsTheSame(oldItem: ViewShow, newItem: ViewShow): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: ViewShow, newItem: ViewShow): Boolean {
        return oldItem == newItem
    }
}