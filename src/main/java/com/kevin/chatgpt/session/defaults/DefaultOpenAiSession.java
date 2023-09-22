package com.kevin.chatgpt.session.defaults;

import com.kevin.chatgpt.IOpenAiApi;
import com.kevin.chatgpt.domain.chat.ChatCompletionRequest;
import com.kevin.chatgpt.domain.chat.ChatCompletionResponse;
import com.kevin.chatgpt.domain.qa.QACompletionRequest;
import com.kevin.chatgpt.domain.qa.QACompletionResponse;
import com.kevin.chatgpt.session.OpenAiSession;

import java.io.Serializable;

/**
 * @description:
 * @author：kevin
 * @date: 2023/9/2
 * @Copyright：
 */
public class DefaultOpenAiSession implements OpenAiSession {

    private IOpenAiApi openAiApi;

    public DefaultOpenAiSession(IOpenAiApi openAiApi) {
        this.openAiApi = openAiApi;
    }

    @Override
    public ChatCompletionResponse completions(ChatCompletionRequest chatCompletionRequest) {
        return this.openAiApi.completions(chatCompletionRequest).blockingGet();
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
