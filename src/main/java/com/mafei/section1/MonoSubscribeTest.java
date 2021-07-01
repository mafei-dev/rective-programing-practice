package com.mafei.section1;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Mono;

/*
  @Author mafei
*/
public class MonoSubscribeTest {

    public static void main(String[] args) {

        Mono<String> mono = Mono
                .just("Hi, i am " + SubscriberUtil.FAKER.name().fullName())
                .map(s -> {
                    return s + ", from Sri Lanka.";
                })
                /*.map(s -> {
                    throw new RuntimeException("There is an error!");
                })*/;

        mono.subscribe(
                SubscriberUtil.onNext(),
                SubscriberUtil.onError(),
                SubscriberUtil.onComplete()
        );

    }
}
