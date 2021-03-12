package com.uszkai.mealdeas.framework.presentation.sample.state

import android.os.Parcelable
import com.uszkai.mealdeas.business.domain.model.Sample
import com.uszkai.mealdeas.business.domain.state.ViewState
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SampleViewState(
    var samples: List<Sample>? = null,
    var layoutManager: Parcelable? = null
) : ViewState, Parcelable