package dinhtc.android.xml.repository

import dinhtc.android.xml.model.ProductModel
import dinhtc.android.xml.repository.productImpl.ProductServiceImpl
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productServices : ProductServiceImpl){

    fun getListProduct() : List<ProductModel> {
        return productServices.getListProduct()
    }
}