package org.shujito.ec;

import org.shujito.ec.credit.CreditController;
import org.shujito.ec.user.UserController;
import org.shujito.ec.util.CustomErrorHandler;
import org.shujito.ec.util.GsonEngine;

import lombok.extern.slf4j.Slf4j;
import ro.pippo.controller.ControllerApplication;
import ro.pippo.core.route.TrailingSlashHandler;

/**
 * @author shujito, 8/3/18
 */
@Slf4j
public class EasyCreditApplication extends ControllerApplication {
	@Override
	@SuppressWarnings("unchecked")
	protected void onInit() {
		ANY("/.*", rc -> {
			log.debug("{} '{}'", rc.getRequestMethod(), rc.getRequestUri());
			rc.next();
		});
		ANY("/.*", new TrailingSlashHandler(false));
		this.registerContentTypeEngine(GsonEngine.class);
		this.addControllers(UserController.class);
		this.addControllers(CreditController.class);
		this.setErrorHandler(new CustomErrorHandler(this));
	}
}
