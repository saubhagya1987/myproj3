/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.versawork.http.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author User20
 */
@Entity
@Table(name = "notification_type")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "NotificationType.findAll", query = "SELECT n FROM NotificationType n"),
		@NamedQuery(name = "NotificationType.findByNotificationTypeId", query = "SELECT n FROM NotificationType n WHERE n.notificationTypeId = :notificationTypeId"),
		@NamedQuery(name = "NotificationType.findByNotificationType", query = "SELECT n FROM NotificationType n WHERE n.notificationType = :notificationType"),
		@NamedQuery(name = "NotificationType.findByNotificationTypeDescription", query = "SELECT n FROM NotificationType n WHERE n.notificationTypeDescription = :notificationTypeDescription") })
public class NotificationType implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "notification_type_id")
	private Integer notificationTypeId;
	@Basic(optional = false)
	@Column(name = "notification_type")
	private String notificationType;
	@Column(name = "notification_type_description")
	private String notificationTypeDescription;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "notificationTypeId")
	private List<AccountNotificationHistory> accountNotificationHistoryList;

	public NotificationType() {
	}

	public NotificationType(Integer notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}

	public NotificationType(Integer notificationTypeId, String notificationType) {
		this.notificationTypeId = notificationTypeId;
		this.notificationType = notificationType;
	}

	public Integer getNotificationTypeId() {
		return notificationTypeId;
	}

	public void setNotificationTypeId(Integer notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getNotificationTypeDescription() {
		return notificationTypeDescription;
	}

	public void setNotificationTypeDescription(String notificationTypeDescription) {
		this.notificationTypeDescription = notificationTypeDescription;
	}

	@XmlTransient
	public List<AccountNotificationHistory> getAccountNotificationHistoryList() {
		return accountNotificationHistoryList;
	}

	public void setAccountNotificationHistoryList(List<AccountNotificationHistory> accountNotificationHistoryList) {
		this.accountNotificationHistoryList = accountNotificationHistoryList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (notificationTypeId != null ? notificationTypeId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof NotificationType)) {
			return false;
		}
		NotificationType other = (NotificationType) object;
		if ((this.notificationTypeId == null && other.notificationTypeId != null)
				|| (this.notificationTypeId != null && !this.notificationTypeId.equals(other.notificationTypeId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "javaapplication2.NotificationType[ notificationTypeId=" + notificationTypeId + " ]";
	}

}