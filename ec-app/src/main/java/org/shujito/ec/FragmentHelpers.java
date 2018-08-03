package org.shujito.ec;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Utilities for Fragments
 *
 * @author Alberto Ramos
 */
@SuppressWarnings("unused")
public final class FragmentHelpers {
	private FragmentHelpers() {
		throw new RuntimeException("don't!");
	}

	/**
	 * Performs a fragment transaction.
	 *
	 * @param fragmentManager to work with.
	 * @param container       id to be replaced.
	 * @param fragment        to be shown.
	 * @param addToBackStack  whether or not to add to the back stack. Defaults to true.
	 */
	public static void show(FragmentManager fragmentManager, int container, Fragment fragment, boolean addToBackStack) {
		String fragmentName = fragment.getClass().getSimpleName();
		FragmentTransaction fragtrans = fragmentManager.beginTransaction();
		fragtrans.replace(container, fragment, fragmentName);
		fragtrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		if (addToBackStack) {
			fragtrans.addToBackStack(fragmentName);
		}
		fragtrans.commit();
	}

	/**
	 * Performs a fragment transaction.
	 *
	 * @param fragmentManager to work with.
	 * @param fragment        to be shown.
	 */
	public static void show(FragmentManager fragmentManager, Fragment fragment) {
		show(fragmentManager, android.R.id.content, fragment, true);
	}

	/**
	 * Performs a fragment transaction.
	 *
	 * @param fragmentManager to work with.
	 * @param container       to be replaced.
	 * @param fragment        to be shown.
	 */
	public static void show(FragmentManager fragmentManager, int container, Fragment fragment) {
		show(fragmentManager, container, fragment, true);
	}

	/**
	 * Performs a fragment transaction.
	 *
	 * @param fragmentManager to work with.
	 * @param fragment        to be shown
	 * @param addToBackStack  whether or not to add to the back stack. Defaults to true.
	 */
	public static void show(FragmentManager fragmentManager, Fragment fragment, boolean addToBackStack) {
		show(fragmentManager, android.R.id.content, fragment, addToBackStack);
	}

	/**
	 * Finds a fragment by its current id
	 *
	 * @param fragmentManager to query the fragment from
	 * @param id              of the fragment
	 * @return a fragment
	 */
	public static Fragment find(FragmentManager fragmentManager, int id) {
		return fragmentManager.findFragmentById(id);
	}

	/**
	 * Finds a fragment by its tag
	 *
	 * @param fragmentManager to query the fragment from
	 * @param tag             of the fragment
	 * @return a fragment
	 */
	public static Fragment find(FragmentManager fragmentManager, String tag) {
		return fragmentManager.findFragmentByTag(tag);
	}

	@SuppressWarnings("unchecked")
	public static <T extends Fragment> T find(FragmentManager fm, int container, int id) {
		String fragmentName = String.format("android:switcher:%s:%s", container, id);
		return (T) fm.findFragmentByTag(fragmentName);
	}
}
