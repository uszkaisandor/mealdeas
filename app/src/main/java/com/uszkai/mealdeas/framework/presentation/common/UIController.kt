package com.uszkai.mealdeas.framework.presentation.common

import com.uszkai.mealdeas.business.domain.state.Response
import com.uszkai.mealdeas.business.domain.state.StateMessageCallback

interface UIController {

    fun onResponseReceived(
        response: Response,
        stateMessageCallback: StateMessageCallback
    )
}