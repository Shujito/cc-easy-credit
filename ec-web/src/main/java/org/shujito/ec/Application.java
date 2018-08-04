package org.shujito.ec;

import ro.pippo.core.Pippo;

public class Application {
	public static void main(String[] args) {
		Pippo pippo = new Pippo(new EasyCreditApplication());
		pippo.getServer().getSettings().port(7070);
		pippo.getServer().getSettings().host(null);
		pippo.start();
	}
}
