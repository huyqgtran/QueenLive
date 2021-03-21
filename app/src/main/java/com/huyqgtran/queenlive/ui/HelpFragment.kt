package com.huyqgtran.queenlive.ui

import android.os.Bundle
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.BulletSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.huyqgtran.queenlive.R
import com.huyqgtran.queenlive.databinding.HelpFragmentBinding

class HelpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = HelpFragmentBinding.inflate(inflater, container, false)
        val result = createText(getString(R.string.first_par), getString(R.string.second_par),
                getString(R.string.third_par))
        binding.helpContent.text = result
        (activity as MainActivity).setToolbarTitle(getString(R.string.app_name))
        return binding.root
    }

    private fun createText(vararg strings: String): CharSequence {
        var result: CharSequence = ""
        for (string in strings) {
            val par = SpannableString(string)
            par.setSpan(BulletSpan(15), 0, string.length, 0)
            result = TextUtils.concat(result, par)
        }

        return result
    }
}