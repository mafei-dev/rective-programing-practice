package com.mafei.section4;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;

/*
  @Author mafei
*/
public class L01Handle {
    public static void main(String[] args) {
        /*doSomething()
                .take(16)  //even though the publisher publish 20 loop, subscriber gets 16.
                .subscribe(data -> {
                    System.out.println("data = " + data);
                });*/

        canadaExample().subscribe(o -> {
            System.out.println("name of the country " + o);
        }, throwable -> {

        }, () -> {
            System.err.println("complete");
        });
    }

    public static Flux<Object> doSomething() {
        //handle = filter + map
        return Flux.range(1, 20)
                .handle((integer, synchronousSink) -> {
                    System.err.println("--counting");
                    if (integer % 2 == 0) {
                        synchronousSink.next(integer); //filter part
                    } else {
                        synchronousSink.next(integer + "a"); //map part
                    }
                    if (integer == 15) {
                        synchronousSink.complete(); //after counted to 15, the mission is completed.
                    }
                });
    }

    public static Flux<Object> canadaExample() {
        return Flux.generate(synchronousSink -> {
            synchronousSink.next(SubscriberUtil.FAKER.country().name());
        })
                .map(Object::toString)
                .map(String::toLowerCase)
                .handle((o, synchronousSink) -> {
                    synchronousSink.next(o);
                    if (o.equals("canada")) {
                        synchronousSink.complete();
                    }
                });
    }

}
