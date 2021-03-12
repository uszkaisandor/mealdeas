package com.uszkai.mealdeas.framework.datasource.network.implementation

import com.uszkai.mealdeas.business.domain.model.Sample
import com.uszkai.mealdeas.framework.datasource.network.abstarction.SampleNetworkService
import com.uszkai.mealdeas.framework.datasource.network.api.SampleApiService
import com.uszkai.mealdeas.framework.datasource.network.mapper.SampleNetworkMapper
import com.uszkai.mealdeas.util.Constants.NetworkConstants.Companion.API_KEY
import com.uszkai.mealdeas.util.Constants.NetworkConstants.Companion.API_LANGUAGE_CODE
import javax.inject.Inject

class SampleNetworkServiceImpl
@Inject
constructor(
    private val sampleApiService: SampleApiService,
    private val sampleNetworkMapper: SampleNetworkMapper
) : SampleNetworkService {

    override suspend fun getSamples(): List<Sample> {
        return sampleNetworkMapper.entityListToNoteList(
            entityListItems = sampleApiService.getSamples(API_LANGUAGE_CODE, API_KEY).articles
        )
    }
}