package com.halawy.Story.ui.StoryActivity.ActivityStory

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.halawy.Story.R
import com.halawy.Story.databinding.FragmentStoryBinding
import com.halawy.Story.model.DataShowStory
import com.halawy.Story.viewmodel.Object.viewmodel


class FragmentStory : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentStoryBinding
    private lateinit var dataShowStory: DataShowStory
    private var pos:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {    bundle ->
            dataShowStory=bundle.getParcelable("DataShowStory")!!
        }
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentStoryBinding.inflate(layoutInflater,container,false)


        binding.title.text=dataShowStory.TitleStory.toString()
        var ImageStory:String=dataShowStory.ImageStory
        var bitmapimage= getBitmapFromEncodedString(ImageStory)
        binding.imageView3.setImageBitmap(bitmapimage)
        getChapterStory(dataShowStory.IDStory)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            binding.back.setOnClickListener {
                findNavController().navigate(R.id.action_fragmentStory_to_fragmentHome)
            }

        }
        override fun onItemSelected(parent: AdapterView<*>?,
                                    view: View, position: Int,
                                    id: Long) {
                binding.nextButton.setOnClickListener {
                    if(position+1!=binding.spinner.getCount()){
                    binding.spinner.setSelection(position+1)
                        binding.nestedScrollView2.fullScroll(NestedScrollView.FOCUS_UP)
                    }
                }
                binding.previousButton.setOnClickListener {
                    if(position-1!=-1){
                        binding.spinner.setSelection(position-1)
                        binding.nestedScrollView2.fullScroll(NestedScrollView.FOCUS_UP)

                    }
                }

            viewmodel!!.getChapters(dataShowStory.IDStory,position).observe(viewLifecycleOwner) {
            binding.tvChapter.text= it.TextChapter.toString()
            }

        }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
    private fun getBitmapFromEncodedString(encodedImage: String?): Bitmap? {
        return if (encodedImage != null) {
            val bytes: ByteArray = Base64.decode(encodedImage, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        } else {
            null
        }
    }

    private fun getChapterStory(StoryID:String){
        var numberChapterList = arrayOf<String?>()
        var TextChapterList = arrayOf<String?>()

        viewmodel!!.getChapters(StoryID).observe(viewLifecycleOwner) {
            it.forEach {
                numberChapterList +="${++it.counter} : ${it.NameChapter}"
                TextChapterList += it.TextChapter
                binding.spinner.onItemSelectedListener = this
                val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    numberChapterList
                )
                ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner.adapter = ad
            }

        }
    }

}