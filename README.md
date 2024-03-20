# FoodTruck 基于微服务的WEB API
## 项目结构说明：

###apigateway	API网关服务

###food-service	食品车管理服务

###common-utils	公共工具类服务（jar）

## 项目技术选型：
### 开发语言： JDK17
### 基础框架：  Springboot-3.0.0
### 微服务框架： SpringCloud-2022.0.0.0-RC1
### 数据库:MySQL-8.0.33
### 中间件:Redis

## 代码规范说明:
### pom 引入外部依赖
1、根项目的pom依赖的是所有子项目公用的常见组件

2、具体子项目如有特殊的依赖项可以单独在子pom文件中添加依赖

### java包命名
控制器层：com.patrick.xxxx.controller

服务逻辑层：com.patrick.xxxx.service

服务代理层：com.patrick.xxxx.service.proxy（定义feign client接口）

数据持久化层：com.patrick.xxxx.mapper

业务实体模型层：com.patrick.xxxx.model(业务逻辑处理用的实体类和返回结果的实体)

数据模型层：com.patrick.xxxx.domain(映射数据库表的实体)

以上xxxx代表具体的服务名缩写，比如food等
### java类命名
1、普通类以驼峰式命名方式进行命名，首字母大写,如：MemberServiceImpl

2、接口类以I开头，如IMemberService；接口实现类以Impl结尾，如：MemberServiceImpl

3、抽象类以Abstract开头，如：AbstractMemberService
### java 变量命名
1、普通变量声明时命名以首字母小写驼峰式命名，如：String niceName="test"

2、静态变量和常量，命名全部大写，多个单词时以下划线拼接，如：int OPEN_STATUS = 1

### 在线接口文档访问
1、http://localhost:{port}/doc.html
2、接口文档以knife4j在线文档给到前端进行联调
### 接口规范
1、controller层按照业务模块进行划分，每一个controller层有一个统一的前缀，比如ProductCatagoryController（类目管理）,
RequestMapping("/productCatagory"),使用swagger的接口级别的注解定义，比如@Api(tag="xxx")

2、具体接口方法上加注解@ApiOperation，定义具体接口的命名说明，通过在接口方法上加注解@ApiResponses可定义返回的参数报文格式，返回码信息

3、入参，涉及到实体类的，需要在实体类上加上注解@ApiModel("xxx")

4、出参为实体类的报文，一样需要在返回实体类上加上注解@ApiModel("xxx")

5、具体参数属性命名，需要加上注解@ApiModelProperty来定义属性的名字，是否必填等属性

### 前后端交互的规范

1、登录态：

前端需要把登录后的用户token信息放到header中作为公共参数，传给后续业务接口数据查询、数据更新的公共参数。

后端需要根据header中的token解析用户信息。

2、后端返回统一的格式
{
    "code":200,
    "data":,
    "message":""
}
涉及到分页的返回如下统一的格式：
{
    "code":200,
    "data":{
        "total": 0,
        "list": [],
        "pageNum": 1,
        "pageSize": 10
    },
    "message":""
}
3、接口路由
所有后端服务对外提供的接口必须要经过api网关，统一路由的前缀为:/api/v1/

4、数据持久化
4.1、表命名规范：业务模块缩写+实体信息，比如商品基础信息product_baseinfo,商品品牌product_brand等等
4.2、主键统一采用bigint类型，数值统一采用雪花算法，具体参考SnowflakeIdWorker类
4.3、索引命名：比如唯一索引idx_unique_字段名，其他普通索引idx_字段名
4.4、orm统一采用tkmybatis框架，涉及到复杂sql时请放在xml文件中定义，单表的操作可以采用框架自带的api或者通过注解实现
4.5、查询语句切记不要使用*作为结果返回，比如select * from ；where条件切记不要$作为传参，统一用#{xxx}， 对应的字段如果用到索引请不要用函数进行计算
4.6、涉及货币金额统一采用整型(bigint)存储
4.7、涉及到日期或时间落库的统一采用java的LocalDate或LocalDateTime




