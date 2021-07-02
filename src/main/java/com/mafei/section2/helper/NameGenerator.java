package com.mafei.section2.helper;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/*
  @Author mafei
*/
public class NameGenerator {

    public static List<String> getNames(int count) {
        List<String> names = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            names.add(getName());
        }
        return names;
    }

    private static String getName() {
        SubscriberUtil.seep(1);
        return SubscriberUtil.FAKER.name().firstName();
    }

    public static Flux<String> getName(int count) {
        return Flux.range(0, count)
                .map(integer -> getName());
    }
}