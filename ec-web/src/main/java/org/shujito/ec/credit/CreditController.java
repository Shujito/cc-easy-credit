package org.shujito.ec.credit;

import org.shujito.ec.Database;
import org.shujito.ec.credit.model.Credit;
import org.shujito.ec.credit.model.PendingCreditRequest;
import org.shujito.ec.credit.service.CreditService;

import java.util.List;

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
	private final CreditService creditService = Database.getJdbi().onDemand(CreditService.class);

	public CreditController() {
		this.creditService.initialize();
	}

	@GET
	@Produces(Produces.JSON)
	public List<PendingCreditRequest> get(
		@Param int userId, @Param String status
	) {
		return this.creditService.getPendingCreditRequests(userId, status);
	}

	@POST
	@Produces(Produces.JSON)
	public Credit post(
		@NonNull @Param Integer userId,
		@NonNull @Param Double amount,
		@NonNull @Param Integer months
	) {
		return this.creditService.newCredit(userId, amount, months);
	}
}
