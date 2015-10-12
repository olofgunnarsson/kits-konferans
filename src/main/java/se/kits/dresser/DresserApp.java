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

import static se.kits.utils.NettyUtils.createGetRequest;
import static se.kits.utils.NettyUtils.writeAsJson;

public class DresserApp {

    public static final int PEOPLE_APP_PORT = 1234;
    public static final int CLOTHING_APP_PORT = 1235;

    public static void main(String[] args) {
        HttpServer<ByteBuf, ServerSentEvent> server = RxNetty.createHttpServer(
                1236,
                (request, response) ->
                        Observable
                                .zip(getShirts(), getPants(), getShoes(), getPeople(),
                                        ((shirt, pants, shoes, person) -> new DressedPerson(person, shirt, pants, shoes)))
                                .flatMap(writeAsJson(response)),
                PipelineConfigurators.<ByteBuf>serveSseConfigurator());
        System.out.println("Dresser server started...");
        server.startAndWait();
    }

    public static Observable<Person> getPeople() {
        return createGetRequest("localhost", PEOPLE_APP_PORT, "/")
                .onBackpressureBuffer()
                .map(s -> JsonHelper.getAsObject(s, Person.class));
    }

    public static Observable<Clothing> getShirts() {
        return createGetRequest("localhost", CLOTHING_APP_PORT, "/shirts")
                .onBackpressureDrop()
                .map(s -> JsonHelper.getAsObject(s, Clothing.class));
    }

    public static Observable<Clothing> getPants() {
        return createGetRequest("localhost", CLOTHING_APP_PORT, "/pants")
                .onBackpressureDrop()
                .map(s -> JsonHelper.getAsObject(s, Clothing.class));
    }

    public static Observable<Clothing> getShoes() {
        return createGetRequest("localhost", CLOTHING_APP_PORT, "/shoes")
                .onBackpressureDrop()
                .map(s -> JsonHelper.getAsObject(s, Clothing.class));
    }
}
