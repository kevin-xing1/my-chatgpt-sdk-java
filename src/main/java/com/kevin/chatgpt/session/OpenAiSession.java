package com.kevin.chatgpt.session;

import com.kevin.chatgpt.domain.chat.ChatCompletionRequest;
import com.kevin.chatgpt.domain.chat.ChatCompletionResponse;
import com.kevin.chatgpt.domain.qa.QACompletionRequest;
import com.kevin.chatgpt.domain.qa.QACompletionResponse;

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
