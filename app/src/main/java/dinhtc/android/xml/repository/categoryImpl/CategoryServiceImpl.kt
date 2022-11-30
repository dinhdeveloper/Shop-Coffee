package dinhtc.android.xml.repository.categoryImpl

import android.content.Context
import dinhtc.android.xml.model.CategoryModel
import dinhtc.android.xml.ultils.FormatJsonFile
import javax.inject.Inject

class CategoryServiceImpl @Inject constructor(private val context: Context) {

    fun getListCategory(): List<CategoryModel> {
        return FormatJsonFile.readFileToCategoryModel(context)
    }
}