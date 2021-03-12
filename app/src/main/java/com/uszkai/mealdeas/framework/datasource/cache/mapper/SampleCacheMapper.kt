package com.uszkai.mealdeas.framework.datasource.cache.mapper

import com.uszkai.mealdeas.business.domain.model.Sample
import com.uszkai.mealdeas.business.domain.util.EntityMapper
import com.uszkai.mealdeas.framework.datasource.cache.model.SampleCacheEntity
import javax.inject.Inject

class SampleCacheMapper
@Inject
constructor() : EntityMapper<SampleCacheEntity, Sample> {

    override fun mapFromEntity(entity: SampleCacheEntity): Sample {
        return Sample(
            title = entity.title
        )
    }

    override fun mapToEntity(domainModel: Sample): SampleCacheEntity {
        return SampleCacheEntity(
            title = domainModel.title
        )
    }

    fun entityListToNoteList(entities: List<SampleCacheEntity>): List<Sample> {
        return entities.map { sampleCacheEntity ->
            mapFromEntity(sampleCacheEntity)
        }
    }
}