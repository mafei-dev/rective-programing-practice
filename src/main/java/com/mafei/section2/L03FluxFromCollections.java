package com.mafei.section2;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/*
  @Author mafei
*/
public class L03FluxFromCollections {
    public static void main(String[] args) {

        List<String> dataList = new ArrayList<>();
        dataList.add("A");
        dataList.add("B");
        dataList.add("C");

        Flux<String> flux = Flux.fromIterable(dataList);

        flux.subscribe(
                SubscriberUtil.onNext()
        );

    }
}
