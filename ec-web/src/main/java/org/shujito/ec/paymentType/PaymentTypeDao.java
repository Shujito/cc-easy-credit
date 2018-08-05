package org.shujito.ec.paymentType;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

/**
 * @author shujito, 8/3/18
 */
@UseClasspathSqlLocator
@RegisterBeanMapper(PaymentType.class)
public interface PaymentTypeDao {
	@SqlUpdate
	int createTable();

	@SqlQuery
	List<PaymentType> all();

	@SqlUpdate
	@GetGeneratedKeys
	int insert(@BindBean PaymentType paymentTypes);
}
