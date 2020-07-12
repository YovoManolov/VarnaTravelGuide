package com.example.yovo_user.varnatravelguide.webServiceDirectory;

import android.content.Context;

import com.example.yovo_user.varnatravelguide.R;
import com.okta.oidc.OIDCConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VTGWebServClient {

    static final String BASE_URL = "http://10.0.2.2/";

    public static Retrofit retrofit = null;

    public static Retrofit getApiClient(Context applicationContext){

        OIDCConfig config = new OIDCConfig.Builder()
                .withJsonFile(applicationContext, R.raw.okta_oidc_config)
                .create();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(  httpLoggingInterceptor )
                .addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer "
                                + "eyJraWQiOiJCMFEwaURJTzBHb1ZhY0s0a0tqdFB0dEkyUlpFNUJ2a2FQOG51a" +
                                "mMxY193IiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULmd1NGdjRm" +
                                "xaUGVtamlERWY1b0djQnByYXo2c1BWZjhjVHE0MU1USHlIcWciLCJpc3MiO" +
                                "iJodHRwczovL2Rldi0zODA4MDEub2t0YS5jb20vb2F1dGgyL2RlZmF1bHQiLC" +
                                "JhdWQiOiJhcGk6Ly9kZWZhdWx0IiwiaWF0IjoxNTk0NTY4NTQzLCJleHAiOjE" +
                                "1OTQ1NzIxNDMsImNpZCI6IjBvYWF3Nms1dFd4QnpsUHZiNHg2IiwidWlkIjoi" +
                                "MDB1YXc2MjE1ZzVhMDVQakY0eDYiLCJzY3AiOlsib3BlbmlkIiwicHJvZmlsZ" +
                                "SJdLCJzdWIiOiJ0cmFuY2U5NkBhYnYuYmcifQ.O2Qp21LhRHjlU3W0_bcuMtX" +
                                "puKW4qyPf39w7-eb7BM1C9WIeFvUun1jJqfTFVt7P0DzpP6OQKg5mhMFRA6Cd" +
                                "GBhqFQyoCYCUMjTdFxFmfghqOO_NEKyupWCk03wEii8Y4IAbvvs3Ks17VnbMU" +
                                "noC9GsgYX0LoB2r3kTGqaavbzcHc-I_ekZUzaBdE2e-6rQTeSxD-Ng4cryd-" +
                                "qRvLIXzreIkx--POA2mIMMZC3A34BHXTqd8RJ_blM6KZiEtJ6R7emily8ZoMi" +
                                "xrHq0lPNd2PBctcjF9YBHuxotDEsqS7ZyG9XbXA9ku0Mm0hTvLhPT29WdFde" +
                                "sBbC7TU2fxwmZI_A")
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}

