package com.halawy.Story.util

import android.R
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.halawy.Story.databinding.ItemCategoryBinding
import com.halawy.Story.model.DataDepartment


interface ClickCategory{
    fun clickCategory(DepartmentID:String,namecategory:String)
}
class Department_Adapter(var categoryList : List<DataDepartment>, var click:ClickCategory) :
    RecyclerView.Adapter<Department_Adapter.DepartmentHolder>() {
    companion object var pos:Int=0
    inner class DepartmentHolder(var binding: ItemCategoryBinding):RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentHolder {
        return DepartmentHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    @SuppressLint("ResourceAsColor", "ResourceType")
    override fun onBindViewHolder(holder: DepartmentHolder, position: Int) {
        var department=categoryList[position]
        holder.binding.category.text=department.Name.toString()
        holder.itemView.setOnClickListener {
            click.clickCategory(department.DID,department.Name)
            pos= holder.adapterPosition
            notifyDataSetChanged()
        }
        if(pos==0){
            var department=categoryList[0]
            click.clickCategory(department.DID,department.Name)
            holder.binding.category.setTextColor(Color.parseColor("#FFFFFF"))
        }
        if(pos==position){
            holder.binding.category.setTextColor(Color.parseColor("#FFFFFF"))
            holder.binding.cardCategory //Orang
                .background.setColorFilter(Color.parseColor("#FF6B00"), PorterDuff.Mode.SRC_ATOP)
            pos=position
        }
        else {
            holder.binding.cardCategory //White
                .background.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP)
            holder.binding.category.setTextColor(Color.parseColor("#676767"))//Gray
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

}