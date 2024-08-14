package message.kafka.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import message.kafka.model.HeaderModel;
import message.kafka.service.DataProducerService;
import message.kafka.util.CachedBodyHttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

@Component
@Order(1)
public class ProducerFilter implements Filter {

    private Logger logger = LogManager.getLogger("ProducerFilterLogger");

    private final DataProducerService dataProducerService;

    public ProducerFilter(DataProducerService dataProducerService) {
        this.dataProducerService = dataProducerService;
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) {

        HttpServletRequest req = (HttpServletRequest) request;

        HeaderModel header = new HeaderModel(req.getRequestURI(),req.getMethod(),req.getUserPrincipal(),null);

        logger.info("Sending the message to the DataProducerService");

        dataProducerService.sendMessage((Serializable) header.toMap());

        logger.info("Message sent");

    }
}
