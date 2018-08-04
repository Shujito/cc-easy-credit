package org.shujito.ec;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author shujito, 7/28/18
 */
public class SplashActivity extends AppCompatActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new Handler().postDelayed(() -> {
			this.startActivity(new Intent(this, AuthActivity.class));
			this.finish();
		}, 500);
	}
}
