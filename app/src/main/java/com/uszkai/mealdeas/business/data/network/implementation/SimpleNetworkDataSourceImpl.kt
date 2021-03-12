package com.uszkai.mealdeas.business.data.network.implementation

import com.uszkai.mealdeas.business.data.network.abstraction.SimpleNetworkDataSource
import com.uszkai.mealdeas.business.domain.model.Sample
import com.uszkai.mealdeas.framework.datasource.network.abstarction.SampleNetworkService

class SimpleNetworkDataSourceImpl
constructor(
    private val sampleNetworkService: SampleNetworkService
) : SimpleNetworkDataSource {

    override suspend fun getSamples(): List<Sample> {
        return sampleNetworkService.getSamples()
    }
}