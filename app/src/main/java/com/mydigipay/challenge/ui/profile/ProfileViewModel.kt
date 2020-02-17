package com.mydigipay.challenge.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mydigipay.challenge.network.model.user.User
import com.mydigipay.challenge.utils.Coroutines
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@UseExperimental(FlowPreview::class)
class ProfileViewModel(private val profileRepository: ProfileRepository) : ViewModel() {


    val profileState = MutableLiveData<ProfileViewState>(ProfileViewState())

    init {
        loadProfile()
    }

    private fun loadProfile() {
        Coroutines.ioThenMain({ profileRepository.getProfile() }) {
            onExecute { profileState.value = profileState.value!!.copy(isLoading = true) }
            finally { profileState.value = profileState.value!!.copy(isLoading = false) }
            onComplete {
                profileState.value = profileState.value!!.copy(
                    isLoading = false,
                    user = it,
                    error = null
                )
            }
            onError {
                profileState.value = profileState.value!!.copy(isLoading = false, error = it)
            }
        }
    }

    fun requestUpdate(key: String, value: String) {
        Coroutines.ioThenMain({ profileRepository.updateUserProfile(mapOf(key to value)) }) {
            onExecute { profileState.value = profileState.value!!.copy(isLoading = true) }
            finally { profileState.value = profileState.value!!.copy(isLoading = false) }
            onComplete {
                profileState.value = profileState.value!!.copy(
                    isLoading = false,
                    user = it,
                    error = null
                )
            }
            onError {
                profileState.value = profileState.value!!.copy(isLoading = false, error = it)
            }
        }
    }
}