package com.example.travelserver.netty;

import io.netty.channel.Channel;

import java.util.HashMap;

/**
 * 用户id和channel的关联关系处理
 */
public class UserChannelRel {
    private static HashMap<String, Channel> manage = new HashMap<>();

    public static void put(String fromUserId, Channel channel) {
        manage.put(fromUserId, channel);
    }

    public static Channel get(String fromUserId) {
        return manage.get(fromUserId);
    }
}
