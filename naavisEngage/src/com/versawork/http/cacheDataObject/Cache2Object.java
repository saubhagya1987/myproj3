package com.versawork.http.cacheDataObject;

import java.lang.reflect.Type;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.versawork.http.cacheClientsInfo.ClientCacheSettingsCheck;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.exception.BusinessException;

public class Cache2Object<T> {

	private static ClientCacheSettingsCheck cCS;

	private static CacheAccessbyJson cache;
	private static Gson gson;
	private static Logger LOGGER = LoggerFactory.getLogger("CACHEFILE");

	public Cache2Object(ClientCacheSettingsCheck cCS, CacheAccessbyJson<T> cache) {
		if (Cache2Object.cCS == null) {
			Cache2Object.cCS = cCS;
		}
		if (Cache2Object.cache == null) {
			Cache2Object.cache = cache;
		}
		if (gson == null) {
			GsonBuilder builder = new GsonBuilder();
			builder.serializeNulls();
			gson = builder.create();
		}
	}

	public List<T> getObject(String cacheKey, Boolean isCachingOn, Type clazz) throws BusinessException {
		String jsonResponse = "null";
		if (isCachingOn) {

			try {

				jsonResponse = cache.fetchCacheData(cacheKey);
				// LOGGER.debug("GOT SAVE String IN CACHE :  "+ jsonResponse);
				if (jsonResponse == null)
					return null;
				else if (jsonResponse.equalsIgnoreCase("error")) {
					isCachingOn = false;
					cCS.sendCacheMail(false);
					return null;
				} else {
					// Type type = new TypeToken<List<T>>() {}.getType();
					List<T> list = gson.fromJson(jsonResponse, clazz);
					return list;
				}

			} catch (Exception e) {
				isCachingOn = false;
				cCS.sendCacheMail(false);
			}
		}

		return null;
	}

	public Boolean setObject(String cacheKey, Boolean isCachingOn, List<T> object, Type type) {
		Boolean isSaved = false;
		if (isCachingOn) {
			try {

				String json = gson.toJson(object, type);
				isSaved = cache.setCacheData(cacheKey, json);
				// LOGGER.debug("Save cache status "+isSaved );
				return isSaved;
			} catch (Exception e) {
				LOGGER.debug("Failed to set lab cache.");
			}
		}
		return isSaved;
	}

}
