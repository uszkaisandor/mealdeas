package com.uszkai.mealdeas.business.data.cache.abstraction

import com.uszkai.mealdeas.business.domain.model.Sample

interface SampleCacheDataSource {

    suspend fun insert(sample: Sample): Long

    suspend fun getSamples(): List<Sample>
}