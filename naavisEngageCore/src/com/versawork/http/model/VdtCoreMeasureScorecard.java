/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.versawork.http.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * 
 * @author Sohaib
 */
@Entity
/*
 * @Table(name = "vdt_core_measure_scorecard")
 * 
 * @XmlRootElement
 * 
 * @NamedQueries({
 * 
 * @NamedQuery(name = "VdtCoreMeasureScorecard.findAll", query =
 * "SELECT v FROM VdtCoreMeasureScorecard v"),
 * 
 * @NamedQuery(name = "VdtCoreMeasureScorecard.findByStage", query =
 * "SELECT v FROM VdtCoreMeasureScorecard v WHERE v.stage = :stage"),
 * 
 * @NamedQuery(name = "VdtCoreMeasureScorecard.findByMeasureType", query =
 * "SELECT v FROM VdtCoreMeasureScorecard v WHERE v.measureType = :measureType"
 * ),
 * 
 * @NamedQuery(name = "VdtCoreMeasureScorecard.findByMeasureNumber", query =
 * "SELECT v FROM VdtCoreMeasureScorecard v WHERE v.measureNumber = :measureNumber"
 * ),
 * 
 * @NamedQuery(name = "VdtCoreMeasureScorecard.findByMeasureSubNumber", query =
 * "SELECT v FROM VdtCoreMeasureScorecard v WHERE v.measureSubNumber = :measureSubNumber"
 * ),
 * 
 * @NamedQuery(name = "VdtCoreMeasureScorecard.findByMeasure", query =
 * "SELECT v FROM VdtCoreMeasureScorecard v WHERE v.measure = :measure"),
 * 
 * @NamedQuery(name = "VdtCoreMeasureScorecard.findByMeasureDesc", query =
 * "SELECT v FROM VdtCoreMeasureScorecard v WHERE v.measureDesc = :measureDesc"
 * ),
 * 
 * @NamedQuery(name = "VdtCoreMeasureScorecard.findByMeasureGoal", query =
 * "SELECT v FROM VdtCoreMeasureScorecard v WHERE v.measureGoal = :measureGoal"
 * ),
 * 
 * @NamedQuery(name = "VdtCoreMeasureScorecard.findByMeasureGoal2", query =
 * "SELECT v FROM VdtCoreMeasureScorecard v WHERE v.measureGoal2 = :measureGoal2"
 * ),
 * 
 * @NamedQuery(name = "VdtCoreMeasureScorecard.findByPopulateDenominator", query
 * =
 * "SELECT v FROM VdtCoreMeasureScorecard v WHERE v.populateDenominator = :populateDenominator"
 * ),
 * 
 * @NamedQuery(name = "VdtCoreMeasureScorecard.findByPopulateNumerator", query =
 * "SELECT v FROM VdtCoreMeasureScorecard v WHERE v.populateNumerator = :populateNumerator"
 * ),
 * 
 * @NamedQuery(name = "VdtCoreMeasureScorecard.findByNumeratorRecorded", query =
 * "SELECT v FROM VdtCoreMeasureScorecard v WHERE v.numeratorRecorded = :numeratorRecorded"
 * ),
 * 
 * @NamedQuery(name = "VdtCoreMeasureScorecard.findByPercentage", query =
 * "SELECT v FROM VdtCoreMeasureScorecard v WHERE v.percentage = :percentage"),
 * 
 * @NamedQuery(name = "VdtCoreMeasureScorecard.findByPercentageInt", query =
 * "SELECT v FROM VdtCoreMeasureScorecard v WHERE v.percentageInt = :percentageInt"
 * ),
 * 
 * @NamedQuery(name = "VdtCoreMeasureScorecard.findByIsStg1GoalMet", query =
 * "SELECT v FROM VdtCoreMeasureScorecard v WHERE v.isStg1GoalMet = :isStg1GoalMet"
 * ),
 * 
 * @NamedQuery(name = "VdtCoreMeasureScorecard.findByIsStg2GoalMet", query =
 * "SELECT v FROM VdtCoreMeasureScorecard v WHERE v.isStg2GoalMet = :isStg2GoalMet"
 * )})
 */public class VdtCoreMeasureScorecard implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	// Annotated all columns with ID since no primary key is present, hence
	// combining all columns to make composite key
	@Basic(optional = false)
	@Column(name = "stage")
	private Integer stage;
	@Id
	@Basic(optional = false)
	@Column(name = "measure_type")
	private String measureType;
	@Id
	@Basic(optional = false)
	@Column(name = "measure_number")
	private Integer measureNumber;
	@Id
	@Basic(optional = false)
	@Column(name = "measure_sub_number")
	private Integer measureSubNumber;
	@Id
	@Basic(optional = false)
	@Column(name = "measure")
	private String measure;
	@Id
	@Basic(optional = false)
	@Column(name = "measure_desc")
	private String measureDesc;
	@Id
	@Basic(optional = false)
	@Column(name = "measure_goal")
	private Integer measureGoal;
	@Id
	@Basic(optional = false)
	@Column(name = "measure_goal2")
	private Integer measureGoal2;
	@Id
	@Basic(optional = false)
	@Column(name = "populate_denominator")
	private Integer populateDenominator;
	@Id
	@Basic(optional = false)
	@Column(name = "populate_numerator")
	private Integer populateNumerator;
	@Id
	@Basic(optional = false)
	@Column(name = "numerator_recorded")
	private Integer numeratorRecorded;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Id
	@Basic(optional = false)
	@Column(name = "percentage")
	private BigDecimal percentage;
	@Id
	@Basic(optional = false)
	@Column(name = "percentage_int")
	private Integer percentageInt;
	@Id
	@Basic(optional = false)
	@Column(name = "is_stg_1_goal_met")
	private Integer isStg1GoalMet;
	@Id
	@Basic(optional = false)
	@Column(name = "is_stg_2_goal_met")
	private Integer isStg2GoalMet;

	public VdtCoreMeasureScorecard() {
	}

	public VdtCoreMeasureScorecard(Integer stage) {
		this.stage = stage;
	}

	public VdtCoreMeasureScorecard(Integer stage, String measureType, Integer measureNumber, Integer measureSubNumber,
			String measure, String measureDesc, Integer measureGoal, Integer measureGoal2, Integer populateDenominator,
			Integer populateNumerator, Integer numeratorRecorded, BigDecimal percentage, Integer percentageInt,
			Integer isStg1GoalMet, Integer isStg2GoalMet) {
		this.stage = stage;
		this.measureType = measureType;
		this.measureNumber = measureNumber;
		this.measureSubNumber = measureSubNumber;
		this.measure = measure;
		this.measureDesc = measureDesc;
		this.measureGoal = measureGoal;
		this.measureGoal2 = measureGoal2;
		this.populateDenominator = populateDenominator;
		this.populateNumerator = populateNumerator;
		this.numeratorRecorded = numeratorRecorded;
		this.percentage = percentage;
		this.percentageInt = percentageInt;
		this.isStg1GoalMet = isStg1GoalMet;
		this.isStg2GoalMet = isStg2GoalMet;
	}

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	public String getMeasureType() {
		return measureType;
	}

	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}

	public Integer getMeasureNumber() {
		return measureNumber;
	}

	public void setMeasureNumber(Integer measureNumber) {
		this.measureNumber = measureNumber;
	}

	public Integer getMeasureSubNumber() {
		return measureSubNumber;
	}

	public void setMeasureSubNumber(Integer measureSubNumber) {
		this.measureSubNumber = measureSubNumber;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getMeasureDesc() {
		return measureDesc;
	}

	public void setMeasureDesc(String measureDesc) {
		this.measureDesc = measureDesc;
	}

	public Integer getMeasureGoal() {
		return measureGoal;
	}

	public void setMeasureGoal(Integer measureGoal) {
		this.measureGoal = measureGoal;
	}

	public Integer getMeasureGoal2() {
		return measureGoal2;
	}

	public void setMeasureGoal2(Integer measureGoal2) {
		this.measureGoal2 = measureGoal2;
	}

	public Integer getPopulateDenominator() {
		return populateDenominator;
	}

	public void setPopulateDenominator(Integer populateDenominator) {
		this.populateDenominator = populateDenominator;
	}

	public Integer getPopulateNumerator() {
		return populateNumerator;
	}

	public void setPopulateNumerator(Integer populateNumerator) {
		this.populateNumerator = populateNumerator;
	}

	public Integer getNumeratorRecorded() {
		return numeratorRecorded;
	}

	public void setNumeratorRecorded(Integer numeratorRecorded) {
		this.numeratorRecorded = numeratorRecorded;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public Integer getPercentageInt() {
		return percentageInt;
	}

	public void setPercentageInt(Integer percentageInt) {
		this.percentageInt = percentageInt;
	}

	public Integer getIsStg1GoalMet() {
		return isStg1GoalMet;
	}

	public void setIsStg1GoalMet(Integer isStg1GoalMet) {
		this.isStg1GoalMet = isStg1GoalMet;
	}

	public Integer getIsStg2GoalMet() {
		return isStg2GoalMet;
	}

	public void setIsStg2GoalMet(Integer isStg2GoalMet) {
		this.isStg2GoalMet = isStg2GoalMet;
	}

	@Override
	public int hashCode() {
		Integer hash = 0;
		hash += (stage != null ? stage.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof VdtCoreMeasureScorecard)) {
			return false;
		}
		VdtCoreMeasureScorecard other = (VdtCoreMeasureScorecard) object;
		if ((this.stage == null && other.stage != null) || (this.stage != null && !this.stage.equals(other.stage))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "javaapplication2.VdtCoreMeasureScorecard[ stage=" + stage + " ]";
	}

}
