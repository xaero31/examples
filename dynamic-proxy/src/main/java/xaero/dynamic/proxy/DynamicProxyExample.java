package xaero.dynamic.proxy;

import lombok.extern.java.Log;

import java.lang.reflect.Proxy;

@Log
public class DynamicProxyExample {

    public static void main(String[] args) {
        final var classLoader = ObjectToProxyInterface.class.getClassLoader();
        final var interfaces = new Class[]{ObjectToProxyInterface.class};
        final var invocationHandler = new ObjectToProxyInvocationHandler(new ObjectToProxy());
        final var proxyInstance = (ObjectToProxyInterface) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        log.info(proxyInstance.getString());
    }
}
