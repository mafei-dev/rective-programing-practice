package com.mafei.section2;

import com.mafei.utils.SubscriberUtil;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

/*
  @Author mafei
*/
public class L06Subscription {
    public static void main(String[] args) {
        AtomicReference<Subscription> atomicReference = new AtomicReference<>();


        Flux.range(1, 20)
                .log()
                .subscribeWith(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("Received sub " + subscription);
                        atomicReference.set(subscription);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        //
                        System.out.println("onNext.integer = " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.err.println("onError " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Completed.");
                    }
                });

        SubscriberUtil.seep(3);
        atomicReference.get().request(3);
        SubscriberUtil.seep(5);
        atomicReference.get().request(3);
        SubscriberUtil.seep(5);
        System.out.println("going to cancel");
        atomicReference.get().cancel();
        //after cancel, the request is not obtained
        SubscriberUtil.seep(3);
        atomicReference.get().request(6);

    }
}
