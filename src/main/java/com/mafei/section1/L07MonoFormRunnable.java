package com.mafei.section1;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/*
  @Author mafei
*/
public class L07MonoFormRunnable {
    public static void main(String[] args) {

        //notify after the completion. we can use this for like sending email task.
        Mono.fromRunnable(getName()).subscribe(
                SubscriberUtil.onNext(),
                SubscriberUtil.onError(),
                SubscriberUtil.onComplete()

        );
    }

    private static Runnable getName() {
        System.out.println("L07MonoFormRunnable.getName-entered");
        return () -> {
            SubscriberUtil.seep(5);
            System.out.println("Email has been sent.");
        };
    }
}
