package com.mafei.section3;

import com.mafei.section3.helper.NameProducer;
import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;

/*
  @Author mafei
*/
public class L02FluxCreateRefactor {
    public static void main(String[] args) {
        NameProducer nameProducer = new NameProducer();
        Flux.create(nameProducer)
                .subscribe(SubscriberUtil.subscriber());
        nameProducer.produce();
        nameProducer.doPrintTime();
        nameProducer.produce();
    }
}
