package org.shujito.ec.credit.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.shujito.ec.credit.model.CreditStatus;

/**
 * @author shujito, 8/3/18
 */
@UseClasspathSqlLocator
@RegisterBeanMapper(CreditStatus.class)
public interface CreditStatusDao {
	@SqlUpdate
	int createTable();

	@SqlUpdate
	int insert(@BindBean CreditStatus newCreditStatus);

	@SqlQuery
	CreditStatus find(@Bind("id") int id);
}
