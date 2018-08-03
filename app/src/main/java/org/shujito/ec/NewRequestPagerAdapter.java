package org.shujito.ec;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.annimon.stream.function.Supplier;

/**
 * @author shujito, 7/28/18
 */
public class NewRequestPagerAdapter extends FragmentPagerAdapter {
	private enum Pages {
		Amount(NewRequestAmountFragment::new),
		Age(NewRequestAgeFragment::new),
		HasCreditCard(NewRequestHasCreditCardFragment::new),
		Payments(NewRequestPaymentsFragment::new),
		//
		;
		public final Supplier<Fragment> supplier;

		Pages(Supplier<Fragment> supplier) {
			this.supplier = supplier;
		}
	}

	public NewRequestPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return Pages.values()[position].supplier.get();
	}

	@Override
	public int getCount() {
		return Pages.values().length;
	}
}
