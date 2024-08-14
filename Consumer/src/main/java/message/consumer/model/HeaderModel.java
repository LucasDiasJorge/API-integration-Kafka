package message.consumer.model;

import java.util.HashMap;
import java.util.Map;

public class HeaderModel {

    private String uri;
    private String httpMethod;
    private String principal;
    private ServerModel server;

    public HeaderModel() {
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
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
        ret.put("uri", getUri());
        ret.put("httpMethod", getHttpMethod());
        ret.put("principal", getPrincipal());
        ret.put("server", server);
        return ret;
    }
}
