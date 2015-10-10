package se.kits.dresser;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.pipeline.PipelineConfigurators;
import io.reactivex.netty.protocol.http.server.HttpServer;
import io.reactivex.netty.protocol.http.sse.ServerSentEvent;
import rx.Observable;
import se.kits.clothing.Clothing;
import se.kits.people.Person;
import se.kits.utils.JsonHelper;
import se.kits.utils.NettyUtils;

public class DresserApp {

    public static void main(String[] args) {
        HttpServer<ByteBuf, ServerSentEvent> server = RxNetty.createHttpServer(
                1236,
                (request, response) ->
                        Observable
                                .zip(getClothing(), getPeople(), (clothing, person) -> new DressedPerson(person, clothing))
                                .flatMap(NettyUtils.writeResponse(response)),
                PipelineConfigurators.<ByteBuf>serveSseConfigurator());
        System.out.println("Dresser server started...");
        server.startAndWait();
    }

    public static Observable<Person> getPeople() {
        return NettyUtils
                .createGetRequest("localhost", 1234)
                .map(s -> JsonHelper.getAsObject(s, Person.class));

    }

    public static Observable<Clothing> getClothing() {
        return NettyUtils
                .createGetRequest("localhost", 1235)
                .map(s -> JsonHelper.getAsObject(s, Clothing.class));

    }


}
