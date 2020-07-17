package com.fanbo.app.controller;

import com.fanbo.app.feign.OrderFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderFeign orderFeign;

    @RequestMapping("/getOrderInfo")
    public String getOrderInfo(){
        return "api调order服务返回的结果是：" + orderFeign.getOrderInfo();
    }

    @RequestMapping("/feignTimeOutTest")
    public String feignTimeOutTest(){
        return "api调order服务返回结果是：" + orderFeign.feignTimeOutTest();
    }

    @HystrixCommand(fallbackMethod = "orderFallbackMethod")
    @RequestMapping("/hystrixTest")
    public String hystrixTest(){
        String threadName = Thread.currentThread().getName();
        System.out.println("------------threadName=" + threadName);
        return "开启了Hystrix： threadName=" + threadName;
    }

    public String orderFallbackMethod(){
        System.out.println("订单服务繁忙，请稍后再试！");
        return "订单服务繁忙，请稍后再试！";
    }

    public String limitFallbackMethod(){
        System.out.println("限流，请稍后再试！");
        return "限流了，请稍后再试！";
    }

    @RequestMapping("/noHystrixTest")
    public String noHystrixTest(){
        String threadName = Thread.currentThread().getName();
        System.out.println("++++++++++++threadName=" + threadName);
        return "没有开启Hystrix： threadName=" + threadName;
    }

    //限流接口
    /**
     * execution.isolation.strategy：隔离策略   THREAD —— 在固定大小线程池中，以单独线程执行，并发请求数受限于线程池大小。
     *                                          SEMAPHORE —— 在调用线程中执行，通过信号量来限制并发量。
     * execution.isolation.thread.timeoutInMilliseconds：设置调用者等待命令执行的超时限制，超过这个时间调fallbackMethod
     * circuitBreaker.enabled：设置断路器是否起作用
     * circuitBreaker.requestVolumeThreshold：设置在一个滚动窗口中，打开断路器的最少请求数
     *
     * */
    @HystrixCommand(fallbackMethod = "limitFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2")},
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"),  //线程池大小
                    @HystrixProperty(name = "maxQueueSize", value = "2") //最大的队列值
            })
    @RequestMapping("/limitTest")
    public String limitTest(){
        String threadName = Thread.currentThread().getName();
        System.out.println("*********limitTest threadName=" + threadName);
        return "开启了Hystrix： ****limitTest threadName=" + threadName;
    }
}
