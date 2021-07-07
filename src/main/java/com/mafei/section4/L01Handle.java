package com.mafei.section4;

import reactor.core.publisher.Flux;

/*
  @Author mafei
*/
public class L01Handle {
    public static void main(String[] args) {
        doSomething()
                .take(16)  //even though the publisher publish 20 loop, subscriber gets 16.
                .subscribe(data -> {
                    System.out.println("data = " + data);
                });
    }

    public static Flux<Object> doSomething() {
        //handle = filter + map
        return Flux.range(1, 20)
                .handle((integer, synchronousSink) -> {
                    System.out.println("--counting");
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

}
