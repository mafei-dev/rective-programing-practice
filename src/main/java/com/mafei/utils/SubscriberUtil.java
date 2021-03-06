package com.mafei.utils;

import com.github.javafaker.Faker;
import com.mafei.section3.DefaultSubscriber;
import org.reactivestreams.Subscriber;

import java.util.function.Consumer;

/*
  @Author mafei
*/
public class SubscriberUtil {

    public final static Faker FAKER = Faker.instance();

    public static Consumer<Object> onNext() {
        return o -> System.out.println("object = " + o);
    }

    public static Consumer<Throwable> onError() {
        return e -> System.err.println("Error = " + e.getMessage());
    }

    public static Runnable onComplete() {
        return () -> System.out.println("completed.");
    }

    public static void seep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Subscriber<Object> subscriber() {
        return new DefaultSubscriber();
    }

    public static Subscriber<Object> subscriber(final String name) {
        return new DefaultSubscriber(name);
    }
}
