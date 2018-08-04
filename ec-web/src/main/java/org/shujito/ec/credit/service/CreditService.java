package org.shujito.ec.credit.service;

import org.jdbi.v3.sqlobject.CreateSqlObject;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.transaction.Transaction;
import org.shujito.ec.credit.dao.CreditDao;
import org.shujito.ec.credit.dao.CreditStatusDao;
import org.shujito.ec.credit.dao.CreditStatusTypeDao;
import org.shujito.ec.credit.model.Credit;
import org.shujito.ec.credit.model.CreditStatusType;
import org.shujito.ec.credit.model.PendingCreditRequest;
import org.shujito.ec.paymentType.PaymentType;
import org.shujito.ec.paymentType.PaymentTypeDao;

import java.util.List;
import java.util.Optional;

import lombok.NonNull;
import ro.pippo.controller.extractor.Param;

/**
 * @author shujito, 8/4/18
 */
@UseClasspathSqlLocator
@RegisterBeanMapper(PendingCreditRequest.class)
public interface CreditService {
	@CreateSqlObject
	CreditDao creditDao();

	@CreateSqlObject
	CreditStatusDao creditStatusDao();

	@CreateSqlObject
	CreditStatusTypeDao creditStatusTypeDao();

	@CreateSqlObject
	PaymentTypeDao paymentTypeDao();

	@SqlQuery
	List<PendingCreditRequest> getPendingCreditRequests(
		@Bind int userId,
		@Bind String status
	);

	@Transaction
	default void initialize() {
		this.creditDao().createTable();
		this.paymentTypeDao().createTable();
		this.creditStatusDao().createTable();
		this.creditStatusTypeDao().createTable();
		if (this.paymentTypeDao().all().size() == 0) {
			PaymentType paymentType = new PaymentType();
			paymentType.setMonths(3);
			paymentType.setInterest(0.05);
			this.paymentTypeDao().insert(paymentType);
			paymentType.setMonths(6);
			paymentType.setInterest(0.07);
			this.paymentTypeDao().insert(paymentType);
			paymentType.setMonths(9);
			paymentType.setInterest(0.12);
			this.paymentTypeDao().insert(paymentType);
		}
		if (this.creditStatusTypeDao().all().size() == 0) {
			CreditStatusType creditStatusType = new CreditStatusType();
			creditStatusType.setDescription("Aceptada");
			this.creditStatusTypeDao().insert(creditStatusType);
			creditStatusType.setDescription("Rechazada");
			this.creditStatusTypeDao().insert(creditStatusType);
		}
	}

	@Transaction
	default Credit newCredit(
		@NonNull @Param Integer userId,
		@NonNull @Param Double amount,
		@NonNull @Param Integer months
	) {
		Optional<PaymentType> paymentTypeOptional = this.paymentTypeDao().all().stream().filter(pt -> pt.getMonths() == months).findFirst();
		if (!paymentTypeOptional.isPresent()) {
			throw new IllegalStateException("no se encontró el metodo de pago");
		}
		PaymentType paymentType = paymentTypeOptional.get();
		Credit credit = new Credit();
		credit.setUserId(userId);
		credit.setAmount(amount);
		credit.setPaymentTypeId(paymentType.getId());
		int insert = this.creditDao().insert(credit);
		return this.creditDao().find(insert);
	}

	default List<Credit> getCredit(int userId) {
		if (userId > 0) {
			return this.creditDao().byUserId(userId);
		} else {
			return this.creditDao().all();
		}
	}
}
