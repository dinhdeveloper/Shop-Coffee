package dinhtc.android.xml.repository.productImpl

import android.content.Context
import dinhtc.android.xml.model.ProductModel
import dinhtc.android.xml.ultils.FormatJsonFile
import javax.inject.Inject

class ProductServiceImpl @Inject constructor(private val context: Context) {
    fun getListProduct(): List<ProductModel> {
        return FormatJsonFile.readFileToProductModel(context)
    }
}