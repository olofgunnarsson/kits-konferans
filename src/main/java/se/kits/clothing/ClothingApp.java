package se.kits.clothing;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.pipeline.PipelineConfigurators;
import io.reactivex.netty.protocol.http.server.HttpServer;
import io.reactivex.netty.protocol.http.sse.ServerSentEvent;
import rx.Observable;
import se.kits.utils.NettyUtils;

import java.net.URI;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class ClothingApp {

    public static void main(String[] args) {
        HttpServer<ByteBuf, ServerSentEvent> server = RxNetty.createHttpServer(
                1235,
                (request, response) -> {
                    final URI uri = URI.create(request.getUri());
                    if (uri.getPath().equals("/shirts"))
                        return Observable
                                .interval(1, MILLISECONDS)
                                .map(aLong -> RandomClothing.getRandomShirt())
                                .flatMap(NettyUtils.writeAsJson(response));

                    else if (uri.getPath().equals("/pants")) {
                        return Observable
                                .interval(1, MILLISECONDS)
                                .map(aLong -> RandomClothing.getRandomPants())
                                .flatMap(NettyUtils.writeAsJson(response));
                    } else if (uri.getPath().equals("/shoes"))
                        return Observable
                                .interval(1, MILLISECONDS)
                                .map(aLong -> RandomClothing.getRandomShoes())
                                .flatMap(NettyUtils.writeAsJson(response));
                    return Observable.empty();
                },
                PipelineConfigurators.<ByteBuf>serveSseConfigurator());
        System.out.println("Clothing server started...");
        server.startAndWait();
    }
}
