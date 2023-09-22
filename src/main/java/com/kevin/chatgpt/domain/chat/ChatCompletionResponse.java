package com.kevin.chatgpt.domain.chat;

import com.kevin.chatgpt.domain.other.Usage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 对话请求结果信息
 * @author：kevin
 * @date: 2023/9/2
 * @Copyright：
 */
@Data
public class ChatCompletionResponse implements Serializable {

    /**
     * ID
     */
    private String id;
    /**
     * 对象
     */
    private String object;
    /**
     * 模型
     */
    private String model;
    /**
     * 对话
     */
    private List<ChatChoice> choices;
    /**
     * 创建
     */
    private long created;
    /**
     * 耗材
     */
    private Usage usage;

}
