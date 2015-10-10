package se.kits.clothing;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.pipeline.PipelineConfigurators;
import io.reactivex.netty.protocol.http.server.HttpServer;
import io.reactivex.netty.protocol.http.sse.ServerSentEvent;
import rx.Observable;
import se.kits.utils.NettyUtils;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ClothingApp {

    public static void main(String[] args) {
        HttpServer<ByteBuf, ServerSentEvent> server = RxNetty.createHttpServer(
                1235,
                (request, response) ->
                        Observable
                                .interval(1, SECONDS)
                                .map(aLong -> RandomClothing.getRandomCloth())
                                .flatMap(NettyUtils.writeResponse(response)),
                PipelineConfigurators.<ByteBuf>serveSseConfigurator());
        System.out.println("Clothing server started...");
        server.startAndWait();
    }
}
