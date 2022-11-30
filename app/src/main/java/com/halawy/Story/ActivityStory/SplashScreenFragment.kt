package com.halawy.Story.ui.StoryActivity.ActivityStory

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.halawy.Story.R
import com.halawy.Story.databinding.FragmentSplashscreenBinding
import com.halawy.Story.db.DataBaseStory
import com.halawy.Story.repostory.RepostoryStory
import com.halawy.Story.viewmodel.Object.viewmodel
import com.halawy.Story.viewmodel.ViewModelStory


class SplashScreenFragment : Fragment() {
    lateinit var binding: FragmentSplashscreenBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
        binding=FragmentSplashscreenBinding.inflate(layoutInflater,container,false)
       Handler().postDelayed({
            findNavController().navigate(R.id.action_splashScreenFragment_to_fragmentHome)
        },3000)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var databaseStory = DataBaseStory(requireContext())
        var repostoryStory = RepostoryStory(databaseStory.getDao())
        viewmodel= ViewModelStory(repostoryStory)
    }

}