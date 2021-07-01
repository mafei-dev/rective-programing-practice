package com.mafei.section2;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;

/*
  @Author mafei
*/
public class L02FluxMultipleSubscribers {
    public static void main(String[] args) {
        Flux<Object> flux = Flux.just(1, 2, 3, 5, "mafei");

        //this subscriber interesting only for String.
        flux.filter(o -> o instanceof String)
                .subscribe(
                        o -> {
                            System.out.println("Name is = " + o);
                        },
                        SubscriberUtil.onError(),
                        () -> System.out.println("name subscriber completed.")
                );

        //this subscriber interesting only for numbers [Integer].
        flux.filter(o -> o instanceof Integer).subscribe(
                SubscriberUtil.onNext(),
                SubscriberUtil.onError(),
                () -> System.out.println("number subscribe completed.")
        );
    }
}
