package com.mafei.utils;

import java.util.function.Consumer;

/*
  @Author mafei
*/
public class SubscriberUtil {
    public static Consumer<Object> onNext() {
        return o -> System.out.println("object = " + o);
    }

    public static Consumer<Throwable> onError() {
        return e -> System.err.println("Error = " + e.getMessage());
    }

    public static Runnable onComplete() {
        return () -> System.err.println("completed.");
    }

}
