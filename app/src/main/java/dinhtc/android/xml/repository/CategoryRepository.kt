package dinhtc.android.xml.repository

import dinhtc.android.xml.model.CategoryModel
import dinhtc.android.xml.repository.categoryImpl.CategoryServiceImpl
import dinhtc.android.xml.ultils.DataResponse
import dinhtc.android.xml.ultils.ResultState
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryServices : CategoryServiceImpl){

    fun getListCategory() : List<CategoryModel> {
        return categoryServices.getListCategory()
    }
}