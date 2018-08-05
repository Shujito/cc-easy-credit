package org.shujito.ec.network;

import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.google.gson.Gson;

import org.shujito.ec.BuildConfig;
import org.shujito.ec.util.GsonUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author shujito, 8/3/18
 */
public class EasyCreditApi {
	private static final String TAG = EasyCreditApi.class.getSimpleName();
	private static final String BASE_URL;
	public static final Uri URI;
	public static final Gson GSON;
	public static final Api INSTANCE;

	static {
		String hardware = Build.HARDWARE;
		if (hardware.matches("(goldfish|ranchu)")) {
			BASE_URL = "http://10.0.2.2:7070/";
		} else {
			BASE_URL = BuildConfig.API_BASE_URL;
		}
		URI = Uri.parse(BASE_URL);
		GSON = GsonUtils.create();
		INSTANCE = new Retrofit.Builder()
			.client(new OkHttpClient.Builder()
				.connectTimeout(2, TimeUnit.MINUTES)
				.writeTimeout(10, TimeUnit.MINUTES)
				.readTimeout(5, TimeUnit.MINUTES)
				.addInterceptor(new HttpLoggingInterceptor(message -> Log.w(TAG, message))
					.setLevel(HttpLoggingInterceptor.Level.BODY))
				.build())
			.addConverterFactory(GsonConverterFactory.create(GSON))
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.baseUrl(BASE_URL)
			.build()
			.create(Api.class);
	}

	public interface Api {
		@POST("user")
		@FormUrlEncoded
		Single<User> user(
			@Field("username") String username,
			@Field("age") String age
		);

		@POST("credit")
		@FormUrlEncoded
		Single<Credit> newCredit(
			@Field("userId") int userId,
			@Field("amount") String amount,
			@Field("months") int paymentType
		);

		@GET("credit")
		Single<List<CreditRequest>> getCreditRequests(
			@Query("userId") int userId,
			@Query("status") String status
		);

		@GET("credit")
		Single<List<CreditRequest>> getCreditRequests(
			@Query("userId") int userId
		);
	}
}
