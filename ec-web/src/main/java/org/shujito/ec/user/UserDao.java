package org.shujito.ec.user;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
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
@RegisterBeanMapper(User.class)
public interface UserDao {
	@SqlUpdate
	void createTable();

	@SqlQuery
	List<User> all();

	@SqlUpdate
	@GetGeneratedKeys
	int insert(@BindBean User user);

	@SqlQuery
	User find(@Bind("id") int id);

	@SqlQuery
	User findByUsername(String username);
}
