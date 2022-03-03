package com.example.travelserver.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

public class WebSocketServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //获取管道(pipeline)
        ChannelPipeline pipeline = socketChannel.pipeline();
        //websocket 基于http协议，所需要的http编解码器
        pipeline.addLast(new HttpServerCodec());
        //在http上有一些数据流产生，有大有小，我们对其进行处理，用netty对其提供支持
        pipeline.addLast(new ChunkedWriteHandler());
        //对httpMessage进行聚合处理，聚合成request或response
        pipeline.addLast(new HttpObjectAggregator(1024*64));

        //------------------------增加心跳支持----------------------------
        //针对客户端，如果在1分钟内没有向服务器端发送读写心跳，则主动断开连接
        //如果有读、写空闲，则不做任何处理
        pipeline.addLast(new IdleStateHandler(40,50,60));
        //自定义空闲状态检测handler
        pipeline.addLast(new HeartBeatHandler());

        //本handler会处理一些繁重复杂的事情，握手动作：handshaking(close、ping、pong)
        //ping + pong = 心跳
        //websocket都是以frames进行传输的
        pipeline.addLast(new WebSocketServerProtocolHandler("/chat"));

        //自定义的handler
        pipeline.addLast(new ChatHandler());
    }
}
