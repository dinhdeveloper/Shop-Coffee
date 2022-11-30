package dinhtc.android.xml.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dinhtc.android.xml.databinding.ItemAddExtraBinding
import dinhtc.android.xml.model.ProductModel

class AddExtraAdapter (
    private var itemProduct: List<ProductModel>,
    private var context: Context
) :
    RecyclerView.Adapter<AddExtraAdapter.ViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null
    private var selectedPosition = 0

    interface OnItemClickListener {
        fun onClickItem(productModel: ProductModel?, position: Int)
    }

    fun setOnClickItem(onClickListener: OnItemClickListener) {
        this.onItemClickListener = onClickListener
    }

    inner class ViewHolder(val binding: ItemAddExtraBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAddExtraBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentItem = itemProduct[position]

        val itemBinding = holder.binding
        itemBinding.apply {
            productModel = currentItem

            itemProduct.setOnClickListener {
                onItemClickListener?.onClickItem(currentItem, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemProduct.size
    }
}