package dinhtc.android.xml.ultils

import android.content.Context
import android.util.Log
import dinhtc.android.xml.model.CategoryModel
import dinhtc.android.xml.model.ProductModel
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException

object FormatJsonFile {

    private fun parseFileToString(context: Context, filename: String?): String? {
        try {
            val stream = context.assets.open(filename!!)
            val size = stream.available()
            val bytes = ByteArray(size)
            stream.read(bytes)
            stream.close()
            return String(bytes)
        } catch (e: IOException) {
            Log.e("GuiFormData", "IOException: " + e.message)
        }
        return null
    }

    fun readFileToCategoryModel(context: Context): List<CategoryModel> {
        val jsonFileContent: String? = parseFileToString(context, "category_json.json")
        val jsonArray = JSONArray(jsonFileContent)
        val listCategory: MutableList<CategoryModel> = ArrayList()
        for (i in 0 until jsonArray.length()) {
            val jsonObj: JSONObject = jsonArray.getJSONObject(i)
            val categoryId = jsonObj.getInt("categoryId")
            val categoryName = jsonObj.getString("categoryName")
            listCategory.add(CategoryModel(categoryId, categoryName))
        }
        return listCategory
    }

    fun readFileToProductModel(context: Context): List<ProductModel> {
        val jsonFileContent: String? = parseFileToString(context, "product_json.json")
        val jsonArray = JSONArray(jsonFileContent)
        val listProduct: MutableList<ProductModel> = ArrayList()
        for (i in 0 until jsonArray.length()) {
            val jsonObj: JSONObject = jsonArray.getJSONObject(i)
            val productId = jsonObj.getInt("productId")
            val categoryId = jsonObj.getInt("categoryId")
            val priceId = jsonObj.getInt("priceId")
            val productName = jsonObj.getString("productName")
            val percentSale = jsonObj.getString("percentSale")
            val priceSale = jsonObj.getString("priceSale")
            val productImage = jsonObj.getString("productImage")
            val status = jsonObj.getString("status")
            val countPreview = jsonObj.getInt("countPreview")
            val productDescription = jsonObj.getString("productDescription")
            val rate = jsonObj.getString("rate")
            listProduct.add(
                ProductModel(
                    priceId,
                    categoryId,
                    percentSale,
                    priceSale,
                    productId,
                    productImage,
                    productName,
                    rate,
                    productDescription,
                    status,
                    countPreview
                )
            )
        }
        return listProduct
    }
}