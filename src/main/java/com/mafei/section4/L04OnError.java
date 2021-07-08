package com.mafei.section4;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
  @Author mafei
*/
public class L04OnError {
    public static void main(String[] args) {
        System.out.println("Main Thread > " + Thread.currentThread().getName());
        Flux.range(1, 10)
                .map(integer -> 10 / (5 - integer))
                .onErrorReturn(-1)
                .subscribe(integer -> {
                    System.out.println("Thread > " + Thread.currentThread().getName());
                    System.out.println("integer = " + integer);
                });
    }
}
