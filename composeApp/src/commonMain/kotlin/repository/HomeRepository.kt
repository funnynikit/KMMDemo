package repository

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import network.httpClient
import util.Resource

class HomeRepository {

    fun getProducts(): Flow<Resource> = flow{

        val response = httpClient.get("https://fakestoreapi.com/products")
        if(response.status.isSuccess())
        {
           emit(Resource.Success(response.body()))
        }
        else
        {
            emit(Resource.Error("Error fetching data"))
        }

    }.flowOn(Dispatchers.IO)

}