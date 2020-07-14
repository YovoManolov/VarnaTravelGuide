package com.example.yovo_user.varnatravelguide.webServiceDirectory;

import android.content.Context;

import com.example.yovo_user.varnatravelguide.R;
import com.okta.oidc.OIDCConfig;
import com.okta.oidc.Okta;
import com.okta.oidc.Tokens;
import com.okta.oidc.clients.sessions.SessionClient;
import com.okta.oidc.clients.web.WebAuthClient;
import com.okta.oidc.util.AuthorizationException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VTGWebServClient {

    static final String BASE_URL = "http://10.0.2.2/";

    public static Retrofit retrofit = null;
    public static OkHttpClient okHttpClient = null;

    public static Retrofit getApiClient(Context applicationContext) {

        OIDCConfig config = new OIDCConfig.Builder()
                .withJsonFile(applicationContext, R.raw.okta_oidc_config)
                .create();

        WebAuthClient client = new Okta.WebAuthBuilder()
                .withConfig(config)
                .withContext(applicationContext)
                .create();

        SessionClient sessionClient = client.getSessionClient();

        try {
            Tokens token = sessionClient.getTokens();
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer "
                                        + token.getAccessToken())
                                .build();
                        return chain.proceed(newRequest);
                    }).build();


            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .client(okHttpClient)
                        .baseUrl(BASE_URL).
                                addConverterFactory(GsonConverterFactory.create()).build();
            }


            return retrofit;

        } catch (AuthorizationException e) {
            e.printStackTrace();
        }

        return null;
    }
}

