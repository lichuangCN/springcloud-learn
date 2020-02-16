## Spring Cloud系列的学习项目
资料来源：https://github.com/macrozheng/springcloud-learning
非常感谢作者的开源支持，也希望自己能够在此学习中有所收获。

### 第一部分 服务注册与发现 Eureka
对应作者的博客链接：https://juejin.im/post/5d78cd53f265da03d55e8351

模块结构：
```
springcloud-learn
├── eureka-server -- eureka注册中心
├── eureka-security-server -- 带登录认证的eureka注册中心
└── eureka-client -- eureka客户端
```

### 第二部分 负载均衡 Robbin
对应作者的博客链接：https://juejin.im/post/5d7f9006f265da03951a260c

模块结构：
```
springcloud-learn
├── eureka-server -- eureka注册中心
├── user-service -- 提供User对象CRUD接口的服务
└── ribbon-service -- ribbon服务调用测试服务
```

### 第三部分 服务容错保护 Hystrix
对应作者的博客链接：https://juejin.im/post/5d822d27e51d45621479ad92
其他参考：https://www.cnblogs.com/hellxz/p/9056806.html
https://www.cnblogs.com/hellxz/p/9071163.html

模块结构：
```
springcloud-learning
├── eureka-server -- eureka注册中心
├── user-service -- 提供User对象CRUD接口的服务
└── hystrix-service -- hystrix服务调用测试服务
```