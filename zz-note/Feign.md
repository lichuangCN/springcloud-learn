## Feign
### 1.Feign简介
Feign是声明式的服务调用工具，我们只需创建一个接口并用注解的方式来配置它，就可以实现对某个服务接口的调用，简化了直接使用RestTemplate来调用服务接口的开发量。Feign具备可插拔的注解支持，同时支持Feign注解、JAX-RS注解及SpringMvc注解。当使用Feign时，Spring Cloud集成了Ribbon和Eureka以提供负载均衡的服务调用及基于Hystrix的服务容错保护功能

### 2.服务绑定
通过@FeignClient注解实现了一个Feign客户端，其中的value为user-service表示这是对user-service服务的接口调用客户端。 fallback: 设置服务降级处理类为UserFeignServiceFallback
```java
@FeignClient(value = "user-service",fallback = UserFeignServiceFallback.class)
```
注：通过定义一个接口，与服务提供方进行绑定，绑定的是服务提供方暴露出来的Controller(远程调用接口)

### 3.Feign下的Ribbon

### 4.Feign下的Hystrix

### 5.日志打印

### 6.常见配置
```yaml
feign:
  hystrix:
    enabled: true #在Feign中开启Hystrix
  compression:
    request:
      enabled: false #是否对请求进行GZIP压缩
      mime-types: text/xml,application/xml,application/json #指定压缩的请求数据类型
      min-request-size: 2048 #超过该大小的请求会被压缩
    response:
      enabled: false #是否对响应进行GZIP压缩
logging:
  level: #修改日志级别
    site.muzhi.springcloud.service.UserFeignService: debug
```


