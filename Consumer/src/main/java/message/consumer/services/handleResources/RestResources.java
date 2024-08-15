package message.consumer.services.handleResources;

import message.consumer.model.HeaderModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;

@Service
public class RestResources {

    private final Map<String, Function<HeaderModel, Object>> integrationMap = Map.of(
            "GET:/api/resource1", this::populateItem,
            "POST:/api/v2/item", this::createNewItem,
            "GET:/api/resource2", this::handleGetResource2
            // Add more mappings as needed
    );

    public Map<String, Function<HeaderModel, Object>> getIntegrationMap() {
        return integrationMap;
    }

    private HttpStatus populateItem(HeaderModel header) {
        // Integration logic for GET /api/resource1
        // http
        // Always with error treatment
        return null;
    }

    private HttpStatus createNewItem(HeaderModel header) {
        // Integration logic for POST /api/resource1
        return null;
    }

    private HttpStatus handleGetResource2(HeaderModel header) {
        // Integration logic for GET /api/resource2
        return null;
    }

}
