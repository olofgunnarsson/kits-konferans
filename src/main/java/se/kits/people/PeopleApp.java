package se.kits.people;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.pipeline.PipelineConfigurators;
import io.reactivex.netty.protocol.http.server.HttpServer;
import io.reactivex.netty.protocol.http.sse.ServerSentEvent;
import rx.Observable;
import se.kits.utils.NettyUtils;

import java.util.ArrayList;
import java.util.List;

public class PeopleApp {
    private static List<Person> PEOPLE = new ArrayList<Person>() {{
        add(new Person("Johan", "Herbo"));
        add(new Person("Gustav", "Jorlöv"));
        add(new Person("Patrik", "Nilsson"));
        add(new Person("Per", "Stolpe"));
        add(new Person("Olof", "Gunnarsson"));
        add(new Person("Sture", "Svensson"));
        add(new Person("Joakim", "Jonsson"));
        add(new Person("Håkan", "Wall"));
        add(new Person("Patrik", "Nilsson"));
        add(new Person("Joakim", "Johansson"));
        add(new Person("Tobias", "Lans"));
        add(new Person("Hoi-Man", "Lui"));
        add(new Person("Pierre", "Sandboge"));
    }};


    public static void main(String[] args) {
        HttpServer<ByteBuf, ServerSentEvent> server = RxNetty.createHttpServer(
                1234,
                (request, response) ->
                        Observable.from(PEOPLE)
                                .flatMap(NettyUtils.writeAsJson(response)),
                PipelineConfigurators.<ByteBuf>serveSseConfigurator());
        System.out.println("People server started...");
        server.startAndWait();
    }
}
