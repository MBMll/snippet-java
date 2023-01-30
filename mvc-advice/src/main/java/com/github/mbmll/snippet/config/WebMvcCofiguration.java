package com.github.mbmll.snippet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * mvc configuration.
 *
 * @Author xlc
 * @Description
 * @Date 2023/1/30 21:09
 */
@Configuration
public class WebMvcCofiguration implements WebMvcConfigurer {

    /**
     * 交换MappingJackson2HttpMessageConverter与第一位元素
     * 让返回值类型为String的接口能正常返回包装结果
     *
     * @param converters initially an empty list of converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        List<HttpMessageConverter<?>> stringHttpMessageConverters = new ArrayList<>();
        List<HttpMessageConverter<?>> otherMessageConverters = new ArrayList<>();
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof StringHttpMessageConverter) {
                stringHttpMessageConverters.add(converter);
            } else {
                otherMessageConverters.add(converter);
            }
        }
        converters.clear();
        converters.addAll(otherMessageConverters);
        converters.addAll(stringHttpMessageConverters);
    }
}
