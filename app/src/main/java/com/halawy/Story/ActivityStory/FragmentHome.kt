package com.halawy.Story.ui.StoryActivity.ActivityStory

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.halawy.Story.databinding.FragmenthomeBinding
import com.halawy.Story.model.DataStory
import com.halawy.Story.util.ClickCategory
import com.halawy.Story.util.Department_Adapter
import com.halawy.Story.util.Story_Adapter
import com.halawy.Story.viewmodel.Object.viewmodel

class FragmentHome : Fragment() {
    lateinit var binding: FragmenthomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmenthomeBinding.inflate(layoutInflater, container, false)
//        binding.shimmerCategory.startShimmer()
//        binding.shimmerStory.startShimmer()

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initalize_Recycler_Category()
    }
    private fun initalize_Recycler_Category() {

        var liststory:List<DataStory>
//        var databaseStory = DataBaseStory(requireContext())
//        var repostoryStory = RepostoryStory(databaseStory.getDao())
//        var viewmodel = ViewModelStory(repostoryStory)
        if (viewmodel!=null) {
           viewmodel!!.getAllDepartment().observe(viewLifecycleOwner) {
                var departmentAdapter =
                    Department_Adapter(it, object : ClickCategory {
                        override fun clickCategory(DepartmentID: String, namecategory: String) {
                           viewmodel!!.getStory(DepartmentID).observe(viewLifecycleOwner, {
                                liststory = emptyList()
                                liststory = it
                                Log.e("StoryAdapter", it.toString())
                                initalize_Recycler_Story(liststory, namecategory)
                            })
                        }
                    })
                binding.recyclerCategory.adapter = departmentAdapter
                departmentAdapter.notifyDataSetChanged()
            }
        }
    }
    private fun initalize_Recycler_Story(storyList: List<DataStory>,namecategory:String) {
        var storyAdapter = Story_Adapter(storyList,namecategory)
        binding.recyclerStory.adapter = storyAdapter
        storyAdapter.notifyDataSetChanged()
        binding.shimmerCategory.stopShimmer()
        binding.shimmerStory.stopShimmer()
        binding.shimmer.visibility = View.GONE

    }


}