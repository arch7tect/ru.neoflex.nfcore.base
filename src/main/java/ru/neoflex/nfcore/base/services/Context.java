package ru.neoflex.nfcore.base.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.emfjson.jackson.module.EMFModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Context {
    private final static Log logger = LogFactory.getLog(Context.class);

    @Autowired
    private Context current;
    @Autowired
    private Store store;
    @Autowired
    private Groovy groovy;
    @Autowired
    private
    EMFModule emfModule;

    private static final ThreadLocal<Context> tlContext = new ThreadLocal<Context>();

    public static Context getCurrent() {
        return tlContext.get();
    }

    public void setCurrent() {
        tlContext.set(current);
    }

    public Store getStore() {
        return store;
    }

    public Groovy getGroovy() {
        return groovy;
    }

    public EMFModule getEmfModule() {
        return emfModule;
    }

    public ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(emfModule);
        return mapper;
    }

}
