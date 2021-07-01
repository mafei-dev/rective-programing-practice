package com.mafei.section2;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/*
  @Author mafei
*/
public class L04FluxFromStream {
    public static void main(String[] args) {

        List<Integer> integerList = List.of(1, 2, 3, 4);
        Stream<Integer> integerStream = integerList.stream();
        //integerStream.forEach(System.out::println); //stream is closed after use one time
        //stream can't be use multiple times
        //integerStream.forEach(System.out::println);

        Flux<Integer> integerFlux = Flux.fromStream(() -> integerList.stream());

        //subscriber 1
        //use the stream successfully
        integerFlux.subscribe(
                SubscriberUtil.onNext(),
                SubscriberUtil.onError(),
                SubscriberUtil.onComplete()
        );

        //subscriber 2
        //an error will throw due to the 2nd use [stream has already been operated upon or closed]
        integerFlux.subscribe(
                SubscriberUtil.onNext(),
                SubscriberUtil.onError(),
                SubscriberUtil.onComplete()
        );


    }
}
