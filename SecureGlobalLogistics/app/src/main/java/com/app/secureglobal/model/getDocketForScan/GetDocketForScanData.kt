package com.app.secureglobal.model.getDocketForScan

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetDocketForScanData {


    @SerializedName("docketId")
    @Expose
    private var docketId: Int? = null

    @SerializedName("businessId")
    @Expose
    private var businessId: Int? = null

    @SerializedName("originBranchId")
    @Expose
    private var originBranchId: Int? = null

    @SerializedName("docketNo")
    @Expose
    private var docketNo: String? = null

    @SerializedName("docketDate")
    @Expose
    private var docketDate: String? = null

    @SerializedName("docketSrNo")
    @Expose
    private var docketSrNo: Int? = null

    @SerializedName("shipperId")
    @Expose
    private var shipperId: Int? = null

    @SerializedName("senderName")
    @Expose
    private var senderName: Any? = null

    @SerializedName("senderMobileNo")
    @Expose
    private var senderMobileNo: Any? = null

    @SerializedName("buyerId")
    @Expose
    private var buyerId: Int? = null

    @SerializedName("receiverName")
    @Expose
    private var receiverName: Any? = null

    @SerializedName("receiverMobileNo")
    @Expose
    private var receiverMobileNo: Any? = null

    @SerializedName("goodsDescription")
    @Expose
    private var goodsDescription: String? = null

    @SerializedName("noOfPkg")
    @Expose
    private var noOfPkg: Int? = null

    @SerializedName("netWt")
    @Expose
    private var netWt: Double? = null

    @SerializedName("netWtUnit")
    @Expose
    private var netWtUnit: String? = null

    @SerializedName("grWt")
    @Expose
    private var grWt: Double? = null

    @SerializedName("grWtUnit")
    @Expose
    private var grWtUnit: String? = null

    @SerializedName("dimensionLength")
    @Expose
    private var dimensionLength: Int? = null

    @SerializedName("dimensionWidth")
    @Expose
    private var dimensionWidth: Int? = null

    @SerializedName("dimensionHeight")
    @Expose
    private var dimensionHeight: Int? = null

    @SerializedName("sglbagNo")
    @Expose
    private var sglbagNo: String? = null

    @SerializedName("specialInstuction")
    @Expose
    private var specialInstuction: String? = null

    @SerializedName("invoiceNo")
    @Expose
    private var invoiceNo: String? = null

    @SerializedName("invoiceValue")
    @Expose
    private var invoiceValue: Double? = null

    @SerializedName("typeOfService")
    @Expose
    private var typeOfService: Int? = null

    @SerializedName("insurance")
    @Expose
    private var insurance: Int? = null

    @SerializedName("productType")
    @Expose
    private var productType: Int? = null

    @SerializedName("createBy")
    @Expose
    private var createBy: Int? = null

    @SerializedName("createDate")
    @Expose
    private var createDate: String? = null

    @SerializedName("modifyBy")
    @Expose
    private var modifyBy: Int? = null

    @SerializedName("modifyDate")
    @Expose
    private var modifyDate: String? = null

    @SerializedName("currentBranchId")
    @Expose
    private var currentBranchId: Int? = null

    @SerializedName("destinationBranchId")
    @Expose
    private var destinationBranchId: Int? = null

    @SerializedName("currentStatusId")
    @Expose
    private var currentStatusId: Int? = null

    @SerializedName("buyerCode")
    @Expose
    private var buyerCode: String? = null

    @SerializedName("buyerName")
    @Expose
    private var buyerName: String? = null

    @SerializedName("buyerAddress")
    @Expose
    private var buyerAddress: String? = null

    @SerializedName("buyerCity")
    @Expose
    private var buyerCity: String? = null

    @SerializedName("buyerState")
    @Expose
    private var buyerState: String? = null

    @SerializedName("buyerPin")
    @Expose
    private var buyerPin: String? = null

    @SerializedName("buyerMobileNo")
    @Expose
    private var buyerMobileNo: String? = null

    @SerializedName("buyerEmail")
    @Expose
    private var buyerEmail: Any? = null

    @SerializedName("buyerGstin")
    @Expose
    private var buyerGstin: Any? = null

    @SerializedName("buyerPersonName")
    @Expose
    private var buyerPersonName: Any? = null

    @SerializedName("buyerPersonMobile")
    @Expose
    private var buyerPersonMobile: String? = null

    @SerializedName("shipperCode")
    @Expose
    private var shipperCode: String? = null

    @SerializedName("shipperName")
    @Expose
    private var shipperName: String? = null

    @SerializedName("shipperAddress")
    @Expose
    private var shipperAddress: String? = null

    @SerializedName("shipperCity")
    @Expose
    private var shipperCity: String? = null

    @SerializedName("shipperState")
    @Expose
    private var shipperState: String? = null

    @SerializedName("shipperPin")
    @Expose
    private var shipperPin: String? = null

    @SerializedName("shipperMobileNo")
    @Expose
    private var shipperMobileNo: String? = null

    @SerializedName("shipperEmail")
    @Expose
    private var shipperEmail: Any? = null

    @SerializedName("shipperGstin")
    @Expose
    private var shipperGstin: String? = null

    @SerializedName("shipperPersonName")
    @Expose
    private var shipperPersonName: String? = null

    @SerializedName("shipperPersonMobile")
    @Expose
    private var shipperPersonMobile: String? = null

    @SerializedName("status")
    @Expose
    private var status: String? = null

    @SerializedName("freight")
    @Expose
    private var freight: Int? = null

    @SerializedName("billingType")
    @Expose
    private var billingType: Int? = null

    @SerializedName("transportMode")
    @Expose
    private var transportMode: String? = null

    @SerializedName("originBranchCode")
    @Expose
    private var originBranchCode: String? = null

    @SerializedName("currentBranch")
    @Expose
    private var currentBranch: String? = null

    @SerializedName("insuranceCharge")
    @Expose
    private var insuranceCharge: Any? = null

    @SerializedName("shccharge")
    @Expose
    private var shccharge: Any? = null

    @SerializedName("isEditable")
    @Expose
    private var isEditable: Boolean? = null

    @SerializedName("branchList")
    @Expose
    private var branchList: List<GetDocketBranchData>? = null

    fun getDocketId(): Int? {
        return docketId
    }

    fun setDocketId(docketId: Int?) {
        this.docketId = docketId
    }

    fun getBusinessId(): Int? {
        return businessId
    }

    fun setBusinessId(businessId: Int?) {
        this.businessId = businessId
    }

    fun getOriginBranchId(): Int? {
        return originBranchId
    }

    fun setOriginBranchId(originBranchId: Int?) {
        this.originBranchId = originBranchId
    }

    fun getDocketNo(): String? {
        return docketNo
    }

    fun setDocketNo(docketNo: String?) {
        this.docketNo = docketNo
    }

    fun getDocketDate(): String? {
        return docketDate
    }

    fun setDocketDate(docketDate: String?) {
        this.docketDate = docketDate
    }

    fun getDocketSrNo(): Int? {
        return docketSrNo
    }

    fun setDocketSrNo(docketSrNo: Int?) {
        this.docketSrNo = docketSrNo
    }

    fun getShipperId(): Int? {
        return shipperId
    }

    fun setShipperId(shipperId: Int?) {
        this.shipperId = shipperId
    }

    fun getSenderName(): Any? {
        return senderName
    }

    fun setSenderName(senderName: Any?) {
        this.senderName = senderName
    }

    fun getSenderMobileNo(): Any? {
        return senderMobileNo
    }

    fun setSenderMobileNo(senderMobileNo: Any?) {
        this.senderMobileNo = senderMobileNo
    }

    fun getBuyerId(): Int? {
        return buyerId
    }

    fun setBuyerId(buyerId: Int?) {
        this.buyerId = buyerId
    }

    fun getReceiverName(): Any? {
        return receiverName
    }

    fun setReceiverName(receiverName: Any?) {
        this.receiverName = receiverName
    }

    fun getReceiverMobileNo(): Any? {
        return receiverMobileNo
    }

    fun setReceiverMobileNo(receiverMobileNo: Any?) {
        this.receiverMobileNo = receiverMobileNo
    }

    fun getGoodsDescription(): String? {
        return goodsDescription
    }

    fun setGoodsDescription(goodsDescription: String?) {
        this.goodsDescription = goodsDescription
    }

    fun getNoOfPkg(): Int? {
        return noOfPkg
    }

    fun setNoOfPkg(noOfPkg: Int?) {
        this.noOfPkg = noOfPkg
    }

    fun getNetWt(): Double? {
        return netWt
    }

    fun setNetWt(netWt: Double?) {
        this.netWt = netWt
    }

    fun getNetWtUnit(): String? {
        return netWtUnit
    }

    fun setNetWtUnit(netWtUnit: String?) {
        this.netWtUnit = netWtUnit
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

    fun getDimensionLength(): Int? {
        return dimensionLength
    }

    fun setDimensionLength(dimensionLength: Int?) {
        this.dimensionLength = dimensionLength
    }

    fun getDimensionWidth(): Int? {
        return dimensionWidth
    }

    fun setDimensionWidth(dimensionWidth: Int?) {
        this.dimensionWidth = dimensionWidth
    }

    fun getDimensionHeight(): Int? {
        return dimensionHeight
    }

    fun setDimensionHeight(dimensionHeight: Int?) {
        this.dimensionHeight = dimensionHeight
    }

    fun getSglbagNo(): String? {
        return sglbagNo
    }

    fun setSglbagNo(sglbagNo: String?) {
        this.sglbagNo = sglbagNo
    }

    fun getSpecialInstuction(): String? {
        return specialInstuction
    }

    fun setSpecialInstuction(specialInstuction: String?) {
        this.specialInstuction = specialInstuction
    }

    fun getInvoiceNo(): String? {
        return invoiceNo
    }

    fun setInvoiceNo(invoiceNo: String?) {
        this.invoiceNo = invoiceNo
    }

    fun getInvoiceValue(): Double? {
        return invoiceValue
    }

    fun setInvoiceValue(invoiceValue: Double?) {
        this.invoiceValue = invoiceValue
    }

    fun getTypeOfService(): Int? {
        return typeOfService
    }

    fun setTypeOfService(typeOfService: Int?) {
        this.typeOfService = typeOfService
    }

    fun getInsurance(): Int? {
        return insurance
    }

    fun setInsurance(insurance: Int?) {
        this.insurance = insurance
    }

    fun getProductType(): Int? {
        return productType
    }

    fun setProductType(productType: Int?) {
        this.productType = productType
    }

    fun getCreateBy(): Int? {
        return createBy
    }

    fun setCreateBy(createBy: Int?) {
        this.createBy = createBy
    }

    fun getCreateDate(): String? {
        return createDate
    }

    fun setCreateDate(createDate: String?) {
        this.createDate = createDate
    }

    fun getModifyBy(): Int? {
        return modifyBy
    }

    fun setModifyBy(modifyBy: Int?) {
        this.modifyBy = modifyBy
    }

    fun getModifyDate(): String? {
        return modifyDate
    }

    fun setModifyDate(modifyDate: String?) {
        this.modifyDate = modifyDate
    }

    fun getCurrentBranchId(): Int? {
        return currentBranchId
    }

    fun setCurrentBranchId(currentBranchId: Int?) {
        this.currentBranchId = currentBranchId
    }

    fun getDestinationBranchId(): Int? {
        return destinationBranchId
    }

    fun setDestinationBranchId(destinationBranchId: Int?) {
        this.destinationBranchId = destinationBranchId
    }

    fun getCurrentStatusId(): Int? {
        return currentStatusId
    }

    fun setCurrentStatusId(currentStatusId: Int?) {
        this.currentStatusId = currentStatusId
    }

    fun getBuyerCode(): String? {
        return buyerCode
    }

    fun setBuyerCode(buyerCode: String?) {
        this.buyerCode = buyerCode
    }

    fun getBuyerName(): String? {
        return buyerName
    }

    fun setBuyerName(buyerName: String?) {
        this.buyerName = buyerName
    }

    fun getBuyerAddress(): String? {
        return buyerAddress
    }

    fun setBuyerAddress(buyerAddress: String?) {
        this.buyerAddress = buyerAddress
    }

    fun getBuyerCity(): String? {
        return buyerCity
    }

    fun setBuyerCity(buyerCity: String?) {
        this.buyerCity = buyerCity
    }

    fun getBuyerState(): String? {
        return buyerState
    }

    fun setBuyerState(buyerState: String?) {
        this.buyerState = buyerState
    }

    fun getBuyerPin(): String? {
        return buyerPin
    }

    fun setBuyerPin(buyerPin: String?) {
        this.buyerPin = buyerPin
    }

    fun getBuyerMobileNo(): String? {
        return buyerMobileNo
    }

    fun setBuyerMobileNo(buyerMobileNo: String?) {
        this.buyerMobileNo = buyerMobileNo
    }

    fun getBuyerEmail(): Any? {
        return buyerEmail
    }

    fun setBuyerEmail(buyerEmail: Any?) {
        this.buyerEmail = buyerEmail
    }

    fun getBuyerGstin(): Any? {
        return buyerGstin
    }

    fun setBuyerGstin(buyerGstin: Any?) {
        this.buyerGstin = buyerGstin
    }

    fun getBuyerPersonName(): Any? {
        return buyerPersonName
    }

    fun setBuyerPersonName(buyerPersonName: Any?) {
        this.buyerPersonName = buyerPersonName
    }

    fun getBuyerPersonMobile(): String? {
        return buyerPersonMobile
    }

    fun setBuyerPersonMobile(buyerPersonMobile: String?) {
        this.buyerPersonMobile = buyerPersonMobile
    }

    fun getShipperCode(): String? {
        return shipperCode
    }

    fun setShipperCode(shipperCode: String?) {
        this.shipperCode = shipperCode
    }

    fun getShipperName(): String? {
        return shipperName
    }

    fun setShipperName(shipperName: String?) {
        this.shipperName = shipperName
    }

    fun getShipperAddress(): String? {
        return shipperAddress
    }

    fun setShipperAddress(shipperAddress: String?) {
        this.shipperAddress = shipperAddress
    }

    fun getShipperCity(): String? {
        return shipperCity
    }

    fun setShipperCity(shipperCity: String?) {
        this.shipperCity = shipperCity
    }

    fun getShipperState(): String? {
        return shipperState
    }

    fun setShipperState(shipperState: String?) {
        this.shipperState = shipperState
    }

    fun getShipperPin(): String? {
        return shipperPin
    }

    fun setShipperPin(shipperPin: String?) {
        this.shipperPin = shipperPin
    }

    fun getShipperMobileNo(): String? {
        return shipperMobileNo
    }

    fun setShipperMobileNo(shipperMobileNo: String?) {
        this.shipperMobileNo = shipperMobileNo
    }

    fun getShipperEmail(): Any? {
        return shipperEmail
    }

    fun setShipperEmail(shipperEmail: Any?) {
        this.shipperEmail = shipperEmail
    }

    fun getShipperGstin(): String? {
        return shipperGstin
    }

    fun setShipperGstin(shipperGstin: String?) {
        this.shipperGstin = shipperGstin
    }

    fun getShipperPersonName(): String? {
        return shipperPersonName
    }

    fun setShipperPersonName(shipperPersonName: String?) {
        this.shipperPersonName = shipperPersonName
    }

    fun getShipperPersonMobile(): String? {
        return shipperPersonMobile
    }

    fun setShipperPersonMobile(shipperPersonMobile: String?) {
        this.shipperPersonMobile = shipperPersonMobile
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun getFreight(): Int? {
        return freight
    }

    fun setFreight(freight: Int?) {
        this.freight = freight
    }

    fun getBillingType(): Int? {
        return billingType
    }

    fun setBillingType(billingType: Int?) {
        this.billingType = billingType
    }

    fun getTransportMode(): String? {
        return transportMode
    }

    fun setTransportMode(transportMode: String?) {
        this.transportMode = transportMode
    }

    fun getOriginBranchCode(): String? {
        return originBranchCode
    }

    fun setOriginBranchCode(originBranchCode: String?) {
        this.originBranchCode = originBranchCode
    }

    fun getCurrentBranch(): String? {
        return currentBranch
    }

    fun setCurrentBranch(currentBranch: String?) {
        this.currentBranch = currentBranch
    }

    fun getInsuranceCharge(): Any? {
        return insuranceCharge
    }

    fun setInsuranceCharge(insuranceCharge: Any?) {
        this.insuranceCharge = insuranceCharge
    }

    fun getShccharge(): Any? {
        return shccharge
    }

    fun setShccharge(shccharge: Any?) {
        this.shccharge = shccharge
    }

    fun getIsEditable(): Boolean? {
        return isEditable
    }

    fun setIsEditable(isEditable: Boolean?) {
        this.isEditable = isEditable
    }

    fun getBranchList(): List<GetDocketBranchData>? {
        return branchList
    }

    fun setBranchList(branchList: List<GetDocketBranchData>?) {
        this.branchList = branchList
    }

}