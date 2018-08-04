package org.shujito.ec.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ro.pippo.core.Application;
import ro.pippo.core.ContentTypeEngine;
import ro.pippo.core.HttpConstants;

/**
 * @author shujito, 8/3/18
 */
public class GsonEngine implements ContentTypeEngine {
	private final Gson gson;

	public GsonEngine() {
		this.gson = new GsonBuilder()
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
			.excludeFieldsWithoutExposeAnnotation()
			.create();
	}

	@Override
	public void init(Application application) {
	}

	@Override
	public String getContentType() {
		return HttpConstants.ContentType.APPLICATION_JSON;
	}

	@Override
	public String toString(Object object) {
		return this.gson.toJson(object);
	}

	@Override
	public <T> T fromString(String content, Class<T> classOfT) {
		return this.gson.fromJson(content, classOfT);
	}
}
