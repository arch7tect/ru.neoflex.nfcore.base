package ru.neoflex.nfcore.base.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.nfcore.base.components.Publisher;

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
    private Workspace workspace;
    @Autowired
    private Publisher publisher;
    @Autowired
    private Epsilon epsilon;

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

    public Workspace getWorkspace() {
        return workspace;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public Epsilon getEpsilon() {
        return epsilon;
    }
}
