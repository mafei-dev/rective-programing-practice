package com.mafei.section4;

import reactor.core.publisher.Flux;

/*
  @Author mafei
*/
public class L06DefaultIfEmpty {
    public static void main(String[] args) {
        getOrderNums()
                .filter(integer -> integer > 10)
                .defaultIfEmpty(-0)
                .subscribe(integer -> {
                    System.out.println("1-integer = " + integer);
                });

        getOrderNums()
                .filter(integer -> integer > 5)
                .defaultIfEmpty(-0)
                .subscribe(integer -> {
                    System.out.println("2-integer = " + integer);
                });
    }

    public static Flux<Integer> getOrderNums() {
        return Flux.range(1, 10);
    }
}
