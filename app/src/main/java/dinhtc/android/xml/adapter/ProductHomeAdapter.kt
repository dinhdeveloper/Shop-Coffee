package dinhtc.android.xml.adapter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dinhtc.android.xml.databinding.ItemProductHomeBinding
import dinhtc.android.xml.model.ProductModel
import java.util.*


class ProductHomeAdapter(
    private var itemProduct: List<ProductModel>,
    private var context: Context,
) :
    RecyclerView.Adapter<ProductHomeAdapter.ViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onClickItem(productModel: ProductModel?, position: Int)
    }

    fun setOnClickItem(onClickListener: OnItemClickListener) {
        this.onItemClickListener = onClickListener
    }

    inner class ViewHolder(val binding: ItemProductHomeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductHomeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemProduct[position]

        showProgressBar(holder.binding)

        val itemBinding = holder.binding
        itemBinding.apply {
            productModel = currentItem
            itemProduct.setOnClickListener {
                onItemClickListener?.onClickItem(currentItem, position)
            }
        }
    }

    private fun showProgressBar(binding: ItemProductHomeBinding) {
        binding.progressBar.visibility = View.VISIBLE
        binding.itemProduct.isEnabled = false
        val runnable = Runnable {
            binding.progressBar.visibility = View.GONE
            binding.layoutProgress.visibility = View.GONE
            binding.itemProduct.isEnabled = true
        }
        Handler().postDelayed(runnable, 3000)
    }

    override fun getItemCount(): Int {
        return itemProduct.size
    }
}