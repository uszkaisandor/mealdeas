package com.uszkai.mealdeas.framework.presentation.sample.state

import com.uszkai.mealdeas.R
import com.uszkai.mealdeas.business.domain.state.StateEvent

sealed class SampleStateEvent : StateEvent {

    object FetchSampleEvent : SampleStateEvent() {

        override fun errorInfoRes(): Int {
            return R.string.sample_error
        }

        override fun eventName(): String {
            return "FetchSampleEvent"
        }

        override fun shouldDisplayProgressBar(): Boolean {
            return true
        }
    }
}