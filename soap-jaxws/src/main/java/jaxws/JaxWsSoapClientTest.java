package jaxws;

import client.simpleservice.SimpleModel;
import client.simpleservice.SimpleService;
import client.simpleservice.SimpleServiceService;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

public class JaxWsSoapClientTest {
    public static void main(String[] args) throws MalformedURLException {
        final SimpleServiceService service = new SimpleServiceService(new URL("http://service.url/"));
        final SimpleService endpoint = service.getSimpleServicePort();

        final BindingProvider provider = (BindingProvider) endpoint;
        provider.getBinding().getHandlerChain().add(EncodingSOAPHandler.getSoapHandler()); // handler for getting correct encoding

        final Map<String, Object> context = provider.getRequestContext();
        context.put(BindingProvider.USERNAME_PROPERTY, "username"); // basic auth
        context.put(BindingProvider.PASSWORD_PROPERTY, "password"); // basic auth
        context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "service.endpoint.url"); // endpoint http url

        final SimpleModel model = endpoint.getModel(); // invoke remote soap service
    }

    private static class EncodingSOAPHandler {
        static SOAPHandler<SOAPMessageContext> getSoapHandler() {
            return new SOAPHandler<SOAPMessageContext>() {
                @Override
                public Set<QName> getHeaders() {
                    return null;
                }

                @Override
                public boolean handleMessage(SOAPMessageContext context) {
                    Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
                    if (outbound) {
                        try {
                            context.getMessage().setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "UTF-8");
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return true;
                }

                @Override
                public boolean handleFault(SOAPMessageContext context) {
                    return true;
                }

                @Override
                public void close(MessageContext context) {}
            };
        }
    }
}
