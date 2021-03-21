package com.huyqgtran.queenlive.ui.adapter

import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.huyqgtran.queenlive.R
import com.huyqgtran.queenlive.databinding.SongItemBinding
import com.huyqgtran.queenlive.ui.ViewSong
import timber.log.Timber

class SongAdapter(val onItemClick: (Int, ViewSong) -> Boolean) : ListAdapter<ViewSong, SongAdapter.SongViewHolder>(SongDiffCallBack()) {

    private val songs: MutableList<ViewSong> = mutableListOf()

    fun setData(list: List<ViewSong>) {
        songs.clear()
        songs.addAll(list)
        notifyDataSetChanged()
    }

    inner class SongViewHolder(private val binding: SongItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ViewSong) {
            val displayStr: String = item.name
            var note = ""
            if (item.rarelyPlayed) {
                note = binding.root.context.resources.getString(R.string.rarely_played)
            }
            binding.note.text = note
            binding.titleText.text = displayStr
            binding.moreBtn.setOnClickListener {
                addPopupMenu(it, item)
            }
        }

        private fun addPopupMenu(v: View, item: ViewSong) {
            val popup = PopupMenu(v.context, v)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.actions, popup.menu)
            popup.setOnMenuItemClickListener {
                onItemClick(it.itemId, item)
            }
            popup.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SongItemBinding.inflate(layoutInflater, parent, false)
        return SongViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(songs[position])
    }

    override fun getItemCount(): Int {
        Timber.d("item count: %d", songs.size)
        return songs.size
    }
}

class SongDiffCallBack: DiffUtil.ItemCallback<ViewSong>() {
    override fun areItemsTheSame(oldItem: ViewSong, newItem: ViewSong): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: ViewSong, newItem: ViewSong): Boolean {
        return oldItem == newItem
    }
}