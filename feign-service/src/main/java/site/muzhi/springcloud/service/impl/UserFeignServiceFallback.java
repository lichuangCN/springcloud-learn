package site.muzhi.springcloud.service.impl;

import org.springframework.stereotype.Component;
import site.muzhi.springcloud.domain.CommonResult;
import site.muzhi.springcloud.domain.User;
import site.muzhi.springcloud.service.UserFeignService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: LiChuang
 * @date: 2020/02/16
 * @description: Feign中的服务降级使用只需要为Feign客户端定义的接口添加一个服务降级处理的实现类即可，
 * 需要注意的是它实现了UserService接口，并且对接口中的每个实现方法进行了服务降级逻辑的实现。
 */
@Component
public class UserFeignServiceFallback implements UserFeignService {
    @Override
    public CommonResult create(User user) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(500, "服务降级", defaultUser);
    }

    @Override
    public CommonResult<User> queryById(Long id) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(500, "服务降级", defaultUser);
    }

    @Override
    public CommonResult<User> queryByUsername(String username) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(500, "服务降级", defaultUser);
    }

    @Override
    public CommonResult<List<User>> listByIds(String ids) {
        List<User> defaultUserList = new ArrayList<>(1);
        defaultUserList.add(new User(-1L, "defaultUser", "123456"));
        return new CommonResult<>(500, "服务降级", defaultUserList);
    }

    @Override
    public CommonResult update(User user) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(500, "服务降级", defaultUser);
    }

    @Override
    public CommonResult delete(Long id) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(500, "服务降级", defaultUser);
    }
}
