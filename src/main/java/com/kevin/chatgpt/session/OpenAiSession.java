package com.kevin.chatgpt.session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kevin.chatgpt.domain.chat.ChatCompletionRequest;
import com.kevin.chatgpt.domain.chat.ChatCompletionResponse;
import com.kevin.chatgpt.domain.qa.QACompletionRequest;
import com.kevin.chatgpt.domain.qa.QACompletionResponse;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

/**
 * @description:
 * @author：kevin
 * @date: 2023/9/2
 * @Copyright：
 */

public interface OpenAiSession {

    /**
     * 默认 GPT-3.5 问答模型
     * @param chatCompletionRequest 请求信息
     * @return                      返回结果
     */
    ChatCompletionResponse completions(ChatCompletionRequest chatCompletionRequest);

    /**
     * 问答模型 GPT-3.5/4.0 & 流式反馈
     * @param chatCompletionRequest 请求信息
     * @param eventSourceListener   实现监听；通过监听的 onEvent 方法接收数据
     * @return                      返回结果
     * @throws JsonProcessingException
     */
    EventSource chatCompletions(ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException;


    /**
     * 文本问答
     * @param qaCompletionRequest 请求信息
     * @return                    返回结果
     */
    QACompletionResponse completions(QACompletionRequest qaCompletionRequest);

    /**
     * 文本问答；简单请求
     * @param question 请求信息
     * @return         返回结果
     */
    QACompletionResponse completions(String question);

}
