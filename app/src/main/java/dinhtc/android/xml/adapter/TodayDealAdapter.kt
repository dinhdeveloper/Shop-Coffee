package dinhtc.android.xml.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dinhtc.android.xml.databinding.ItemTodaydealHomeBinding
import dinhtc.android.xml.model.ProductModel

class TodayDealAdapter(
    private var itemProduct: List<ProductModel>,
    private var context: Context
) :
    RecyclerView.Adapter<TodayDealAdapter.ViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onClickItem(productModel: ProductModel?, position: Int)
    }

    fun onClickItemListener(onClickListener: OnItemClickListener) {
        this.onItemClickListener = onClickListener
    }

    var onAddClickListener: OnAddClickListener? = null

    interface OnAddClickListener {
        fun onClickItem(productModel: ProductModel?, position: Int)
    }

    fun onClickAddListener(onClickListener: OnAddClickListener) {
        this.onAddClickListener = onClickListener
    }

    inner class ViewHolder(val binding: ItemTodaydealHomeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTodaydealHomeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentItem = itemProduct[position]

        val itemBinding = holder.binding
        itemBinding.apply {
            productModel = currentItem
            productItem.setOnClickListener {
                onItemClickListener?.onClickItem(currentItem, position)
            }

            layoutAdd.setOnClickListener {
                onAddClickListener?.onClickItem(currentItem, position)
            }

        }
    }

    override fun getItemCount(): Int {
        return itemProduct.size
    }
}