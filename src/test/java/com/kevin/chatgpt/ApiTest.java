package com.kevin.chatgpt;

import com.kevin.chatgpt.common.Constants;
import com.kevin.chatgpt.domain.chat.ChatCompletionRequest;
import com.kevin.chatgpt.domain.chat.ChatCompletionResponse;
import com.kevin.chatgpt.domain.chat.Message;
import com.kevin.chatgpt.session.Configuration;
import com.kevin.chatgpt.session.OpenAiSession;
import com.kevin.chatgpt.session.OpenAiSessionFactory;
import com.kevin.chatgpt.session.defaults.DefaultOpenAiSessionFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

/**
 * @description:
 * @author：kevin
 * @date: 2023/9/2
 * @Copyright：
 */

@Slf4j
public class ApiTest {

    private OpenAiSession openAiSession;

    @Before
    public void test_OpenAiSessionFactory() {
        //1.配置文件
        Configuration configuration = new Configuration();
        configuration.setApiHost("https://pro-share-aws-api.zcyai.com/");
        configuration.setApiKey("sk-ODuxcFCgshH0onYh43F7F3E519B44103961469D7B60b40Ea");

        //2.会话工厂
        OpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);

        //3.开启会话
        this.openAiSession = factory.openAiSession();
    }

    /**
     * 此对话模型 3.5 接近于官网体验
     */
    @Test
    public void test_chat_completions() {
        //1.创建参数
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .messages(Collections.singletonList(Message.builder().role(Constants.Role.USER).content("写一个Java冒泡排序").build()))
                .model(ChatCompletionRequest.Model.GPT_3_5_TURBO.getCode())
                .build();

        //2.发起请求
        ChatCompletionResponse chatCompletionResponse = openAiSession.completions(chatCompletionRequest);

        //3.解析结果
        chatCompletionResponse.getChoices().forEach(chatChoice -> {
            log.info("测试结果：{}",chatChoice.getMessage());
        });
    }
}
