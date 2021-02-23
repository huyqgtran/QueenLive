package com.huyqgtran.queenlive.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.huyqgtran.queenlive.databinding.TourItemBinding
import com.huyqgtran.queenlive.ui.TourFragmentDirections
import com.huyqgtran.queenlive.ui.ViewTour
import com.huyqgtran.queenlive.ui.viewmodels.TourViewModel

class TourAdapter(private val viewModel: TourViewModel): ListAdapter<ViewTour, TourAdapter.TourViewHolder>(
    TourDiffCallBack()
) {

    private val tours: MutableList<ViewTour> = mutableListOf()

    fun setData(list: List<ViewTour>) {
        tours.clear()
        tours.addAll(list)
        notifyDataSetChanged()
    }

    class TourViewHolder private constructor(private val binding: TourItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private fun navigateToShowFragment(tourName: String, view: View) {
            val action =
                TourFragmentDirections.tourFragmentToShowFragment(
                    tourName
                )
            view.findNavController().navigate(action)
        }

        fun bind(viewModel: TourViewModel, item: ViewTour) {
            binding.titleText.text = item.name
            binding.tourItem.setOnClickListener {
                navigateToShowFragment(item.name, it)
            }
        }

        companion object {
            fun from(parent: ViewGroup): TourViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TourItemBinding.inflate(layoutInflater, parent, false)
                return TourViewHolder(
                    binding
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourViewHolder {
        return TourViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: TourViewHolder, position: Int) {
        holder.bind(viewModel, tours[position])
    }

    override fun getItemCount(): Int {
        return tours.size
    }
}

class TourDiffCallBack: DiffUtil.ItemCallback<ViewTour>() {
    override fun areItemsTheSame(oldItem: ViewTour, newItem: ViewTour): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ViewTour, newItem: ViewTour): Boolean {
        return oldItem == newItem
    }
}