package ru.neoflex.nfcore.base.services;

import com.fasterxml.jackson.databind.JsonNode;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;

@Service
public class Groovy {
    private static final Logger logger = LoggerFactory.getLogger(Groovy.class);

    @Autowired
    Context context;

    public Object eval(Object instance, String method, List args) throws Exception {
        return context.withClassLoader(() -> {
            Binding b = new Binding();
            b.setVariable("instance", instance);
            b.setVariable("method", method);
            b.setVariable("args", args);
            GroovyShell sh = new GroovyShell(b);
            Object result =  sh.evaluate("instance.\"$method\"(*args)");
            return result;
        });
    }

    public Object callStatic(String fullClassName, String method, JsonNode args) throws Exception {
        return context.withClassLoader(() -> {
            Class scriptClass = Thread.currentThread().getContextClassLoader().loadClass(fullClassName);
            Method declaredMethod = scriptClass.getDeclaredMethod(method, new Class[] {args.getClass()} );
            Object result = declaredMethod.invoke(null, new Object[]{args});
            return result;
        });
    }
}
