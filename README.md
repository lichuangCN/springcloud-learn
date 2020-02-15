## Spring Cloud系列的学习项目
资料来源：https://github.com/macrozheng/springcloud-learning
非常感谢作者的开源支持，也希望自己能够在此学习中有所收获。

### 第一部分 注册中心 Eureka
对应作者的博客链接：https://juejin.im/post/5d78cd53f265da03d55e8351

模块结构：
```
springcloud-learn
├── eureka-server -- eureka注册中心
├── eureka-security-server -- 带登录认证的eureka注册中心
└── eureka-client -- eureka客户端
```

### 第二部分 负载均衡 Robbin
对应作者的博客链接：https://juejin.im/post/5d7f9006f265da03951a260c#heading-20

模块结构：
```
springcloud-learn
├── eureka-server -- eureka注册中心
├── user-service -- 提供User对象CRUD接口的服务
└── ribbon-service -- ribbon服务调用测试服务
```
