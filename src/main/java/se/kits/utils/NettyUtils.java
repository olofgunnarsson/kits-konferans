package se.kits.utils;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.pipeline.PipelineConfigurators;
import io.reactivex.netty.protocol.http.AbstractHttpContentHolder;
import io.reactivex.netty.protocol.http.client.HttpClient;
import io.reactivex.netty.protocol.http.client.HttpClientRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.sse.ServerSentEvent;
import rx.Observable;
import rx.functions.Func1;

import java.io.UnsupportedEncodingException;

public class NettyUtils {
    public static Observable<String> createGetRequest(String host, int port, String uri) {
        final HttpClient<ByteBuf, ServerSentEvent> client = RxNetty.createHttpClient(host, port, PipelineConfigurators.<ByteBuf>clientSseConfigurator());
        final HttpClientRequest<ByteBuf> request = HttpClientRequest.createGet(uri);
        return client.submit(request)
                .flatMap(AbstractHttpContentHolder::getContent)
                .map(ServerSentEvent::contentAsString);
    }

    public static <R> Func1<R, Observable<? extends Void>> writeAsJson(HttpServerResponse<ServerSentEvent> response) {
        return object -> {
            final ByteBuf byteBuf;
            try {
                byteBuf = response.getAllocator().buffer().writeBytes(JsonHelper.getAsJson(object).getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            final ServerSentEvent msg = new ServerSentEvent(byteBuf);
            return response.writeAndFlush(msg);
        };
    }
}
