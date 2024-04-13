package viewmodel
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import repository.HomeRepository
import util.Resource

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow<Resource>(Resource.Idle())
    val products = _state

    private val homeRepository = HomeRepository()

    init {
       getProductList()
    }

    fun getProductList(){
        viewModelScope.launch {
            val result = homeRepository.getProducts()
            result.collect(){
                when(it){
                    is Resource.Success-> {
                        _state.value = Resource.Success(it.data)
                    }
                    is Resource.Error-> {
                        _state.value = Resource.Error(it.error)
                    }
                    is Resource.Idle -> TODO()
                }
            }
        }
    }

}