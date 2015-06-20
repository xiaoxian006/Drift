#Drift

Drift是一个Java性能测试套件,目的在于简化性能测试和开发的性能自验证。

-  封装计时器，报表，词表等多种测试kit
-  不使用反射而采用模板，减小测试噪音
-  目前提供两种原生的测试app：负载测试和稳定性测试
-  测试载荷支持定制，测试框架支持扩展

项目地址：[Drift (戳我) ](https://github.com/elloray/Drift)

###Current
目前的项目由我业余时间在开发和维护，最近我接的性能测试需求都是使用这组套件完成测试。

- 目前的进度

    可以支持目前的性能测试需求，对于需要大容量词表的测试暂时没办法提供词表，需要自己来构造词表

- 后续的开发计划

    - 可定制化的报告
    - 完善词表，具备fuzz能力
    - 精准反映函数调用栈及各部分的性能
    - jvm快照
    
- 可能的隐患
    
    目前所使用的线程池是java原生的线程池，并发时不太稳定，后续看有什么解决办法。


###Structure
| 框架 | 模块 | 包 | 描述 |
| ------- | -------- | ------- |
| drift | frame | app | 包含两个测试应用：负载测试和稳定性测试 |
|  |  | frame | 包含设置类，执行器，测试载荷，指标 | 
|  | kit | annotation | 相关注解(TBD) | 
|  |  | dictionary | 词表(TBD) |
|  |  | report | 报表(TBD) |
|  |  | timer | 计时器 |
|  |  | util | random和assert |

###Get Started
- 以负载测试为例    
    
    1. 新建一个负载测试，有两种方法：

        - 直接使用匿名类实现
  
        `LoadTest loadTest = new LoadTest(1, 600){}`
    
        - 继承实现`LoadTest`类
  
        `public class alterTest extends LoadTest`
    2. 实现负载测试的测试载荷
    
        `public class metamodel extends TestModel`
        
        将需要测试的接口或者代码块在`invoke()`接口中实现
        
        在`Assert()`接口实现返回值的判断，判断业务请求/响应是否正确
        
    3. 定制负载最优策略`setStrategy()`，预留接口暂时不用实现，目前默认的是tps最高为负载最好
    
    4. 所有都完成之后，调用`loadTest.run()`来启动测试
        
    
- 稳定性测试同理
- 也可以直接使用执行器，在此基础上进行定制
- 目前的报告会打印在日志或者直接在控制台显示

> --------------Metadata Performance Test----------------
 
> 线程数 : 1
 
> 持续时间 : 1s

> Request Times : 5894421 , TPS : 5894421 , Avg Time : 5.08955841464327E-5ms , Max Time : 2ms , 99% Request returns in : 0 ms , incorrect Num:5894421 , biz failed : 0 , 0%
    
- 具体的方法含义可参见**dirft-example**
   

###RoadMap

| Checkpoint | Version | Feature |
| ------- | -------- | ------- |
| 6.20 | 0.9 | 第一个可用版本 | 
| 7.1 | 0.9.2 | 增加压力词表特性 | 
| 7.30 | 0.9.4 | 增加报表特性 | 
| TBD | 0.9.6 | 函数栈 | 
| TBD | 1.0 | jvm快照 | 