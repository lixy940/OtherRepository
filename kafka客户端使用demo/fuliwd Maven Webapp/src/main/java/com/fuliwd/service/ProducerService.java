package com.fuliwd.service;

import javax.jms.Destination;

/**
 * Created by Administrator on 2017/3/3.
 */
public interface ProducerService {
    void sendMessage(Destination destination, final String message);
}
