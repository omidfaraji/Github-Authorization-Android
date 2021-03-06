package com.mydigipay.challenge.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mydigipay.challenge.extentions.networkErrorDialog
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.utils.EventBus
import com.mydigipay.challenge.utils.NetworkErrorEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.android.inject


@ExperimentalCoroutinesApi
@FlowPreview
class MainActivity : AppCompatActivity() {

    private val eventBus: EventBus by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initEvents()
    }

    private fun initEvents() {
        eventBus.collectEventOnMainThread<NetworkErrorEvent> {
            networkErrorDialog(
                it.throwable.message ?: getString(R.string.network_problem),
                it.onRetry,
                it.onCancle
            )
        }
    }


}
