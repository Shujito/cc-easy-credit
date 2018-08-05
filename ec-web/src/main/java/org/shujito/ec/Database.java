package org.shujito.ec;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.shujito.ec.credit.dao.CreditDao;
import org.shujito.ec.credit.dao.CreditStatusDao;
import org.shujito.ec.credit.dao.CreditStatusTypeDao;
import org.shujito.ec.paymentType.PaymentTypeDao;
import org.shujito.ec.user.UserDao;
import org.sqlite.SQLiteConfig;

import lombok.Getter;

/**
 * @author shujito, 8/3/18
 */
public class Database {
	@Getter
	private static Jdbi jdbi;

	static {
		SQLiteConfig sqLiteConfig = new SQLiteConfig();
		sqLiteConfig.enforceForeignKeys(true);
		sqLiteConfig.setReadUncommited(true);
		jdbi = Jdbi.create("jdbc:sqlite:ec.db3", sqLiteConfig.toProperties());
		jdbi.installPlugin(new SqlObjectPlugin());
		// create tables
		jdbi.withExtension(UserDao.class, UserDao::createTable);
		jdbi.withExtension(CreditDao.class, CreditDao::createTable);
		jdbi.withExtension(PaymentTypeDao.class, PaymentTypeDao::createTable);
		jdbi.withExtension(CreditStatusDao.class, CreditStatusDao::createTable);
		jdbi.withExtension(CreditStatusTypeDao.class, CreditStatusTypeDao::createTable);
	}
}
