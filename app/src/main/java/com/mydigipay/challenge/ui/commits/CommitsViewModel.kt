package com.mydigipay.challenge.ui.commits

import androidx.lifecycle.MutableLiveData
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.utils.Coroutines
import kotlin.properties.Delegates

class CommitsViewModel(private val commitRepository: CommitRepository) : BaseViewModel() {

    private val viewState = CommitViewState()
    var owner by Delegates.notNull<String>()
    var repo by Delegates.notNull<String>()
    val commitState = MutableLiveData<CommitViewState>(viewState)

    fun requestCommits() {
        loadCommits()
    }

    private fun loadCommits() {
        Coroutines.ioThenMain(work = { commitRepository.getCommits(owner, repo) }) {
            onExecute { commitState.value = viewState.copy(isLoading = true) }
            finally { commitState.value = viewState.copy(isLoading = false) }
            onComplete {
                commitState.value = viewState.copy(
                    isLoading = false,
                    commits = it,
                    error = null
                )
            }
            onError {
                commitState.value = viewState.copy(
                    isLoading = false,
                    error = it
                )
            }
        }.also { addToUnsubscribe(it) }
    }
}