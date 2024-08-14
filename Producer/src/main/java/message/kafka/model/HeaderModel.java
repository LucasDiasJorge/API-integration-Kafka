package message.kafka.model;

import org.springframework.http.HttpMethod;

import java.net.URI;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

public class HeaderModel {

    private URI uri;
    private HttpMethod httpMethod;
    private Principal principal;
    private ServerModel server;

    public HeaderModel(String uri, String httpMethod, Principal principal, ServerModel server) {
        this.uri = URI.create(uri);
        this.httpMethod = HttpMethod.valueOf(httpMethod);
        this.principal = principal;
        this.server = server;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public ServerModel getServer() {
        return server;
    }

    public void setServer(ServerModel server) {
        this.server = server;
    }

    public Map<String,Object> toMap(){
        Map<String, Object> ret = new HashMap<>();
        ret.put("uri", uri.toString());
        ret.put("httpMethod", httpMethod.toString());
        ret.put("principal", principal.getName());
        ret.put("server", server);
        return ret;
    }
}
