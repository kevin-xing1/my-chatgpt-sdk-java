package com.kevin.chatgpt.interceptor;

import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;

/**
 * @description:
 * @author：kevin
 * @date: 2023/9/2
 * @Copyright：
 */
public class OpenAiInterceptor implements Interceptor {

    private String apiKey;

    public OpenAiInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
