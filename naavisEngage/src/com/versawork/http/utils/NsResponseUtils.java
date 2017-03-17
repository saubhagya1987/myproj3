package com.versawork.http.utils;

import com.versawork.http.dataobject.AccountInfo;
import com.versawork.http.dataobject.CacheInputs;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.ResponseData;

public class NsResponseUtils {

	public static ResponseData getResponseData(String description, Integer result) {
		ResponseData responseData = new ResponseData();
		responseData.setDescription(description);
		responseData.setResult(result);
		return responseData;

	}

	public static NsResponse getNsResponse(AccountInfo accountInfo, String responseDataDescription,
			Integer responseDataResult) {
		NsResponse nsResponse = new NsResponse();
		if (accountInfo != null) {
			nsResponse.setAccountInfo(accountInfo);
		}
		nsResponse.setResponseData(getResponseData(responseDataDescription, responseDataResult));
		return nsResponse;
	}

	public static NsResponse getCacheResponse(CacheInputs cacheInputs, String responseDataDescription,
			Integer responseDataResult) {
		NsResponse nsResponse = new NsResponse();
		if (cacheInputs != null) {
			nsResponse.setCacheInputs(cacheInputs);
		}
		nsResponse.setResponseData(getResponseData(responseDataDescription, responseDataResult));
		return nsResponse;
	}
	
	public static ResponseData getResponseData(String header, String description, Integer result) {
		ResponseData responseData = new ResponseData();
		responseData.setDescription(description);
		responseData.setResult(result);
		responseData.setHeader(header);
		return responseData;

	}

	public static NsResponse getNsResponse(AccountInfo accountInfo,String header, String responseDataDescription,
			Integer responseDataResult) {
		NsResponse nsResponse = new NsResponse();
		if (accountInfo != null) {
			nsResponse.setAccountInfo(accountInfo);
		}
		nsResponse.setResponseData(getResponseData(header, responseDataDescription, responseDataResult));
		return nsResponse;
	}
}
