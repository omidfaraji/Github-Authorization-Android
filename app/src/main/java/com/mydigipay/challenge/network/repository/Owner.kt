package com.mydigipay.challenge.network.repository

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("login")
    @Expose
    var login: String? = null,
    @SerializedName("id")
    @Expose
    var id: Int = 0,
    @SerializedName("node_id")
    @Expose
    var nodeId: String? = null,
    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String? = null,
    @SerializedName("gravatar_id")
    @Expose
    var gravatarId: String? = null,
    @SerializedName("url")
    @Expose
    var url: String? = null,
    @SerializedName("received_events_url")
    @Expose
    var receivedEventsUrl: String? = null,
    @SerializedName("type")
    @Expose
    var type: String? = null
)