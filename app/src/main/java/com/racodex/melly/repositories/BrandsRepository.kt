package com.racodex.melly.repositories

import com.racodex.melly.data.local.RoomDao
import com.racodex.melly.models.Advertisement
import com.racodex.melly.models.Manufacturer
import com.racodex.melly.sealed.DataResponse
import com.racodex.melly.sealed.Error
import com.racodex.melly.utils.getStructuredManufacturers
import javax.inject.Inject

class BrandsRepository @Inject constructor(
    private val dao: RoomDao,
) {
    suspend fun getBrandsAdvertisements(): DataResponse<List<Advertisement>> {
        /** First we should check the local storage */
        dao.getAdvertisements().let {
            return if (it.isNotEmpty()) {
                DataResponse.Success(data = it)
            } else {
                /** Now we should fetch from the remote server */
                DataResponse.Error(error = Error.Empty)
            }
        }
    }

    suspend fun getBrandsWithProducts(): DataResponse<List<Manufacturer>> {
        /** First we should check the local storage */
        dao.getManufacturersWithProducts().getStructuredManufacturers().let {
            return if (it.isNotEmpty()) {
                DataResponse.Success(data = it)
            } else {
                /** Now we should fetch from the remote server */
                DataResponse.Error(error = Error.Empty)
            }
        }
    }
}

