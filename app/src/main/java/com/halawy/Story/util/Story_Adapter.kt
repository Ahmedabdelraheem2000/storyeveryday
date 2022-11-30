package com.halawy.Story.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.halawy.Story.R
import com.halawy.Story.databinding.ItemstoryBinding
import com.halawy.Story.model.DataShowStory
import com.halawy.Story.model.DataStory

class Story_Adapter(var storyList : List<DataStory>, var namecategory:String) :
    RecyclerView.Adapter<Story_Adapter.StoryHolder>() {

    class StoryHolder(var binding: ItemstoryBinding):RecyclerView.ViewHolder(binding.root){
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryHolder {
        return StoryHolder(ItemstoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: StoryHolder, position: Int) {
           var animation= AnimationUtils.loadAnimation(holder.itemView.context,android.R.anim.slide_in_left)
        holder.itemView.startAnimation(animation)
        var story=storyList[position]
        holder.binding.TitleStory.text=story.NameStory
        holder.binding.textdepartment.text=namecategory
        var bitmapimage=getBitmapFromEncodedString(story.ImageStory)
        holder.binding.imageViewStory.setImageBitmap(bitmapimage)
        holder.itemView.setOnClickListener {

            var TitleStory:String=story.NameStory
            var ImageStory:String=story.ImageStory
            var IDStory:String=story.Story_ID
            val datashowStory = DataShowStory(TitleStory,ImageStory, IDStory)
            var bundle= bundleOf("DataShowStory" to datashowStory)
            it.findNavController().navigate(R.id.action_fragmentHome_to_fragmentStory,bundle)


        }
    }
    override fun getItemCount(): Int {
        return storyList.size
    }
    private fun getBitmapFromEncodedString(encodedImage: String?): Bitmap? {
        return if (encodedImage != null) {
            val bytes: ByteArray = Base64.decode(encodedImage, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        } else {
            null
        }
    }

}