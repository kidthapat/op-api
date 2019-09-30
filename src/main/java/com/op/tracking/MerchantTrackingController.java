package com.op.tracking;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MerchantTrackingController {
    private static Log LOG = LogFactory.getLog(MerchantTrackingController.class);

    @MessageMapping("/users/{id}")
    @SendTo("/notifyAll")
    public String notifyToUser(@DestinationVariable String id) {
        LOG.info("user id: " + id);
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
}
