package com.versawork.http.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dao.ClientNaavisDatabaseServiceDAO;
import com.versawork.http.dao.GetServicesListDAO;
import com.versawork.http.dao.LinkedAccountDao;
import com.versawork.http.dao.PatientBillingDAO;
import com.versawork.http.dataobject.BillDetailData;
import com.versawork.http.dataobject.BillSummaryData;
import com.versawork.http.dataobject.BillingInfo;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.ResponseData;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.PatientBillDetail;
import com.versawork.http.model.PatientBillSummary;
import com.versawork.http.model.PatientTransactionLog;
import com.versawork.http.service.BillService;
import com.versawork.http.utils.DateUtils;

@Service
public class BillServiceImpl implements BillService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private PatientBillingDAO patientBillingDAO;

	@Autowired
	private MessageSource messageSource;
	static Logger LOGGER = LoggerFactory.getLogger(BillServiceImpl.class);

	@Override
	public NsResponse getBillSummaryList(Integer accountId) throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		List<PatientBillSummary> billSummaries = patientBillingDAO.getPatientBillSummaryList(accountId, false);
		List<BillSummaryData> billSummaryDataList = new ArrayList<BillSummaryData>();
		if (billSummaries == null || billSummaries.isEmpty()) {
			LOGGER.debug("Bill Summary data not found in getBillSummaryList");
			throw new BusinessException(VersaWorkConstant.NO_BILL_SUMMARY_FOUND);
		} else {
			for (PatientBillSummary billSummary : billSummaries) {
				BillSummaryData billSummaryData = new BillSummaryData();
				billSummaryData.setInvoiceNumber(billSummary.getPatientBillSummaryPK().getBillId());
				billSummaryData.setSourceName(billSummary.getSourceName());
				billSummaryData.setEncounterStartDate(DateUtils.getFormatDate(billSummary.getEncounterStartDate(),
						VersaWorkConstant.TIME_FORMAT_MM_DD_YYYY));
				billSummaryData.setEncounterEndDate(DateUtils.getFormatDate(billSummary.getEncounterEndDate(),
						VersaWorkConstant.TIME_FORMAT_MM_DD_YYYY));
				billSummaryData.setTotalDue(billSummary.getTotalDue().toString());
				billSummaryDataList.add(billSummaryData);

				billSummary.setIsViewed(true);
				patientBillingDAO.update(billSummary);
			}
			nsResponse.setBillSummaryDataList(billSummaryDataList);
		}

		responseData.setResult(VersaWorkConstant.Status_Codes.SUCCESS_RESPONSE_CODE.getCode());
		responseData.setDescription(VersaWorkConstant.RESPONSE_DATA_DESCRIPTION_SUCCESS);
		nsResponse.setResponseData(responseData);

		return nsResponse;
	}

	@Override
	public NsResponse getBillDetail(String billId) throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		PatientBillSummary billSummary = patientBillingDAO.getPatientBillSummaryByBillId(billId);
		List<BillDetailData> billDetailDataList = new ArrayList<BillDetailData>();
		BillSummaryData billSummaryData = new BillSummaryData();
		List<PatientBillDetail> patientBillDetailList = patientBillingDAO.getPatientBillDetailList(billId);
		if (patientBillDetailList == null || patientBillDetailList.isEmpty()) {
			throw new BusinessException(VersaWorkConstant.NO_RESULT_FOUND);
		}
		billSummaryData.setSourceName(billSummary.getSourceName());
		billSummaryData.setAddress1(billSummary.getAddress1());
		billSummaryData.setAddress2(billSummary.getAddress2());
		billSummaryData.setCity(billSummary.getCity());
		billSummaryData.setState(billSummary.getState());
		billSummaryData.setPostalCode(billSummary.getPostalCode());
		billSummaryData.setAccountNumber(billSummary.getPatientBillSummaryPK().getVisitIdentifier());
		billSummaryData.setInvoiceNumber(billSummary.getPatientBillSummaryPK().getBillId());
		billSummaryData.setInsuranceProvider(billSummary.getInsuranceProvider());
		billSummaryData.setBillDate(DateUtils.getFormatDate(billSummary.getBillDate(),
				VersaWorkConstant.TIME_FORMAT_MM_DD_YYYY));
		billSummaryData.setEncounterStartDate(DateUtils.getFormatDate(billSummary.getEncounterStartDate(),
				VersaWorkConstant.TIME_FORMAT_MM_DD_YYYY));
		billSummaryData.setEncounterEndDate(DateUtils.getFormatDate(billSummary.getEncounterEndDate(),
				VersaWorkConstant.TIME_FORMAT_MM_DD_YYYY));
		billSummaryData.setTotalDue(billSummary.getTotalDue().toString());

		for (PatientBillDetail patientBillDetail : patientBillDetailList) {
			BillDetailData billDetailData = new BillDetailData();
			billDetailData.setBillDescription(patientBillDetail.getBillDescription());
			billDetailData.setQuantity(patientBillDetail.getQuantity().toString());
			billDetailData.setDetailAmount(patientBillDetail.getDetailAmount().toString());

			billDetailDataList.add(billDetailData);
		}
		billSummaryData.setBillDetailDataList(billDetailDataList);
		nsResponse.setBillSummaryData(billSummaryData);
		responseData.setResult(VersaWorkConstant.Status_Codes.SUCCESS_RESPONSE_CODE.getCode());
		responseData.setDescription(VersaWorkConstant.RESPONSE_DATA_DESCRIPTION_SUCCESS);
		nsResponse.setResponseData(responseData);

		return nsResponse;
	}

	public NsResponse getTransactionStatus(NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responseData = new ResponseData();
		BillingInfo billingInfo = nsRequest.getBillingInfo();
		PatientBillSummary billSummary = patientBillingDAO
				.getPatientBillSummaryByBillId(billingInfo.getInvoiceNumber());
		if (billingInfo.getBillingStatus().equalsIgnoreCase(VersaWorkConstant.BILLING_STATUS_APPROVED)) {
			billSummary.setIsBillPaid(true);
			patientBillingDAO.update(billSummary);
		}
		PatientTransactionLog patientTransactionLog = new PatientTransactionLog();
		patientTransactionLog.setAmount(BigDecimal.valueOf(Float.parseFloat(billingInfo.getAmountPaid())));
		patientTransactionLog.setBillId(billingInfo.getInvoiceNumber());
		patientTransactionLog.setDateTime(DateUtils.getDate(billingInfo.getDatTime(), "yyyy-MM-dd HH:mm:ss"));
		patientTransactionLog.setResponse(billingInfo.getBillingResponse());
		patientTransactionLog.setStatus(billingInfo.getBillingStatus());
		patientBillingDAO.create(patientTransactionLog);
		responseData.setResult(VersaWorkConstant.Status_Codes.SUCCESS_RESPONSE_CODE.getCode());
		responseData.setDescription(VersaWorkConstant.RESPONSE_DATA_DESCRIPTION_SUCCESS);
		nsResponse.setResponseData(responseData);

		// persist transaction data

		return nsResponse;
	}
}
