package com.mafei.section3.helper;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Date;
import java.util.function.Consumer;

/*
  @Author mafei
*/
public class NameProducer implements Consumer<FluxSink<String>> {

    private FluxSink<String> stringFluxSink;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.stringFluxSink = stringFluxSink;
    }

    public void produce() {
        String fullName = SubscriberUtil.FAKER.name().fullName();
        this.stringFluxSink.next(fullName);
    }

    public void doPrintTime() {
        this.stringFluxSink.next(new Date().toString());
    }


}
