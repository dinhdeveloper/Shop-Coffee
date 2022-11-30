package dinhtc.android.xml.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dinhtc.android.xml.databinding.ItemCategoryHomeBinding
import dinhtc.android.xml.model.CategoryModel

class CategoryHomeAdapter(
    private var itemCategory: List<CategoryModel>,
    private var context: Context
) :
    RecyclerView.Adapter<CategoryHomeAdapter.ViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null
    private var selectedPosition = 0

    interface OnItemClickListener {
        fun onClickItem(categoryModel: CategoryModel?, position: Int)
    }

    fun setOnClickItem(onClickListener: OnItemClickListener) {
        this.onItemClickListener = onClickListener
    }


    inner class ViewHolder(val binding: ItemCategoryHomeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCategoryHomeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        var currentItem = itemCategory[position]

        val itemBinding = holder.binding
        itemBinding.apply {
            categoryModel = currentItem

            itemBinding.itemCategory.setOnClickListener {
                selectedPosition = position
                onItemClickListener?.onClickItem(currentItem, position)
                notifyDataSetChanged()
            }

            if (selectedPosition == position){
                itemSelectedColor =  Color.WHITE
                tvName.alpha = 1f
            }else{
                itemSelectedColor = Color.WHITE
                tvName.alpha = 0.6f
            }
        }

    }

    override fun getItemCount(): Int {
        return itemCategory.size
    }
}