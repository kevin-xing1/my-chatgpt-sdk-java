package com.kevin.chatgpt.session.defaults;

import com.kevin.chatgpt.IOpenAiApi;
import com.kevin.chatgpt.interceptor.OpenAiInterceptor;
import com.kevin.chatgpt.session.Configuration;
import com.kevin.chatgpt.session.OpenAiSession;
import com.kevin.chatgpt.session.OpenAiSessionFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author：kevin
 * @date: 2023/9/2
 * @Copyright：
 */
public class DefaultOpenAiSessionFactory implements OpenAiSessionFactory {

    private final Configuration configuration;

    public DefaultOpenAiSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public OpenAiSession openAiSession() {
        //1、日志配置
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        //2、开启Http客户端
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                // 添加 HTTP 日志拦截器，用于记录请求和响应
                .addInterceptor(httpLoggingInterceptor)
                // 添加自定义的拦截器，用于身份验证的拦截器,提供 API 密钥
                .addInterceptor(new OpenAiInterceptor(configuration.getApiKey()))
                // 设置连接超时时间
                .connectTimeout(450, TimeUnit.SECONDS)
                // 设置写入数据超时时间
                .writeTimeout(450, TimeUnit.SECONDS)
                // 设置读取数据超时时间
                .readTimeout(450, TimeUnit.SECONDS)
                .build();
        configuration.setOkHttpClient(okHttpClient);

        //3、创建API服务 类似于对DAO接口与数据库的连接数据源之间的操作
        IOpenAiApi openAiApi = new Retrofit.Builder()
                //设置 API 的基础 URL，API 服务的根地址
                .baseUrl(configuration.getApiHost())
                .client(okHttpClient)
                //添加 RxJava 适配器，这允许你在处理 API 响应时使用响应式编程的方式
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //添加 Jackson 转换器，用于将 API 响应转换为 Java 对象（通常是 POJO）
                .addConverterFactory(JacksonConverterFactory.create())
                //构建 Retrofit 实例并创建 API 接口的实例，这个接口将用于发起具体的 API 请求
                .build().create(IOpenAiApi.class);
        configuration.setOpenAiApi(openAiApi);

        return new DefaultOpenAiSession(configuration);
    }

}
