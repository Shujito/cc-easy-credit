package org.shujito.ec.credit;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 * @author shujito, 8/3/18
 */
@UseClasspathSqlLocator
@RegisterBeanMapper(CreditStatusType.class)
public interface CreditStatusTypeDao {
	@SqlUpdate
	void createTable();

	@SqlUpdate
	@GetGeneratedKeys
	int insert(@BindBean CreditStatusType newCreditStatusType);

	@SqlQuery
	CreditStatusType find(@Bind("id") int id);
}
