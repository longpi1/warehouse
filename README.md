# 基于Spring Boot的中小型仓库物流管理系统

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.1.8-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MyBatis-Plus](https://img.shields.io/badge/MyBatis--Plus-3.2.0-blue.svg)](https://mp.baomidou.com/)
[![Shiro](https://img.shields.io/badge/Shiro-1.4.1-orange.svg)](https://shiro.apache.org/)
[![LayUI](https://img.shields.io/badge/LayUI-2.x-green.svg)](https://www.layui.com/)

## 项目简介

这是一个基于 **Spring Boot + MyBatis-Plus + Shiro + LayUI** 开发的中小型企业仓库物流管理系统，提供了完整的仓储业务流程管理功能，包括基础数据管理、物流流程管理、系统权限管理等核心模块。

### 核心特性

- 🔐 **完善的权限体系**：基于 Shiro 实现的 RBAC 权限模型
- 📦 **全流程管理**：覆盖商品入库、出库、退货、发货等完整业务流程
- 🎯 **灵活的角色管理**：支持多角色、多权限动态配置
- 💾 **库存实时同步**：进货、退货自动更新商品库存
- 📊 **数据可视化**：基于 LayUI 提供友好的数据展示界面
- 🔒 **安全加密**：MD5+盐值双重加密保障数据安全

## 技术栈

### 后端技术

| 技术 | 版本 | 说明 |
|------|------|------|
| Spring Boot | 2.1.8.RELEASE | 基础框架 |
| MyBatis-Plus | 3.2.0 | ORM 框架 |
| Apache Shiro | 1.4.1 | 安全框架 |
| Druid | 1.1.18 | 数据库连接池 |
| MySQL | 5.7+ | 数据库 |
| Thymeleaf | - | 模板引擎 |
| FastJSON | 1.2.61 | JSON 处理 |
| Hutool | 4.6.6 | 工具类库 |
| Pinyin4j | 2.5.1 | 拼音转换 |
| Log4j | 1.2.17 | 日志框架 |
| Lombok | - | 简化实体类开发 |

### 前端技术

- **LayUI 2.x**：响应式UI框架
- **jQuery**：JavaScript库
- **dtree**：树形组件
- **layui-extend**：扩展组件

## 功能模块

### 1. 基础管理模块

#### 1.1 商品管理
- 商品信息增删改查
- 商品分类管理
- 供应商关联
- 库存数量实时显示
- 商品图片上传
- 商品规格(尺寸)管理
- 商品可用状态控制

#### 1.2 客户管理
- 客户信息维护
- 客户联系方式管理
- 客户地址管理
- 客户可用状态控制
- 批量操作支持

#### 1.3 供应商管理
- 供应商信息维护
- 联系人管理
- 供应商电话/地址管理
- 可用供应商筛选
- 批量删除功能

### 2. 物流管理模块

#### 2.1 进货管理
- 商品进货登记
- 供应商选择
- 进货数量记录
- 进货时间自动记录
- 操作人员自动记录
- 库存自动增加
- 进货单查询(支持时间范围、供应商、商品等多维度筛选)
- 备注信息记录

#### 2.2 退货管理
- 基于进货单的退货处理
- 退货数量控制
- 库存自动减少
- 退货原因备注
- 退货记录查询
- 时间范围筛选

#### 2.3 发货管理
- 发货单创建
- 客户选择
- 商品发货数量记录
- 发货时间记录
- 操作人员记录
- 库存自动扣减
- 发货记录查询
- 多条件筛选功能

### 3. 系统管理模块

#### 3.1 菜单管理
- 树形菜单结构
- 菜单增删改查
- 菜单图标配置
- 菜单排序
- 菜单层级管理
- 菜单可用状态控制
- 支持左右分栏展示

#### 3.2 部门管理
- 树形部门结构
- 部门信息维护
- 部门层级管理
- 部门排序
- 部门可用状态
- 部门负责人管理

### 4. 人事管理模块

#### 4.1 权限管理
- 权限树形展示
- 菜单权限/操作权限分类
- 权限编码管理
- 权限图标配置
- 权限排序
- 权限可用状态

#### 4.2 角色管理
- 角色信息管理
- 角色权限分配
- 基于树形结构的权限勾选
- 角色可用状态控制
- 预置角色：
  - 最终管理员：拥有所有权限
  - 基础管理员：基础数据管理权限
  - 物流管理员：仓库操作权限
  - 人事管理员：人员管理权限
  - 系统管理员：系统配置权限

#### 4.3 用户管理
- 用户信息维护
- 部门关联
- 直属领导设置
- 用户角色分配
- 用户名自动转拼音
- 密码MD5加密
- 用户类型(超级管理员/普通用户)
- 密码重置功能
- 用户可用状态控制

### 5. 其他功能

#### 5.1 登录日志
- 登录时间记录
- 登录IP记录
- 登录用户记录
- 日志查询与筛选
- 批量删除

#### 5.2 文件管理
- 图片上传
- 文件下载
- 文件路径管理

#### 5.3 验证码
- 图形验证码生成
- 验证码校验

## 项目结构

```
warehouse/
├── sql/                          # SQL脚本
│   └── warehouse.sql            # 数据库初始化脚本
├── src/
│   ├── main/
│   │   ├── java/com/lp/
│   │   │   ├── sys/             # 系统模块
│   │   │   │   ├── common/     # 通用工具类
│   │   │   │   ├── config/     # 配置类(Shiro、MyBatis-Plus)
│   │   │   │   ├── controller/ # 控制器层
│   │   │   │   ├── domain/     # 实体类
│   │   │   │   ├── mapper/     # 数据访问层
│   │   │   │   ├── realm/      # Shiro认证授权
│   │   │   │   ├── service/    # 业务逻辑层
│   │   │   │   └── vo/         # 视图对象
│   │   │   ├── warehouse/       # 仓储模块
│   │   │   │   ├── controller/ # 业务控制器
│   │   │   │   ├── domain/     # 业务实体
│   │   │   │   ├── mapper/     # 数据访问
│   │   │   │   ├── service/    # 业务逻辑
│   │   │   │   └── vo/         # 视图对象
│   │   │   └── Application.java # 启动类
│   │   └── resources/
│   │       ├── mapper/          # MyBatis XML映射文件
│   │       ├── static/          # 静态资源
│   │       │   ├── resources/   # CSS/JS/JSON等
│   │       │   └── index.html   # 登录页面
│   │       ├── templates/       # Thymeleaf模板
│   │       │   ├── system/      # 系统管理页面
│   │       │   └── warehouse/   # 仓储业务页面
│   │       ├── application.yml  # 应用配置
│   │       └── log4j.properties # 日志配置
│   └── test/                    # 测试代码
└── pom.xml                      # Maven依赖配置
```

## 快速开始

### 环境要求

- JDK 1.8+
- Maven 3.6+
- MySQL 5.7+
- IDE (推荐 IntelliJ IDEA)

### 安装步骤

1. **克隆项目**
```bash
git clone <repository-url>
cd warehouse
```

2. **创建数据库**
```sql
CREATE DATABASE warehouse DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
```

3. **导入数据**
```bash
# 执行 sql/warehouse.sql 文件
mysql -u root -p warehouse < sql/warehouse.sql
```

4. **修改配置**

编辑 `src/main/resources/application.yml`：
```yaml
spring:
  datasource:
    druid:
      url: jdbc:mysql://127.0.0.1:3306/warehouse?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=UTC
      username: root      # 修改为你的数据库用户名
      password: 123456    # 修改为你的数据库密码
```

5. **启动项目**

**方式一：使用 Maven 命令**
```bash
mvn clean install
mvn spring-boot:run
```

**方式二：使用 IDE**
- 直接运行 `com.lp.Application` 主类

6. **访问系统**

打开浏览器访问：`http://localhost:8080`

**默认账号**：
- 账号：`system`
- 密码：`123456`
- **注意**：验证码字母必须大写

### 配置说明

#### 数据库配置
```yaml
spring:
  datasource:
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://127.0.0.1:3306/warehouse
      username: root
      password: 123456
      max-active: 20        # 最大连接数
      initial-size: 1       # 初始化连接数
```

#### Shiro 配置
```yaml
shiro:
  hash-algorithm-name: md5  # 加密算法
  hash-iterations: 2        # 加密次数
  login-url: /index.html    # 登录页面
  anon-urls:                # 匿名访问路径
    - /index.html*
    - /resources/**
  authc-ulrs:               # 需要认证的路径
    - /**
```

#### MyBatis-Plus 配置
```yaml
mybatis-plus:
  mapper-locations: classpath:mapper/*/*Mapper.xml
  global-config:
    db-config:
      id-type: auto         # 主键自增策略
```

## 核心技术实现

### 1. Shiro 权限控制

#### 认证流程
1. 用户登录 → `LoginController`
2. 调用 `Subject.login()` → `UserRealm.doGetAuthenticationInfo()`
3. 密码验证（MD5+盐值）
4. 认证成功，将用户信息存入 Session

#### 授权流程
1. 访问需要权限的资源
2. 调用 `UserRealm.doGetAuthorizationInfo()`
3. 根据用户角色查询权限
4. 返回权限信息进行验证

#### 密码加密
```java
// 加密配置：MD5 + 2次散列
HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
matcher.setHashAlgorithmName("md5");
matcher.setHashIterations(2);
```

### 2. MyBatis-Plus 集成

- 继承 `BaseMapper` 获得基础 CRUD 方法
- 使用 `QueryWrapper` 构建动态查询条件
- 分页插件自动处理分页逻辑
- 主键自增策略

### 3. 业务特性

#### 库存自动更新
```java
// 进货时增加库存
goods.setNumber(goods.getNumber() + inportVo.getNumber());

// 退货时减少库存
goods.setNumber(goods.getNumber() - outportVo.getNumber());
```

#### 操作人员自动记录
```java
User user = (User) WebUtils.getSession().getAttribute("user");
inportVo.setOperateperson(user.getName());
```

#### 用户名转拼音
```java
// 使用 Pinyin4j 将中文姓名转换为拼音作为登录名
String pinyin = PinyinUtils.getPinyin(username);
```

### 4. 数据库设计

#### 核心表结构

**用户表 (sys_user)**
- id, name, loginname, pwd, address, tel
- type (用户类型), hiredate (入职时间)
- deptid (部门ID), mgr (直属领导)
- available (可用状态)

**角色表 (sys_role)**
- id, name, remark
- available, createtime

**权限表 (sys_permission)**
- id, pid (父级ID)
- type (menu/permission)
- title, percode (权限编码)
- icon, href, ordernum

**角色权限关联表 (sys_role_permission)**
- rid (角色ID), pid (权限ID)

**用户角色关联表 (sys_user_role)**
- uid (用户ID), rid (角色ID)

**商品表 (bus_goods)**
- id, goodsname, providerid
- produceplace (产地), size (规格)
- number (库存), remark

**进货表 (bus_inport)**
- id, goodsid, providerid, number
- inporttime, operateperson, remark

**退货表 (bus_outport)**
- id, goodsid, providerid, number
- outputtime, operateperson, remark

## 安装步骤

详见上方「快速开始」章节。

## 默认账号

管理员账号：`system`  
密码：`123456`  
**注意**：验证码字母要大写

## 功能展示：

登录界面：![登录界面.png](https://s2.loli.net/2022/08/03/RQvBPIWNL1pVyeh.png)

功能界面：![功能界面.png](https://s2.loli.net/2022/08/03/gKi8TM52HoaR4lu.png)
