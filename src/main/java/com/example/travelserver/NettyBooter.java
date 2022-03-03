package com.example.travelserver;

import com.example.travelserver.netty.WebSocketServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null){
            try{
                WebSocketServer.getInstance().start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
