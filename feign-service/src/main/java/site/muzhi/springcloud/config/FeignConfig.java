package site.muzhi.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: LiChuang
 * @date: 2020/02/16
 * @description:
 */
@Configuration
public class FeignConfig {
    /**
     * 打印最详细的Http请求日志信息。
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
