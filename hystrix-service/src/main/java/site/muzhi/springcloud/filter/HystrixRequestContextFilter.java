package site.muzhi.springcloud.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author: LiChuang
 * @date: 2020/02/15
 * @description:
 */
@Component
@WebFilter(urlPatterns = "/", asyncSupported = true)
public class HystrixRequestContextFilter implements Filter {

    /**
     * 配置过滤器，保证每个请求前后初始化和关闭HystrixRequestContext
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            context.close();
        }
    }
}
