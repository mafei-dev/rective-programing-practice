package com.mafei.section5;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

/*
  @Author mafei
*/
public class L04HotPublishAutoConnect {
    public static void main(String[] args) {

        //share = publish().refCount(1)
        Flux<String> movieStream = Flux.fromStream(() -> getMovies())
                .delayElements(Duration.ofSeconds(1))
                //no need subscriber to publish data.
                // if one subscriber subscribe to publisher,
                // then publish the data as usual from the time that the subscriber joined
                .publish().autoConnect(0);

        SubscriberUtil.seep(3);

        movieStream.subscribe(s -> {
            System.out.println("user 1 : " + s);
        });

        SubscriberUtil.seep(10);

        System.out.println("user 2 is about to join");

        movieStream.subscribe(s -> {
            System.out.println("user 2 : " + s);
        });

        SubscriberUtil.seep(60);

    }

    public static Stream<String> getMovies() {
        System.out.println("L01ColdPublisher.getMovies");
        return Stream.of(
                "Scene 1",
                "Scene 2",
                "Scene 3",
                "Scene 4",
                "Scene 5",
                "Scene 7"
        );
    }
}
