package com.versawork.http.utils;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.versawork.http.ccd.*;
import com.versawork.http.constant.VersaWorkConstant;
import com.versawork.http.dataobject.AllergiesInfo;
import com.versawork.http.dataobject.CarePlanInfo;
import com.versawork.http.dataobject.LabResultInfo;
import com.versawork.http.dataobject.MedicationInfo;
import com.versawork.http.dataobject.NsDoctorsList;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.PatientInpatientDiagnosisInfo;
import com.versawork.http.dataobject.PatientInpatientFunctionalStatusInfo;
import com.versawork.http.dataobject.PatientInpatientImmunalizationInfo;
import com.versawork.http.dataobject.PatientProblemInfo;
import com.versawork.http.dataobject.ProcedureInfo;
import com.versawork.http.dataobject.VitalSigns;

public class GenerateCCDA {

	private static Marshaller jaxbMarshaller;

	static {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(ClinicalDocument.class);
			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static byte[] generateCCD(NsResponse nsResponse, String version) throws Exception {

		byte[] ccdBytes = null;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		try {
			ClinicalDocument ccd = new ClinicalDocument();

			ccd = generateHeader(ccd, nsResponse);
			ccd = generateBody(ccd, nsResponse, version);
			jaxbMarshaller.marshal(ccd, byteArrayOutputStream);
			ccdBytes = byteArrayOutputStream.toByteArray();

			byteArrayOutputStream.flush();
			byteArrayOutputStream.close();

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return ccdBytes;
	}

	public static ClinicalDocument generateHeader(ClinicalDocument ccd, NsResponse nsResponse) {

		try {
			ccd.setXmlnsXsi("http://www.w3.org/2001/XMLSchema-instance");
			ccd.setXmlnsSdtc("urn:hl7-org:sdtc");
			ccd.setXmlnsVoc("urn:hl7-org:v3/voc");

			RealmCode realmCode = new RealmCode();
			realmCode.setCode("US");
			ccd.setRealmCode(realmCode);

			TypeId typeId = new TypeId();
			typeId.setExtension("POCD_HD000040");
			typeId.setRoot("2.16.840.1.113883.1.3");
			ccd.setTypeId(typeId);

			TemplateId templateId = new TemplateId();
			// templateId.setRoot("2.16.840.1.113883.10.20.1");
			templateId.setRoot("2.16.840.1.113883.10.20.22.1.1");
			ccd.setTemplateId(templateId);

			Id id = new Id();
			id.setRoot(UUID.randomUUID().toString());
			ccd.setId(id);

			ccd.setTitle("Continuity of Care Document");

			Code headerCode = new Code();
			headerCode.setCode("34133-9");
			headerCode.setDisplayName("Summarization of Episode Note");
			headerCode.setCodeSystem("2.16.840.1.113883.6.1");
			headerCode.setCodeSystemName("LOINC");
			ccd.setCode(headerCode);

			TEffectiveTime effectiveTime = new TEffectiveTime();
			effectiveTime.setValue(DateUtils.getFormatDate(new Date(), "yyyyMMddHHmmssZZ"));
			ccd.setEffectiveTime(effectiveTime);

			ConfidentialityCode confidentialityCode = new ConfidentialityCode();
			confidentialityCode.setCode("N");
			confidentialityCode.setCodeSystem("2.16.840.1.113883.5.25");
			ccd.setConfidentialityCode(confidentialityCode);

			LanguageCode languageCode = new LanguageCode();
			languageCode.setCode("en-US");
			ccd.setLanguageCode(languageCode);

			// -------------------- creating record target
			// ------------------------
			RecordTarget recordTarget = new RecordTarget();
			PatientRole patientRole = new PatientRole();
			Patient patient = new Patient();

			Addr patientAddr = new Addr();
			patientAddr.getStreetAddressLine().add("1357 Amber Dr");
			patientAddr.setCity("Beaverton");
			patientAddr.setState("OR");
			patientAddr.setPostalCode(new Long(97867));
			patientAddr.setCountry("USA");
			patientRole.setAddr(patientAddr);

			Telecom patientTelecom = new Telecom();
			patientTelecom.setValue("tel:+1-810-673-8378");
			patientRole.setTelecom(patientTelecom);

			Id patientRoleId = new Id();
			patientRoleId.setExtension("889978477A");
			patientRoleId.setRoot("2.16.840.1.113883.4.572");
			patientRole.setId(patientRoleId);

			// set address for patient role if it exists in DB

			Name name = new Name();
			name.setGiven(nsResponse.getViewPatientInfo().getFirstName());
			name.setFamily(nsResponse.getViewPatientInfo().getLastName()); // last
			// name
			// from
			// db
			patient.setName(name);

			AdministrativeGenderCode administrativeGenderCode = new AdministrativeGenderCode();
			administrativeGenderCode.setCode(nsResponse.getViewPatientInfo().getSex());
			administrativeGenderCode.setCodeSystem("2.16.840.1.113883.5.1");
			patient.setAdministrativeGenderCode(administrativeGenderCode);

			if (nsResponse.getViewPatientInfo().getBirthDate() != null) {
				BirthTime birthTime = new BirthTime();
				Date dob = DateUtils.getDate(nsResponse.getViewPatientInfo().getBirthDate(), "MM/dd/yyyy");
				birthTime.setValue(new Long(DateUtils.getFormatDate(dob, "yyyyMMdd")));
				patient.setBirthTime(birthTime);
			}

			RaceCode raceCode = new RaceCode();
			raceCode.setCode(nsResponse.getViewPatientInfo().getRaceCode()); // race
			// code
			// from
			// db
			raceCode.setCodeSystem("2.16.840.1.113883.6.238");
			raceCode.setDisplayName(nsResponse.getViewPatientInfo().getRace());
			patient.setRaceCode(raceCode);

			EthnicGroupCode ethnicGroupCode = new EthnicGroupCode();
			ethnicGroupCode.setCode(nsResponse.getViewPatientInfo().getEthnicityCode()); // ethnic
			// code
			// from
			// db
			ethnicGroupCode.setCodeSystem("2.16.840.1.113883.6.238");
			ethnicGroupCode.setDisplayName(nsResponse.getViewPatientInfo().getEthnicity());
			patient.setEthnicGroupCode(ethnicGroupCode);

			LanguageCommunication languageCommunication = new LanguageCommunication();
			LanguageCode patientLanguageCode = new LanguageCode();
			patientLanguageCode.setCode(nsResponse.getViewPatientInfo().getPreferredLang());
			languageCommunication.setLanguageCode(patientLanguageCode);
			patient.setLanguageCommunication(languageCommunication);

			patientRole.setPatient(patient);
			recordTarget.setPatientRole(patientRole);
			ccd.setRecordTarget(recordTarget);

			// ----------------- creating author
			// -----------------------------------

			Author author = new Author();
			AssignedAuthor assignedAuthor = new AssignedAuthor();

			Id assignedAuthorID = new Id();
			assignedAuthorID.setRoot("2.16.840.1.113883.4.6");
			assignedAuthor.setId(assignedAuthorID);

			Addr assignedAuthorAddr = new Addr();
			assignedAuthorAddr.getStreetAddressLine().add("202 Burlington Rd.");
			assignedAuthorAddr.setCity("Bedford");
			assignedAuthorAddr.setState("MA");
			assignedAuthorAddr.setPostalCode(Long.parseLong("01730"));
			assignedAuthorAddr.setCountry("US");
			assignedAuthor.setAddr(assignedAuthorAddr);

			Telecom assignedAuthorTel = new Telecom();
			assignedAuthorTel.setValue("tel:(555)555-1002");
			assignedAuthor.setTelecom(assignedAuthorTel);

			AssignedAuthoringDevice assignedAuthoringDevice = new AssignedAuthoringDevice();
			assignedAuthoringDevice.setManufacturerModelName("Medical Center Interface Device");
			assignedAuthoringDevice.setSoftwareName("Navis Engage");
			assignedAuthor.setAssignedAuthoringDevice(assignedAuthoringDevice);

			RepresentedOrganization representedOrganization = new RepresentedOrganization();
			Id representedOrganizationID = new Id();
			representedOrganizationID.setRoot("2.16.840.1.113883.19.5");
			representedOrganization.setName("Good Health Clinic");
			representedOrganization.getId().add(representedOrganizationID);
			assignedAuthor.setRepresentedOrganization(representedOrganization);

			Time time = new Time();
			time.setValue(DateUtils.getFormatDate(new Date(), "yyyyMMddHHmmssZZ"));
			author.setTime(time);
			author.setAssignedAuthor(assignedAuthor);
			ccd.setAuthor(author);

			// ------------------ creating custodian
			// ------------------------------
			Custodian custodian = new Custodian();
			AssignedCustodian assignedCustodian = new AssignedCustodian();
			RepresentedCustodianOrganization representedCustodianOrganization = new RepresentedCustodianOrganization();

			Id custodianId = new Id();
			custodianId.setRoot("2.16.840.1.113883.19.5");
			representedCustodianOrganization.setId(custodianId);

			representedCustodianOrganization.setName("Cypress Test Deck");

			Addr custodianAddr = new Addr();
			custodianAddr.getStreetAddressLine().add("202 Burlington Rd.");
			custodianAddr.setCity("Bedford");
			custodianAddr.setState("MA");
			custodianAddr.setPostalCode((long) 01730);
			custodianAddr.setCountry("US");
			representedCustodianOrganization.setAddr(custodianAddr);

			Telecom representedCustodianTelecom = new Telecom();
			representedCustodianTelecom.setValue("tel:(555)555-1002");
			representedCustodianOrganization.setTelecom(representedCustodianTelecom);

			assignedCustodian.setRepresentedCustodianOrganization(representedCustodianOrganization);
			custodian.setAssignedCustodian(assignedCustodian);
			ccd.setCustodian(custodian);

			// ------------------ creating
			// authenticator---------------------------
			LegalAuthenticator legalAuthenticator = new LegalAuthenticator();
			Time legalAuthenticatorTime = new Time();
			legalAuthenticatorTime.setValue(DateUtils.getFormatDate(new Date(), "yyyyMMddHHmmssZZ"));
			legalAuthenticator.setTime(legalAuthenticatorTime);

			// legalAuthenticator.setTelecom(legalAuthenticatorTelecom);

			SignatureCode signatureCode = new SignatureCode();
			signatureCode.setCode("S");
			legalAuthenticator.setSignatureCode(signatureCode);

			AssignedEntity legalEntity = new AssignedEntity();
			Id legalEntityID = new Id();
			legalEntityID.setRoot("2.16.840.1.113883.19");
			legalEntity.setId(legalEntityID);
			RepresentedOrganization legalEntityOrganization = new RepresentedOrganization();
			Id legalEntityOrganizationID = new Id();
			legalEntityOrganizationID.setRoot("2.16.840.1.113883.19.5");
			legalEntityOrganization.setName("Good Health Clinic");
			legalEntityOrganization.getId().add(legalEntityOrganizationID);
			legalEntity.setRepresentedOrganization(legalEntityOrganization);

			Addr legalEntityAddr = new Addr();
			legalEntityAddr.getStreetAddressLine().add("202 Burlington Rd.");
			legalEntityAddr.setCity("Bedford");
			legalEntityAddr.setState("MA");
			legalEntityAddr.setPostalCode((long) 01730);
			legalEntityAddr.setCountry("US");
			legalEntity.setAddr(legalEntityAddr);

			Telecom legalEntityTelecom = new Telecom();
			legalEntityTelecom.setValue("tel:(555)555-1002");
			legalEntity.setTelecom(legalEntityTelecom);

			AssignedPerson assignedlegalAuthenticator = new AssignedPerson();
			Name assignedlegalAuthenticatorName = new Name();
			assignedlegalAuthenticatorName.setGiven("Mary");
			assignedlegalAuthenticatorName.setFamily("Grant");
			assignedlegalAuthenticator.setName(assignedlegalAuthenticatorName);
			legalEntity.setAssignedPerson(assignedlegalAuthenticator);

			legalAuthenticator.setAssignedEntity(legalEntity);
			ccd.setLegalAuthenticator(legalAuthenticator);

			// ----------------- creating document of section
			// ----------------------
			DocumentationOf documentationOf = new DocumentationOf();
			ServiceEvent serviceEvent = new ServiceEvent();
			serviceEvent.setClassCode("PCPR");
			serviceEvent.setMoodCode("EVN");

			TEffectiveTime docEffectiveTime = new TEffectiveTime();
			Low low = new Low();
			High high = new High();
			if (nsResponse.getVisitType() == 2 && nsResponse.getViewPatientInfo() != null) {
				if (nsResponse.getViewPatientInfo().getDateAdded() != null) {
					Date admmDate = DateUtils.getDate(nsResponse.getViewPatientInfo().getDateAdded(), "MM/dd/yyyy");
					low.setValue(new Long(DateUtils.getFormatDate(admmDate, "yyyyMMddhhmm")));
				}
				if (nsResponse.getViewPatientInfo().getDateAdded() != null) {
					Date dischargeDate = DateUtils
							.getDate(nsResponse.getViewPatientInfo().getDateAdded(), "MM/dd/yyyy");
					high.setValue(new Long(DateUtils.getFormatDate(dischargeDate, "yyyyMMddhhmm")));
				}
			} else if (nsResponse.getVisitType() == 2) {
				if (nsResponse.getPatientInpatientMetadataInfo() != null) {
					if (nsResponse.getPatientInpatientMetadataInfo().getAdmissionDate() != null) {
						Date admmDate = DateUtils.getDate(nsResponse.getPatientInpatientMetadataInfo()
								.getAdmissionDate(), "MM/dd/yyyy");
						low.setValue(new Long(DateUtils.getFormatDate(admmDate, "yyyyMMddhhmm")));
					}
					if (nsResponse.getPatientInpatientMetadataInfo().getDischargeDate() != null) {
						Date dischargeDate = DateUtils.getDate(nsResponse.getPatientInpatientMetadataInfo()
								.getDischargeDate(), "MM/dd/yyyy");
						high.setValue(new Long(DateUtils.getFormatDate(dischargeDate, "yyyyMMddhhmm")));
					}
				}
			}
			docEffectiveTime.setLow(low);
			docEffectiveTime.setHigh(high);
			serviceEvent.setEffectiveTime(docEffectiveTime);

			List<Performer> performers = new ArrayList<Performer>();
			for (NsDoctorsList doctor : nsResponse.getNsDoctorsList()) {
				Performer performer = new Performer();
				performer.setTypeCode("PRF");

				FunctionCode functionCode = new FunctionCode();
				functionCode.setCode("PCP");
				functionCode.setCodeSystem("2.16.840.1.113883.5.88");
				performer.setFunctionCode(functionCode);

				AssignedEntity assignedEntity = new AssignedEntity();
				Id assignedEntityId = new Id();
				assignedEntityId.setRoot("2.16.840.1.113883.4.6");
				assignedEntity.setId(assignedEntityId);

				Telecom assignedEntityTelecom = new Telecom();
				assignedEntityTelecom.setValue("Tel: 555-555-1122");
				assignedEntity.setTelecom(assignedEntityTelecom);

				Addr entityAddr = new Addr();
				entityAddr.setUse("WP");
				entityAddr.getStreetAddressLine().add(doctor.getAddress1());
				entityAddr.setState(doctor.getState());
				entityAddr.setCity(doctor.getCity());
				entityAddr.setCountry("US");
				if (doctor.getPostalCode() != null) {
					entityAddr.setPostalCode(Long.parseLong(doctor.getPostalCode()));
				}
				assignedEntity.setAddr(entityAddr);

				AssignedPerson assignedDoctor = new AssignedPerson();
				assignedDoctor.setClassCode("PSN");
				Name assignedDoctorName = new Name();
				assignedDoctorName.setGiven(doctor.getFirstName());
				assignedDoctorName.setFamily(doctor.getLastName());
				assignedDoctor.setName(assignedDoctorName);

				RepresentedOrganization assignedPersonOrganization = new RepresentedOrganization();
				assignedPersonOrganization.setName("");
				Id assignedOrganizationID = new Id();
				assignedOrganizationID.setRoot("2.16.840.1.113883.19.5");
				assignedPersonOrganization.setName("Good Health Clinic");
				assignedPersonOrganization.getId().add(representedOrganizationID);

				assignedEntity.setRepresentedOrganization(assignedPersonOrganization);
				assignedEntity.setAssignedPerson(assignedDoctor);
				performer.setAssignedEntity(assignedEntity);
				performers.add(performer);
			}
			serviceEvent.setPerformer(performers);
			documentationOf.setServiceEvent(serviceEvent);
			ccd.setDocumentationOf(documentationOf);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ccd;
	}

	public static ClinicalDocument generateBody(ClinicalDocument ccd, NsResponse nsResponse, String version) {

		StructuredBody stBody = new StructuredBody();
		Component comp = new Component();
		List<Component> componentsList = new ArrayList<Component>();
		componentsList.add(addSocialHistory(nsResponse));
		componentsList.add(addProblems(nsResponse)); // Add Problems
		componentsList.add(addAlerts(nsResponse)); // Add Alerts
		componentsList.add(addVitalSigns(nsResponse)); // Add Vital Signs
		componentsList.add(addProcedures(nsResponse)); // Add Procedures
		componentsList.add(addPlanOfCare(nsResponse)); // Add Plan Of Care
		componentsList.add(addMedications(nsResponse)); // Add Medications
		componentsList.add(addResults(nsResponse)); // Add Results
		if (nsResponse.getVisitType() == 1) {
			componentsList.add(addDischargeInstructions(nsResponse));
			if (nsResponse.getPatientInpatientDiagnosisInfoList() != null
					&& nsResponse.getPatientInpatientDiagnosisInfoList().size() != 0) {
				componentsList.add(addEncounterDiagnosis(nsResponse));
			}
			// Add encounter diagnosis will be in both summary and toc as per
			// TTT Validation
			if (version.equalsIgnoreCase("summary")) {
				componentsList.add(addHospitalisationReason(nsResponse));

			} else if (version.equalsIgnoreCase("toc")) {
				if (nsResponse.getPatientInpatientFunctionalStatusInfoList() != null
						&& nsResponse.getPatientInpatientFunctionalStatusInfoList().size() != 0) {
					componentsList.add(addFunctionalStatus(nsResponse));
				}
				// Add Functional Status
				if (nsResponse.getPatientInpatientImmunalizationInfoList() != null
						&& nsResponse.getPatientInpatientImmunalizationInfoList().size() != 0) {
					componentsList.add(addImmunizations(nsResponse));
				}
				// Add Immunizations

			}
		}
		stBody.setComponent(componentsList);
		comp.setStructuredBody(stBody);
		ccd.setComponent(comp);

		return ccd;
	}

	public static Component addSocialHistory(NsResponse nsResponse) {

		Component socialHistoryComponent = new Component();
		Section socialHistorySection = new Section();

		TemplateId tempId = new TemplateId();
		tempId.setRoot("2.16.840.1.113883.10.20.22.2.17"); // fetch from
		// property file
		socialHistorySection.getTemplateId().add(tempId);

		Code code = new Code();
		code.setCode("29762-2"); // fetch from property file
		code.setCodeSystem("2.16.840.1.113883.6.1");
		code.setDisplayName("Social History");
		socialHistorySection.setCode(code);

		socialHistorySection.setTitle("Social History");

		Text text = new Text();
		socialHistorySection.setText(text);

		addSocialHistorySection(socialHistorySection, nsResponse);

		socialHistoryComponent.setSection(socialHistorySection);
		return socialHistoryComponent;
	}

	public static Component addEncounterDiagnosis(NsResponse nsResponse) {

		Component encounterDiagnosis = new Component();
		Section encounterDiagnosisSection = new Section();
		TemplateId tempId = new TemplateId();
		tempId.setRoot("2.16.840.1.113883.10.20.22.2.22.1"); // fetch from
		// property file
		encounterDiagnosisSection.getTemplateId().add(tempId);
		Code code = new Code();
		code.setCode("46240-8"); // fetch from property file
		code.setCodeSystem("2.16.840.1.113883.6.1");
		encounterDiagnosisSection.setCode(code);
		encounterDiagnosisSection.setTitle("Encounters");
		Text text = new Text();
		/*
		 * Table table = new Table();
		 * 
		 * //Head Of Table Created Thead thead = new Thead(); Tr tr = new Tr();
		 * List<Th> thList =tr.getTh(); thList.add(new
		 * Th("Encounter Diagnosis")); thList.add(new Th("Status"));
		 * thList.add(new Th("Date")); tr.setTh(thList); thead.setTr(tr);
		 * table.setThead(thead);
		 */

		addEncounterDiagnosisSection(encounterDiagnosisSection, nsResponse);

		// text.setTable(table);
		encounterDiagnosisSection.setText(text);
		encounterDiagnosis.setSection(encounterDiagnosisSection);
		return encounterDiagnosis;

	}

	public static Component addProblems(NsResponse nsResponse) {

		Component problems = new Component();
		Section problemsSection = new Section();
		TemplateId tempId = new TemplateId();
		tempId.setRoot("2.16.840.1.113883.10.20.22.2.5.1"); // fetch from
		// property file
		problemsSection.getTemplateId().add(tempId);
		Code code = new Code();
		code.setCode("11450-4"); // fetch from property file
		code.setCodeSystem("2.16.840.1.113883.6.1");
		problemsSection.setCode(code);
		problemsSection.setTitle("Problems");
		Text text = new Text();
		/*
		 * Table table = new Table();
		 * 
		 * //Head Of Table Created Thead thead = new Thead(); Tr tr = new Tr();
		 * List<Th> thList =tr.getTh(); thList.add(new Th("Problem"));
		 * thList.add(new Th("Status")); thList.add(new Th("DateAdded"));
		 * tr.setTh(thList); thead.setTr(tr); table.setThead(thead);
		 */

		addProblemSection(problemsSection, nsResponse);

		// text.setTable(table);
		problemsSection.setText(text);
		problems.setSection(problemsSection);
		return problems;
	}

	public static Component addFunctionalStatus(NsResponse nsResponse) {

		Component functionalStatus = new Component();
		Section functionalStatusSection = new Section();
		TemplateId tempId = new TemplateId();
		tempId.setRoot("2.16.840.1.113883.10.20.22.2.14"); // fetch from
		// property file
		functionalStatusSection.getTemplateId().add(tempId);

		Code code = new Code();
		code.setCode("47420-5"); // fetch from property file
		code.setCodeSystem("2.16.840.1.113883.6.1");
		functionalStatusSection.setCode(code);
		functionalStatusSection.setTitle("Functional Status");

		Text text = new Text();
		/*
		 * Table table = new Table();
		 * 
		 * //Head Of Table Created Thead thead = new Thead(); Tr tr = new Tr();
		 * List<Th> thList =tr.getTh(); thList.add(new
		 * Th("Functional Condition")); thList.add(new Th("Effective Dates"));
		 * thList.add(new Th("Status")); tr.setTh(thList); thead.setTr(tr);
		 * table.setThead(thead);
		 */

		addFunctionalStatusSection(functionalStatusSection, nsResponse);

		// text.setTable(table);
		functionalStatusSection.setText(text);

		functionalStatus.setSection(functionalStatusSection);
		return functionalStatus;
	}

	public static Component addAlerts(NsResponse nsResponse) {

		Component alert = new Component();
		Section alertsSection = new Section();
		TemplateId tempId = new TemplateId();
		tempId.setRoot("2.16.840.1.113883.10.20.22.2.6.1"); // fetch from
		// property file
		alertsSection.getTemplateId().add(tempId);
		Code code = new Code();
		code.setCode("48765-2"); // fetch from property file
		code.setCodeSystem("2.16.840.1.113883.6.1");
		alertsSection.setCode(code);
		alertsSection.setTitle("Allergies, Adverse Reactions, Alerts");
		Text text = new Text();
		/*
		 * Table table = new Table();
		 * 
		 * //Head Of Table Created Thead thead = new Thead(); Tr tr = new Tr();
		 * List<Th> thList =tr.getTh(); thList.add(new Th("Substance"));
		 * thList.add(new Th("Reaction")); thList.add(new Th("Status"));
		 * tr.setTh(thList); thead.setTr(tr); table.setThead(thead);
		 */

		addAlertsSection(alertsSection, nsResponse);

		// text.setTable(table);
		alertsSection.setText(text);
		alert.setSection(alertsSection);
		return alert;
	}

	public static Component addVitalSigns(NsResponse nsResponse) {

		Component vitalSigns = new Component();

		Section vitalSignsSection = new Section();
		TemplateId tempId = new TemplateId();
		tempId.setRoot("2.16.840.1.113883.10.20.22.2.4.1"); // fetch from
		// property file
		vitalSignsSection.getTemplateId().add(tempId);

		Code code = new Code();
		code.setCode("8716-3"); // fetch from property file
		code.setCodeSystem("2.16.840.1.113883.6.1");
		vitalSignsSection.setCode(code);

		vitalSignsSection.setTitle("Vital Signs");

		Text text = new Text();
		/*
		 * Table table = new Table();
		 * 
		 * //Head Of Table Created Thead thead = new Thead(); Tr tr = new Tr();
		 * List<Th> thList =tr.getTh(); thList.add(new Th("Vital Sign"));
		 * thList.add(new Th("Value")); thList.add(new Th("Status"));
		 * tr.setTh(thList); thead.setTr(tr); table.setThead(thead);
		 */
		addVitalSignsSection(vitalSignsSection, nsResponse);

		// text.setTable(table);
		vitalSignsSection.setText(text);

		vitalSigns.setSection(vitalSignsSection);

		return vitalSigns;
	}

	public static Component addProcedures(NsResponse nsResponse) {

		Component procedures = new Component();

		Section proceduresSection = new Section();
		TemplateId tempId = new TemplateId();
		tempId.setRoot("2.16.840.1.113883.10.20.22.2.7.1"); // fetch from
		// property file
		proceduresSection.getTemplateId().add(tempId);

		Code code = new Code();
		code.setCode("47519-4"); // fetch from property file
		code.setCodeSystem("2.16.840.1.113883.6.1");
		proceduresSection.setCode(code);

		proceduresSection.setTitle("Procedures");
		Text text = new Text();
		/*
		 * Table table = new Table();
		 * 
		 * //Head Of Table Created Thead thead = new Thead(); Tr tr = new Tr();
		 * List<Th> thList =tr.getTh(); thList.add(new Th("Procedure"));
		 * thList.add(new Th("Date")); tr.setTh(thList); thead.setTr(tr);
		 * table.setThead(thead);
		 */

		addProceduresSection(proceduresSection, nsResponse);

		// text.setTable(table);
		proceduresSection.setText(text);

		procedures.setSection(proceduresSection);
		return procedures;
	}

	public static Component addPlanOfCare(NsResponse nsResponse) {

		Component planOfCare = new Component();
		Section section = new Section();

		TemplateId tempId = new TemplateId();
		tempId.setRoot("2.16.840.1.113883.10.20.22.2.10");
		section.getTemplateId().add(tempId);

		Code code = new Code();
		code.setCode("18776-5");
		code.setCodeSystem("2.16.840.1.113883.6.1");
		section.setCode(code);

		section.setTitle("Plan of Care");

		try {
			Text text = new Text();
			Table table = new Table();

			// Head Of Table Created
			Thead thead = new Thead();
			Tr trHead = new Tr();
			List<Th> thList = trHead.getTh();
			thList.add(new Th("Goal"));
			thList.add(new Th("Instructions"));
			thList.add(new Th("Date"));
			trHead.setTh(thList);
			thead.setTr(trHead);
			table.setThead(thead);

			/*
			 * Tbody tbody = new Tbody(); Tr bodyRow = new Tr(); List<Td> tdList
			 * = new ArrayList<Td>(); tdList.add(new
			 * Td(nsResponse.getPatientInpatientMetadataInfo
			 * ().getHospitalizationReason())); bodyRow.setTd(tdList);
			 * tbody.getTr().add(bodyRow); table.setTbody(tbody);
			 */

			Tbody tbody = new Tbody();
			if (nsResponse.getCarePlanInfoList() != null && nsResponse.getCarePlanInfoList().size() > 0) {
				for (CarePlanInfo carePlanInfo : nsResponse.getCarePlanInfoList()) {
					Tr bodyRow = new Tr();
					Td goal = new Td(carePlanInfo.getGoal());
					Td instructions = new Td(carePlanInfo.getInstructions());
					List<Td> tdList = new ArrayList<Td>();
					if (carePlanInfo.getDateAdded() != null) {
						Td date = new Td(DateUtils.getFormatDate(
								DateUtils.getDate(carePlanInfo.getDateAdded(), "MM/dd/yyyy"), "MM/dd/yyyy"));
						tdList.add(date);
					}

					tdList.add(goal);
					tdList.add(instructions);

					bodyRow.setTd(tdList);
					tbody.getTr().add(bodyRow);
				}
			} else {
				Tr bodyRow = new Tr();
				Td td = new Td("No Care Plan Info");
				List<Td> tdList = new ArrayList<Td>();
				tdList.add(td);
				bodyRow.setTd(tdList);
				tbody.getTr().add(bodyRow);
			}

			table.setTbody(tbody);
			text.setTable(table);
			section.setText(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (CarePlanInfo carePlanInfo : nsResponse.getCarePlanInfoList()) {
			Entry entry = new Entry();
			entry.setTypeCode("DRIV");

			Observation observation = new Observation();
			observation.setClassCode("OBS");
			observation.setMoodCode("RQO");

			List<TemplateId> templateIds = new ArrayList<TemplateId>();
			TemplateId obstemplateId = new TemplateId();
			obstemplateId.setRoot("2.16.840.1.113883.10.20.1.25"); // fetch from
			// property
			// file
			templateIds.add(obstemplateId);
			observation.setTemplateId(templateIds);

			Id id = new Id();
			id.setRoot(UUID.randomUUID().toString());
			observation.setId(id);

			StatusCode statusCode = new StatusCode();
			statusCode.setCode("new"); // fetch from property file
			observation.setStatusCode(statusCode);

			Code obsCode = new Code();
			obsCode.setCode("289169006"); // fetch from property file
			obsCode.setCodeSystem("2.16.840.1.113883.6.96"); // fetch from
			// property file
			obsCode.setDisplayName(carePlanInfo.getGoal());
			observation.setCode(obsCode);

			// Date admmDate = DateUtils.getDate(carePlanInfo.getDateAdded(),
			// "dd-MM-yyyy");
			// low.setValue(new
			// Long(DateUtils.getFormatDate(carePlanInfo.getDateAdded(),
			// "yyyyMMdd")));

			try {
				EffectiveTime effectiveTime = new EffectiveTime();
				Center center = new Center();
				if (carePlanInfo.getDateAdded() != null) {
					center.setValue(new Long(DateUtils.getFormatDate(
							DateUtils.getDate(carePlanInfo.getDateAdded(), "MM/dd/yyyy"), "yyyyMMdd")));
					effectiveTime.setCenter(center); // this will come from db
					observation.setEffectiveTime(effectiveTime);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			entry.setObservation(observation);

			section.getEntry().add(entry);
		}

		planOfCare.setSection(section);
		return planOfCare;
	}

	public static Component addImmunizations(NsResponse nsResponse) {

		Component immunizations = new Component();
		Section section = new Section();

		TemplateId tempId = new TemplateId();
		tempId.setRoot("2.16.840.1.113883.10.20.22.2.2.1");
		section.getTemplateId().add(tempId);

		Code code = new Code();
		code.setCode("11369-6");
		code.setCodeSystem("2.16.840.1.113883.6.1");
		section.setCode(code);

		section.setTitle("Immunizations");

		Text text = new Text();
		/*
		 * Table table = new Table();
		 * 
		 * //Head Of Table Created Thead thead = new Thead(); Tr tr = new Tr();
		 * List<Th> thList =tr.getTh(); thList.add(new Th("Vaccine"));
		 * thList.add(new Th("Date")); thList.add(new Th("Status"));
		 * tr.setTh(thList); thead.setTr(tr); table.setThead(thead);
		 */

		addImmunizationsSection(section, nsResponse);

		// text.setTable(table);
		section.setText(text);

		immunizations.setSection(section);

		return immunizations;
	}

	public static Component addMedications(NsResponse nsResponse) {

		Component medications = new Component();

		Section medSection = new Section();
		TemplateId templateId = new TemplateId();
		templateId.setRoot("2.16.840.1.113883.10.20.22.2.1.1");
		medSection.getTemplateId().add(templateId);

		Code medCode = new Code();
		medCode.setCode("10160-0");
		medCode.setCodeSystem("2.16.840.1.113883.6.1");
		medSection.setCode(medCode);

		medSection.setTitle("Medications");

		Text text = new Text();
		/*
		 * Table table = new Table();
		 * 
		 * //Head Of Table Created Thead thead = new Thead(); Tr tr = new Tr();
		 * List<Th> thList =tr.getTh(); thList.add(new Th("Medication"));
		 * thList.add(new Th("Instructions")); thList.add(new Th("Date"));
		 * thList.add(new Th("Status")); tr.setTh(thList); thead.setTr(tr);
		 * table.setThead(thead);
		 */

		addMedicationSection(medSection, nsResponse);

		// text.setTable(table);
		medSection.setText(text);

		medications.setSection(medSection);
		return medications;
	}

	public static Component addResults(NsResponse nsResponse) {

		Component results = new Component();

		Section resultsSection = new Section();
		TemplateId templateId = new TemplateId();
		templateId.setRoot("2.16.840.1.113883.10.20.22.2.3.1");
		resultsSection.getTemplateId().add(templateId);

		Code resultCode = new Code();
		resultCode.setCode("30954-2");
		resultCode.setCodeSystem("2.16.840.1.113883.6.1");
		resultsSection.setCode(resultCode);

		Text text = new Text();
		/*
		 * Table table = new Table();
		 * 
		 * //Head Of Table Created Thead thead = new Thead(); Tr tr = new Tr();
		 * List<Th> thList =tr.getTh(); thList.add(new Th("Test Name"));
		 * thList.add(new Th("Test Value")); thList.add(new Th("Status"));
		 * tr.setTh(thList); thead.setTr(tr); table.setThead(thead);
		 */

		addResultsSection(resultsSection, nsResponse);

		// text.setTable(table);
		resultsSection.setTitle("Results");
		resultsSection.setText(text);
		results.setSection(resultsSection);
		return results;
	}

	public static Component addHospitalisationReason(NsResponse nsResponse) {

		Component hospitalisationReason = new Component();

		Section hospitalisationReasonSection = new Section();
		TemplateId templateId = new TemplateId();
		templateId.setRoot("2.16.840.1.113883.10.20.22.2.13");
		hospitalisationReasonSection.getTemplateId().add(templateId);

		Code resultCode = new Code();
		resultCode.setCode("46239-0");
		resultCode.setCodeSystem("2.16.840.1.113883.6.1");
		hospitalisationReasonSection.setCode(resultCode);

		Table table = new Table();
		Text text = new Text();

		Thead thead = new Thead();
		Tr tr = new Tr();
		List<Th> thList = tr.getTh();
		thList.add(new Th("Reason for Hospitalisation/Chief Complaint"));
		tr.setTh(thList);
		thead.setTr(tr);
		table.setThead(thead);

		Tbody tbody = new Tbody();
		Tr bodyRow = new Tr();
		List<Td> tdList = new ArrayList<Td>();
		String hospitalizationReason = "";
		if (nsResponse.getPatientInpatientMetadataInfo() != null) {
			hospitalizationReason = nsResponse.getPatientInpatientMetadataInfo().getHospitalizationReason() != null ? nsResponse
					.getPatientInpatientMetadataInfo().getHospitalizationReason() : "N/A";
		}
		tdList.add(new Td(hospitalizationReason));
		bodyRow.setTd(tdList);
		tbody.getTr().add(bodyRow);
		table.setTbody(tbody);

		text.setTable(table);
		hospitalisationReasonSection.setTitle("Hospitalisation Reason");
		hospitalisationReasonSection.setText(text);

		hospitalisationReason.setSection(hospitalisationReasonSection);
		return hospitalisationReason;
	}

	public static Component addDischargeInstructions(NsResponse nsResponse) {

		Component dischargeInstructions = new Component();

		Section dischargeInstructionsSection = new Section();
		TemplateId templateId = new TemplateId();
		templateId.setRoot("2.16.840.1.113883.10.20.22.2.41");
		dischargeInstructionsSection.getTemplateId().add(templateId);

		Code resultCode = new Code();
		resultCode.setCode("8653-8");
		resultCode.setCodeSystem("2.16.840.1.113883.6.1");
		dischargeInstructionsSection.setCode(resultCode);

		Text text = new Text();
		Content content = new Content();
		String instrusctions = "";
		if (nsResponse.getPatientInpatientMetadataInfo() != null) {
			instrusctions = nsResponse.getPatientInpatientMetadataInfo().getDischargeInstruction() != null ? nsResponse
					.getPatientInpatientMetadataInfo().getDischargeInstruction() : "N/A";
		}
		instrusctions = instrusctions.replace("&#xD;", "");
		content.setValue(instrusctions);
		text.setContent(content);
		dischargeInstructionsSection.setTitle("Discharge Instructions");
		dischargeInstructionsSection.setText(text);

		dischargeInstructions.setSection(dischargeInstructionsSection);
		return dischargeInstructions;
	}

	public static Section addSocialHistorySection(Section section, NsResponse nsResponse) {

		try {
			Entry entry = new Entry();
			entry.setTypeCode("DRIV");

			Observation observation = new Observation();
			observation.setClassCode("OBS");
			observation.setMoodCode("EVN");

			TemplateId templateId = new TemplateId();
			templateId.setRoot("2.16.840.1.113883.10.20.22.4.78");
			List<TemplateId> templateIds = new ArrayList<TemplateId>();
			templateIds.add(templateId);
			observation.setTemplateId(templateIds);

			Id id = new Id();
			id.setRoot(UUID.randomUUID().toString());
			observation.setId(id);

			Code code = new Code();
			code.setCode("ASSERTION");
			code.setCodeSystem("2.16.840.1.113883.5.4");
			observation.setCode(code);

			StatusCode statusCode = new StatusCode();
			statusCode.setCode("completed");
			observation.setStatusCode(statusCode);

			@SuppressWarnings("deprecation")
			Date lowDate = new Date(nsResponse.getViewPatientInfo().getDateAdded());
			// String lowDate =
			// nsResponse.getViewPatientInfo().getDateAdded().replaceAll("/","");
			EffectiveTime effectiveTime = new EffectiveTime();
			Low low = new Low();
			low.setValue(new Long(DateUtils.getFormatDate(lowDate, "yyyyMMdd")));
			effectiveTime.setLow(low);
			observation.setEffectiveTime(effectiveTime);

			Value value = new Value();
			value.setXsiType("CD");
			value.setCode(nsResponse.getViewPatientInfo().getSmokingStatusCode());
			value.setDisplayName(nsResponse.getViewPatientInfo().getSmokingStatus());
			value.setCodeSystem("2.16.840.1.113883.6.96");
			observation.setValue(value);
			entry.setObservation(observation);
			section.getEntry().add(entry);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return section;
	}

	public static Section addEncounterDiagnosisSection(Section section, NsResponse nsResponse) {

		try {
			for (PatientInpatientDiagnosisInfo diagnosisInfo : nsResponse.getPatientInpatientDiagnosisInfoList()) {
				Entry entry = new Entry();
				entry.setTypeCode("DRIV");

				Encounter encounter = new Encounter();
				encounter.setClassCode("ENC");
				encounter.setMoodCode("EVN");

				TemplateId templateId = new TemplateId();
				templateId.setRoot("2.16.840.1.113883.10.20.22.4.49");
				encounter.setTemplateId(templateId);

				Id id = new Id();
				id.setRoot(UUID.randomUUID().toString());
				encounter.setId(id);

				Code code = new Code();
				code.setCode("GENRL"); // fetch from property file
				code.setDisplayName("General");
				code.setCodeSystemName("2.16.840.1.113883.5.4");
				code.setOriginalText("Checkup Examination"); // fetch from db,
				// hard coded
				// now done only
				// for
				// certification
				encounter.setCode(code);

				EffectiveTime effectiveTime = new EffectiveTime();
				Date diagnosisDate = DateUtils.getDate(diagnosisInfo.getDateAdded(), "dd/MM/yyyy");
				effectiveTime.setValue(new Long(DateUtils.getFormatDate(diagnosisDate, "yyyyMMdd")));
				encounter.setEffectiveTime(effectiveTime);

				Participant participant = new Participant();
				participant.setTypeCode("LOC");

				TemplateId participantTemplate = new TemplateId();
				participantTemplate.setRoot("2.16.840.1.113883.10.20.1.45");
				participant.setTemplateId(participantTemplate);

				ParticipantRole participantRole = new ParticipantRole();
				participantRole.setClassCode("SDLOC");
				Id participantRoleid = new Id();
				participantRoleid.setRoot("2.16.840.1.113883.19.5");
				participantRole.setId(participantRoleid);

				PlayingEntity playingEntity = new PlayingEntity();
				playingEntity.setClassCode("PLC");

				playingEntity.setName("Navis Health");

				participantRole.setPlayingEntity(playingEntity);
				participant.setParticipantRole(participantRole);
				encounter.setParticipant(participant);

				// Adding Entry Relationship containing encounter diagnosis
				// Start - Diagnosis Entry Relationship
				EntryRelationship entryRelationship = new EntryRelationship();
				entryRelationship.setTypeCode("RSON");

				Observation observation = new Observation();
				observation.setClassCode("OBS");
				observation.setMoodCode("EVN");
				TemplateId obsTemplateId = new TemplateId();
				obsTemplateId.setRoot("2.16.840.1.113883.10.20.22.4.19");
				observation.getTemplateId().add(obsTemplateId);
				Id obsId = new Id();
				obsId.setRoot(UUID.randomUUID().toString());
				obsId.setExtension("45665");
				observation.setId(obsId);
				Code obsCode = new Code();
				obsCode.setCode("404684003");
				obsCode.setDisplayName("Finding");
				obsCode.setCodeSystem("2.16.840.1.113883.6.96");
				obsCode.setCodeSystemName("SNOMED CT");
				observation.setCode(obsCode);
				StatusCode statusCode = new StatusCode();
				statusCode.setCode(diagnosisInfo.getStatus());
				observation.setStatusCode(statusCode);
				observation.setEffectiveTime(effectiveTime);
				Value value = new Value();
				value.setXsiType("CD");
				value.setCode(diagnosisInfo.getDiagnosisCode());
				value.setDisplayName(diagnosisInfo.getDiagnosisDesc());
				value.setCodeSystem("2.16.840.1.113883.6.96");
				observation.setValue(value);
				entryRelationship.setObservation(observation);
				// End - Diagnosis Entry Relationship

				encounter.setEntryRelationship(entryRelationship);

				entry.setEncounter(encounter);
				section.getEntry().add(entry);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return section;
	}

	public static Section addProblemSection(Section section, NsResponse nsResponse) {

		try {
			for (PatientProblemInfo patientProblemInfo : nsResponse.getPatientProblemInfoList()) {
				Entry entry = new Entry();
				Act act = new Act();
				act.setClassCode("ACT"); // fetch from property file
				act.setMoodCode("EVN"); // fetch from property file
				TemplateId templateId = new TemplateId();
				templateId.setRoot("2.16.840.1.113883.10.20.22.4.3"); // fetch
				// from
				// property
				// file
				act.setTemplateId(templateId);

				Code code = new Code();
				code.setNullFlavor("NA"); // fetch from property file
				act.setCode(code);

				StatusCode actStatusCode = new StatusCode();
				actStatusCode.setCode(patientProblemInfo.getStatus());
				act.setStatusCode(actStatusCode);

				/* SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); */
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
				Date convertedCurrentDate;

				Observation observation = new Observation();
				observation.setClassCode("OBS"); // fetch from property file
				observation.setMoodCode("EVN"); // fetch from property file
				if (patientProblemInfo.getDateAdded() != null) {
					convertedCurrentDate = sdf.parse(patientProblemInfo.getDateAdded());

					EffectiveTime actEffectiveTime = new EffectiveTime();
					Low actEffectiveTimeLow = new Low();
					actEffectiveTimeLow.setValue(new Long(DateUtils.getFormatDate(convertedCurrentDate, "yyyyMMdd")));
					actEffectiveTime.setLow(actEffectiveTimeLow);
					act.setEffectiveTime(actEffectiveTime);

					EffectiveTime effectiveTime = new EffectiveTime();
					Low low = new Low();

					low.setValue(new Long(DateUtils.getFormatDate(convertedCurrentDate, "yyyyMMdd")));
					effectiveTime.setLow(low);
					observation.setEffectiveTime(effectiveTime);
				}
				Id id = new Id();
				id.setRoot(UUID.randomUUID().toString());
				act.setId(id);

				EntryRelationship entryRelationship = new EntryRelationship();
				entryRelationship.setTypeCode("SUBJ"); // fetch from property
				// file

				Id obsId = new Id();
				obsId.setRoot(UUID.randomUUID().toString());
				observation.setId(obsId);

				Code obsCode = new Code();
				obsCode.setCode("ASSERTION"); // fetch from property file
				obsCode.setCodeSystem("2.16.840.1.113883.5.4"); // fetch from
				// property file
				observation.setCode(obsCode);

				List<TemplateId> templateIds = new ArrayList<TemplateId>();
				TemplateId obstemplateId = new TemplateId();
				obstemplateId.setRoot("2.16.840.1.113883.10.20.22.4.4"); // fetch
				// from
				// property
				// file
				templateIds.add(obstemplateId);
				observation.setTemplateId(templateIds);

				StatusCode observationStatusCode = new StatusCode();
				observationStatusCode.setCode("completed"); // fetch from
				// property file
				observation.setStatusCode(observationStatusCode);

				Value value = new Value();
				value.setXsiType("CD");
				value.setCode(patientProblemInfo.getProblemCode());
				value.setCodeSystem("2.16.840.1.113883.6.96"); // fetch from
				// property file
				value.setDisplayName(patientProblemInfo.getProblemName());
				observation.setValue(value);

				List<EntryRelationship> pblmstatusEntryRelationshipList = new ArrayList<EntryRelationship>();
				EntryRelationship pblmstatusEntryRelationship = new EntryRelationship();
				pblmstatusEntryRelationship.setTypeCode("REFR");
				Observation pblmstatusObservation = new Observation();
				pblmstatusObservation.setClassCode("OBS");
				pblmstatusObservation.setMoodCode("EVN");

				List<TemplateId> pblmStatusTemplateIds = new ArrayList<TemplateId>();
				TemplateId pblmStatusTemplateId = new TemplateId();
				pblmStatusTemplateId.setRoot("2.16.840.1.113883.10.20.1.50");
				pblmStatusTemplateIds.add(pblmStatusTemplateId);
				pblmstatusObservation.setTemplateId(pblmStatusTemplateIds);

				Code pblmCode = new Code();
				pblmCode.setCode("33999-4");
				pblmCode.setCodeSystem("2.16.840.1.113883.6.1");
				pblmCode.setDisplayName("Status");
				pblmstatusObservation.setCode(pblmCode);

				StatusCode pblmStatusCode = new StatusCode();
				pblmStatusCode.setCode("completed");
				pblmstatusObservation.setStatusCode(pblmStatusCode);

				Value pblmStatusValue = new Value();
				pblmStatusValue.setXsiType("CD");
				pblmStatusValue.setCodeSystem("2.16.840.1.113883.6.96");
				pblmStatusValue.setDisplayName(patientProblemInfo.getStatus());
				pblmStatusValue.setCode(patientProblemInfo.getStatusCode()); // get
				// problem
				// status
				// code
				// from
				// db
				pblmstatusObservation.setValue(pblmStatusValue);

				pblmstatusEntryRelationship.setObservation(pblmstatusObservation);
				pblmstatusEntryRelationshipList.add(pblmstatusEntryRelationship);
				observation.setEntryRelationship(pblmstatusEntryRelationshipList);

				entryRelationship.setObservation(observation);
				act.setEntryRelationship(entryRelationship);
				entry.setAct(act);
				section.getEntry().add(entry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return section;
	}

	public static Section addFunctionalStatusSection(Section section, NsResponse nsResponse) {

		try {
			for (PatientInpatientFunctionalStatusInfo patientInpatientFunctionalStatusInfo : nsResponse
					.getPatientInpatientFunctionalStatusInfoList()) {
				Entry entry = new Entry();
				Act act = new Act();
				act.setClassCode("ACT"); // fetch from property file
				act.setMoodCode("EVN"); // fetch from property file
				TemplateId templateId = new TemplateId();
				templateId.setRoot("2.16.840.1.113883.10.20.1.27"); // fetch
				// from
				// property
				// file
				act.setTemplateId(templateId);

				Id id = new Id();
				id.setRoot(UUID.randomUUID().toString());
				act.setId(id);

				Code code = new Code();
				code.setNullFlavor("NA"); // fetch from property file
				act.setCode(code);

				EntryRelationship entryRelationship = new EntryRelationship();
				entryRelationship.setTypeCode("SUBJ"); // fetch from property
				// file
				Observation observation = new Observation();
				observation.setClassCode("OBS"); // fetch from property file
				observation.setMoodCode("EVN"); // fetch from property file

				Id obsId = new Id();
				obsId.setRoot(UUID.randomUUID().toString());
				observation.setId(obsId);

				Code obsCode = new Code();
				obsCode.setCode("ASSERTION"); // fetch from property file
				obsCode.setCodeSystem("2.16.840.1.113883.5.4"); // fetch from
				// property file
				observation.setCode(obsCode);

				List<TemplateId> templateIds = new ArrayList<TemplateId>();
				TemplateId obstemplateId = new TemplateId();
				obstemplateId.setRoot("2.16.840.1.113883.10.20.1.28"); // fetch
				// from
				// property
				// file
				templateIds.add(obstemplateId);
				observation.setTemplateId(templateIds);

				StatusCode statusCode = new StatusCode();
				statusCode.setCode("completed"); // fetch from property file
				observation.setStatusCode(statusCode);

				EffectiveTime effectiveTime = new EffectiveTime();
				Low low = new Low();
				// SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				// Date convertedCurrentDate;
				Date diagnosisDate = DateUtils.getDate(patientInpatientFunctionalStatusInfo.getDateAdded(),
						"dd/MM/yyyy");
				// convertedCurrentDate =
				// sdf.parse(patientInpatientFunctionalStatusInfo.getDateAdded());
				low.setValue(new Long(DateUtils.getFormatDate(diagnosisDate, "yyyyMMdd")));
				effectiveTime.setLow(low);
				observation.setEffectiveTime(effectiveTime);

				Value value = new Value();
				value.setXsiType("CD");
				value.setCode(patientInpatientFunctionalStatusInfo.getFunctionalStatusCode());
				value.setCodeSystem("2.16.840.1.113883.6.96"); // fetch from
				// property file
				value.setDisplayName(patientInpatientFunctionalStatusInfo.getFunctionalStatusDesc());
				observation.setValue(value);

				List<EntryRelationship> pblmstatusEntryRelationshipList = new ArrayList<EntryRelationship>();
				EntryRelationship pblmstatusEntryRelationship = new EntryRelationship();
				pblmstatusEntryRelationship.setTypeCode("REFR");
				Observation pblmstatusObservation = new Observation();
				pblmstatusObservation.setClassCode("OBS");
				pblmstatusObservation.setMoodCode("EVN");

				List<TemplateId> pblmStatusTemplateIds = new ArrayList<TemplateId>();
				TemplateId pblmStatusTemplateId = new TemplateId();
				pblmStatusTemplateId.setRoot("2.16.840.1.113883.10.20.1.44");
				pblmStatusTemplateIds.add(pblmStatusTemplateId);
				pblmstatusObservation.setTemplateId(pblmStatusTemplateIds);

				Code pblmCode = new Code();
				pblmCode.setCode("33999-4");
				pblmCode.setCodeSystem("2.16.840.1.113883.6.1");
				pblmCode.setDisplayName("Status");
				pblmstatusObservation.setCode(pblmCode);

				StatusCode pblmStatusCode = new StatusCode();
				pblmStatusCode.setCode("completed");
				pblmstatusObservation.setStatusCode(pblmStatusCode);

				Value pblmStatusValue = new Value();
				pblmStatusValue.setXsiType("CE");
				pblmStatusValue.setCodeSystem("2.16.840.1.113883.6.96");
				pblmStatusValue.setDisplayName(patientInpatientFunctionalStatusInfo.getStatus());
				pblmStatusValue.setCode(patientInpatientFunctionalStatusInfo.getStatusCode()); // get
				// problem
				// status
				// code
				// from
				// db
				pblmstatusObservation.setValue(pblmStatusValue);

				pblmstatusEntryRelationship.setObservation(pblmstatusObservation);
				pblmstatusEntryRelationshipList.add(pblmstatusEntryRelationship);
				observation.setEntryRelationship(pblmstatusEntryRelationshipList);

				entryRelationship.setObservation(observation);
				act.setEntryRelationship(entryRelationship);
				entry.setAct(act);
				section.getEntry().add(entry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return section;
	}

	@SuppressWarnings("deprecation")
	public static Section addAlertsSection(Section section, NsResponse nsResponse) {

		try {
			for (AllergiesInfo allergiesInfo : nsResponse.getAllergiesInfoList()) {
				Entry entry = new Entry();
				entry.setTypeCode("DRIV");
				Act act = new Act();
				act.setClassCode("ACT"); // fetch from property file
				act.setMoodCode("EVN"); // fetch from property file
				TemplateId templateId = new TemplateId();
				templateId.setRoot("2.16.840.1.113883.10.20.22.4.30"); // fetch
				// from
				// property
				// file
				act.setTemplateId(templateId);

				Id id = new Id();
				id.setRoot(UUID.randomUUID().toString());
				act.setId(id);

				StatusCode actStatusCode = new StatusCode();
				actStatusCode.setCode(allergiesInfo.getStatus());
				act.setStatusCode(actStatusCode);

				// String lowDate = allergiesInfo.getDateAdded();
				// lowDate = lowDate.replace('-', '/');
				EffectiveTime actEffectiveTime = new EffectiveTime();
				Low actEffectiveTimeLow = new Low();
				String lowEffectiveTime = allergiesInfo.getDateAdded();
				lowEffectiveTime = lowEffectiveTime.replace('-', '/');
				actEffectiveTimeLow.setValue(new Long(DateUtils.getFormatDate(new Date(lowEffectiveTime), "yyyyMMdd")));
				actEffectiveTime.setLow(actEffectiveTimeLow);
				act.setEffectiveTime(actEffectiveTime);

				Code code = new Code();
				code.setCode("48765-2");
				code.setCodeSystem("2.16.840.1.113883.6.1");
				// code.setNullFlavor("NA"); //fetch from property file
				act.setCode(code);

				EntryRelationship entryRelationship = new EntryRelationship();
				entryRelationship.setTypeCode("SUBJ"); // fetch from property
				// file
				Observation observation = new Observation();
				observation.setClassCode("OBS"); // fetch from property file
				observation.setMoodCode("EVN"); // fetch from property file

				Id obsId = new Id();
				obsId.setRoot(UUID.randomUUID().toString());
				observation.setId(obsId);

				String lowDate = allergiesInfo.getDateAdded();
				lowDate = lowDate.replace('-', '/');
				EffectiveTime effectiveTime = new EffectiveTime();
				Low low = new Low();
				low.setValue(new Long(DateUtils.getFormatDate(new Date(lowDate), "yyyyMMdd")));
				effectiveTime.setLow(low);
				observation.setEffectiveTime(effectiveTime);

				Code obsCode = new Code();
				obsCode.setCode("ASSERTION"); // fetch from property file
				obsCode.setCodeSystem("2.16.840.1.113883.5.4"); // fetch from
				// property file
				observation.setCode(obsCode);

				List<TemplateId> templateIds = new ArrayList<TemplateId>();
				TemplateId obstemplateId = new TemplateId();
				obstemplateId.setRoot("2.16.840.1.113883.10.20.22.4.7"); // fetch
				// from
				// property
				// file
				templateIds.add(obstemplateId);
				observation.setTemplateId(templateIds);

				StatusCode statusCode = new StatusCode();
				statusCode.setCode("completed"); // fetch from property file
				observation.setStatusCode(statusCode);

				Value value = new Value();
				value.setXsiType("CD");
				value.setCode("419511003");
				value.setCodeSystem("2.16.840.1.113883.6.96"); // fetch from
				// property file
				value.setDisplayName("Adverse reaction");
				observation.setValue(value);

				Participant participant = new Participant();
				ParticipantRole participantRole = new ParticipantRole();
				PlayingEntity playingEntity = new PlayingEntity();
				Code partCode = new Code();
				if (allergiesInfo.getAllergenCode() == null || allergiesInfo.getAllergenCode().trim().length() == 0) {
					partCode.setCode(VersaWorkConstant.NOT_APPLICABLE);
				} else {
					partCode.setCode(allergiesInfo.getAllergenCode()); // it
																		// will
				}
				// come from
				// db
				partCode.setCodeSystem("2.16.840.1.113883.6.88"); // fetch from
				// property
				// file
				if (allergiesInfo.getAllergen() == null || allergiesInfo.getAllergen().trim().length() == 0) {
					partCode.setDisplayName(VersaWorkConstant.NOT_APPLICABLE);
				} else {
					partCode.setDisplayName(allergiesInfo.getAllergen());
				}
				playingEntity.setCode(partCode);
				playingEntity.setClassCode("MMAT");
				participantRole.setClassCode("MANU");
				participantRole.setPlayingEntity(playingEntity);
				participant.setParticipantRole(participantRole);
				participant.setTypeCode("CSM");
				observation.setParticipant(participant);

				List<EntryRelationship> pblmstatusEntryRelationshipList = new ArrayList<EntryRelationship>();
				EntryRelationship rxnEntryRelationship = new EntryRelationship();
				rxnEntryRelationship.setTypeCode("MFST");
				rxnEntryRelationship.setInversionInd(true);
				Observation rxnObservation = new Observation();
				rxnObservation.setClassCode("OBS");
				rxnObservation.setMoodCode("EVN");

				List<TemplateId> pblmStatusTemplateIds = new ArrayList<TemplateId>();
				TemplateId pblmStatusTemplateId = new TemplateId();
				pblmStatusTemplateId.setRoot("2.16.840.1.113883.10.20.22.4.9");
				pblmStatusTemplateIds.add(pblmStatusTemplateId);
				rxnObservation.setTemplateId(pblmStatusTemplateIds);

				Id rxnObservationId = new Id();
				rxnObservationId.setRoot(UUID.randomUUID().toString());
				rxnObservation.setId(rxnObservationId);

				Code pblmCode = new Code();
				pblmCode.setCode("ASSERTION");
				pblmCode.setCodeSystem("2.16.840.1.113883.5.4");
				rxnObservation.setCode(pblmCode);

				StatusCode pblmStatusCode = new StatusCode();
				pblmStatusCode.setCode("completed");
				rxnObservation.setStatusCode(pblmStatusCode);

				Value pblmStatusValue = new Value();
				pblmStatusValue.setXsiType("CD");
				pblmStatusValue.setCode(allergiesInfo.getReactionCode()); // get
				// from
				// db
				pblmStatusValue.setCodeSystem("2.16.840.1.113883.6.96");
				pblmStatusValue.setDisplayName(allergiesInfo.getReaction());
				rxnObservation.setValue(pblmStatusValue);
				rxnEntryRelationship.setObservation(rxnObservation);
				pblmstatusEntryRelationshipList.add(rxnEntryRelationship);
				// ----------------------- reaction of allergy explained above

				EntryRelationship alerstatusEntryRelationship = new EntryRelationship();
				alerstatusEntryRelationship.setTypeCode("SUBJ");
				alerstatusEntryRelationship.setInversionInd(true);

				Observation alerstatusObservation = new Observation();
				alerstatusObservation.setClassCode("OBS");
				alerstatusObservation.setMoodCode("EVN");

				List<TemplateId> alerstatusTemplateIds = new ArrayList<TemplateId>();
				TemplateId alerstatusTemplateId = new TemplateId();
				alerstatusTemplateId.setRoot("2.16.840.1.113883.10.20.22.4.28");
				alerstatusTemplateIds.add(alerstatusTemplateId);
				alerstatusObservation.setTemplateId(alerstatusTemplateIds);

				Code alerstatusCode = new Code();
				alerstatusCode.setCode("33999-4");
				alerstatusCode.setCodeSystem("2.16.840.1.113883.6.1");
				alerstatusCode.setDisplayName("Status");
				alerstatusObservation.setCode(alerstatusCode);

				StatusCode alertStatusCode = new StatusCode();
				alertStatusCode.setCode("completed");
				alerstatusObservation.setStatusCode(alertStatusCode);

				Value alertStatusValue = new Value();
				alertStatusValue.setXsiType("CE");
				alertStatusValue.setCode(allergiesInfo.getStatusCode()); // get
				// from
				// db
				alertStatusValue.setCodeSystem("2.16.840.1.113883.6.96");
				alertStatusValue.setDisplayName(allergiesInfo.getStatus());
				alerstatusObservation.setValue(alertStatusValue);
				alerstatusEntryRelationship.setObservation(alerstatusObservation);
				pblmstatusEntryRelationshipList.add(alerstatusEntryRelationship);

				observation.setEntryRelationship(pblmstatusEntryRelationshipList);
				entryRelationship.setObservation(observation);
				act.setEntryRelationship(entryRelationship);
				entry.setAct(act);
				section.getEntry().add(entry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return section;
	}

	public static Section addVitalSignsSection(Section section, NsResponse nsResponse) {

		Entry entry = new Entry();
		Organizer organizer = new Organizer();
		organizer.setClassCode("CLUSTER");
		organizer.setMoodCode("EVN");

		List<TemplateId> vitalsignTemplateIds = new ArrayList<TemplateId>();
		TemplateId vitalsignTemplateId = new TemplateId();
		vitalsignTemplateId.setRoot("2.16.840.1.113883.10.20.22.4.26");
		vitalsignTemplateIds.add(vitalsignTemplateId);
		organizer.setTemplateId(vitalsignTemplateIds);

		Id orgId = new Id();
		orgId.setRoot(UUID.randomUUID().toString());
		organizer.setId(orgId);

		Code orgCode = new Code();
		orgCode.setCode("46680005"); // fetch from property file
		orgCode.setCodeSystem("2.16.840.1.113883.6.96"); // fetch from property
		// file
		orgCode.setDisplayName("Vital signs");
		organizer.setCode(orgCode);

		StatusCode statusCode = new StatusCode();
		statusCode.setCode("completed"); // fetch from property file
		organizer.setStatusCode(statusCode);

		try {
			TEffectiveTime effectiveTime = new TEffectiveTime();
			if (nsResponse.getVitalSignsList() != null && nsResponse.getVitalSignsList().size() > 0) {
				effectiveTime.setValue(DateUtils.getFormatDate(
						DateUtils.getDate(nsResponse.getVitalSignsList().get(0).getDateAdded(), "MM/dd/yyyy"),
						"yyyyMMdd")); // will
				// come
				// from
				// db
				// for
				// this
				// create
				// diff
				// table
				// for
				// vital
				// signs
				// effectiveTime.setValue((new
				// SimpleDateFormat("MM-dd-yyyy").format(nsResponse.getVitalSignsList().get(0).getDateAdded())).toString());
				// //will come from db for this create diff table for vital
				// signs
			}
			organizer.setEffectiveTime(effectiveTime);
		} catch (Exception e) {
			e.printStackTrace();
		}

		organizer.setComponent(addComponentList(nsResponse));

		entry.setOrganizer(organizer);
		section.getEntry().add(entry);

		return section;
	}

	public static List<Component> addComponentList(NsResponse nsResponse) {

		List<Component> componentsList = new ArrayList<Component>();
		try {
			for (VitalSigns vitalSigns : nsResponse.getVitalSignsList()) {
				// Adding height Component
				Component height = new Component();

				Observation heightObservation = new Observation();
				heightObservation.setClassCode("OBS");
				heightObservation.setMoodCode("EVN");

				List<TemplateId> heightTemplateIds = new ArrayList<TemplateId>();
				TemplateId heightTemplateId = new TemplateId();
				heightTemplateId.setRoot("2.16.840.1.113883.10.20.22.4.27");
				heightTemplateIds.add(heightTemplateId);
				heightObservation.setTemplateId(heightTemplateIds);

				Id heigthId = new Id();
				heigthId.setRoot(UUID.randomUUID().toString());
				heightObservation.setId(heigthId);

				Code htCode = new Code();
				htCode.setCode("50373000"); // fetch from property file
				htCode.setCodeSystem("2.16.840.1.113883.6.96"); // fetch from
				// property file
				htCode.setDisplayName("Body height");
				heightObservation.setCode(htCode);

				StatusCode statusCode = new StatusCode();
				statusCode.setCode("completed");
				heightObservation.setStatusCode(statusCode);

				EffectiveTime effectiveTime = new EffectiveTime();
				// effectiveTime.setValue(new Long(new
				// SimpleDateFormat("MM-dd-yyyy").format(vitalSigns.getDateAdded())));
				effectiveTime.setValue(new Long(DateUtils.getFormatDate(
						DateUtils.getDate(vitalSigns.getDateAdded(), "MM/dd/yyyy"), "yyyyMMdd"))); // data
				// added
				// from
				// db
				heightObservation.setEffectiveTime(effectiveTime);

				Value value = new Value();
				value.setXsiType("PQ");
				if (!vitalSigns.getHeightInches().equalsIgnoreCase(VersaWorkConstant.NOT_APPLICABLE)) {
					value.setValue(vitalSigns.getHeightInches());
				}

				value.setUnit("in"); // Inches
				heightObservation.setValue(value);

				height.setObservation(heightObservation);

				componentsList.add(height);

				// Adding Weight Component
				Component weight = new Component();

				Observation weightObservation = new Observation();
				weightObservation.setClassCode("OBS");
				weightObservation.setMoodCode("EVN");

				List<TemplateId> weightTemplateIds = new ArrayList<TemplateId>();
				TemplateId weightTemplateId = new TemplateId();
				weightTemplateId.setRoot("2.16.840.1.113883.10.20.22.4.27");
				weightTemplateIds.add(weightTemplateId);
				weightObservation.setTemplateId(weightTemplateIds);

				Id weightId = new Id();
				weightId.setRoot(UUID.randomUUID().toString());
				weightObservation.setId(weightId);

				Code wtCode = new Code();
				wtCode.setCode("27113001"); // fetch from property file
				wtCode.setCodeSystem("2.16.840.1.113883.6.96"); // fetch from
				// property file
				wtCode.setDisplayName("Body weight");
				weightObservation.setCode(wtCode);

				// StatusCode statusCode = new StatusCode();
				// statusCode.setCode("completed"); //used from above as this
				// will be same as per hl7 specs
				weightObservation.setStatusCode(statusCode);

				// EffectiveTime effectiveTime = new EffectiveTime();
				// effectiveTime.setValue(new Long(19991114)); //used from above
				// as this will come from db currently ot os hard coded
				weightObservation.setEffectiveTime(effectiveTime);

				Value wtvalue = new Value();
				wtvalue.setXsiType("PQ");
				wtvalue.setValue(vitalSigns.getWeightLbs());
				wtvalue.setUnit("lbs"); // lbs
				weightObservation.setValue(wtvalue);

				weight.setObservation(weightObservation);

				componentsList.add(weight);

				// Adding sys BP component
				Component sysBP = new Component();

				Observation sysBPObservation = new Observation();
				sysBPObservation.setClassCode("OBS");
				sysBPObservation.setMoodCode("EVN");

				List<TemplateId> sysBPTemplateIds = new ArrayList<TemplateId>();
				TemplateId sysBPTemplateId = new TemplateId();
				sysBPTemplateId.setRoot("2.16.840.1.113883.10.20.22.4.27");
				sysBPTemplateIds.add(sysBPTemplateId);
				sysBPObservation.setTemplateId(sysBPTemplateIds);

				Id sysBPId = new Id();
				sysBPId.setRoot(UUID.randomUUID().toString());
				sysBPObservation.setId(sysBPId);

				Code sysBPCode = new Code();
				sysBPCode.setCode("271649006"); // fetch from property file
				sysBPCode.setCodeSystem("2.16.840.1.113883.6.96"); // fetch from
				// property
				// file
				sysBPCode.setDisplayName("Systolic BP");
				sysBPObservation.setCode(sysBPCode);

				// StatusCode statusCode = new StatusCode();
				// statusCode.setCode("completed"); //used from above as this
				// will be same as per hl7 specs
				sysBPObservation.setStatusCode(statusCode);

				// EffectiveTime effectiveTime = new EffectiveTime();
				// effectiveTime.setValue(new Long(19991114)); //used from above
				// as this will come from db currently ot os hard coded
				sysBPObservation.setEffectiveTime(effectiveTime);

				Value sysBPvalue = new Value();
				sysBPvalue.setXsiType("PQ");
				if (!vitalSigns.getSystolicBp().equalsIgnoreCase(VersaWorkConstant.NOT_APPLICABLE)) {
					sysBPvalue.setValue(vitalSigns.getSystolicBp()); // get sys
																		// from
				}
				// db
				sysBPvalue.setUnit(vitalSigns.getSystolicBpUnit()); // can come
				// from db
				sysBPObservation.setValue(sysBPvalue);

				sysBP.setObservation(sysBPObservation);

				componentsList.add(sysBP);

				// Adding DIA BP component
				Component diaBP = new Component();

				Observation diaBPObservation = new Observation();
				diaBPObservation.setClassCode("OBS");
				diaBPObservation.setMoodCode("EVN");

				List<TemplateId> diaBPTemplateIds = new ArrayList<TemplateId>();
				TemplateId diaBPTemplateId = new TemplateId();
				diaBPTemplateId.setRoot("2.16.840.1.113883.10.20.22.4.27");
				diaBPTemplateIds.add(diaBPTemplateId);
				diaBPObservation.setTemplateId(diaBPTemplateIds);

				Id diaBPId = new Id();
				diaBPId.setRoot(UUID.randomUUID().toString());
				diaBPObservation.setId(diaBPId);

				Code diaBPCode = new Code();
				diaBPCode.setCode("271650006"); // fetch from property file
				diaBPCode.setCodeSystem("2.16.840.1.113883.6.96"); // fetch from
				// property
				// file
				diaBPCode.setDisplayName("Diastolic BP");
				diaBPObservation.setCode(diaBPCode);

				// StatusCode statusCode = new StatusCode();
				// statusCode.setCode("completed"); //used from above as this
				// will be same as per hl7 specs
				diaBPObservation.setStatusCode(statusCode);

				// EffectiveTime effectiveTime = new EffectiveTime();
				// effectiveTime.setValue(new Long(19991114)); //used from above
				// as this will come from db currently ot os hard coded
				diaBPObservation.setEffectiveTime(effectiveTime);

				Value diaBPvalue = new Value();
				diaBPvalue.setXsiType("PQ");
				if (!vitalSigns.getDiastolicBp().equalsIgnoreCase(VersaWorkConstant.NOT_APPLICABLE)) {
					diaBPvalue.setValue(vitalSigns.getDiastolicBp()); // get sys
				}
				// from db
				diaBPvalue.setUnit(vitalSigns.getDiastolicBpUnit()); // can come
				// from
				// db
				diaBPObservation.setValue(diaBPvalue);

				diaBP.setObservation(diaBPObservation);

				componentsList.add(diaBP);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return componentsList;

	}

	public static Section addProceduresSection(Section section, NsResponse nsResponse) {

		try {
			for (ProcedureInfo procedureInfo : nsResponse.getProcedureInfoList()) {
				Entry entry = new Entry();
				Procedure procedure = new Procedure();
				procedure.setClassCode("PROC");
				procedure.setMoodCode("EVN");

				List<TemplateId> procTemplateIds = new ArrayList<TemplateId>();
				TemplateId procTemplateId = new TemplateId();
				procTemplateId.setRoot("2.16.840.1.113883.10.20.22.4.14");
				procTemplateIds.add(procTemplateId);
				procedure.setTemplateId(procTemplateIds);

				Id procId = new Id();
				procId.setRoot(UUID.randomUUID().toString());
				procedure.setId(procId);

				Code code = new Code();
				code.setCode(procedureInfo.getProcedureCode());
				code.setCodeSystem("2.16.840.1.113883.6.96");
				code.setDisplayName(procedureInfo.getProcedureName());
				procedure.setCode(code);

				StatusCode statusCode = new StatusCode();
				statusCode.setCode("completed");
				procedure.setStatusCode(statusCode);

				EffectiveTime effectiveTime = new EffectiveTime();
				Low low = new Low();
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
				// SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				Date convertedCurrentDate;
				convertedCurrentDate = sdf.parse(procedureInfo.getDateAdded());
				low.setValue(new Long(DateUtils.getFormatDate(convertedCurrentDate, "yyyyMMdd")));
				effectiveTime.setLow(low);
				procedure.setEffectiveTime(effectiveTime);

				entry.setProcedure(procedure);
				section.getEntry().add(entry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return section;
	}

	public static Section addImmunizationsSection(Section section, NsResponse nsResponse) {

		try {
			for (PatientInpatientImmunalizationInfo patientInpatientImmunalizationInfo : nsResponse
					.getPatientInpatientImmunalizationInfoList()) {
				Entry entry = new Entry();
				entry.setTypeCode("DRIV");

				SubstanceAdministration substanceAdministration = new SubstanceAdministration();
				substanceAdministration.setClassCode("SBADM");
				substanceAdministration.setMoodCode("EVN");
				substanceAdministration.setNegationInd(false);

				List<TemplateId> subAdminTemplateIds = new ArrayList<TemplateId>();
				TemplateId subAdminTemplateId = new TemplateId();
				subAdminTemplateId.setRoot("2.16.840.1.113883.10.20.22.4.52");
				subAdminTemplateIds.add(subAdminTemplateId);
				substanceAdministration.setTemplateId(subAdminTemplateIds);

				Id subAdminId = new Id();
				subAdminId.setRoot(UUID.randomUUID().toString());
				substanceAdministration.setId(subAdminId);

				StatusCode statusCode = new StatusCode();
				statusCode.setCode("completed");
				substanceAdministration.setStatusCode(statusCode);

				List<EffectiveTime> effectiveTimeList = new ArrayList<EffectiveTime>();
				EffectiveTime effectiveTime = new EffectiveTime();
				effectiveTime.setXsiType("IVL_TS");
				Date convertedCurrentDate = DateUtils.getDate(patientInpatientImmunalizationInfo.getDateAdded(),
						"MM/dd/yyyy");
				Center center = new Center();
				center.setValue(new Long(DateUtils.getFormatDate(convertedCurrentDate, "yyyyMMdd")));
				effectiveTime.setCenter(center);
				effectiveTimeList.add(effectiveTime);
				substanceAdministration.setEffectiveTime(effectiveTimeList);

				/*
				 * RouteCode routeCode = new RouteCode();
				 * routeCode.setCode(patientInpatientImmunalizationInfo
				 * .getRouteCode()); // Will come from DB
				 * routeCode.setCodeSystem("2.16.840.1.113883.5.112");
				 * routeCode.
				 * setDisplayName(patientInpatientImmunalizationInfo.getRouteName
				 * ()); //Will come from DB
				 * substanceAdministration.setRouteCode(routeCode);
				 */

				Consumable consumable = new Consumable();
				ManufacturedProduct manufacturedProduct = new ManufacturedProduct();
				manufacturedProduct.setClassCode("MANU");

				TemplateId manuTemplateId = new TemplateId();
				manuTemplateId.setRoot("2.16.840.1.113883.10.20.22.4.54");
				manufacturedProduct.setTemplateId(manuTemplateId);

				ManufacturedMaterial manufacturedMaterial = new ManufacturedMaterial();
				Code manuMaterialCode = new Code();
				manuMaterialCode.setCode(patientInpatientImmunalizationInfo.getImmunalizationCode());
				manuMaterialCode.setCodeSystem("2.16.840.1.113883.12.292");
				manuMaterialCode.setDisplayName(patientInpatientImmunalizationInfo.getImmunalizationName());

				manuMaterialCode.setOriginalText("Vaccine");

				manufacturedMaterial.setCode(manuMaterialCode);
				manufacturedProduct.setManufacturedMaterial(manufacturedMaterial);

				consumable.setManufacturedProduct(manufacturedProduct);
				substanceAdministration.setConsumable(consumable);

				entry.setSubstanceAdministration(substanceAdministration);
				section.getEntry().add(entry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return section;
	}

	public static Section addMedicationSection(Section section, NsResponse nsResponse) {

		try {
			for (MedicationInfo medicationInfo : nsResponse.getMedicationInfoList()) {
				Entry entry = new Entry();
				entry.setTypeCode("DRIV");

				SubstanceAdministration substanceAdministration = new SubstanceAdministration();
				substanceAdministration.setClassCode("SBADM");
				substanceAdministration.setMoodCode("EVN");

				List<TemplateId> subAdminTemplateIds = new ArrayList<TemplateId>();
				TemplateId subAdminTemplateId = new TemplateId();
				subAdminTemplateId.setRoot("2.16.840.1.113883.10.20.22.4.16");
				subAdminTemplateIds.add(subAdminTemplateId);
				substanceAdministration.setTemplateId(subAdminTemplateIds);

				Id subAdminId = new Id();
				subAdminId.setRoot(UUID.randomUUID().toString());
				substanceAdministration.setId(subAdminId);

				StatusCode statusCode = new StatusCode();
				// Date currDate= new Date();

				/*
				 * if(currDate.before(medicationInfo.getEndDate())) // fetch end
				 * date from db { statusCode.setCode("active"); } else
				 * {statusCode.setCode("completed");}
				 */
				statusCode.setCode(medicationInfo.getStatus());
				substanceAdministration.setStatusCode(statusCode);

				List<EffectiveTime> effectiveTimeList = new ArrayList<EffectiveTime>();
				EffectiveTime effectiveTime = new EffectiveTime();
				effectiveTime.setXsiType("IVL_TS");
				Low low = new Low();
				low.setValue(new Long(DateUtils.getFormatDate(
						DateUtils.getDate(medicationInfo.getStartDate(), "MM/dd/yyyy"), "yyyyMMdd"))); // get
				// start
				// date
				// from
				// db
				effectiveTime.setLow(low);
				High high = new High();
				high.setValue(new Long(DateUtils.getFormatDate(
						DateUtils.getDate(medicationInfo.getEndDate(), "MM/dd/yyyy"), "yyyyMMdd"))); // get
				// end
				// date
				// from
				// db
				effectiveTime.setHigh(high);
				effectiveTimeList.add(effectiveTime);
				// substanceAdministration.setEffectiveTime(effectiveTime);

				/*
				 * RouteCode routeCode = new RouteCode();
				 * routeCode.setCode(medicationInfo.getRouteCode()); // will
				 * come from db
				 * routeCode.setCodeSystem("2.16.840.1.113883.5.11");
				 * routeCode.setDisplayName(medicationInfo.getRouteName()); //
				 * will come from db
				 * substanceAdministration.setRouteCode(routeCode);
				 */

				EffectiveTime periodEffectiveTime = new EffectiveTime();
				periodEffectiveTime.setXsiType("PIVL_TS");
				periodEffectiveTime.setOperator("A");
				Period period = new Period();
				// DDD period.setValue(new
				// Long(medicationInfo.getDosageDescription()));
				// period.setValue(medicationInfo.getDosageDescription());
				period.setUnit("h");
				periodEffectiveTime.setPeriod(period);
				effectiveTimeList.add(periodEffectiveTime);
				substanceAdministration.setEffectiveTime(effectiveTimeList);

				DoseQuantity doseQuantity = new DoseQuantity(); // Currently
				// only supports
				// Integer
				doseQuantity.setValue(medicationInfo.getDoseStrength().toString()); // get
				// dose
				// quantity
				// from
				// db
				doseQuantity.setUnit("mg");
				substanceAdministration.setDoseQuantity(doseQuantity);

				Consumable consumable = new Consumable();
				ManufacturedProduct manufacturedProduct = new ManufacturedProduct();
				manufacturedProduct.setClassCode("MANU");

				TemplateId manuTemplateId = new TemplateId();
				manuTemplateId.setRoot("2.16.840.1.113883.10.20.22.4.23");
				manufacturedProduct.setTemplateId(manuTemplateId);

				ManufacturedMaterial manufacturedMaterial = new ManufacturedMaterial();
				Code manuMaterialCode = new Code();
				manuMaterialCode.setCode(medicationInfo.getRxNumber());
				manuMaterialCode.setCodeSystem("2.16.840.1.113883.6.88");
				manuMaterialCode.setDisplayName(medicationInfo.getMedicationName());

				manuMaterialCode.setOriginalText(medicationInfo.getMedicationName());

				manufacturedMaterial.setCode(manuMaterialCode);
				manufacturedProduct.setManufacturedMaterial(manufacturedMaterial);

				consumable.setManufacturedProduct(manufacturedProduct);
				substanceAdministration.setConsumable(consumable);

				entry.setSubstanceAdministration(substanceAdministration);
				section.getEntry().add(entry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return section;
	}

	public static Section addResultsSection(Section section, NsResponse nsResponse) {

		try {
			Entry entry = new Entry(); // it can be mulitple based on different
			// organiser this will come from db
			entry.setTypeCode("DRIV");

			Organizer organizer = new Organizer();
			organizer.setClassCode("BATTERY");
			organizer.setMoodCode("EVN");

			List<TemplateId> orgTemplateIds = new ArrayList<TemplateId>();
			TemplateId orgTemplateId = new TemplateId();
			orgTemplateId.setRoot("2.16.840.1.113883.10.20.22.4.1");
			orgTemplateIds.add(orgTemplateId);
			organizer.setTemplateId(orgTemplateIds);

			Id orgId = new Id();
			orgId.setRoot(UUID.randomUUID().toString());
			organizer.setId(orgId);

			Code code = new Code();
			code.setCode("57021-8"); // This will come from db
			code.setCodeSystem("2.16.840.1.113883.6.96");
			code.setDisplayName("CBC W Auto Differential panel in Blood"); // This
			// will
			// come
			// from
			// db
			organizer.setCode(code);

			StatusCode statusCode = new StatusCode();
			statusCode.setCode("completed");
			organizer.setStatusCode(statusCode);

			for (LabResultInfo labResultInfo : nsResponse.getLabResultInfoList()) {

				Component component = new Component();
				Observation observation = new Observation();

				observation.setClassCode("OBS");
				observation.setMoodCode("EVN");

				List<TemplateId> templateIds = new ArrayList<TemplateId>();
				TemplateId obstemplateId = new TemplateId();
				obstemplateId.setRoot("2.16.840.1.113883.10.20.22.4.2"); // fetch
				// from
				// property
				// file
				templateIds.add(obstemplateId);
				observation.setTemplateId(templateIds);

				Id id = new Id();
				id.setRoot(UUID.randomUUID().toString());
				observation.setId(id);

				Code obsCode = new Code();
				obsCode.setCode(labResultInfo.getLabTestCode()); // fetch from
				// property
				// file
				obsCode.setCodeSystem("2.16.840.1.113883.6.1"); // fetch from
				// property file
				obsCode.setDisplayName(labResultInfo.getLabTest());
				observation.setCode(obsCode);

				StatusCode obsStatusCode = new StatusCode();
				obsStatusCode.setCode("completed");
				observation.setStatusCode(obsStatusCode);

				Date resultDate = DateUtils.getDate(labResultInfo.getDateAdded(), "MM/dd/yyyy");
				EffectiveTime obsEffectiveTime = new EffectiveTime();
				obsEffectiveTime.setValue(new Long(DateUtils.getFormatDate(resultDate, "yyyyMMdd")));
				observation.setEffectiveTime(obsEffectiveTime);

				Value value = new Value();
				value.setXsiType("PQ");
				value.setValue(labResultInfo.getLabResult());
				value.setUnit(labResultInfo.getLabResultUnit()); // will come
				// from db
				observation.setValue(value);

				InterpretationCode interpretationCode = new InterpretationCode();
				interpretationCode.setCode(labResultInfo.getInterpretationCode().trim()); // will
				// come
				// from
				// db?
				interpretationCode.setCodeSystem("2.16.840.1.113883.5.83");
				observation.setInterpretationCode(interpretationCode);

				/*
				 * ReferenceRange referenceRange = new ReferenceRange();
				 * ObservationRange observationRange = new ObservationRange();
				 * 
				 * Value obsValue = new Value(); obsValue.setXsiType("IVL_PQ");
				 * ObservationRangeLow low = new ObservationRangeLow();
				 * low.setValue(new Double("3.5")); // will come from db?
				 * low.setUnit("meq/l"); // will come from db?
				 * obsValue.setLow(low); ObservationRangeHigh high = new
				 * ObservationRangeHigh(); high.setValue(new Double("5.5")); //
				 * will come from db? high.setUnit("meq/l"); // will come from
				 * db? obsValue.setHigh(high);
				 * 
				 * observationRange.setValue(obsValue);
				 * referenceRange.setObservationRange(observationRange);
				 * observation.setReferenceRange(referenceRange);
				 */

				component.setObservation(observation);

				organizer.getComponent().add(component);
			}
			entry.setOrganizer(organizer);
			section.getEntry().add(entry);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return section;
	}

	/*
	 * public static void main(String args[]) { ClinicalDocument ccd = new
	 * ClinicalDocument(); try {
	 * 
	 * JAXBContext jaxbContext =
	 * JAXBContext.newInstance(ClinicalDocument.class); Marshaller
	 * jaxbMarshaller = jaxbContext.createMarshaller();
	 * 
	 * // output pretty printed
	 * jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); ccd =
	 * generateHeader(ccd,new NsResponse()); //jaxbMarshaller.marshal(obj,
	 * file); ccd = generateBody(ccd,new NsResponse());
	 * jaxbMarshaller.marshal(ccd, System.out);
	 * 
	 * } catch (JAXBException e) { e.printStackTrace(); }
	 * 
	 * 
	 * }
	 */

	public static Byte[] toObjects(byte[] bytesPrim) {

		Byte[] bytes = new Byte[bytesPrim.length];
		int i = 0;
		for (byte b : bytesPrim)
			bytes[i++] = b; // Autoboxing
		return bytes;

	}
}
