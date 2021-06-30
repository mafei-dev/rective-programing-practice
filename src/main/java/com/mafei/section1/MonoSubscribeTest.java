package com.mafei.section1;

import reactor.core.publisher.Mono;

/*
  @Author mafei
*/
public class MonoSubscribeTest {

    public static void main(String[] args) {

        Mono<String> mono = Mono
                .just("hi i am mafei")
                .map(s -> {
                    return s + ", from Sri Lanka.";
                })
                .map(s -> {
                    throw new RuntimeException("There is an error!");
                });

        mono.subscribe(
                //on next
                msg -> {
                    System.out.println("msg = " + msg);
                },
                //onError
                throwable -> {
                    System.err.println("Error = " + throwable.getMessage());
                },
                //conComplete
                () -> {
                    System.out.println("complete.");
                }
        );

    }
}
