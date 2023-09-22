package com.kevin.chatgpt.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * @description:
 * @author：kevin
 * @date: 2023/9/2
 * @Copyright：
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {

    @Getter
    @NotNull
    private String apiKey;

    @Getter
    private String apiHost;

}
