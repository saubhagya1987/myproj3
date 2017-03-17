package com.versawork.http.utils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPHeaderCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.PdfWriter;
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
import com.versawork.http.dataobject.PatientInpatientMetadataInfo;
import com.versawork.http.dataobject.PatientProblemInfo;
import com.versawork.http.dataobject.ProcedureInfo;
import com.versawork.http.dataobject.ViewPatientInfo;
import com.versawork.http.dataobject.VitalSigns;

/**
 * @author Nitin
 * 
 */

public class GeneratePDF {

	private static final Font LIST_ITEM_FONT;
	private static final Font TABLE_HEADER_FONT;
	private static final Font TABLE_CELL_FONT;
	private static final Font TITLE_FONT;

	private static final float TABLE_BORDER_WIDTH = 1f;

	static {
		LIST_ITEM_FONT = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD | Font.UNDERLINE, BaseColor.BLUE);
		TABLE_HEADER_FONT = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		TABLE_CELL_FONT = new Font(FontFamily.TIMES_ROMAN, 10);
		TITLE_FONT = new Font(FontFamily.HELVETICA, 15, Font.BOLD);
	}

	/**
	 * @param nsResponse
	 * @return
	 */
	public byte[] generatePDF(NsResponse nsResponse, String version) {
		byte[] pdfBytes = null;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		try {
			// step 1
			Document document = new Document(PageSize.A4, 80, 80, 50, 50);

			// step 2
			PdfWriter pdfWriter = PdfWriter.getInstance(document, byteArrayOutputStream);

			/** PDF tagging enabled & primary language set */
			pdfWriter.setTagged();
			pdfWriter.setUserProperties(true);
			pdfWriter.setLanguage("en");
			pdfWriter.setViewerPreferences(PdfWriter.DisplayDocTitle);
			pdfWriter.setPdfVersion(PdfWriter.VERSION_1_6);

			/**
			 * Document title, author, subject, keywords, language, creator
			 * added
			 */
			document.addTitle("Continuity of Care Document" + " - " + version);
			document.addAuthor("Navis Health");
			document.addSubject("Continuity of Care Document");
			document.addKeywords("CCD, PDF");
			document.addLanguage("en");

			// step 3
			document.open();

			// step 4
			Paragraph paragraph = new Paragraph();
			Date createdDate = new Date();

			Chunk top = new Chunk("Continuity of Care Document", TITLE_FONT);
			paragraph.setAlignment(Paragraph.ALIGN_CENTER);
			paragraph.add(top);
			paragraph.add(getNewLine());
			document.add(paragraph);

			Font headingsFont = new Font(FontFamily.HELVETICA, 14, Font.BOLDITALIC);

			paragraph = new Paragraph();
			Chunk createdOn = new Chunk("Created On:" + createdDate, headingsFont);
			paragraph.setAlignment(Paragraph.ALIGN_CENTER);
			paragraph.add(createdOn);
			document.add(paragraph);

			/** Populate Patient Info */
			populatePatientInfo(document, nsResponse.getViewPatientInfo(), createdDate);

			Chunk tableOfContent = new Chunk("Table of Contents", new Font(FontFamily.HELVETICA, 14, Font.BOLDITALIC
					| Font.UNDERLINE));
			document.add(new Paragraph(tableOfContent));
			document.add(getNewLine());

			Paragraph pp = new Paragraph();

			pp.add(createList(nsResponse.getVisitType(), version));
			pp.add(getNewLine());
			pp.add(getNewLine());
			if (nsResponse.getPatientProblemInfoList() != null) {
				pp.add(createProblems((ArrayList<PatientProblemInfo>) nsResponse.getPatientProblemInfoList()));
				pp.add(getNewLine());
			}
			if (nsResponse.getMedicationInfoList() != null) {
				pp.add(createMedications((ArrayList<MedicationInfo>) nsResponse.getMedicationInfoList()));
				pp.add(getNewLine());
			}
			if (nsResponse.getAllergiesInfoList() != null) {
				pp.add(createAllergies((ArrayList<AllergiesInfo>) nsResponse.getAllergiesInfoList()));
				pp.add(getNewLine());
			}
			if (nsResponse.getProcedureInfoList() != null) {
				pp.add(createProcedure((ArrayList<ProcedureInfo>) nsResponse.getProcedureInfoList()));
				pp.add(getNewLine());
			}
			if (nsResponse.getNsDoctorsList() != null) {
				pp.add(createProviderList((ArrayList<NsDoctorsList>) nsResponse.getNsDoctorsList()));
				pp.add(getNewLine());
			}
			if (nsResponse.getLabResultInfoList() != null) {
				pp.add(createLabResult((ArrayList<LabResultInfo>) nsResponse.getLabResultInfoList()));
				pp.add(getNewLine());
			}
			if (nsResponse.getVitalSignsList() != null) {
				pp.add(createVitalSigns((ArrayList<VitalSigns>) nsResponse.getVitalSignsList()));
				pp.add(getNewLine());
			}
			if (nsResponse.getCarePlanInfoList() != null) {
				pp.add(createCarePlan((ArrayList<CarePlanInfo>) nsResponse.getCarePlanInfoList()));
				pp.add(getNewLine());
			}
			if (nsResponse.getVisitType() == 1) {
				if (nsResponse.getPatientInpatientMetadataInfo() != null) {
					pp.add(createInpatientDischargeInstructionsData((PatientInpatientMetadataInfo) nsResponse
							.getPatientInpatientMetadataInfo()));

					pp.add(getNewLine());
				}
				if (version.equalsIgnoreCase("summary")) {
					if (nsResponse.getPatientInpatientMetadataInfo() != null) {
						pp.add(createInpatientData((PatientInpatientMetadataInfo) nsResponse
								.getPatientInpatientMetadataInfo()));
						pp.add(getNewLine());
					}
					if (nsResponse.getPatientInpatientMetadataInfo() != null) {
						pp.add(createInpatientHospitalReasonData((PatientInpatientMetadataInfo) nsResponse
								.getPatientInpatientMetadataInfo()));
						pp.add(getNewLine());
					}
				} else if (version.equalsIgnoreCase("toc")) {
					if (nsResponse.getPatientInpatientDiagnosisInfoList() != null) {
						pp.add(createInpatientDiagnosisData((ArrayList<PatientInpatientDiagnosisInfo>) nsResponse
								.getPatientInpatientDiagnosisInfoList()));
						pp.add(getNewLine());
					}
					if (nsResponse.getPatientInpatientFunctionalStatusInfoList() != null) {
						pp.add(createInpatientFunctionalData((ArrayList<PatientInpatientFunctionalStatusInfo>) nsResponse
								.getPatientInpatientFunctionalStatusInfoList()));
						pp.add(getNewLine());
					}
					if (nsResponse.getPatientInpatientImmunalizationInfoList() != null) {
						pp.add(createInpatientImmunalizationData((ArrayList<PatientInpatientImmunalizationInfo>) nsResponse
								.getPatientInpatientImmunalizationInfoList()));
						pp.add(getNewLine());
					}
				}

			}

			document.add(pp);

			// step 5
			document.close();
			pdfBytes = byteArrayOutputStream.toByteArray();

			byteArrayOutputStream.flush();
			byteArrayOutputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pdfBytes;
	}

	public static Byte[] toObjects(byte[] bytesPrim) {

		Byte[] bytes = new Byte[bytesPrim.length];
		int i = 0;
		for (byte b : bytesPrim)
			bytes[i++] = b; // Autoboxing
		return bytes;

	}

	public static Paragraph getNewLine() {

		Paragraph newLine = new Paragraph(" ");

		return newLine;
	}

	private static void populatePatientInfo(Document document, ViewPatientInfo viewPatientInfo, Date date)
			throws Exception {

		document.add(getNewLine());

		Chunk content = new Chunk("Patient Information", new Font(FontFamily.HELVETICA, 14, Font.BOLDITALIC
				| Font.UNDERLINE));
		document.add(new Paragraph(content));

		document.add(getNewLine());

		document.add(new Paragraph("Patient name: " + viewPatientInfo.getFirstName() + " "
				+ viewPatientInfo.getLastName()));
		document.add(new Paragraph("Patient IDs: " + viewPatientInfo.getUnitNumber()));
		if (viewPatientInfo.getSex() != null) {
			document.add(new Paragraph("Sex: " + viewPatientInfo.getSex()));
		} else {
			document.add(new Paragraph("Sex: "));
		}
		if (viewPatientInfo.getBirthDate() != null) {
			document.add(new Paragraph("Date of birth: " + viewPatientInfo.getBirthDate()));
		} else {
			document.add(new Paragraph("Date of birth: "));
		}
		if (viewPatientInfo.getRace() != null) {
			document.add(new Paragraph("Race: " + viewPatientInfo.getRace()));
		} else {
			document.add(new Paragraph("Race: "));
		}
		if (viewPatientInfo.getEthnicity() != null) {
			document.add(new Paragraph("Ethnicity: " + viewPatientInfo.getEthnicity()));
		} else {
			document.add(new Paragraph("Ethnicity: "));
		}
		if (viewPatientInfo.getPreferredLang() != null && viewPatientInfo.getPreferredLang().equalsIgnoreCase("eng")) {
			document.add(new Paragraph("Preferred Language: " + "English"));
		}
		// document.add(new Paragraph("Author: " + "Versaworks Inc."));
		/* document.add(new Paragraph("Contact Info: " + "Versaworks Inc.")); */
		// document.add(new Paragraph("Document Id: " + UUID.randomUUID()));
		// document.add(new Paragraph("Document Created: " + date));
		document.add(new Paragraph("Smoking Status: " + viewPatientInfo.getSmokingStatus()));
		// document.add(new Paragraph("Document Maintained By: " +
		// "Versaworks Inc."));
		document.add(getNewLine());
	}

	/**
	 * @return
	 */
	private static List createList(Integer visitType, String version) {

		Font listItemTextFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.UNDERLINE, BaseColor.BLUE);
		Font listSymbolFont = new Font(FontFamily.TIMES_ROMAN, 16);

		List list = new List(List.UNORDERED, List.ANCHOR);
		list.setIndentationLeft(20);
		list.setListSymbol("\u2022");

		Chunk chunkListItem = new Chunk("Problems", listItemTextFont).setAction(
				PdfAction.gotoLocalPage("Problems", false)).setLocalDestination("ProblemsItem");
		chunkListItem.setAccessibleAttribute(PdfName.ALT, new PdfName("Problems"));
		ListItem listItem = new ListItem(chunkListItem);
		listItem.setFont(listSymbolFont);
		list.add(listItem);

		chunkListItem = new Chunk("Medications", listItemTextFont).setAction(
				PdfAction.gotoLocalPage("Medications", false)).setLocalDestination("MedicationsItem");
		chunkListItem.setAccessibleAttribute(PdfName.ALTERNATE, new PdfString("Medications"));
		listItem = new ListItem(chunkListItem);
		listItem.setFont(listSymbolFont);
		list.add(listItem);

		chunkListItem = new Chunk("Allergies", listItemTextFont).setAction(PdfAction.gotoLocalPage("Allergies", false))
				.setLocalDestination("AllergiesItem");
		chunkListItem.setAccessibleAttribute(PdfName.ALTERNATE, new PdfString("Allergies"));
		listItem = new ListItem(chunkListItem);
		listItem.setFont(listSymbolFont);
		list.add(listItem);

		chunkListItem = new Chunk("Procedures", listItemTextFont).setAction(
				PdfAction.gotoLocalPage("Procedures", false)).setLocalDestination("ProceduresItem");
		chunkListItem.setAccessibleAttribute(PdfName.ALTERNATE, new PdfString("Procedures"));
		listItem = new ListItem(chunkListItem);
		listItem.setFont(listSymbolFont);
		list.add(listItem);

		chunkListItem = new Chunk("Care Team", listItemTextFont).setAction(PdfAction.gotoLocalPage("Care Team", false))
				.setLocalDestination("Care TeamItem");
		chunkListItem.setAccessibleAttribute(PdfName.ALTERNATE, new PdfString("Care Team"));
		listItem = new ListItem(chunkListItem);
		listItem.setFont(listSymbolFont);
		list.add(listItem);

		chunkListItem = new Chunk("Laboratory Test/Result", listItemTextFont).setAction(
				PdfAction.gotoLocalPage("Laboratory Test/Result", false)).setLocalDestination(
				"Laboratory Test/ResultItem");
		chunkListItem.setAccessibleAttribute(PdfName.ALTERNATE, new PdfString("Laboratory Test/Result"));
		listItem = new ListItem(chunkListItem);
		listItem.setFont(listSymbolFont);
		list.add(listItem);

		chunkListItem = new Chunk("Vital Signs", listItemTextFont).setAction(
				PdfAction.gotoLocalPage("Vital Signs", false)).setLocalDestination("Vital SignsItem");
		chunkListItem.setAccessibleAttribute(PdfName.ALTERNATE, new PdfString("Vital Signs"));
		listItem = new ListItem(chunkListItem);
		listItem.setFont(listSymbolFont);
		list.add(listItem);

		chunkListItem = new Chunk("Care Plans", listItemTextFont).setAction(
				PdfAction.gotoLocalPage("Care Plans", false)).setLocalDestination("Care PlansItem");
		chunkListItem.setAccessibleAttribute(PdfName.ALTERNATE, new PdfString("Care Plans"));
		listItem = new ListItem(chunkListItem);
		listItem.setFont(listSymbolFont);
		list.add(listItem);

		if (visitType == 1) {

			chunkListItem = new Chunk("Discharge Instructions", listItemTextFont).setAction(
					PdfAction.gotoLocalPage("Discharge Instructions", false)).setLocalDestination(
					"Discharge InstructionsItem");
			chunkListItem.setAccessibleAttribute(PdfName.ALTERNATE, new PdfString("Discharge Instructions"));
			listItem = new ListItem(chunkListItem);
			listItem.setFont(listSymbolFont);
			list.add(listItem);

			if (version.equalsIgnoreCase("summary")) {
				chunkListItem = new Chunk("Encounter Information", listItemTextFont).setAction(
						PdfAction.gotoLocalPage("Encounter Information", false)).setLocalDestination(
						"Encounter InformationItem");
				chunkListItem.setAccessibleAttribute(PdfName.ALTERNATE, new PdfString("Encounter Information"));
				listItem = new ListItem(chunkListItem);
				listItem.setFont(listSymbolFont);
				list.add(listItem);

				chunkListItem = new Chunk("Hospitalization Reason", listItemTextFont).setAction(
						PdfAction.gotoLocalPage("Hospitalization Reason", false)).setLocalDestination(
						"Hospitalization ReasonItem");
				chunkListItem.setAccessibleAttribute(PdfName.ALTERNATE, new PdfString("Hospitalization Reason"));
				listItem = new ListItem(chunkListItem);
				listItem.setFont(listSymbolFont);
				list.add(listItem);
			}

			else if (version.equalsIgnoreCase("toc")) {
				chunkListItem = new Chunk("Encounter Diagnosis", listItemTextFont).setAction(
						PdfAction.gotoLocalPage("Encounter Diagnosis", false)).setLocalDestination(
						"Encounter DiagnosisItem");
				chunkListItem.setAccessibleAttribute(PdfName.ALTERNATE, new PdfString("Encounter Diagnosis"));
				listItem = new ListItem(chunkListItem);
				listItem.setFont(listSymbolFont);
				list.add(listItem);

				chunkListItem = new Chunk("Functional Status", listItemTextFont).setAction(
						PdfAction.gotoLocalPage("Functional Status", false)).setLocalDestination(
						"Functional StatusItem");
				chunkListItem.setAccessibleAttribute(PdfName.ALTERNATE, new PdfString("Functional Status"));
				listItem = new ListItem(chunkListItem);
				listItem.setFont(listSymbolFont);
				list.add(listItem);

				chunkListItem = new Chunk("Immunization", listItemTextFont).setAction(
						PdfAction.gotoLocalPage("Immunization", false)).setLocalDestination("ImmunizationItem");
				chunkListItem.setAccessibleAttribute(PdfName.ALTERNATE, new PdfString("Immunization"));
				listItem = new ListItem(chunkListItem);
				listItem.setFont(listSymbolFont);
				list.add(listItem);
			}
		}
		return list;

	}

	/**
	 * @param prblmList
	 * @return
	 * @throws DocumentException
	 */
	public static Paragraph createProblems(ArrayList<PatientProblemInfo> prblmList) throws DocumentException {

		Paragraph problemPara = new Paragraph();

		Chunk prblmTop = new Chunk("Problems", LIST_ITEM_FONT)
				.setAction(PdfAction.gotoLocalPage("ProblemsItem", false)).setLocalDestination("Problems");
		problemPara.add(prblmTop);
		problemPara.add(new Phrase("\n\n"));

		if (prblmList != null && !prblmList.isEmpty()) {

			/** creating table for problems */
			PdfPTable table = new PdfPTable(3);

			/** Table Headers and Width Percentage added */
			table.setHeaderRows(1);
			table.setWidthPercentage(100f);

			PdfPHeaderCell tableHeader = new PdfPHeaderCell();

			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Problem", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Status", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Date", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			for (PatientProblemInfo pblmInfo : prblmList) {
				// Font TABLE_CELL_FONT = new Font(FontFamily.TIMES_ROMAN, 10);

				PdfPCell tableCell = new PdfPCell(new Phrase(pblmInfo.getProblemName(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(pblmInfo.getStatus(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(pblmInfo.getDateAdded(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);
			}
			problemPara.add(table);
		} else {
			problemPara.add(new Phrase("No patient problems data."));
		}
		return problemPara;
	}

	public static Paragraph createMedications(ArrayList<MedicationInfo> medicationInfoList) {

		Paragraph medicationPara = new Paragraph();

		Chunk medicationTop = new Chunk("Medications", LIST_ITEM_FONT).setAction(
				PdfAction.gotoLocalPage("MedicationsItem", false)).setLocalDestination("Medications");
		medicationPara.add(medicationTop);
		// medicationPara.add("\n");

		if (medicationInfoList != null && !medicationInfoList.isEmpty()) {

			// creating table for problems
			PdfPTable table = new PdfPTable(5);
			table.setHeaderRows(1);
			table.setWidthPercentage(100f);

			// Font TABLE_HEADER_FONT = new Font(FontFamily.TIMES_ROMAN, 12,
			// Font.BOLD);
			PdfPHeaderCell tableHeader = new PdfPHeaderCell();

			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Medication", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Dosage", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("RX Number", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Order Date", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Status", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			for (MedicationInfo medicationInfo : medicationInfoList) {
				// Font TABLE_CELL_FONT = new Font(FontFamily.TIMES_ROMAN, 10);

				PdfPCell tableCell = new PdfPCell(new Phrase(medicationInfo.getMedicationName(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(medicationInfo.getDosageDescription(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(medicationInfo.getRxNumber(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(medicationInfo.getDateAdded(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(medicationInfo.getStatus(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

			}
			medicationPara.add(table);
		} else {
			medicationPara.add("No medications data.");
		}
		return medicationPara;
	}

	public static Paragraph createAllergies(ArrayList<AllergiesInfo> allergiesInfoList) {

		Paragraph allergiesPara = new Paragraph();

		Chunk allergiesTop = new Chunk("Allergies", LIST_ITEM_FONT).setAction(
				PdfAction.gotoLocalPage("AllergiesItem", false)).setLocalDestination("Allergies");
		allergiesPara.add(allergiesTop);
		allergiesPara.add("\n");

		if (allergiesInfoList != null && !allergiesInfoList.isEmpty()) {

			PdfPTable table = new PdfPTable(3);
			table.setHeaderRows(1);
			table.setWidthPercentage(100f);

			// Font TABLE_HEADER_FONT = new Font(FontFamily.TIMES_ROMAN, 12,
			// Font.BOLD);
			PdfPHeaderCell tableHeader = new PdfPHeaderCell();

			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Allergen", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Reaction", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Status", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			for (AllergiesInfo allergiesInfo : allergiesInfoList) {
				// Font TABLE_CELL_FONT = new Font(FontFamily.TIMES_ROMAN, 10);

				PdfPCell tableCell = new PdfPCell(new Phrase(allergiesInfo.getAllergen(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(allergiesInfo.getReaction(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(allergiesInfo.getStatus(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);
			}
			allergiesPara.add(table);
		} else {
			allergiesPara.add("No allergies data.");
		}
		return allergiesPara;
	}

	public static Paragraph createProcedure(ArrayList<ProcedureInfo> procedureInfoList) {

		Paragraph proceduresPara = new Paragraph();

		Chunk allergiesTop = new Chunk("Procedures", LIST_ITEM_FONT).setAction(
				PdfAction.gotoLocalPage("ProceduresItem", false)).setLocalDestination("Procedures");
		proceduresPara.add(allergiesTop);
		proceduresPara.add("\n");

		if (procedureInfoList != null && !procedureInfoList.isEmpty()) {

			PdfPTable table = new PdfPTable(3);
			table.setHeaderRows(1);
			table.setWidthPercentage(100f);

			// Font TABLE_HEADER_FONT = new Font(FontFamily.TIMES_ROMAN, 12,
			// Font.BOLD);
			PdfPHeaderCell tableHeader = new PdfPHeaderCell();

			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Procedure", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Procedure Code (SNOMED CT)", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Date", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			for (ProcedureInfo procedureInfo : procedureInfoList) {
				// Font TABLE_CELL_FONT = new Font(FontFamily.TIMES_ROMAN, 10);

				PdfPCell tableCell = new PdfPCell(new Phrase(procedureInfo.getProcedureName(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(procedureInfo.getProcedureCode(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(procedureInfo.getDateAdded(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);
			}
			proceduresPara.add(table);

		} else {
			proceduresPara.add("No procedures data.");
		}
		return proceduresPara;
	}

	public static Paragraph createProviderList(ArrayList<NsDoctorsList> nsDoctorsList) {

		Paragraph providersPara = new Paragraph();

		Chunk providersTop = new Chunk("Care Team", LIST_ITEM_FONT).setAction(
				PdfAction.gotoLocalPage("Care TeamItem", false)).setLocalDestination("Care Team");
		providersPara.add(providersTop);
		providersPara.add("\n");

		if (nsDoctorsList != null && !nsDoctorsList.isEmpty()) {

			PdfPTable table = new PdfPTable(4);
			table.setHeaderRows(1);
			table.setWidthPercentage(100f);

			// Font TABLE_HEADER_FONT = new Font(FontFamily.TIMES_ROMAN, 12,
			// Font.BOLD);
			PdfPHeaderCell tableHeader = new PdfPHeaderCell();

			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Provider Name", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Speciality", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Address", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Email Id", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			for (NsDoctorsList doctorsList : nsDoctorsList) {
				// Font TABLE_CELL_FONT = new Font(FontFamily.TIMES_ROMAN, 10);

				PdfPCell tableCell = new PdfPCell(new Phrase(doctorsList.getFirstName(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(doctorsList.getSpecialty(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(doctorsList.getAddress1(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(doctorsList.getContactEmail(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);
			}
			providersPara.add(table);

		} else {
			providersPara.add("No doctors data.");
		}
		return providersPara;
	}

	public static Paragraph createLabResult(ArrayList<LabResultInfo> labResultInfoList) {

		Paragraph labResultsPara = new Paragraph();

		Chunk labResultsTop = new Chunk("Laboratory Test/Result", LIST_ITEM_FONT).setAction(
				PdfAction.gotoLocalPage("Laboratory Test/ResultItem", false)).setLocalDestination(
				"Laboratory Test/Result");
		labResultsPara.add(labResultsTop);
		labResultsPara.add("\n");

		if (labResultInfoList != null && !labResultInfoList.isEmpty() && labResultInfoList.size() != 0) {

			PdfPTable table = new PdfPTable(4);
			table.setHeaderRows(1);
			table.setWidthPercentage(100f);

			// Font TABLE_HEADER_FONT = new Font(FontFamily.TIMES_ROMAN, 12,
			// Font.BOLD);
			PdfPHeaderCell tableHeader = new PdfPHeaderCell();

			/*
			 * tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			 * tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			 * tableHeader.setPhrase(new Phrase("Provider Name",
			 * TABLE_HEADER_FONT)); table.addCell(tableHeader);
			 */

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Lab Test", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Lab Test Code (LOINC)", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Lab Result", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Date", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			for (LabResultInfo labResultInfo : labResultInfoList) {
				// Font TABLE_CELL_FONT = new Font(FontFamily.TIMES_ROMAN, 10);

				/*
				 * PdfPCell tableCell = new PdfPCell(new
				 * Phrase(labResultInfo.getOrgName(), TABLE_CELL_FONT));
				 * tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				 * table.addCell(tableCell);
				 */

				PdfPCell tableCell = new PdfPCell(new Phrase(labResultInfo.getLabTest(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(labResultInfo.getLabTestCode(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(labResultInfo.getLabResult(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(labResultInfo.getDateAdded(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);
			}
			labResultsPara.add(table);
		} else {
			labResultsPara.add("No lab results.");
		}
		return labResultsPara;
	}

	public static Paragraph createVitalSigns(ArrayList<VitalSigns> vitalSignsList) {

		Paragraph labResultsPara = new Paragraph();

		Chunk labResultsTop = new Chunk("Vital Signs", LIST_ITEM_FONT).setAction(
				PdfAction.gotoLocalPage("Vital SignsItem", false)).setLocalDestination("Vital Signs");
		labResultsPara.add(labResultsTop);
		labResultsPara.add("\n");

		if (vitalSignsList != null && !vitalSignsList.isEmpty() && vitalSignsList.size() != 0) {

			PdfPTable table = new PdfPTable(5);
			table.setHeaderRows(1);
			table.setWidthPercentage(100f);

			// Font TABLE_HEADER_FONT = new Font(FontFamily.TIMES_ROMAN, 12,
			// Font.BOLD);
			PdfPHeaderCell tableHeader = new PdfPHeaderCell();

			/*
			 * tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			 * tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			 * tableHeader.setPhrase(new Phrase("BMI", TABLE_HEADER_FONT));
			 * table.addCell(tableHeader);
			 */
			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Systolic BP", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Diastolic BP", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			/*
			 * tableHeader = new PdfPHeaderCell();
			 * tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			 * tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			 * tableHeader.setPhrase(new Phrase("Diastolic BP Unit",
			 * TABLE_HEADER_FONT)); table.addCell(tableHeader);
			 */

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Height", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			/*
			 * tableHeader = new PdfPHeaderCell();
			 * tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			 * tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			 * tableHeader.setPhrase(new Phrase("Height Unit",
			 * TABLE_HEADER_FONT)); table.addCell(tableHeader);
			 */

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Weight", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			/*
			 * tableHeader = new PdfPHeaderCell();
			 * tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			 * tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			 * tableHeader.setPhrase(new Phrase("Weight Unit",
			 * TABLE_HEADER_FONT)); table.addCell(tableHeader);
			 */

			/*
			 * tableHeader = new PdfPHeaderCell();
			 * tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			 * tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			 * tableHeader.setPhrase(new Phrase("Systolic BP Unit",
			 * TABLE_HEADER_FONT)); table.addCell(tableHeader);
			 */

			/*
			 * tableHeader = new PdfPHeaderCell();
			 * tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			 * tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			 * tableHeader.setPhrase(new Phrase("Others", TABLE_HEADER_FONT));
			 * table.addCell(tableHeader);
			 */

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Date", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			for (VitalSigns vitalSigns : vitalSignsList) {
				PdfPCell tableCell;
				// Font TABLE_CELL_FONT = new Font(FontFamily.TIMES_ROMAN, 10);

				tableCell = new PdfPCell(new Phrase(vitalSigns.getSystolicBp() + " " + vitalSigns.getSystolicBpUnit(),
						TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(
						vitalSigns.getDiastolicBp() + " " + vitalSigns.getDiastolicBpUnit(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				if(vitalSigns.getHeightFeet().equalsIgnoreCase(VersaWorkConstant.NOT_APPLICABLE) && vitalSigns.getHeightInches().equalsIgnoreCase(VersaWorkConstant.NOT_APPLICABLE)){
					tableCell = new PdfPCell(new Phrase(vitalSigns.getHeightFeet(),
							TABLE_CELL_FONT));
				}else if(vitalSigns.getHeightFeet().equalsIgnoreCase(VersaWorkConstant.NOT_APPLICABLE) && !vitalSigns.getHeightInches().equalsIgnoreCase(VersaWorkConstant.NOT_APPLICABLE)){
					tableCell = new PdfPCell(new Phrase(vitalSigns.getHeightInches() + " in.",
							TABLE_CELL_FONT));
				}else if(!vitalSigns.getHeightFeet().equalsIgnoreCase(VersaWorkConstant.NOT_APPLICABLE) && vitalSigns.getHeightInches().equalsIgnoreCase(VersaWorkConstant.NOT_APPLICABLE)){
					tableCell = new PdfPCell(new Phrase(vitalSigns.getHeightFeet() + " ft.",
							TABLE_CELL_FONT));
				}else{
					tableCell = new PdfPCell(new Phrase(vitalSigns.getHeightFeet() + " ft. " + vitalSigns.getHeightInches() + " in.",
							TABLE_CELL_FONT));
				}
					
				
				
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				String weightUnit = vitalSigns.getWeightUnit();
				if (weightUnit == null) {
					weightUnit = "lbs";
				}
				tableCell = new PdfPCell(new Phrase(vitalSigns.getWeightLbs() + " " + weightUnit, TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(vitalSigns.getDateAdded(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				/*
				 * tableCell = new PdfPCell(new Phrase(vitalSigns.getBMI(),
				 * TABLE_CELL_FONT));
				 * tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				 * table.addCell(tableCell);
				 */

				/*
				 * tableCell = new PdfPCell(new
				 * Phrase(vitalSigns.getDiastolicBpUnit(), TABLE_CELL_FONT));
				 * tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				 * table.addCell(tableCell);
				 */

				/*
				 * tableCell = new PdfPCell(new
				 * Phrase(vitalSigns.getHeightUnit(), TABLE_CELL_FONT));
				 * tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				 * table.addCell(tableCell);
				 */

				/*
				 * tableCell = new PdfPCell(new
				 * Phrase(vitalSigns.getWeightUnit(), TABLE_CELL_FONT));
				 * tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				 * table.addCell(tableCell);
				 */

				/*
				 * tableCell = new PdfPCell(new
				 * Phrase(vitalSigns.getSystolicBpUnit(), TABLE_CELL_FONT));
				 * tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				 * table.addCell(tableCell);
				 */

				/*
				 * tableCell = new PdfPCell(new Phrase(vitalSigns.getOthers(),
				 * TABLE_CELL_FONT));
				 * tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				 * table.addCell(tableCell);
				 */

			}
			labResultsPara.add(table);
		} else {
			labResultsPara.add("No vital signs present");
		}
		return labResultsPara;
	}

	public static Paragraph createCarePlan(ArrayList<CarePlanInfo> carePlanInfoList) {

		Paragraph carePlansPara = new Paragraph();

		Chunk carePlansTop = new Chunk("Care Plans", LIST_ITEM_FONT).setAction(
				PdfAction.gotoLocalPage("Care PlansItem", false)).setLocalDestination("Care Plans");
		carePlansPara.add(carePlansTop);
		carePlansPara.add("\n");

		if (carePlanInfoList != null && !carePlanInfoList.isEmpty()) {

			PdfPTable table = new PdfPTable(2);
			table.setHeaderRows(1);
			table.setWidthPercentage(100f);

			// Font TABLE_HEADER_FONT = new Font(FontFamily.TIMES_ROMAN, 12,
			// Font.BOLD);
			PdfPHeaderCell tableHeader = new PdfPHeaderCell();

			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Goal", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Instructions", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			for (CarePlanInfo carePlanInfo : carePlanInfoList) {
				// Font TABLE_CELL_FONT = new Font(FontFamily.TIMES_ROMAN, 10);

				PdfPCell tableCell = new PdfPCell(new Phrase(carePlanInfo.getGoal(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(carePlanInfo.getInstructions(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);
			}
			carePlansPara.add(table);

		} else {
			carePlansPara.add("No care plan data.");
		}
		return carePlansPara;
	}

	public static Paragraph createInpatientData(PatientInpatientMetadataInfo inPatientInfo) {

		Paragraph inpatientDataPara = new Paragraph();

		Chunk inpatientDataTop = new Chunk("Encounter Information", LIST_ITEM_FONT).setAction(
				PdfAction.gotoLocalPage("Encounter InformationItem", false)).setLocalDestination(
				"Encounter Information");
		inpatientDataPara.add(inpatientDataTop);
		inpatientDataPara.add("\n");

		if (inPatientInfo != null) {

			PdfPTable table = new PdfPTable(4);
			table.setHeaderRows(1);
			table.setWidthPercentage(100f);

			// Font TABLE_HEADER_FONT = new Font(FontFamily.TIMES_ROMAN, 12,
			// Font.BOLD);
			PdfPHeaderCell tableHeader = new PdfPHeaderCell();

			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Admission Date", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Discharge Date", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Admission Location", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Discharge Location", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			// Font TABLE_CELL_FONT = new Font(FontFamily.TIMES_ROMAN, 10);

			PdfPCell tableCell = new PdfPCell(new Phrase(inPatientInfo.getAdmissionDate(), TABLE_CELL_FONT));
			tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
			table.addCell(tableCell);

			tableCell = new PdfPCell(new Phrase(inPatientInfo.getDischargeDate(), TABLE_CELL_FONT));
			tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
			table.addCell(tableCell);

			tableCell = new PdfPCell(new Phrase(inPatientInfo.getAdmissionLocation(), TABLE_CELL_FONT));
			tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
			table.addCell(tableCell);

			tableCell = new PdfPCell(new Phrase(inPatientInfo.getDischargeLocation(), TABLE_CELL_FONT));
			tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
			table.addCell(tableCell);

			inpatientDataPara.add(table);
		} else {
			inpatientDataPara.add("No inpatient data.");
		}
		return inpatientDataPara;
	}

	/**
	 * @param patientHospitalizationReason
	 * @return
	 */
	public static Paragraph createInpatientHospitalReasonData(PatientInpatientMetadataInfo inPatientInfo) {

		Paragraph hospitalReasonPara = new Paragraph();

		Chunk hospitalReasonTop = new Chunk("Hospitalization Reason", LIST_ITEM_FONT).setAction(
				PdfAction.gotoLocalPage("Hospitalization ReasonItem", false)).setLocalDestination(
				"Hospitalization Reason");
		hospitalReasonPara.add(hospitalReasonTop);
		hospitalReasonPara.add("\n");

		if (inPatientInfo.getHospitalizationReason() != null && !inPatientInfo.getHospitalizationReason().isEmpty()) {

			PdfPTable table = new PdfPTable(1);
			table.setHeaderRows(1);
			table.setWidthPercentage(100f);

			// Font TABLE_HEADER_FONT = new Font(FontFamily.TIMES_ROMAN, 12,
			// Font.BOLD);
			PdfPHeaderCell tableHeader = new PdfPHeaderCell();

			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Hospitalization Reason", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			// Font TABLE_CELL_FONT = new Font(FontFamily.TIMES_ROMAN, 10);

			PdfPCell tableCell = new PdfPCell(new Phrase(inPatientInfo.getHospitalizationReason(), TABLE_CELL_FONT));
			tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
			table.addCell(tableCell);

			hospitalReasonPara.add(table);
		} else {
			hospitalReasonPara.add("No hospitalization reason data.");
		}
		return hospitalReasonPara;
	}

	/**
	 * @param patientDischargeInstructions
	 * @return
	 */
	public static Paragraph createInpatientDischargeInstructionsData(PatientInpatientMetadataInfo inPatientInfo) {

		Paragraph dischargeInstructionsPara = new Paragraph();

		Chunk dischargeInstructionsTop = new Chunk("Discharge Instructions", LIST_ITEM_FONT).setAction(
				PdfAction.gotoLocalPage("Discharge InstructionsItem", false)).setLocalDestination(
				"Discharge Instructions");
		dischargeInstructionsPara.add(dischargeInstructionsTop);
		dischargeInstructionsPara.add("\n");

		if (inPatientInfo.getDischargeInstruction() != null) {

			PdfPTable table = new PdfPTable(1);
			table.setHeaderRows(1);
			table.setWidthPercentage(100f);

			// Font TABLE_HEADER_FONT = new Font(FontFamily.TIMES_ROMAN, 12,
			// Font.BOLD);
			PdfPHeaderCell tableHeader = new PdfPHeaderCell();

			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Discharge Instructions", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			// Font TABLE_CELL_FONT = new Font(FontFamily.TIMES_ROMAN, 10);

			PdfPCell tableCell = new PdfPCell(new Phrase(inPatientInfo.getDischargeInstruction(), TABLE_CELL_FONT));
			tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
			table.addCell(tableCell);

			dischargeInstructionsPara.add(table);
		} else {
			dischargeInstructionsPara.add("No discharge instructions.");
		}
		return dischargeInstructionsPara;
	}

	/**
	 * @param patientInpatientDiagnosisInfoList
	 * @return
	 */
	public static Paragraph createInpatientDiagnosisData(
			ArrayList<PatientInpatientDiagnosisInfo> patientInpatientDiagnosisInfoList) {

		Paragraph encounterDiagPara = new Paragraph();

		Chunk encounterDiagTop = new Chunk("Encounter Diagnosis", LIST_ITEM_FONT).setAction(
				PdfAction.gotoLocalPage("Encounter DiagnosisItem", false)).setLocalDestination("Encounter Diagnosis");
		encounterDiagPara.add(encounterDiagTop);
		encounterDiagPara.add("\n");

		if (patientInpatientDiagnosisInfoList != null && !patientInpatientDiagnosisInfoList.isEmpty()) {

			PdfPTable table = new PdfPTable(4);
			table.setHeaderRows(1);
			table.setWidthPercentage(100f);

			// Font TABLE_HEADER_FONT = new Font(FontFamily.TIMES_ROMAN, 12,
			// Font.BOLD);
			PdfPHeaderCell tableHeader = new PdfPHeaderCell();

			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Diagnosis Desc", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Diagnosis Code (SNOMED CT)", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Date", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Status", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			for (PatientInpatientDiagnosisInfo patientInpatientDiagnosisInfo : patientInpatientDiagnosisInfoList) {
				// Font TABLE_CELL_FONT = new Font(FontFamily.TIMES_ROMAN, 10);

				PdfPCell tableCell = new PdfPCell(new Phrase(patientInpatientDiagnosisInfo.getDiagnosisDesc(),
						TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(patientInpatientDiagnosisInfo.getDiagnosisCode(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(patientInpatientDiagnosisInfo.getDateAdded(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(patientInpatientDiagnosisInfo.getStatus(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);
			}
			encounterDiagPara.add(table);
		} else {
			encounterDiagPara.add("No diagnosis data.");
		}
		return encounterDiagPara;
	}

	/**
	 * @param patientInpatientFunctionalStatusInfoList
	 * @return
	 */
	public static Paragraph createInpatientFunctionalData(
			ArrayList<PatientInpatientFunctionalStatusInfo> patientInpatientFunctionalStatusInfoList) {

		Paragraph functionalDataPara = new Paragraph();

		Chunk functionalDataTop = new Chunk("Functional Status", LIST_ITEM_FONT).setAction(
				PdfAction.gotoLocalPage("Functional StatusItem", false)).setLocalDestination("Functional Status");
		functionalDataPara.add(functionalDataTop);
		functionalDataPara.add("\n");

		if (patientInpatientFunctionalStatusInfoList != null && !patientInpatientFunctionalStatusInfoList.isEmpty()) {

			PdfPTable table = new PdfPTable(4);
			table.setHeaderRows(1);
			table.setWidthPercentage(100f);

			// Font TABLE_HEADER_FONT = new Font(FontFamily.TIMES_ROMAN, 12,
			// Font.BOLD);
			PdfPHeaderCell tableHeader = new PdfPHeaderCell();

			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Functional Status", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Code (SNOMED-CT)", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Date", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Status", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			for (PatientInpatientFunctionalStatusInfo patientInpatientFunctionalStatusInfo : patientInpatientFunctionalStatusInfoList) {
				// Font TABLE_CELL_FONT = new Font(FontFamily.TIMES_ROMAN, 10);

				PdfPCell tableCell = new PdfPCell(new Phrase(
						patientInpatientFunctionalStatusInfo.getFunctionalStatusDesc(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(patientInpatientFunctionalStatusInfo.getFunctionalStatusCode(),
						TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(patientInpatientFunctionalStatusInfo.getDateAdded(),
						TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(patientInpatientFunctionalStatusInfo.getStatus(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);
			}
			functionalDataPara.add(table);
		} else {
			functionalDataPara.add("No functional data.");
		}
		return functionalDataPara;
	}

	/**
	 * @param patientInpatientImmunalizationInfoList
	 * @return
	 */
	public static Paragraph createInpatientImmunalizationData(
			ArrayList<PatientInpatientImmunalizationInfo> patientInpatientImmunalizationInfoList) throws Exception {

		Paragraph immunizationPara = new Paragraph();

		Chunk immunizationTop = new Chunk("Immunization", LIST_ITEM_FONT).setAction(
				PdfAction.gotoLocalPage("ImmunizationItem", false)).setLocalDestination("Immunization");
		immunizationPara.add(immunizationTop);
		immunizationPara.add("\n");

		if (patientInpatientImmunalizationInfoList != null && !patientInpatientImmunalizationInfoList.isEmpty()) {
			// PdfPTable table = new PdfPTable(6);
			PdfPTable table = new PdfPTable(4);
			table.setWidths(new float[] { 2.5f, 2.5f, 2.5f, 2.5f });
			table.setHeaderRows(1);
			table.setWidthPercentage(100f);

			// Font TABLE_HEADER_FONT = new Font(FontFamily.TIMES_ROMAN, 12,
			// Font.BOLD);
			PdfPHeaderCell tableHeader = new PdfPHeaderCell();

			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Immunization Name", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Immunization Code (CVX)", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			/*
			 * tableHeader = new PdfPHeaderCell();
			 * tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			 * tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			 * tableHeader.setPhrase(new Phrase("Route Code",
			 * TABLE_HEADER_FONT)); table.addCell(tableHeader);
			 * 
			 * tableHeader = new PdfPHeaderCell();
			 * tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			 * tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			 * tableHeader.setPhrase(new Phrase("Route Name",
			 * TABLE_HEADER_FONT)); table.addCell(tableHeader);
			 */

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Date", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			tableHeader = new PdfPHeaderCell();
			tableHeader.setBorderWidth(TABLE_BORDER_WIDTH);
			tableHeader.setHorizontalAlignment(PdfPHeaderCell.ALIGN_CENTER);
			tableHeader.setPhrase(new Phrase("Status", TABLE_HEADER_FONT));
			table.addCell(tableHeader);

			for (PatientInpatientImmunalizationInfo patientInpatientImmunalizationInfo : patientInpatientImmunalizationInfoList) {
				PdfPCell tableCell = new PdfPCell(new Phrase(
						patientInpatientImmunalizationInfo.getImmunalizationName(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(patientInpatientImmunalizationInfo.getImmunalizationCode(),
						TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				/*
				 * tableCell = new PdfPCell(new
				 * Phrase(patientInpatientImmunalizationInfo.getRouteCode(),
				 * TABLE_CELL_FONT));
				 * tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				 * table.addCell(tableCell);
				 * 
				 * tableCell = new PdfPCell(new
				 * Phrase(patientInpatientImmunalizationInfo.getRouteName(),
				 * TABLE_CELL_FONT));
				 * tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				 * table.addCell(tableCell);
				 */

				tableCell = new PdfPCell(new Phrase(patientInpatientImmunalizationInfo.getDateAdded(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);

				tableCell = new PdfPCell(new Phrase(patientInpatientImmunalizationInfo.getStatus(), TABLE_CELL_FONT));
				tableCell.setBorderWidth(TABLE_BORDER_WIDTH);
				table.addCell(tableCell);
			}
			immunizationPara.add(table);
		} else {
			immunizationPara.add("No Immunization data.");
		}
		return immunizationPara;
	}

}
