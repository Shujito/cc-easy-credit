package org.shujito.ec;

import org.shujito.ec.credit.dao.CreditDao;
import org.shujito.ec.credit.dao.CreditStatusDao;
import org.shujito.ec.credit.dao.CreditStatusTypeDao;
import org.shujito.ec.credit.model.Credit;
import org.shujito.ec.credit.model.CreditStatus;
import org.shujito.ec.credit.model.CreditStatusType;

import java.security.SecureRandom;
import java.util.List;
import java.util.TimerTask;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shujito, 8/4/18
 */
@Slf4j
public class CreditRequestTimerTask extends TimerTask {
	@Override
	public void run() {
		try {
			if (this.execute()) {
				this.cancel();
			}
		} catch (Exception ex) {
			log.error(ex.toString());
			ex.printStackTrace();
		}
	}

	private boolean execute() {
		List<Credit> credits = Database.getJdbi().withExtension(CreditDao.class, CreditDao::all);
		List<CreditStatusType> creditStatusTypes = Database.getJdbi().withExtension(CreditStatusTypeDao.class, CreditStatusTypeDao::all);
		SecureRandom secureRandom = new SecureRandom();
		if (credits.size() == 0) {
			log.info("no credit requests available");
			return false;
		}
		if (creditStatusTypes.size() == 0) {
			log.info("no credit status types available");
			return false;
		}
		int randomCreditIndex = secureRandom.nextInt(credits.size());
		int randomCreditStatusIndex = secureRandom.nextInt(creditStatusTypes.size());
		Credit credit = credits.get(randomCreditIndex);
		CreditStatusType creditStatusType = creditStatusTypes.get(randomCreditStatusIndex);
		CreditStatus creditStatus = new CreditStatus();
		creditStatus.setCreditId(credit.getId());
		creditStatus.setCreditStatusTypeId(creditStatusType.getId());
		Database.getJdbi().withExtension(CreditStatusDao.class, dao -> dao.insert(creditStatus));
		log.info("credit status updated: {}", creditStatus.toString());
		return false;
	}
}
