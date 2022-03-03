package com.example.travelserver.netty;

import com.example.travelserver.service.DialogService;
import com.example.travelserver.utils.JsonUtils;
import com.example.travelserver.utils.SpringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.util.Arrays;
import java.util.List;

/**
 * 用于处理消息的handler
 * TextWebSocketFrame专门用于处理文本的载体，在netty中
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    //用于记录和管理所有的客户端的channel
    public static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //获取客户端所传输的消息
        String content = msg.text();
        DataContent dataContent = JsonUtils.jsonToPojo(content,DataContent.class);
        Integer action = dataContent.getAction();
        Channel channel = ctx.channel();
        if(action.equals(MsgActionEnum.CONNECT.type)){
            //当websocket第一次open的时候，初始化channel，把用的channel和userid关联起来
            String fromUserId = dataContent.getChatMsg().getFromUserId();
            UserChannelRel.put(fromUserId,channel);
            System.out.println("open连接");
            //{"action": 1, "chatMsg": { "fromUserId": "123"}}
        }else if(action.equals(MsgActionEnum.CHAT.type)){
            //{"action": 2, "chatMsg": { "fromUserId": "123", "toUserId": "211005BC84519XWH", "content": "你好，我是inny"}}
            ChatMsg chatMsg = dataContent.getChatMsg();
            //保存消息到数据库
            DialogService dialogService = (DialogService) SpringUtil.getBean("dialogServiceImpl");
            int id = dialogService.insertDialog(chatMsg);
            //发送消息
            DataContent dataContentMsg = new DataContent();
            chatMsg.setDialogId((long)id);
            dataContentMsg.setChatMsg(chatMsg);
            Channel receiverChannel = UserChannelRel.get(chatMsg.getToUserId());
            if(receiverChannel == null){
                //离线用户
                System.out.println("离线用户1");
            }else{
                Channel findChannel = users.find(receiverChannel.id());
                if(findChannel!=null){
                    //用户在线
                    receiverChannel.writeAndFlush(new TextWebSocketFrame(
                            JsonUtils.objectToJson(dataContentMsg)
                    ));
                }else{
                    //离线用户
                    System.out.println("离线用户2");
                }
            }

        }else if(action.equals(MsgActionEnum.SIGNED.type)){
            //签收消息类型，修改数据库中对应的消息签收状态为1
            //{"action":3,"extend":"1,2"}
            DialogService dialogService = (DialogService) SpringUtil.getBean("dialogServiceImpl");
            List<String> msgIdList = Arrays.asList(dataContent.getExtend().split(","));
            System.out.println(msgIdList.toString());
            if(!msgIdList.isEmpty()){
                dialogService.updateDialogSigned(msgIdList);
            }
        }
        else if(action.equals(MsgActionEnum.KEEPALIVE.type)){
            //心跳
            System.out.println("收到来自channel为["+channel+"]的心跳包");
        }

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        users.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        String channelId = ctx.channel().id().asShortText();
        System.out.println("客户端被移除:channel id 为："+channelId);
        users.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //发生了异常后关闭连接，同时从channelGroup移除
        ctx.channel().close();
        users.remove(ctx.channel());
    }
}
