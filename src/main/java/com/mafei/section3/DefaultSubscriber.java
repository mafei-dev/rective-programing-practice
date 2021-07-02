package com.mafei.section3;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


/*
  @Author mafei
*/

public class DefaultSubscriber implements Subscriber<Object> {

    private String name = " - ";

    public DefaultSubscriber() {
    }

    public DefaultSubscriber(String name) {
        this.name = name = " - ";
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);

    }

    @Override
    public void onNext(Object o) {
        System.out.println(name + "Received = " + o);
    }

    @Override
    public void onError(Throwable t) {
        System.err.println(name + "ERROR " + t.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println(name + "Completed.");
    }
}
