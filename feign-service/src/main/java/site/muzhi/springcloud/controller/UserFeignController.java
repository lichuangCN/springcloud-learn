package site.muzhi.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.muzhi.springcloud.domain.CommonResult;
import site.muzhi.springcloud.domain.User;
import site.muzhi.springcloud.service.UserFeignService;

/**
 * @author: LiChuang
 * @date: 2020/02/16
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserFeignController {

    @Autowired
    private UserFeignService userFeignService;

    @GetMapping("/{id}")
    public CommonResult queryById(@PathVariable Long id) {
        return userFeignService.queryById(id);
    }

    @GetMapping("/list")
    public CommonResult listByIds(@RequestParam String ids) {
        return userFeignService.listByIds(ids);
    }

    @GetMapping("/username")
    public CommonResult queryByUsername(@RequestParam String username) {
        return userFeignService.queryByUsername(username);
    }

    @PostMapping("/create")
    public CommonResult create(@RequestBody User user) {
        return userFeignService.create(user);
    }

    @PostMapping("/update")
    public CommonResult update(@RequestBody User user) {
        return userFeignService.update(user);
    }

    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        return userFeignService.delete(id);
    }
}
