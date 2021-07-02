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

    }
}
