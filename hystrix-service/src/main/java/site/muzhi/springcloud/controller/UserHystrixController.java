package site.muzhi.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.muzhi.springcloud.domain.CommonResult;
import site.muzhi.springcloud.domain.User;
import site.muzhi.springcloud.service.UserHystrixService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author: LiChuang
 * @date: 2020/02/15
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserHystrixController {

    @Autowired
    private UserHystrixService userHystrixService;

    @GetMapping("/{id}")
    public CommonResult queryUser(@PathVariable Long id) {
        return userHystrixService.queryUser(id);
    }

    @GetMapping("/command/{id}")
    public CommonResult queryUserCommand(@PathVariable Long id) {
        return userHystrixService.queryUserCommand(id);
    }

    @GetMapping("/exception/{id}")
    public CommonResult queryUserException(@PathVariable Long id) {
        return userHystrixService.queryUserException(id);
    }

    /**
     * 在一次请求中对服务提供方的多次调用会对调用结果进行缓存
     *
     * @param id
     * @return
     */
    @GetMapping("/cache/{id}")
    public CommonResult queryUserCache(@PathVariable Long id) {
        userHystrixService.queryUserCache(id);
        userHystrixService.queryUserCache(id);
        userHystrixService.queryUserCache(id);
        return new CommonResult(200, "操作成功");
    }

    /**
     * 测试清除缓存
     *
     * @param id
     * @return
     */
    @GetMapping("/removeCache/{id}")
    public CommonResult removeCache(@PathVariable Long id) {
        userHystrixService.queryUserCache(id);
        userHystrixService.flushCache(id);
        userHystrixService.queryUserCache(id);
        return new CommonResult(200, "操作成功");
    }

    @GetMapping("/merge")
    public CommonResult merge() throws ExecutionException, InterruptedException {
        Future<User> merge1 = userHystrixService.merge(1L);
        Future<User> merge2 = userHystrixService.merge(2L);
        merge1.get();
        merge2.get();
        Thread.sleep(200);
        Future<User> merge3 = userHystrixService.merge(3L);
        merge3.get();
        return new CommonResult(200, "操作成功");
    }
}
