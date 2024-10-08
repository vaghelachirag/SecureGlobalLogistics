package com.app.secureglobal.model.getSavePickupDataResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class SaveDocketPickupData {

    @SerializedName("DocketId")
    @Expose
    private var docketId: Int? = null

    @SerializedName("SenderName")
    @Expose
    private var senderName: String? = null

    @SerializedName("SenderMobileNo")
    @Expose
    private var senderMobileNo: String? = null

    @SerializedName("SglbagNo")
    @Expose
    private var sglbagNo: String? = null

    fun getDocketId(): Int? {
        return docketId
    }

    fun setDocketId(docketId: Int?) {
        this.docketId = docketId
    }

    fun getSenderName(): String? {
        return senderName
    }

    fun setSenderName(senderName: String?) {
        this.senderName = senderName
    }

    fun getSenderMobileNo(): String? {
        return senderMobileNo
    }

    fun setSenderMobileNo(senderMobileNo: String?) {
        this.senderMobileNo = senderMobileNo
    }

    fun getSglbagNo(): String? {
        return sglbagNo
    }

    fun setSglbagNo(sglbagNo: String?) {
        this.sglbagNo = sglbagNo
    }
}