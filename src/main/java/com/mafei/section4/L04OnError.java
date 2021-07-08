package com.mafei.section4;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/*
  @Author mafei
*/
public class L04OnError {
    public static void main(String[] args) {
        System.out.println("Main Thread > " + Thread.currentThread().getName());
        Flux.range(1, 10)
                .map(integer -> 10 / (5 - integer))
                .onErrorResume(throwable -> fallback())
                .subscribe(integer -> {
                    System.out.println("Thread > " + Thread.currentThread().getName());
                    System.out.println("integer = " + integer);
                });
    }

    public static Mono<Integer> fallback() {
        return Mono.fromSupplier(() -> SubscriberUtil.FAKER.random().nextInt(100, 200));
    }
}
