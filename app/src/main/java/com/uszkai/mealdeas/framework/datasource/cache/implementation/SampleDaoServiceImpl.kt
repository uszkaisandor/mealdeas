package com.uszkai.mealdeas.framework.datasource.cache.implementation

import com.uszkai.mealdeas.business.domain.model.Sample
import com.uszkai.mealdeas.framework.datasource.cache.abstraction.SampleDaoService
import com.uszkai.mealdeas.framework.datasource.cache.database.SampleDao
import com.uszkai.mealdeas.framework.datasource.cache.mapper.SampleCacheMapper
import javax.inject.Inject

class SampleDaoServiceImpl
@Inject
constructor(
    private val sampleDao: SampleDao,
    private val sampleCacheMapper: SampleCacheMapper
) : SampleDaoService {

    override suspend fun insert(sample: Sample): Long {
        return sampleDao.insert(
            sample = sampleCacheMapper.mapToEntity(sample)
        )
    }

    override suspend fun getSamples(): List<Sample> {
        return sampleCacheMapper.entityListToNoteList(
            entities = sampleDao.getAllSamples()
        )
    }
}