package com.mafei.section1;

import reactor.core.publisher.Mono;

/*
  @Author mafei
*/
public class MonoTest {
    public static void main(String[] args) {
        //this is the publisher
        Mono<Integer> mono = Mono.just(1);
        mono.subscribe(integer -> {
            System.out.println("mono.value = " + integer);
        });
    }
}
