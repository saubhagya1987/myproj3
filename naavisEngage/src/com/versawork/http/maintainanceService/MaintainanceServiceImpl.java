package com.versawork.http.maintainanceService;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.PatientUpcomingAppointment;
import com.versawork.http.utils.DateUtils;

@Service
public class MaintainanceServiceImpl implements MaintainanceService {

	@Autowired
	private MaintainanceServiceDAO maintainanceServiceDAO;

	@Autowired
	private MessageSource messageSource;

	final static Logger LOGGER = LoggerFactory.getLogger(MaintainanceServiceImpl.class);

	@Override
	public NsMaintainanceResponse maintainanceService(MaintananceInfo nsRequest) throws BusinessException,
			SystemException {
		try {

			PatientUpcomingAppointment patientUpcomingAppointments = new PatientUpcomingAppointment();
			NsMaintainanceResponse maintainanceResponse = new NsMaintainanceResponse();

			patientUpcomingAppointments.getPatientUpcomingAppointmentPK()
					.setAppointmentId(nsRequest.getAppointmentId());

			// new db changes

			// patientUpcomingAppointments.setMrNumber(nsRequest.getUnitNumber());
			patientUpcomingAppointments.setProviderName(nsRequest.getProviderName());
			patientUpcomingAppointments.setTypeDescription(nsRequest.getTypeDescription());
			// patientUpcomingAppointments.setClientDatabaseId(nsRequest.getClientDataBaseId());
			patientUpcomingAppointments.setSourceId(nsRequest.getSourceId());
			patientUpcomingAppointments.setLocationName(nsRequest.getLocationName());
			patientUpcomingAppointments.setAppointmentDate(DateUtils.getDate(nsRequest.getAppointmentDate(),
					"dd-MM-yyyy HH:mm"));
			// patientUpcomingAppointments.setDateProcessed(DateUtils.getDate(nsRequest.getDateProcessed(),"dd-MM-yyyy HH:mm"));
			patientUpcomingAppointments.setDateAdded(DateUtils.getDate(nsRequest.getDateAdded(), "dd-MM-yyyy HH:mm"));

			maintainanceServiceDAO.persist(patientUpcomingAppointments);

			MaintainanceResponseData responsedata = new MaintainanceResponseData();
			responsedata.setResult(Integer.parseInt(messageSource.getMessage(VersaWorkConstant.SUCCESS_RESPONSE_CODE,
					null, Locale.getDefault())));
			responsedata.setDescription("Appointment Added");

			maintainanceResponse.setMaintainanceResponseData(responsedata);

			return maintainanceResponse;
		} catch (Exception exp) {
			exp.printStackTrace();
			LOGGER.error("Exception occoured on Maintainance Service (appointments)(Hard Delete)) : ", exp);
			throw new BusinessException(exp.getMessage());
		}
	}
}