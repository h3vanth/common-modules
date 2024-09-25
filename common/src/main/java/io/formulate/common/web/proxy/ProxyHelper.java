package io.formulate.common.web.proxy;

import org.apache.cxf.jaxrs.client.Client;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;

import java.util.function.Consumer;

public class ProxyHelper {
    public static <T> T createProxy(String baseUrl, Class<T> clazz) {
        return createProxy(baseUrl, clazz, null);
    }

    public static <T> T createProxy(String baseUrl, Class<T> clazz, Consumer<Client> clientConsumer) {
        Client client = WebClient.create(baseUrl);
        if (clientConsumer != null) {
            clientConsumer.accept(client);
        }
        return JAXRSClientFactory.fromClient(client, clazz);
    }
}
