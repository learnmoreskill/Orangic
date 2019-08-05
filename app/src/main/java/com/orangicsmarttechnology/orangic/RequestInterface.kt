package com.orangicsmarttechnology.orangic

import com.orangicsmarttechnology.orangic.models.ServerRequest
import com.orangicsmarttechnology.orangic.models.ServerResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface RequestInterface {

//Describe the request type and the relative URL//

    @POST("orangic/api/index.php")
//    fun operation() : Observable<List<ResponseMap>>

    fun operation(@Body request: ServerRequest): Observable<ServerResponse>

}