import com.example.post.api.ApiService
import com.example.post.model.Product
import com.example.post.model.ProductResponse
import retrofit2.Response

class ProductRepository(private val apiService: ApiService) {
    suspend fun createProduct(product: Product): Response<ProductResponse> {
        return apiService.createProduct(product)
    }
}
