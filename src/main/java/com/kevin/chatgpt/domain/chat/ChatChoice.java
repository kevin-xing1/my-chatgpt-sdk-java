package com.kevin.chatgpt.domain.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 对话信息
 * @author：kevin
 * @date: 2023/9/2
 * @Copyright：
 */
@Data
public class ChatChoice implements Serializable {

    private String index;
    /**
     * stream = true 请求参数里返回的属性是 delta
     */
    @JsonProperty("delta")
    private Message delta;
    /**
     * stream = false 请求参数里返回的属性是 delta
     */
    @JsonProperty("message")
    private Message message;
    @JsonProperty("finish_reason")
    private String finishReason;
}
