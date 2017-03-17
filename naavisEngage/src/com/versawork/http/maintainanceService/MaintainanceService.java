package com.versawork.http.maintainanceService;

import org.springframework.stereotype.Component;

import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;

@Component
public interface MaintainanceService {

	public NsMaintainanceResponse maintainanceService(MaintananceInfo nsRequest) throws BusinessException,
			SystemException;

}
