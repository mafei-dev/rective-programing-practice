package com.mafei.section1;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/*
  @Author mafei
*/
public class L05MonoFormFuture {
    public static void main(String[] args) {


        Mono.fromFuture(getName()).subscribe(SubscriberUtil.onNext());
        SubscriberUtil.seep(1);
    }

    private static CompletableFuture<String> getName() {
        System.out.println("generating the name..");
        return CompletableFuture.supplyAsync(() -> SubscriberUtil.FAKER.name().firstName());
    }
}
