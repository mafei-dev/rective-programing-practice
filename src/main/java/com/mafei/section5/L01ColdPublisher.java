package com.mafei.section5;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

/*
  @Author mafei
*/
public class L01ColdPublisher {
    public static void main(String[] args) {

        Flux<String> movieStream = Flux.fromStream(() -> getMovies()).delayElements(Duration.ofSeconds(2));
        movieStream.subscribe(s -> {
            System.out.println("user 1 : " + s);
        });

        SubscriberUtil.seep(5);

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
