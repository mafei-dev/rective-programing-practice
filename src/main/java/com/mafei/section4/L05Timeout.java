package com.mafei.section4;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/*
  @Author mafei
*/
public class L05Timeout {
    public static void main(String[] args) {
        System.out.println("Main Thread > " + Thread.currentThread().getName());
        getOrderNum()
                .timeout(Duration.ofMillis(210), fallback())
                .subscribe(integer -> {
                    System.out.println("Thread > " + Thread.currentThread().getName());
                    System.out.println("integer = " + integer);
                });

        SubscriberUtil.seep(60);
    }

    private static Flux<Integer> getOrderNum() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(5));
    }

    //this is the additional one. if subscriber can't receive from the main, then it tries to this one.
    private static Flux<Integer> fallback() {
        return Flux.range(1, 100)
                .delayElements(Duration.ofMillis(200))
                .doOnNext(integer -> {
                    System.out.println("from fallback");
                });

    }


}
