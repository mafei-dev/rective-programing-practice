package com.mafei.section4;

import com.mafei.utils.SubscriberUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.time.Duration;
import java.util.*;

/*
  @Author mafei
*/

class User {
    private Integer userId;
    private String userName;

    public User(Integer userId) {
        this.userId = userId;
        this.userName = SubscriberUtil.FAKER.name().firstName();
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}

class Order {
    private String item;
    private Integer price;
    private String userId;

    @Override
    public String toString() {
        return "Order{" +
                "item='" + item + '\'' +
                ", price='" + price + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public Order(String userId) {
        this.userId = userId;
        this.item = SubscriberUtil.FAKER.commerce().productName();
        this.price = SubscriberUtil.FAKER.random().nextInt(1, 20);
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

class OrderService {
    private static Map<Integer, List<Order>> db = new HashMap<>();

    static {

        db.put(1, Arrays.asList(
                new Order("1"),
                new Order("2"),
                new Order("3")
        ));

        db.put(2, Arrays.asList(
                new Order("2"),
                new Order("2")
        ));
    }

    public static Flux<Order> getOrder(final Integer userId) {
        return Flux.create((FluxSink<Order> fluxSink) -> {
            db.get(userId).forEach(order -> {
                fluxSink.next(order);
            });
            fluxSink.complete();
        }).delayElements(Duration.ofSeconds(1));
    }
}

class UserService {
    public static Flux<User> getUsers() {
        return Flux.range(1, 2).map(integer -> new User(integer));
    }
}

public class L09FlatMap {
    public static void main(String[] args) {

        UserService.getUsers()
                .flatMap(user -> {
                    return OrderService.getOrder(user.getUserId());
                })
                .filter(order -> order.getPrice() > 10)
                .subscribe(order -> {
                    System.out.println("order = " + order);
                });

        SubscriberUtil.seep(5);
    }
}
