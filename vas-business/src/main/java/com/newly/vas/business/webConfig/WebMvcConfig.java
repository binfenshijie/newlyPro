package com.newly.vas.business.webConfig;

import com.newly.vas.base.constant.CommonConstants;
import com.newly.vas.business.login.service.AuthInterceptor;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by bingo on 2019/1/28 下午1:38
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

//    @Value("${async.executor.thread.core_pool_size}")
//    private int corePoolSize;
//    @Value("${async.executor.thread.max_pool_size}")
//    private int maxPoolSize;
//    @Value("${async.executor.thread.queue_capacity}")
//    private int queueCapacity;
//    @Value("${async.executor.thread.name.prefix}")
//    private String namePrefix;


    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        template.setEnableTransactionSupport(true);//打开事务支持
        return template;
    }
//
//    //配置事务管理器
//    @Bean
//    public PlatformTransactionManager transactionManager(DataSource dataSource) throws SQLException {
//        return new DataSourceTransactionManager(dataSource);
//    }

    /**
     * @return org.springframework.web.servlet.HandlerInterceptor
     * @author bingo 2018/12/18 10:30 AM
     * @modificationHistory ========逻辑或功能重大变更记录
     * @modify by user   :{修改人} :{修改时间}
     * @modify by reason :{原因}
     **/
    @Bean
    public HandlerInterceptor getAuthInterceptor() {
        return new AuthInterceptor();
    }

//    @Bean(name = "asyncServiceExecutor")
//    public Executor asyncServiceExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        //配置核心线程数
//        executor.setCorePoolSize(corePoolSize);
//        //配置最大线程数
//        executor.setMaxPoolSize(maxPoolSize);
//        //配置队列大小
//        executor.setQueueCapacity(queueCapacity);
//        //配置线程池中的线程的名称前缀
//        executor.setThreadNamePrefix(namePrefix);
//
//        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
//        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        //执行初始化
//        executor.initialize();
//        return executor;
//    }

    /**
     * @return void
     * @author bingo 2018/12/18 10:32 AM
     * @modificationHistory ========逻辑或功能重大变更记录
     * @modify by user   :{修改人} :{修改时间}
     * @modify by reason :{原因}
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(getAuthInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/login/**")
//                .excludePathPatterns("/role/**")
//                .excludePathPatterns("/liveVideo/**")
//                .excludePathPatterns("/userInfo/**")
//                .excludePathPatterns("/swagger-ui.html")
//                .excludePathPatterns("/webjars/**")
//                .excludePathPatterns("/resources/**")
//                .excludePathPatterns("/swagger-resources/**")
//                .excludePathPatterns("/**/api-docs/**")
//                ;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 全局CORS配置(方法一)
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }

//    /**
//     * 全局CORS配置(方法二)
//     *
//     * @return
//     */
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*")
//                        .allowedHeaders("*")
//                        .allowedMethods("*")
//                        .allowCredentials(false).maxAge(3600);
//            }
//        };
//    }

    @Bean
    public Queue deviceStateQueue() {
        return new Queue(CommonConstants.DEVICE_STATE_QUEUE);
    }
}
