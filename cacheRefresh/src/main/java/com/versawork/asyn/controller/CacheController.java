package com.versawork.asyn.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.versawork.asyn.constant.LogFile;
import com.versawork.asyn.dataobject.NsResponse;
import com.versawork.asyn.dataobject.ResponseData;
import com.versawork.asyn.exception.BusinessException;
import com.versawork.asyn.service.CacheService;
import com.versawork.http.caching.files.JedisFactory;
import com.versawork.http.model.EtlLogger;

/**
 * @author Sohaib https://[HOST_IP]:[PORT
 *         NUMBER]/cacheRefresh/[CLIENT_DATABASE_IP
 *         ]/service/cacheService/cacheRefresh
 *
 */
@Controller
@RequestMapping("/cacheService")
public class CacheController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogFile.ETL_CACHE_LOG_FILE.getFileName());

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CacheService cacheService;

	@Autowired
	private JedisFactory jedisFactory;

	Gson gson = new Gson();

	@RequestMapping(value = { "/cacheRefresh" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody NsResponse cacheRefresh() throws BusinessException {

		LOGGER.debug("\n REQUEST RECEIEVED FOR CACHE REFRESH SERVICE : ");
		NsResponse nsResponse = new NsResponse();
		String message = "Success";
		Integer code = 0;
		try {

			jedisFactory.getJedisConnectionFactory();
			if (!JedisFactory.cachingOn) {
				message = "Cache cluster down.";
				LOGGER.debug("REDIS CLUSTER STATUS : " + message.toUpperCase() + " : \n");
				code = 10;
			}

		} catch (Exception e) {
			message = "Exception occured while accessing cache cluster";
			LOGGER.error("REDIS CLUSTER STATUS : " + message.toUpperCase() + " : \n");
			code = 20;
			e.printStackTrace();
		}
		if (code == 0) {

			new Thread() {

				@Override
				public void run() {

					cacheAction();
				};
			}.start();

		}
		ResponseData responseData = new ResponseData();
		responseData.setDescription(message);
		responseData.setResult(code);
		nsResponse.setResponseData(responseData);

		LOGGER.debug("RESPONSE SENT BY CACHE REFRESH SERVICE : \n" + gson.toJson(nsResponse));
		return nsResponse;
	}

	public void cacheAction() {

		synchronized (this) {
			Integer clientDatabaseId = Integer.parseInt(messageSource.getMessage("client.database.id", null,
					Locale.getDefault()));
			printCacheKeyFormat();
			List<EtlLogger> etlLoggers = cacheService.getEtlLoggerByClientDatabaseId(clientDatabaseId);
			cacheService.startCacheRefresh(etlLoggers);

			cacheService.updateEtlLoggers(etlLoggers);
		}
	}

	private void printCacheKeyFormat() {
		LOGGER.info("Usage: ");
		LOGGER.info("Patient Allergy      : PALL_<CLIENT_DATABASE_ID>_<ACCOUNT_ID>");
		LOGGER.info("Patient Lab	      : PLAB_<CLIENT_DATABASE_ID>_<ACCOUNT_ID>");
		LOGGER.info("Patient Lab History  : PLHIS_<CLIENT_DATABASE_ID>_<ACCOUNT_ID>_<LABCODE>");
		LOGGER.info("Patient Lab By Visit : PVIS_PLAB_<CLIENT_DATABASE_ID>_<ACCOUNT_ID>_<VISIT_ID>");
		LOGGER.info("Patient Imaging      : PIMG_<CLIENT_DATABASE_ID>_<ACCOUNT_ID>");
		LOGGER.info("Patient Medication   : PMED_<CLIENT_DATABASE_ID>_<ACCOUNT_ID>");
		LOGGER.info("Patient Medication By Visit : PVIS_PMED_<CLIENT_DATABASE_ID>_<ACCOUNT_ID>_<VISIT_ID>");
		LOGGER.info("Patient Problem      : PPROB_<CLIENT_DATABASE_ID>_<ACCOUNT_ID>");
		LOGGER.info("Patient Vital Signs  : PVIS_PVSI_<CLIENT_DATABASE_ID>_<ACCOUNT_ID>_<VISIT_ID>");
		LOGGER.info("Patient Procedure 	  : PVIS_PPROC_<CLIENT_DATABASE_ID>_<ACCOUNT_ID>_<VISIT_ID>");
		LOGGER.info("Patient Care Team    : PVIS_PCTE_<CLIENT_DATABASE_ID>_<ACCOUNT_ID>_<VISIT_ID>");
		LOGGER.info("Patient Diagnosis    : PVIS_PDIA_<CLIENT_DATABASE_ID>_<ACCOUNT_ID>_<VISIT_ID>");
		LOGGER.info("Patient Immunization : PVIS_PIMM_<CLIENT_DATABASE_ID>_<ACCOUNT_ID>_<VISIT_ID>");
		LOGGER.info("Patient Functional Status : PVIS_PFST_<CLIENT_DATABASE_ID>_<ACCOUNT_ID>_<VISIT_ID>");
		LOGGER.info("Facility Notice 	  : FNO_<CLIENT_DATABASE_ID>");
		LOGGER.info("Facility Provider 	  : FPR_<CLIENT_DATABASE_ID>");
		LOGGER.info("Facility Service 	  : FSE_<CLIENT_DATABASE_ID>");
		LOGGER.info("Patient Inpatient Metadata : PVIS_PVIN_<CLIENT_DATABASE_ID>_<ACCOUNT_ID>_<VISIT_ID>");
		LOGGER.info("Patient Visit		  : PVIS_<CLIENT_DATABASE_ID>_<ACCOUNT_ID>");
		LOGGER.info("Patient Functional Status : PVIS_PFST_<CLIENT_DATABASE_ID>_<ACCOUNT_ID>_<VISIT_ID>");

	}
}
