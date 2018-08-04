package org.shujito.ec.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author shujito, 8/3/18
 */
public class GsonUtils {
	public static Gson create() {
		return new GsonBuilder()
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
			.excludeFieldsWithoutExposeAnnotation()
			.create();
	}
}
