## Spring Cloud系列的学习项目
资料来源：https://github.com/macrozheng/springcloud-learning
非常感谢作者的开源支持，也希望自己能够在此学习中有所收获。

### 第一部分 服务注册与发现 Eureka
对应作者的博客链接：https://juejin.im/post/5d78cd53f265da03d55e8351

模块结构：
```lua
springcloud-learn
├── eureka-server -- eureka注册中心
├── eureka-security-server -- 带登录认证的eureka注册中心
└── eureka-client -- eureka客户端
```

### 第二部分 负载均衡 Robbin
对应作者的博客链接：https://juejin.im/post/5d7f9006f265da03951a260c

模块结构：
```lua
springcloud-learnlua
├── eureka-server -- eureka注册中心
├── user-service -- 提供User对象CRUD接口的服务
└── ribbon-service -- ribbon服务调用测试服务
```

### 第三部分 服务容错保护 Hystrix
对应作者的博客链接：https://juejin.im/post/5d822d27e51d45621479ad92

其他参考：
1. https://www.cnblogs.com/hellxz/p/9056806.html
2. https://www.cnblogs.com/hellxz/p/9071163.html

模块结构：
```lua
springcloud-learn
├── eureka-server -- eureka注册中心
├── user-service -- 提供User对象CRUD接口的服务
└── hystrix-service -- hystrix服务调用测试服务
```

### 第四部分 断路器执行监控 Hystrix Dashboard
对应作者的博客链接：https://juejin.im/post/5d88cb58f265da03e4679eff

其他参考：
1. https://www.cnblogs.com/hellxz/p/9100224.html

模块结构：
```lua
springcloud-learn
├── eureka-server -- eureka注册中心
├── user-service -- 提供User对象CRUD接口的服务
├── hystrix-service -- hystrix服务调用测试服务
├── turbine-service -- 聚合收集hystrix实例监控信息的服务
└── hystrix-dashboard -- 展示hystrix实例监控信息的仪表盘
```

### 第五部分 基于Ribbon和Hystrix的声明式服务调用 Feign
对应作者的博客链接：https://juejin.im/post/5d9c85c3e51d45782c23fab6

其他参考：
1. https://www.cnblogs.com/hellxz/p/9201608.html
2. https://www.cnblogs.com/hellxz/p/9206785.html

模块结构：
```lua
springcloud-learn
├── eureka-server -- eureka注册中心
├── user-service -- 提供User对象CRUD接口的服务
└── feign-service -- feign服务调用测试服务
```

### 第六部分 API网关服务 Zuul
对应作者的博客链接：https://juejin.im/post/5d9f2dea6fb9a04e3e724067

其他参考：
1. https://www.cnblogs.com/hellxz/p/9282756.html

模块结构：
```lua
springcloud-learn
├── eureka-server -- eureka注册中心
├── user-service -- 提供User对象CRUD接口的服务
├── feign-service -- feign服务调用测试服务
└── zuul-proxy -- zuul作为网关的测试服务
```

### 第七部分 外部集中化配置管理 Config
对应作者的博客链接：https://juejin.im/post/5da4709af265da5baa5b06ac

模块结构：
```lua
springcloud-learn
├── eureka-server -- eureka注册中心
├── config-server -- 配置中心服务
├── config-security-server -- 带安全认证的配置中心服务
└── config-client -- 获取配置的客户端服务
```

### 第八部分 消息总线 Bus
对应作者的博客链接：https://juejin.im/post/5da70d1351882509615bea34

模块结构：
```lua
springcloud-learn
├── eureka-server -- eureka注册中心
├── config-server -- 配置中心服务
└── config-client -- 获取配置的客户端服务
```