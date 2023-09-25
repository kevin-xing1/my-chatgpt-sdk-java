package com.kevin.chatgpt.session.defaults;

import cn.hutool.http.ContentType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevin.chatgpt.IOpenAiApi;
import com.kevin.chatgpt.domain.chat.ChatCompletionRequest;
import com.kevin.chatgpt.domain.chat.ChatCompletionResponse;
import com.kevin.chatgpt.domain.qa.QACompletionRequest;
import com.kevin.chatgpt.domain.qa.QACompletionResponse;
import com.kevin.chatgpt.session.Configuration;
import com.kevin.chatgpt.session.OpenAiSession;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

/**
 * @description:
 * @author：kevin
 * @date: 2023/9/2
 * @Copyright：
 */
public class DefaultOpenAiSession implements OpenAiSession {

    /**
     * 配置信息
     */
    private final Configuration configuration;

    /**
     * OpenAI 接口
     */
    private IOpenAiApi openAiApi;

    /**
     * 工厂事件
     */
    private final EventSource.Factory factory;

    public DefaultOpenAiSession(Configuration configuration) {
        this.configuration = configuration;
        this.openAiApi = configuration.getOpenAiApi();
        this.factory = configuration.createRequestFactory();
    }

    @Override
    public ChatCompletionResponse completions(ChatCompletionRequest chatCompletionRequest) {
        return this.openAiApi.completions(chatCompletionRequest).blockingGet();
    }

    @Override
    public EventSource chatCompletions(ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException {
        // 核心参数校验；不对用户的传参做更改，只返回错误信息。
        if (!chatCompletionRequest.isStream()) {
            throw new RuntimeException("illegal parameter stream is false!");
        }

        // 构建请求信息
        Request request = new Request.Builder()
                // url: https://api.openai.com/v1/chat/completions - 通过 IOpenAiApi 配置的 POST 接口，用这样的方式从统一的地方获取配置信息
                .url(configuration.getApiHost().concat(IOpenAiApi.v1_chat_completions))
                // 封装请求参数信息，如果使用了 Fastjson 也可以替换 ObjectMapper 转换对象
                .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), new ObjectMapper().writeValueAsString(chatCompletionRequest)))
                .build();

        // 返回结果信息；EventSource 对象可以取消应答
        return factory.newEventSource(request, eventSourceListener);
    }

    @Override
    public QACompletionResponse completions(QACompletionRequest qaCompletionRequest) {
        return null;
    }

    @Override
    public QACompletionResponse completions(String question) {
        return null;
    }
}
