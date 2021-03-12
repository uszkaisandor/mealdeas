package com.uszkai.mealdeas.framework.datasource.network.abstarction

import com.uszkai.mealdeas.business.domain.model.Sample

interface SampleNetworkService {

    suspend fun getSamples(): List<Sample>
}