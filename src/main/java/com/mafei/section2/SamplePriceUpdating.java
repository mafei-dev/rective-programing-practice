package com.mafei.section2;

import com.mafei.utils.SubscriberUtil;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/*
  @Author mafei
*/
public class SamplePriceUpdating {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        getPrice().subscribeWith(new Subscriber<Integer>() {
            Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                if (integer > 2) {
                    System.out.println("integer = " + integer);
                } else {
                    this.subscription.cancel();
                    latch.countDown();
                }

            }

            @Override
            public void onError(Throwable t) {
                latch.countDown();

            }

            @Override
            public void onComplete() {
                latch.countDown();
            }
        });
        latch.await();
    }

    public static Flux<Integer> getPrice() {
        return Flux.interval(Duration.ofSeconds(1)).map(aLong -> {
            return ThreadLocalRandom.current().nextInt(0, 20);
        });
    }


}
