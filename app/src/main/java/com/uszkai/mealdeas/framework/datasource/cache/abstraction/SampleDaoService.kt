package com.uszkai.mealdeas.framework.datasource.cache.abstraction

import com.uszkai.mealdeas.business.domain.model.Sample

interface SampleDaoService {

    suspend fun insert(sample: Sample): Long

    suspend fun getSamples(): List<Sample>
}