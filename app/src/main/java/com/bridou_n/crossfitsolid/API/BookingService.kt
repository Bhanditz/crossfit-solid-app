package com.bridou_n.crossfitsolid.API

import com.bridou_n.crossfitsolid.models.GroupActivity
import com.bridou_n.crossfitsolid.models.Profile
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

/**
 * Created by bridou_n on 25/07/2017.
 */

interface BookingService {
    @GET("customers/{customerId}")
    fun getProfile(@Path("customerId") customerId: String) : Single<Profile>

    @GET("businessunits/{businessUnitId}/groupactivities/")
    fun getGroupActivites(@Path("businessUnitId") businessUnitId: Int = 1,
                          @Query("period.start") startDate: String,
                          @Query("period.end") endDate: String,
                          @Query("webCategory") webCategory: Int = 1) : Single<Array<GroupActivity>>
}