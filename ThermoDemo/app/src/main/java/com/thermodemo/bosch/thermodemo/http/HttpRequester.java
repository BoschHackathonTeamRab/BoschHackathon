package com.thermodemo.bosch.thermodemo.http;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpRequester {
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .get()
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();

        Response response = client.newCall(request).execute();
        String body = response.body().string();

        return body;
    }

    public String post(String url, String body) throws IOException {
        RequestBody requestBody = RequestBody.create(
                MediaType.get("application/json; charset=utf-8"), body
        );

        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();

        return responseBody;
    }

    public String put(String url, String body) throws IOException {
        RequestBody requestBody = RequestBody.create(
                MediaType.get("application/json; charset=utf-8"), body
        );

        Request request = new Request.Builder()
                .header("Authorization", Credentials.basic("admin", "admin"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .put(requestBody)
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();

        return responseBody;
    }
}
