package com.sotcha.weather.app.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sotcha.weather.app.common.base.BaseFragment
import com.sotcha.weather.app.utils.viewBinding
import com.sotcha.weather.databinding.FragmentSlideshowBinding

//class SlideshowFragment : Fragment() {
class SlideshowFragment : BaseFragment() {

    private val slideshowViewModel: SlideshowViewModel by viewModels()
    protected val binding by viewBinding(FragmentSlideshowBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSlideshowBinding.inflate(inflater, container, false).root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        slideshowViewModel =
//            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        val textView: TextView = binding.textSlideshow


        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

    }

}