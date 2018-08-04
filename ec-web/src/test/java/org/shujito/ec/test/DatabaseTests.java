package org.shujito.ec.test;

import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.junit.Test;
import org.shujito.ec.Database;
import org.shujito.ec.credit.Credit;
import org.shujito.ec.credit.CreditDao;
import org.shujito.ec.credit.CreditStatus;
import org.shujito.ec.credit.CreditStatusDao;
import org.shujito.ec.credit.CreditStatusType;
import org.shujito.ec.credit.CreditStatusTypeDao;
import org.shujito.ec.paymentType.PaymentType;
import org.shujito.ec.paymentType.PaymentTypeDao;
import org.shujito.ec.user.User;
import org.shujito.ec.user.UserDao;
import org.sqlite.SQLiteException;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author shujito, 8/3/18
 */
public class DatabaseTests {
	static final String USERNAME = "alberto";
	static final int AGE = 27;

	@Test
	public void testUser() {
		User user = Database.getJdbi().withExtension(UserDao.class, dao -> {
			dao.createTable();
			User newUser = new User();
			newUser.setUsername(USERNAME);
			newUser.setAge(AGE);
			int id = dao.insert(newUser);
			assertTrue("se debió haber insertado el usuario", id > 0);
			return dao.find(id);
		});
		assertNotNull("el objeto usuario no debe ser nulo", user);
		assertEquals(String.format("el nombre de usuario debe ser %s", USERNAME), USERNAME, user.getUsername());
		assertEquals(String.format("la edad debe ser %d", AGE), AGE, user.getAge());
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
				fail("no se debió haber insertado el registro");
			} catch (Exception e) {
				assertTrue(String.format("el tipo de la excepción debió ser %s",
					UnableToExecuteStatementException.class.getName()),
					e instanceof UnableToExecuteStatementException);
				assertNotNull("la causa de la excepción no debió ser nula", e.getCause());
				assertTrue(String.format("el tipo de la causa debió ser %s",
					SQLiteException.class.getName()),
					e.getCause() instanceof SQLiteException
				);
			}
			user.setAge(80);
			try {
				dao.insert(user);
				fail("no se debió haber insertado el registro");
			} catch (Exception e) {
				assertTrue(String.format("el tipo de la excepción debió ser %s",
					UnableToExecuteStatementException.class.getName()),
					e instanceof UnableToExecuteStatementException);
				assertNotNull("la causa de la excepción no debió ser nula", e.getCause());
				assertTrue(String.format("el tipo de la causa debió ser %s",
					SQLiteException.class.getName()),
					e.getCause() instanceof SQLiteException
				);
			}
			return dao.all();
		});
		assertEquals("no se debieron haber insertado usuarios", users.size(), 0);
	}

	@Test
	public void testUniquePaymentTypes() {
		List<PaymentType> paymentTypes = Database.getJdbi().withExtension(PaymentTypeDao.class, dao -> {
			dao.createTable();
			PaymentType newPayType = new PaymentType();
			newPayType.setMonths(3);
			newPayType.setInterest(0.05);
			assertTrue("se debió haber insertado el primer registro", dao.insert(newPayType) > 0);
			try {
				dao.insert(newPayType);
			} catch (Exception e) {
				assertTrue("no se debió haber insertado el segundo registro", e instanceof UnableToExecuteStatementException);
			}
			return dao.all();
		});
		assertEquals("debe haber solo un tipo de pago insertado", paymentTypes.size(), 1);
	}

	@Test
	public void testRequest() {
		User user = Database.getJdbi().withExtension(UserDao.class, dao -> {
			dao.createTable();
			User newUser = new User();
			newUser.setUsername(USERNAME);
			newUser.setAge(AGE);
			int id = dao.insert(newUser);
			assertTrue("se debió haber insertado el usuario", id > 0);
			return dao.find(id);
		});
		assertNotNull("el objeto usuario no debe ser nulo", user);
		assertEquals(String.format("el nombre de usuario debe ser %s", USERNAME), USERNAME, user.getUsername());
		assertEquals(String.format("la edad debe ser %d", AGE), AGE, user.getAge());
		int paymentTypeId = Database.getJdbi().withExtension(PaymentTypeDao.class, dao -> {
			dao.createTable();
			PaymentType newPayType = new PaymentType();
			newPayType.setMonths(3);
			newPayType.setInterest(0.05);
			return dao.insert(newPayType);
		});
		assertTrue("se debió haber insertado el tipo de pago", paymentTypeId > 0);
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
		assertEquals(credit.getUserId(), user.getId());
		assertEquals(credit.getAmount(), 4000.0, 0);
		assertTrue(credit.isApproved());
		assertEquals(credit.getPaymentTypeId(), paymentTypeId);
	}

	@Test
	public void testCreditStatus() {
		User user = Database.getJdbi().withExtension(UserDao.class, dao -> {
			dao.createTable();
			User newUser = new User();
			newUser.setUsername(USERNAME);
			newUser.setAge(AGE);
			int id = dao.insert(newUser);
			assertTrue("se debió haber insertado el usuario", id > 0);
			return dao.find(id);
		});
		assertNotNull("el objeto usuario no debe ser nulo", user);
		assertEquals(String.format("el nombre de usuario debe ser %s", USERNAME), USERNAME, user.getUsername());
		assertEquals(String.format("la edad debe ser %d", AGE), AGE, user.getAge());
		int paymentTypeId = Database.getJdbi().withExtension(PaymentTypeDao.class, dao -> {
			dao.createTable();
			PaymentType newPayType = new PaymentType();
			newPayType.setMonths(3);
			newPayType.setInterest(0.05);
			return dao.insert(newPayType);
		});
		assertTrue("se debió haber insertado el tipo de pago", paymentTypeId > 0);
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
		assertEquals(credit.getUserId(), user.getId());
		assertEquals(credit.getAmount(), 4000.0, 0);
		assertTrue(credit.isApproved());
		assertEquals(credit.getPaymentTypeId(), paymentTypeId);
		CreditStatusType creditStatusType = Database.getJdbi().withExtension(CreditStatusTypeDao.class, dao -> {
			dao.createTable();
			CreditStatusType newCreditStatusType = new CreditStatusType();
			newCreditStatusType.setDescription("Aceptada");
			dao.insert(newCreditStatusType);
			newCreditStatusType.setDescription("Rechazada");
			int id = dao.insert(newCreditStatusType);
			return dao.find(id);
		});
		assertNotNull(creditStatusType);
		assertTrue(creditStatusType.getId() > 0);
		assertEquals(creditStatusType.getDescription(), "Rechazada");
		CreditStatus creditStatus = Database.getJdbi().withExtension(CreditStatusDao.class, dao -> {
			dao.createTable();
			CreditStatus newCreditStatus = new CreditStatus();
			newCreditStatus.setCreditId(credit.getId());
			newCreditStatus.setCreditStatusId(creditStatusType.getId());
			int id = dao.insert(newCreditStatus);
			return dao.find(id);
		});
		assertNotNull(creditStatus);
		assertEquals(credit.getId(), creditStatus.getCreditId());
		assertEquals(creditStatusType.getId(), creditStatus.getCreditStatusId());
	}
}
