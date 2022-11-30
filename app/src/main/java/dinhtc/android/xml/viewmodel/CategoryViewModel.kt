package dinhtc.android.xml.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dinhtc.android.xml.model.CategoryModel
import dinhtc.android.xml.repository.CategoryRepository
import dinhtc.android.xml.ultils.DataResponse
import dinhtc.android.xml.ultils.ResultState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {


    private val _categoryResponse = MutableLiveData<List<CategoryModel>>()
    val categoryResponse: LiveData<List<CategoryModel>> = _categoryResponse

    fun getListCategory(){
        viewModelScope.launch {
            categoryRepository.getListCategory().let { dataResponse ->
                _categoryResponse.postValue(dataResponse)
            }
        }
    }
}