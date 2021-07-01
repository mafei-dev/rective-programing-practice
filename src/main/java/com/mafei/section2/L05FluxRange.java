package com.mafei.section2;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

/*
  @Author mafei
*/
public class L05FluxRange {
    public static void main(String[] args) {

        //just like a for loop
        Flux.range(1, 20)
                .log()
                .map(integer -> SubscriberUtil.FAKER.name().firstName())
                .subscribe(
                        SubscriberUtil.onNext(),
                        SubscriberUtil.onError(),
                        SubscriberUtil.onComplete()
                );


    }
}
