package com.uszkai.mealdeas.business.data.network.abstraction

import com.uszkai.mealdeas.business.domain.model.Sample

interface SimpleNetworkDataSource {

    suspend fun getSamples(): List<Sample>
}