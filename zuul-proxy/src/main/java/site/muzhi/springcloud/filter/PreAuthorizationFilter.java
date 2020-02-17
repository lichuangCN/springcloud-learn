package site.muzhi.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: LiChuang
 * @date: 2020/02/17
 * @description:
 */
@Component
public class PreAuthorizationFilter extends ZuulFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(PreAuthorizationFilter.class);

    /**
     * 过滤器类型，有pre、routing、post、error四种。
     * pre：在请求被路由到目标服务前执行，比如权限校验、打印日志等功能；
     * routing：在请求被路由到目标服务时执行，这是使用Apache HttpClient或Netflix Ribbon构建和发送原始HTTP请求的地方；
     * post：在请求被路由到目标服务后执行，比如给目标服务的响应添加头信息，收集统计数据等功能；
     * error：请求在其他阶段发生错误时执行。
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行顺序，数值越小优先级越高。
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 是否进行过滤，返回true会执行过滤。
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 自定义的过滤器逻辑，当shouldFilter()返回true时会执行。
     */
    @Override
    public Object run() throws ZuulException {
        // 获取请求上下文
        RequestContext context = RequestContext.getCurrentContext();
        // 取出当前请求
        HttpServletRequest request = context.getRequest();
        LOGGER.info("进入访问过滤器，访问的URL:{},访问的方法：{}", request.getRequestURL(), request.getMethod());
        // 这里简单校验下如果headers中没有这个accessToken或者该值为空的情况
        String accessToken = request.getHeader("accessToken");
        if (StringUtils.isEmpty(accessToken)) {
            LOGGER.info("当前请求没有accessToken");
            // 过滤此次请求
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
        }
        LOGGER.info("当前请求有accessToken");
        return null;
    }
}
