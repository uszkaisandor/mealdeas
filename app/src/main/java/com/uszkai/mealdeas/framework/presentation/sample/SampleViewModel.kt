package com.uszkai.mealdeas.framework.presentation.sample

import android.os.Parcelable
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.uszkai.mealdeas.business.domain.model.Sample
import com.uszkai.mealdeas.business.domain.state.DataState
import com.uszkai.mealdeas.business.domain.state.StateEvent
import com.uszkai.mealdeas.business.interactors.SampleInteractor
import com.uszkai.mealdeas.framework.presentation.common.BaseViewModel
import com.uszkai.mealdeas.framework.presentation.sample.state.SampleStateEvent.*
import com.uszkai.mealdeas.framework.presentation.sample.state.SampleViewState
import kotlinx.coroutines.flow.Flow

class SampleViewModel
@ViewModelInject
constructor(
    private val sampleInteractor: SampleInteractor,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<SampleViewState>() {

    override fun handleNewData(data: SampleViewState) {

        data.let { viewState ->
            viewState.samples?.let { sampleList ->
                setSamplesListData(sampleList)
            }
        }
    }

    override fun setStateEvent(stateEvent: StateEvent) {
        val job: Flow<DataState<SampleViewState>?> = when (stateEvent) {
            is FetchSampleEvent -> {
                sampleInteractor.fetchSamples(stateEvent)
            }

            else -> {
                emitInvalidStateEvent(stateEvent)
            }
        }

        launchJob(stateEvent, job)
    }

    override fun initNewViewState(): SampleViewState {
        return SampleViewState()
    }

    private fun setSamplesListData(samples: List<Sample>) {
        val update = getCurrentViewStateOrNew()
        update.samples = samples
        setViewState(update)
    }

    fun getLayoutManagerState(): Parcelable? {
        return getCurrentViewStateOrNew().layoutManager
    }

    fun setLayoutManagerState(layoutManagerState: Parcelable?) {
        val update = getCurrentViewStateOrNew()
        update.layoutManager = layoutManagerState
        setViewState(update)
    }

    fun refresh() {
        setLayoutManagerState(null)
        setStateEvent(FetchSampleEvent)
    }
}