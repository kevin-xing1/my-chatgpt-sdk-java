package com.kevin.chatgpt;

import com.kevin.chatgpt.domain.chat.ChatCompletionRequest;
import com.kevin.chatgpt.domain.chat.ChatCompletionResponse;
import com.kevin.chatgpt.domain.qa.QACompletionRequest;
import com.kevin.chatgpt.domain.qa.QACompletionResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @description: 以 ChatGPT 官网 API 模型，定义接口。官网：https://platform.openai.com/playground
 * @author：kevin
 * @date: 2023/9/2
 * @Copyright：
 */

public interface IOpenAiApi {

    /**
     * 文本问答
     * @param qaCompletionRequest 请求信息
     * @return                    返回结果
     */
    @POST("v1/completions")
    Single<QACompletionResponse> completions(@Body QACompletionRequest qaCompletionRequest);

    /**
     * 默认 GPT-3.5 问答模型
     * @param chatCompletionRequest 请求信息
     * @return                      返回结果
     */
    @POST("v1/chat/completions")
    Single<ChatCompletionResponse> completions(@Body ChatCompletionRequest chatCompletionRequest);

}
