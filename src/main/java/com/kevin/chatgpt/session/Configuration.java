package com.kevin.chatgpt.session;

import com.kevin.chatgpt.IOpenAiApi;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;
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

    @Getter
    @Setter
    private IOpenAiApi openAiApi;

    @Getter
    @Setter
    private OkHttpClient okHttpClient;

    public EventSource.Factory createRequestFactory() {
        return EventSources.createFactory(okHttpClient);
    }

}
