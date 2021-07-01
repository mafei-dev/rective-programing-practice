package com.mafei.section2;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;

/*
  @Author mafei
*/
public class L01FluxIntro {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2);
        flux.subscribe(
                SubscriberUtil.onNext(),
                SubscriberUtil.onError(),
                SubscriberUtil.onComplete()
        );

        Flux<Object> empty = Flux.empty();
        empty.subscribe(
                SubscriberUtil.onNext(),
                SubscriberUtil.onError(),
                SubscriberUtil.onComplete()
        );
    }
}
