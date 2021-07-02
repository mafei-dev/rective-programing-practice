package com.mafei.section2;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
  @Author mafei
*/
public class FluxFromMono {
    public static void main(String[] args) {
        Mono<String> name = Mono.just("mafei");
        Flux<String> flux = Flux.from(name);
        flux.subscribe(_name -> {
            System.out.println("name = " + _name);
        });


        //this is how get mono get from flux
        Flux.range(1, 20)
                .next()
                .subscribe(integer -> {
                    System.out.println("integer = " + integer);
                });

        //with filters
        Flux.range(1, 20)
                .filter(integer -> integer > 5)
                .next()
                .subscribe(integer -> {
                    System.out.println("integer = " + integer);
                });

        //with filters
        Flux.range(1, 20)
                .next()
                .filter(integer -> integer > 5)
                .subscribe(integer -> {
                    System.out.println("integer = " + integer);
                });


    }
}
