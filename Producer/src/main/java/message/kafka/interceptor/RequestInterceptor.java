package message.kafka.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import message.kafka.model.HeaderModel;
import message.kafka.service.DataProducerService;
import org.apache.kafka.common.header.Header;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    private Logger logger = LogManager.getLogger("ProducerInterceptorLogger");

    private final DataProducerService dataProducerService;

    public RequestInterceptor(DataProducerService dataProducerService) {
        this.dataProducerService = dataProducerService;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        HttpServletRequest req = request;

        HeaderModel header = new HeaderModel(req.getRequestURI(),req.getMethod(),req.getUserPrincipal(),null);

        logger.info("Sending the message to the DataProducerService");

        dataProducerService.sendMessage((Serializable) header.toMap());

        logger.info("Message sent");

    }
}
