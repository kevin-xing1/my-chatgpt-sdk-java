package com.kevin.chatgpt.interceptor;

import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @description: 自定义拦截器
 * @author：kevin
 * @date: 2023/9/2
 * @Copyright：
 */
public class OpenAiInterceptor implements Interceptor {

    /**
     * OpenAi apiKey 需要在官网申请
     */
    private String apiKey;

    public OpenAiInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(this.auth(apiKey, chain.request()));
    }

    public Request auth(String apiKey, Request original) {
        //创建请求
        return original.newBuilder()
                .header(Header.AUTHORIZATION.getValue(), "Bearer " + apiKey)
                .header(Header.CONTENT_TYPE.getValue(), ContentType.JSON.getValue())
                .method(original.method(), original.body())
                .build();
    }

}
