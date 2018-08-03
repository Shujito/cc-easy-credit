package org.shujito.ec.test;

import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.junit.Test;
import org.shujito.ec.Database;
import org.shujito.ec.credit.Credit;
import org.shujito.ec.credit.CreditDao;
import org.shujito.ec.paymentType.PaymentType;
import org.shujito.ec.paymentType.PaymentTypeDao;
import org.shujito.ec.user.User;
import org.shujito.ec.user.UserDao;
import org.sqlite.SQLiteException;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author shujito, 8/3/18
 */
public class DatabaseTests {
	static final String USERNAME = "shujito";
	static final int AGE = 27;

	@Test
	public void testUser() {
		User user = Database.getJdbi().withExtension(UserDao.class, dao -> {
			dao.createTable();
			User newUser = new User();
			newUser.setUsername(USERNAME);
			newUser.setAge(AGE);
			int id = dao.insert(newUser);
			return dao.find(id);
		});
		assertNotNull(user);
		assertTrue(USERNAME.equals(user.getUsername()));
		assertTrue(AGE == user.getAge());
	}

	@Test
	public void testUserAgeRange() {
		List<User> users = Database.getJdbi().withExtension(UserDao.class, dao -> {
			dao.createTable();
			User user = new User();
			user.setUsername("maria");
			user.setAge(15);
			try {
				dao.insert(user);
			} catch (Exception e) {
				assertTrue(e instanceof UnableToExecuteStatementException);
				assertNotNull(e.getCause());
				assertTrue(e.getCause() instanceof SQLiteException);
			}
			user.setAge(80);
			try {
				dao.insert(user);
			} catch (Exception e) {
				assertTrue(e instanceof UnableToExecuteStatementException);
				assertNotNull(e.getCause());
				assertTrue(e.getCause() instanceof SQLiteException);
			}
			return dao.all();
		});
		assertTrue(users.size() == 0);
	}

	@Test
	public void testRequest() {
		User user = Database.getJdbi().withExtension(UserDao.class, dao -> {
			dao.createTable();
			User newUser = new User();
			newUser.setAge(AGE);
			newUser.setUsername(USERNAME);
			int id = dao.insert(newUser);
			return dao.find(id);
		});
		assertNotNull(user);
		assertTrue(USERNAME.equals(user.getUsername()));
		assertTrue(AGE == user.getAge());
		int paymentTypeId = Database.getJdbi().withExtension(PaymentTypeDao.class, dao -> {
			dao.createTable();
			PaymentType newPayType = new PaymentType();
			newPayType.setMonths(3);
			newPayType.setInterest(0.05);
			return dao.insert(newPayType);
		});
		assertTrue(paymentTypeId > 0);
		Credit credit = Database.getJdbi().withExtension(CreditDao.class, dao -> {
			dao.createTable();
			Credit newCredit = new Credit();
			newCredit.setUserId(user.getId());
			newCredit.setAmount(4000);
			newCredit.setApproved(true);
			newCredit.setPaymentTypeId(paymentTypeId);
			int id = dao.insert(newCredit);
			return dao.find(id);
		});
		assertNotNull(credit);
		assertTrue(credit.getUserId() == user.getId());
		assertTrue(credit.getAmount() == 4000);
		assertTrue(credit.isApproved());
		assertTrue(credit.getPaymentTypeId() == paymentTypeId);
	}
}
