package com.versawork.http.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.ResponseData;
import com.versawork.http.dataobject.SystemAdministratorInfo;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.service.SystemAdministratorService;
import com.versawork.http.utils.Client;
import com.versawork.http.utils.DateUtils;
import com.versawork.http.utils.SecureMailClient;

/**
 * @author Dheeraj
 * 
 */

@Component
public class SystemAdministratorServiceImpl implements SystemAdministratorService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final static Logger LOGGER = LoggerFactory.getLogger(SystemAdministratorServiceImpl.class);

	@Autowired
	private MessageSource messageSource;

	/*
	 * NsResponse nsResponse = new NsResponse(); ResponseData responsedata = new
	 * ResponseData(); SystemAdministratorInfo systemAdministratorInfo = new
	 * SystemAdministratorInfo();
	 */

	@Override
	public NsResponse getDirectAddressInfo(NsRequest nsRequest) throws BusinessException, SystemException {
		byte[] certDesc = null;

		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();
		SystemAdministratorInfo systemAdministratorInfo = new SystemAdministratorInfo();

		systemAdministratorInfo.setEmail(nsRequest.getSystemAdministratorInfo().getEmail());

		Client client = new SecureMailClient();
		certDesc = client.lookupCertificate(nsRequest.getSystemAdministratorInfo().getEmail());
		CertificateFactory cf;
		X509Certificate cert = null;
		try {
			cf = CertificateFactory.getInstance("X.509");
			cert = (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(certDesc));
		} catch (CertificateException e) {
			throw new SystemException(e);
		}

		cert.toString();
		// systemAdministratorInfo.setCertificateDesc(nsResponse.getSystemAdministratorInfo().getCertificateDesc());

		systemAdministratorInfo.setCertificateDesc(new String(cert.toString().trim()));

		responsedata.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("Success");
		nsResponse.setResponseData(responsedata);
		nsResponse.setSystemAdministratorInfo(systemAdministratorInfo);

		return nsResponse;
	}

	@Override
	public NsResponse getNTPServerTime(NsRequest nsRequest) throws BusinessException, SystemException {
		NsResponse nsResponse = new NsResponse();
		ResponseData responsedata = new ResponseData();
		SystemAdministratorInfo systemAdministratorInfo = new SystemAdministratorInfo();

		systemAdministratorInfo.setNTPServerTime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.ENGLISH).format(
				getNTPDate(nsRequest.getSystemAdministratorInfo().getServerTimeZone())).toString()); // Don't
																										// change
																										// the
																										// Date
																										// pattern(dependency
																										// on
																										// IOS)

		systemAdministratorInfo.setNaavisSystemTime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.ENGLISH).format(
				new Date()).toString()); // Don't change the Date
											// pattern(dependency on
											// IOS)

		responsedata.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE, null,
				nsRequest.getLocale())));
		responsedata.setDescription("Success");
		nsResponse.setResponseData(responsedata);
		nsResponse.setSystemAdministratorInfo(systemAdministratorInfo);

		return nsResponse;
	}

	public static Date getNTPDate(String server) {
		NTPUDPClient client = new NTPUDPClient();
		// We want to timeout if a response takes longer than 5 seconds
		client.setDefaultTimeout(5000);
		try {
			InetAddress hostAddr = InetAddress.getByName(server);
			TimeInfo info = client.getTime(hostAddr);
			Date date = new Date(info.getReturnTime());
			return date;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
		return null;
	}
}