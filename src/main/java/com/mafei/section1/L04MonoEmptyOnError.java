package com.mafei.section1;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Mono;

/*
  @Author mafei
*/
public class L04MonoEmptyOnError {
    public static void main(String[] args) {

        userRepo(2).subscribe(
                SubscriberUtil.onNext(),
                SubscriberUtil.onNext(),
                SubscriberUtil.onComplete()
        );
    }

    private static Mono<String> userRepo(int userId) {
        if (userId == 1) {
            return Mono.just(SubscriberUtil.FAKER.name().firstName());
        } else if (userId == 2) {
            return Mono.empty();
        } else {
            return Mono.error(new RuntimeException("404"));
        }
    }
}
