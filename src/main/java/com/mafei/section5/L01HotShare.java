package com.mafei.section5;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

/*
  @Author mafei
*/
public class L01HotShare {
    public static void main(String[] args) {

        //share is a one method to become a hot publisher
        //then all publishers will be received data altogether without past data
        //the later subscribers will not be received the old published data he is received from the current
        //it is line a tv.
        Flux<String> movieStream = Flux.fromStream(() -> getMovies())
                .delayElements(Duration.ofSeconds(2))
                .share();


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
