package dinhtc.android.xml.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dinhtc.android.xml.model.ProductModel
import dinhtc.android.xml.repository.ProductRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {


    private val _productResponse = MutableLiveData<List<ProductModel>>()
    val productResponse: LiveData<List<ProductModel>> = _productResponse

    fun getListProduct(){
        viewModelScope.launch {
            productRepository.getListProduct().let { dataResponse ->
                _productResponse.value = dataResponse
            }
        }
    }
}