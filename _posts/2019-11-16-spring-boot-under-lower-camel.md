---
layout:     post
title:      spring-boot 下划线和驼峰转换
date:       2019-11-16 09:39:32 +0800
postId:     2019-11-16-09-39-32
categories: [blog]
tags:       [Spring]
geneMenu:   true
excerpt:    spring-boot 下划线和驼峰转换
---

spring boot 框架中，下划线和驼峰相互转换问题：

* 返回数据：驼峰转下划线
    - 只需要配置文件配置即可全局生效
* 接收前端传参：下划线转驼峰，使用实体类接收
    - POST请求且使用RAW的json形式：自动转换，无需配置
    - 其他请求（包括POST请求的form表单提交形式）：需要自己特殊处理

## 返回数据
配置内容：

```yaml
spring:
  jackson:
    default-property-inclusion: NON_NULL # 过滤null属性
    property-naming-strategy: SNAKE_CASE # 驼峰转为下划线
    date-format: yyyy-MM-dd HH:mm:ss     # 日期格式化
    time-zone: GMT+8                     # 时区
```

## 接收前端传参

### POST请求且RAW请求
无需自己处理，参考[为spring get请求添加自定义的参数处理（如下划线转驼峰）](https://blog.csdn.net/qq_36752632/article/details/90665221)。

> 为了减少使用 @RequestPath  将get参数封装到实体类中 重写ModelAttributeMethodProcessor。
> 注：由于get请求为非raw请求，spring默认使用@ModelArrtribute注解，不会自动将下划线的数据转为驼峰数据
> 所以需要自定义一个处理器，进行该操作 *

### 其他请求

需要自己写处理器，但是引发了另外一个问题：

* [继承WebMvcConfigurationSupport后自动配置不生效的问题及如何配置拦截器](https://blog.csdn.net/qq_36850813/article/details/87859047)
* [springboot 2.0配置文件不生效原因](https://www.dockop.com/article/17)

## 其他JSONObject配置

在spring mvc框架中默认的是jackson工具包进行序列化相关转换，
在实际项目中，还使用了阿里的JSONObject工具类进行了下划线和驼峰的转换，用于数据中心数据查询后的转换，

```java
@JSONField(name = "create_time")
private String createUser;

// 转换的时候根据上面的配置进行转换
MenuInfoVo menu = dataCenter.query(sql, sqlParam(id), MenuInfoVo.class);
```

## 参考资料

* [test](test.html)