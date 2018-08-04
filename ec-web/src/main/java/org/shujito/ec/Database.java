package org.shujito.ec;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
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
	}
}
