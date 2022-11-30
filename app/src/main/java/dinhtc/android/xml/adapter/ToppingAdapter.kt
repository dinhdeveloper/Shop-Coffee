package dinhtc.android.xml.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dinhtc.android.xml.databinding.ItemToppingBinding
import dinhtc.android.xml.model.ToppingModel

class ToppingAdapter (
    private var itemTopping: List<ToppingModel>,
    private var context: Context
) :
    RecyclerView.Adapter<ToppingAdapter.ViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null
    private var selectedPosition = 0

    interface OnItemClickListener {
        fun onClickItem(productModel: ToppingModel?, position: Int)
    }

    fun setOnClickItem(onClickListener: OnItemClickListener) {
        this.onItemClickListener = onClickListener
    }

    inner class ViewHolder(val binding: ItemToppingBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemToppingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentItem = itemTopping[position]

        val itemBinding = holder.binding
        itemBinding.apply {
            toppingModel = currentItem

            itemTopping.setOnClickListener {
                onItemClickListener?.onClickItem(currentItem, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemTopping.size
    }
}