package com.versawork.http.dao;

import java.io.Serializable;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentityGenerator;

/**
 * @author Sohaib
 * 
 */
public class UseExistingOrGenerateIdGenerator extends IdentityGenerator {

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		try {
			session.connection().createStatement().executeUpdate("SET IDENTITY_INSERT Account ON");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Serializable id = session.getEntityPersister(null, object).getClassMetadata().getIdentifier(object, session);
		return id != null ? id : super.generate(session, object);
	}

}