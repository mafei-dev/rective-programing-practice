package com.mafei.section4;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
  @Author mafei
*/
public class L03Delay {
    public static void main(String[] args) {
        Flux.range(1, 10000)
                .log()
                .delayElements(Duration.ofSeconds(1))
                .subscribe(integer -> {
                    System.out.println("Thread > " + Thread.currentThread().getName());
                    System.out.println("integer = " + integer);
                });
        System.out.println("Main Thread > " + Thread.currentThread().getName());
        SubscriberUtil.seep(50);
    }
}
