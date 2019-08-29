# MY-GOT
一个暑期实习夏令营的企业点餐项目，我负责的主要是顾客点餐到付款的过程中的一系列功能，这一部分的前端由[@islandLiu](https://github.com/IslandLiu)大佬负责，地址：[vue_demo](https://github.com/IslandLiu/vue_demo)，特别感谢下233，以及管理员模块[@jianhuaguo](https://github.com/jianhuaguo/meiya_springboot)和商家模块[@Braun-Liu](https://github.com/Braun-Liu/meiya)的源码~

### 开发环境

 - IDEA 2018
 - Java: 1.8 
 - MySQL: 8.0.15
 - Spring Boot: 2.6
 - Redis: 2.8
 - RabbitMQ: 3.7.16

### 运行

 - git clone https://github.com/zlovae/MEIAH.git
 - 修改`pom.xml`和`application.yml`中的配置信息
 - run

### 功能实现

#### 购物车模块

 1. 存储，使用缓存存储，将商品信息和用户信息都存入redis
 2. 修改，因为我们用的element-UI组件没有将增加和减少分成两个button，所以我们对购物车的修改的方法为每次尝试将对应的信息

#### 订单模块

 1. 提交购物车信息，检查库存并生成子订单
 2. 生成主订单，并返回支付信息给用户
 3. 发送新订单消息给商家

#### 支付模块

 1. 得到订单信息后生成支付信息
 2. 调用支付宝的当面付接口，生成唯一的`qr_code`
 3. 由前端将`qr_code`转换为图形
 4. 用户支付成功后发送支付信息给店家

#### 秒杀模块

这一部分是对基本功能的一个补充，算是对高并发扩展的一个尝试~

 1. 秒杀商品的信息的缓存
 2. 防机器人or刷单的处理
 3. redis预减库存
 4. RabbitMQ削峰
 5. 异步处理订单

### 值得注意的一些问题

#### redis
#### RabbitMQ
#### MySQL

### 总得有个总结才像回事儿~

总结&展望