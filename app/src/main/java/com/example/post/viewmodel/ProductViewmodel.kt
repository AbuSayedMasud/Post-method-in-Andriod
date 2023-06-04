import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.post.ProductCallback
import com.example.post.model.Product
import com.example.post.model.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {


    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    fun createProduct(product: Product, callback: ProductCallback) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response: Response<ProductResponse> = repository.createProduct(product)

                if (response.isSuccessful) {

                    Log.d("response",response.code().toString())
                    withContext(Dispatchers.Main) {
                        callback.onSuccess()
                        _toastMessage.value = "Product created successfully!"
                    }
                } else {

                    val errorMessage = "Failed to create product: ${response.errorBody()?.string()}"
                    withContext(Dispatchers.Main) {
                        callback.onFailure(errorMessage)
                        _toastMessage.value = errorMessage
                    }
                }
            } catch (e: Exception) {
                val errorMessage = "Failed to create product: ${e.message}"
                withContext(Dispatchers.Main) {
                    callback.onFailure(errorMessage)
                    _toastMessage.value = errorMessage
                }
            }
        }
    }
}

