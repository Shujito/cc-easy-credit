package org.shujito.ec;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import lombok.Getter;

/**
 * @author shujito, 8/3/18
 */
public class Database {
	@Getter
	private static final Jdbi jdbi;

	static {
		jdbi = Jdbi.create("jdbc:sqlite::memory:");
		jdbi.installPlugin(new SqlObjectPlugin());
	}
}
