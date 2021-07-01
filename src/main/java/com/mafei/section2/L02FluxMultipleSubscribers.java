package com.mafei.section2;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;

/*
  @Author mafei
*/
public class L02FluxMultipleSubscribers {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3, 5);
        flux.subscribe(
                SubscriberUtil.onNext(),
                SubscriberUtil.onError(),
                () -> System.out.println("subscribe one completed.")
        );

        flux.subscribe(
                SubscriberUtil.onNext(),
                SubscriberUtil.onError(),
                () -> System.out.println("subscribe two completed.")
        );


    }
}
