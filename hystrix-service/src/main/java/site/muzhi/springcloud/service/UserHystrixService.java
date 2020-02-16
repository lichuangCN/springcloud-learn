package site.muzhi.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import site.muzhi.springcloud.domain.CommonResult;
import site.muzhi.springcloud.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author: LiChuang
 * @date: 2020/02/15
 * @description:
 */
@Service
public class UserHystrixService {

    private Logger LOGGER = LoggerFactory.getLogger(UserHystrixService.class);
    @Autowired
    private RestTemplate restTemplate;
    @Value("${service-url.user-service}")
    private String userServiceUrl;

    /**
     * 使用HystrixCommand开启服务降级
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "returnDefaultUser")
    public CommonResult queryUser(Long id) {
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", CommonResult.class, id);
    }

    /**
     * 服务降级调用方法
     *
     * @param id
     * @return
     */
    private CommonResult returnDefaultUser(Long id) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(500, "执行错误", defaultUser);
    }

    /**
     * 参数详情
     * fallbackMethod：服务降级指定方法
     * ignoreExceptions：忽略某些异常，不发生服务降级
     * commandKey：命令名称，用于区分不同命令
     * groupKey：分组名称，Hystrix会根据不同的分组来统计命令的告警以及仪表盘信息
     * threadPoolKey：线程池名称用于划分线程池
     *
     * @param id
     * @return
     */
    @HystrixCommand(
            fallbackMethod = "returnDefaultUser",
            commandKey = "queryUserCommand",
            groupKey = "queryUserGroup",
            threadPoolKey = "queryUserThreadPool")
    public CommonResult queryUserCommand(Long id) {
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", CommonResult.class, id);
    }

    /**
     * ignoreExceptions: 此处设置忽略某些异常
     *
     * @param id
     * @return
     */
    @HystrixCommand(
            fallbackMethod = "returnDefaultUser",
            ignoreExceptions = {NullPointerException.class})
    public CommonResult queryUserException(Long id) {
        if (id == 1) {
            // 抛出数组越界异常
            throw new IndexOutOfBoundsException();
        }
        if (id == 2) {
            // 抛出空指针异常
            throw new NullPointerException();
        }
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", CommonResult.class, id);
    }

    /**
     * Hystrix的请求缓存
     *
     * @param id
     * @return
     */
    @CacheResult(cacheKeyMethod = "getCacheKey")
    @HystrixCommand(
            fallbackMethod = "returnDefaultUser",
            commandKey = "queryUserCache"
    )
    public CommonResult queryUserCache(Long id) {
        LOGGER.info("queryUserCache id:{}", id);
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", CommonResult.class, id);
    }

    /**
     * 为缓存生成key的方法
     *
     * @param id
     * @return
     */
    public String getCacheKey(Long id) {
        return String.valueOf(id);
    }

    /**
     * 该注解用来让请求命令的缓存失效，失效的缓存根据commandKey进行查找。
     */
    @CacheRemove(
            commandKey = "queryUserCache",
            cacheKeyMethod = "getCacheKey")
    @HystrixCommand
    public void flushCache(Long id) {
        LOGGER.info("清除缓存");
    }

    /**
     * 指定批处理方法
     *
     * @param id
     * @return
     */
    @HystrixCollapser(
            batchMethod = "listByIds",
            collapserProperties = {
                    @HystrixProperty(name = "timerDelayInMilliseconds", value = "200")
            })
    public Future<User> merge(Long id) {
        // 此方法内部不会执行
        return null;
    }

    /**
     * 执行批处理的方法
     *
     * @param ids
     * @return
     */
    @HystrixCommand(fallbackMethod = "returnDefaultUserList")
    public List<User> listByIds(List<Long> ids) {
        LOGGER.info("listByIds:{}", ids);
        CommonResult result = restTemplate.getForObject(userServiceUrl + "/user/list?ids={1}", CommonResult.class, StringUtils.join(ids, ","));
        return (List<User>) result.getData();
    }

    /**
     * 服务降级方法
     *
     * @param ids
     * @return
     */
    public List<User> returnDefaultUserList(List<Long> ids) {
        List<User> users = new ArrayList<>();
        users.add(new User(-1L, "defaultUser", "1234565"));
        return users;
    }
}