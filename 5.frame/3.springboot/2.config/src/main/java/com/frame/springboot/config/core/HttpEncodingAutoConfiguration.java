/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *//*


package com.frame.springboot.config.core;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.http.HttpProperties;
import org.springframework.boot.autoconfigure.http.HttpProperties.Encoding.Type;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.filter.OrderedCharacterEncodingFilter;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.CharacterEncodingFilter;

*/
/**
 * {@link EnableAutoConfiguration Auto-configuration} for configuring the encoding to use
 * in web applications.
 *
 * @author Stephane Nicoll
 * @author Brian Clozel
 * @since 2.0.0
 *//*

*/
/**
 * 2021/6/20 19:19 @railgun
 * 表示这是一个配置类，和以前编写的配置文件一样，也可以给容器中添加组件；
 **//*

@Configuration(proxyBeanMethods = false)
*/
/**
 * 2021/6/20 19:19 @railgun
 * 启动指定类的ConfigurationProperties功能；
 * 进入这个HttpProperties查看，将配置文件中对应的值和HttpProperties绑定起来；
 * 并把HttpProperties加入到ioc容器中
 **//*

@EnableConfigurationProperties(HttpProperties.class)
*/
/**
 * 2021/6/20 19:20 @railgun
 * Spring底层@Conditional注解
 * 根据不同的条件判断，如果满足指定的条件，整个配置类里面的配置就会生效；
 * 这里的意思就是判断当前应用是否是web应用，如果是，当前配置类生效
 **//*

@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
*/
/**
 * 2021/6/20 19:21 @railgun
 * 判断当前项目有没有这个类CharacterEncodingFilter；SpringMVC中进行乱码解决的过滤器；
 **//*

@ConditionalOnClass(CharacterEncodingFilter.class)
*/
/**
 * 2021/6/20 19:22 @railgun
 * 判断配置文件中是否存在某个配置：spring.http.encoding.enabled；
 * 如果不存在，判断也是成立的
 * 即使我们配置文件中不配置pring.http.encoding.enabled=true，也是默认生效的；
 **//*

@ConditionalOnProperty(prefix = "spring.http.encoding", value = "enabled", matchIfMissing = true)
public class HttpEncodingAutoConfiguration {

    */
/**
     * 2021/6/20 19:24 @railgun 他已经和SpringBoot的配置文件映射了
     **//*

    private final HttpProperties.Encoding properties;

    */
/**
     * railgun
     * 2021/6/20 19:25
     * PS: 只有一个有参构造器的情况下，参数的值就会从容器中拿
     **//*

    public HttpEncodingAutoConfiguration(HttpProperties properties) {
        this.properties = properties.getEncoding();
    }

    */
/**
     * railgun
     * 2021/6/20 19:25
     * PS: 给容器中添加一个组件，这个组件的某些值需要从properties中获取
     **//*

    @Bean
    */
/**
     * 2021/6/20 19:25 @railgun
     * 判断容器没有这个组件？
     **//*

    @ConditionalOnMissingBean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
        filter.setEncoding(this.properties.getCharset().name());
        filter.setForceRequestEncoding(this.properties.shouldForce(Type.REQUEST));
        filter.setForceResponseEncoding(this.properties.shouldForce(Type.RESPONSE));
        return filter;
    }

    @Bean
    public LocaleCharsetMappingsCustomizer localeCharsetMappingsCustomizer() {
        return new LocaleCharsetMappingsCustomizer(this.properties);
    }

    private static class LocaleCharsetMappingsCustomizer
            implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>, Ordered {

        private final HttpProperties.Encoding properties;

        LocaleCharsetMappingsCustomizer(HttpProperties.Encoding properties) {
            this.properties = properties;
        }

        @Override
        public void customize(ConfigurableServletWebServerFactory factory) {
            if (this.properties.getMapping() != null) {
                factory.setLocaleCharsetMappings(this.properties.getMapping());
            }
        }

        @Override
        public int getOrder() {
            return 0;
        }

    }

}
*/
