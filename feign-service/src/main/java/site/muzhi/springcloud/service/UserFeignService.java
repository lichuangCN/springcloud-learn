package site.muzhi.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import site.muzhi.springcloud.domain.CommonResult;
import site.muzhi.springcloud.domain.User;
import site.muzhi.springcloud.service.impl.UserFeignServiceFallback;

import java.util.List;

/**
 * @author: LiChuang
 * @date: 2020/02/16
 * @description: 添加UserService接口完成对user-service服务的接口绑定
 * <p>
 * 通过@FeignClient注解实现了一个Feign客户端，
 * 其中的value为user-service表示这是对user-service服务的接口调用客户端。
 * 将user-service中的UserController改为接口，保留原来的SpringMvc注释即可
 * fallback: 设置服务降级处理类为UserFeignServiceFallback
 */
@FeignClient(value = "user-service",fallback = UserFeignServiceFallback.class)
public interface UserFeignService {
    @PostMapping("/user/create")
    CommonResult create(@RequestBody User user);

    @GetMapping("/user/{id}")
    CommonResult<User> queryById(@PathVariable Long id);

    @GetMapping("/user/username")
    CommonResult<User> queryByUsername(@RequestParam String username);

    @GetMapping("/user/list")
    CommonResult<List<User>> listByIds(@RequestParam String ids);

    @PostMapping("/user/update")
    CommonResult update(@RequestBody User user);

    @PostMapping("/user/delete/{id}")
    CommonResult delete(@PathVariable Long id);

}
