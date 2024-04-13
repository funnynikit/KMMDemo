package util

import data.ProductItem

sealed class Resource {
    class Idle() : Resource()
    data class Success(val data : List<ProductItem>) : Resource()
    data class Error(val error : String) : Resource()
}