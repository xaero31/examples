package xaero.dynamic.proxy;

import lombok.extern.java.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Log
public class ObjectToProxyInvocationHandler implements InvocationHandler {

    private final ObjectToProxyInterface originalObject;

    public ObjectToProxyInvocationHandler(ObjectToProxyInterface originalObject) {
        this.originalObject = originalObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("invocation of [" + method.getName() + "] method from [" + proxy.getClass().getName() + "]");
        log.info("original method result is: [" + method.invoke(originalObject) + "]");
        return "hello from proxy";
    }
}
