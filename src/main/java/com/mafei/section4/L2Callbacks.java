package com.mafei.section4;

import reactor.core.publisher.Flux;

/*
  @Author mafei
*/
public class L2Callbacks {
    public static void main(String[] args) {
        /*doSomething()
                .doOnComplete(() -> {
                    System.out.println("doOnComplete");
                })
                .doFirst(() -> {
                    System.out.println("doFirst");
                })
                .doOnNext(o -> {
                    System.out.println("doOnNext: o = " + o);
                })
                .doOnSubscribe(subscription -> {
                    System.out.println("doOnSubscribe:subscription = " + subscription);
                })
                .doOnRequest(value -> {
                    System.out.println("doOnRequest:value = " + value);

                })
                .doFirst(() -> {
                    System.out.println("doFirst");
                })
                .doOnError(throwable -> {
                    System.err.println("doOnError:throwable = " + throwable);
                })
                .doOnTerminate(() -> {
                    System.out.println("doOnTerminate");
                })
                .doOnCancel(() -> {
                    System.out.println("doOnCancel");
                })
                .doFinally(signalType -> {
                    System.out.println("doFinally:signalType " + signalType);
                })
                .doOnDiscard(Object.class, o -> {
                    System.out.println("doOnDiscard:o " + o);
                })
                .subscribe(o -> {
                    System.out.println("o = " + o);
                });*/

        doSomething(false)
                .doFirst(() -> {
                    System.out.println("1-doFirst");
                })

                .doOnComplete(() -> {
                    System.out.println("1-doOnComplete");
                })
                .doOnNext(o -> {
                    System.out.println("1-doOnNext-1:o = " + o);
                })
                .doOnTerminate(() -> {
                    System.out.println("1-doOnTerminate");
                })
                .doOnSubscribe(subscription -> {
                    System.out.println("1-doOnSubscribe:subscription " + subscription);
                })
                .doOnError(throwable -> {
                    System.out.println("1-throwable = " + throwable);
                })
                .doOnCancel(() -> {
                    System.out.println("1-doOnCancel");
                })
                .doFinally(signalType -> {

                    System.out.println("1-doFinally:signalType " + signalType);
                })
                .subscribe(o -> {
                    System.out.println("1-o = " + o);
                });

        System.out.println("------------------------------------------------------");

        doSomething(true)
                .doFirst(() -> {
                    System.out.println("2-doFirst");
                })
                .doOnComplete(() -> {
                    System.out.println("2-doOnComplete");
                })
                .doOnNext(o -> {
                    System.out.println("2-doOnNext-1:o = " + o);
                })
                .doOnCancel(() -> {
                    System.out.println("2-doOnCancel");
                })
                .doOnTerminate(() -> {
                    System.out.println("2-doOnTerminate");
                })
                .doOnError(throwable -> {
                    System.out.println("2-throwable = " + throwable);
                })

                .doOnSubscribe(subscription -> {
                    System.out.println("2-doOnSubscribe:subscription " + subscription);
                })
                .doFinally(signalType -> {
                    System.out.println("2-doFinally:signalType " + signalType);
                })
                .take(2)
                .subscribe(o -> {
                    System.out.println("2-o = " + o);
                });


    }

    public static Flux<Object> doSomething(boolean isError) {
        return Flux.create(fluxSink -> {
            System.out.println("inside of create");
            for (int i = 1; i < 5; i++) {
                fluxSink.next(i);
            }
            if (isError) {
                fluxSink.error(new RuntimeException("ERROR"));
            } else {
                fluxSink.complete();
            }
            System.out.println("--completed");
        }).doOnSubscribe(subscription -> {
            System.out.println("from inside subscription");
        });
    }
}
