package dinhtc.android.xml.model

import java.io.Serializable

data class ProductModel(
    val priceId: Int,
    val categoryId: Int,
    val percentSale: String,
    val priceSale: String,
    val productId: Int,
    val productImage: String,
    val productName: String,
    val rate: String,
    val productDescription: String,
    val status: String,
    val countPreview: Int
): Serializable