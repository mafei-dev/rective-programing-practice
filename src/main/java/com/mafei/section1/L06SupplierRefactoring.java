package com.mafei.section1;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;

/*
  @Author mafei
*/
public class L06SupplierRefactoring {
    public static void main(String[] args) {

        getName();
        getName().subscribe(SubscriberUtil.onNext());
        getName();
        getName();


    }

    //building pipeline and executing the pipe line are separate things
    private static Mono<String> getName() {
        System.out.println("Entered gatName");
        return Mono.fromSupplier(() -> {
            System.out.println("generating the name..");
            SubscriberUtil.seep(3);
            return SubscriberUtil.FAKER.name().firstName();
        }).map(String::toUpperCase);
    }
}
