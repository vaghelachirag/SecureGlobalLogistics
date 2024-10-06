package com.app.secureglobal.model.getSaveScanResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetSaveScanResponse {

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

    @SerializedName("GrWt")
    @Expose
    private var grWt: Double? = null

    @SerializedName("GrWtUnit")
    @Expose
    private var grWtUnit: String? = null

    @SerializedName("BillingType")
    @Expose
    private var billingType: Int? = null

    @SerializedName("Freight")
    @Expose
    private var freight: Double? = null

    @SerializedName("InsuranceCharge")
    @Expose
    private var insuranceCharge: Double? = null

    @SerializedName("Shccharge")
    @Expose
    private var shccharge: Double? = null

    @SerializedName("DestinationBranchId")
    @Expose
    private var destinationBranchId: Int? = null

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

    fun getGrWt(): Double? {
        return grWt
    }

    fun setGrWt(grWt: Double?) {
        this.grWt = grWt
    }

    fun getGrWtUnit(): String? {
        return grWtUnit
    }

    fun setGrWtUnit(grWtUnit: String?) {
        this.grWtUnit = grWtUnit
    }

    fun getBillingType(): Int? {
        return billingType
    }

    fun setBillingType(billingType: Int?) {
        this.billingType = billingType
    }

    fun getFreight(): Double? {
        return freight
    }

    fun setFreight(freight: Double?) {
        this.freight = freight
    }

    fun getInsuranceCharge(): Double? {
        return insuranceCharge
    }

    fun setInsuranceCharge(insuranceCharge: Double?) {
        this.insuranceCharge = insuranceCharge
    }

    fun getShccharge(): Double? {
        return shccharge
    }

    fun setShccharge(shccharge: Double?) {
        this.shccharge = shccharge
    }

    fun getDestinationBranchId(): Int? {
        return destinationBranchId
    }

    fun setDestinationBranchId(destinationBranchId: Int?) {
        this.destinationBranchId = destinationBranchId
    }
}