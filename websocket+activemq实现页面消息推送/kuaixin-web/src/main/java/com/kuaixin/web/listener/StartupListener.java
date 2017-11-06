package com.kuaixin.web.listener;

import com.kuaixin.web.websocket.WebSocketEndPoint;
import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;

public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = Logger.getLogger(StartupListener.class);

    @Bean
    public WebSocketEndPoint webSocketEndPoint() {
        return new WebSocketEndPoint();
    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(6000);
                        MotanSwitcherUtil.setSwitcherValue(
                                MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
                        logger.info("注册服务成功");

                        ActivemqThread activemqThread = new ActivemqThread(webSocketEndPoint());
                        Thread t = new Thread(activemqThread);
                        t.setDaemon(true);
                        t.start();
                    } catch (InterruptedException e) {
                        logger.error(e.getMessage());
                    }
                }
            }).start();
        }
    }
}