package org.shujito.ec.credit;

import org.shujito.ec.Database;
import org.shujito.ec.credit.dao.CreditDao;
import org.shujito.ec.credit.dao.CreditStatusDao;
import org.shujito.ec.credit.dao.CreditStatusTypeDao;
import org.shujito.ec.credit.model.Credit;
import org.shujito.ec.credit.model.CreditStatusType;
import org.shujito.ec.paymentType.PaymentType;
import org.shujito.ec.paymentType.PaymentTypeDao;

import java.util.List;
import java.util.Optional;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import ro.pippo.controller.Controller;
import ro.pippo.controller.GET;
import ro.pippo.controller.POST;
import ro.pippo.controller.Path;
import ro.pippo.controller.Produces;
import ro.pippo.controller.extractor.Param;

/**
 * @author shujito, 8/3/18
 */
@Slf4j
@Path("/credit")
public class CreditController extends Controller {
	private final CreditDao creditDao = Database.getJdbi().onDemand(CreditDao.class);
	private final CreditStatusDao creditStatusDao = Database.getJdbi().onDemand(CreditStatusDao.class);
	private final CreditStatusTypeDao creditStatusTypeDao = Database.getJdbi().onDemand(CreditStatusTypeDao.class);
	private final PaymentTypeDao paymentTypeDao = Database.getJdbi().onDemand(PaymentTypeDao.class);

	public CreditController() {
		this.creditDao.createTable();
		this.paymentTypeDao.createTable();
		this.creditStatusDao.createTable();
		this.creditStatusTypeDao.createTable();
		if (this.paymentTypeDao.all().size() == 0) {
			PaymentType paymentType = new PaymentType();
			paymentType.setMonths(3);
			paymentType.setInterest(0.05);
			this.paymentTypeDao.insert(paymentType);
			paymentType.setMonths(6);
			paymentType.setInterest(0.07);
			this.paymentTypeDao.insert(paymentType);
			paymentType.setMonths(9);
			paymentType.setInterest(0.12);
			this.paymentTypeDao.insert(paymentType);
		}
		if (this.creditStatusTypeDao.all().size() == 0) {
			CreditStatusType creditStatusType = new CreditStatusType();
			creditStatusType.setDescription("Aceptada");
			this.creditStatusTypeDao.insert(creditStatusType);
			creditStatusType.setDescription("Rechazada");
			this.creditStatusTypeDao.insert(creditStatusType);
		}
	}

	@GET
	@Produces(Produces.JSON)
	public List<Credit> get(@Param int userId) {
		if (userId > 0) {
			return this.creditDao.byUserId(userId);
		} else {
			return this.creditDao.all();
		}
	}

	@POST
	@Produces(Produces.JSON)
	public Credit post(
		@NonNull @Param Integer userId,
		@NonNull @Param Double amount,
		@NonNull @Param Integer months
	) {
		Optional<PaymentType> paymentTypeOptional = this.paymentTypeDao.all().stream().filter(pt -> pt.getMonths() == months).findFirst();
		if (!paymentTypeOptional.isPresent()) {
			throw new IllegalStateException("no se encontró el metodo de pago");
		}
		PaymentType paymentType = paymentTypeOptional.get();
		Credit credit = new Credit();
		credit.setUserId(userId);
		credit.setAmount(amount);
		credit.setPaymentTypeId(paymentType.getId());
		int insert = this.creditDao.insert(credit);
		return this.creditDao.find(insert);
	}
}
