package com.versawork.http.cacheClientsInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.versawork.http.caching.files.JedisFactory;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.utils.SendMail;

@Component
public class ClientCacheSettingsCheck {

	/*
	 * @Autowired private SendMail sendMail;
	 */

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private SendMail sendMail;


	private Map<String, Map<String, Boolean>> settingsMap;
	private String isCacheModuleOn;
	static Logger LOGGER = LoggerFactory.getLogger("CACHEFILE");

	/*
	 * public static void main(String args[]) throws BusinessException,
	 * SystemException { Boolean bool = isCachingOn(1,"PV");
	 * System.out.println(bool); }
	 */
	public Boolean isCachingOn(String module) throws BusinessException, SystemException {
		try {
			String clientDataBaseIdForCalc = messageSource.getMessage("client.database.id", null, Locale.getDefault());
			if (settingsMap == null || !settingsMap.containsKey(clientDataBaseIdForCalc)) {
				settingsMap = new HashMap<String, Map<String, Boolean>>();
				isCacheModuleOn = messageSource.getMessage("isCacheModuleOn", null, Locale.getDefault());
				if (isCacheModuleOn.equalsIgnoreCase("false")) {
					isCacheModuleOn = null;
					return false;
				} else/* (isCacheModuleOn.equalsIgnoreCase("true")) */{

					Integer totalModules = Integer.parseInt(messageSource.getMessage("totalModules", null,
							Locale.getDefault()));

					List<String> modules = new ArrayList<String>();
					String moduleName = null;// messageSource.getMessage("M" +
												// x, null,
												// Locale.getDefault());
					for (int x = 1; x <= totalModules; x++) {
						moduleName = messageSource.getMessage("M" + x, null, Locale.getDefault());
						if (moduleName != null && !moduleName.isEmpty())
							modules.add(moduleName.trim());
					}

					String moduleForCalc = "";
					Map<String, Boolean> modulemap = new HashMap<String, Boolean>();
					Iterator<String> iterModules = modules.iterator();
					String isCacheModuleOnFor = null;
					while (iterModules.hasNext()) {
						moduleForCalc = iterModules.next();
						isCacheModuleOnFor = "isCacheModuleOnFor" + moduleForCalc;
						if (isCacheModuleOn.equalsIgnoreCase("true")
								&& messageSource.getMessage(isCacheModuleOnFor, null, Locale.getDefault())
										.equalsIgnoreCase("true")) {
							modulemap.put(moduleForCalc, true);
						} else {
							modulemap.put(moduleForCalc, false);
						}
					}
					settingsMap.put(clientDataBaseIdForCalc, modulemap);
				}
				LOGGER.info("Successfully initialized Cache Access Settings");
			}
			Map<String, Boolean> calcMap = new HashMap<String, Boolean>();
			calcMap = settingsMap.get(clientDataBaseIdForCalc);
			if (JedisFactory.cachingOn) {
				boolean modeuleOn = calcMap.get(module);
				return modeuleOn;
				/*
				 * if(calcMap.get(module)==null) { return false; } else { return
				 * true; }
				 */
			} else {
				if (!JedisFactory.mailCounter) {
					sendCacheMail(true);
					JedisFactory.mailCounter = true;
				}
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("error fetching cache settings " + e.getMessage());
			return false;
		}
	}

	public void sendCacheMail(Boolean duringInit) throws BusinessException {

		try {

			Runnable sendCacheDownMail = new SendCacheMail(duringInit, sendMail, messageSource);
			Thread t1 = new Thread(sendCacheDownMail);
			t1.start();
		} catch (Exception exp) {
			exp.printStackTrace();
			LOGGER.error("Exception occoured while sending the mail during creating Account: ", exp);
			throw new BusinessException(exp.getMessage());
		}
	}

	public String getHashSlotBean(Integer slot) {
		return messageSource.getMessage(slot.toString(), null, Locale.getDefault());

	}

	public Integer getTotalSlots() {
		String totalSlots = messageSource.getMessage("totalSlots", null, Locale.getDefault());
		return Integer.parseInt(totalSlots);
	}
}