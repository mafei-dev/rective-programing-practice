package com.mafei.section3;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;

/*
  @Author mafei
*/
public class L01FluxCrater {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            String countryName;
            do {
                countryName = SubscriberUtil.FAKER.country().name();
                fluxSink.next(countryName);
            } while (!countryName.toLowerCase().equals("canada"));
            fluxSink.complete();
        }).subscribe(SubscriberUtil.subscriber());
    }
}
