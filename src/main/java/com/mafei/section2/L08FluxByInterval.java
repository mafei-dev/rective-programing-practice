package com.mafei.section2;

import com.mafei.section2.helper.NameGenerator;
import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

/*
  @Author mafei
*/
public class L08FluxByInterval {

    public static void main(String[] args) {
        Flux.interval(Duration.ofSeconds(1)).subscribe(aLong -> {
            System.out.println("aLong = " + aLong);
        });
        SubscriberUtil.seep(20);
    }
}
