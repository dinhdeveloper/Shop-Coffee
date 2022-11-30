package dinhtc.android.xml.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dinhtc.android.xml.databinding.ItemPopulerHomeBinding
import dinhtc.android.xml.model.ProductModel

class PopulerHomeAdapter(
    private var itemProduct: List<ProductModel>,
    private var context: Context
):
    RecyclerView.Adapter<PopulerHomeAdapter.ViewHolder>() {

    inner class ViewHolder (val binding: ItemPopulerHomeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPopulerHomeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentItem = itemProduct[position]

        val itemBinding = holder.binding
        itemBinding.apply {
            productModel = currentItem
        }
    }

    override fun getItemCount(): Int {
        return itemProduct.size
    }
}