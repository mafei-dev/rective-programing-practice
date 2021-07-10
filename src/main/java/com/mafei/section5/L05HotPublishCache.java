package com.mafei.section5;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

/*
  @Author mafei
*/
public class L05HotPublishCache {

    public static void main(String[] args) {

        //cache = publish().replay()
        Flux<String> movieStream = Flux.fromStream(() -> getMovies())
                .delayElements(Duration.ofSeconds(1))
                //cache = publish().replay()
                //the publisher does store the published data until new one comes.
                // after came, the publisher push missing data at once.
                //cache time = int.max [if we want, can be changed]
                .cache();

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
