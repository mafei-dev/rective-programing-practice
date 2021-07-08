package com.mafei.section4;

import reactor.core.publisher.Flux;

/*
  @Author mafei
*/
public class L06SwitchIfEmpty {
    public static void main(String[] args) {

        //01
        getOrderNums()
                .filter(integer -> integer > 10)
                .switchIfEmpty(s -> {
                    System.out.println("switchIfEmpty fallback");
                })
                .subscribe(integer -> {
                    System.out.println("1-integer = " + integer);
                });
        //02
        getOrderNums()
                .filter(integer -> integer > 50)
                .switchIfEmpty(fallback())
                .filter(integer -> integer > 12)
                .subscribe(integer -> {
                    System.out.println("1-integer = " + integer);
                });

        //03
        getOrderNums()
                .filter(integer -> integer > 10)
                .switchIfEmpty(fallback())
                .subscribe(integer -> {
                    System.out.println("1-integer = " + integer);
                });


        /*As an example, think that you have redis cache,
        but when you call them, there is no cache data
        then you can call the database,
        like this kind of cases we can use this.
         */
    }

    public static Flux<Integer> getOrderNums() {
        return Flux.range(1, 10);
    }

    public static Flux<Integer> fallback() {
        return Flux.range(20, 10);
    }


}
