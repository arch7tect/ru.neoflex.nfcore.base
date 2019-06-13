package ru.neoflex.nfcore.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.neoflex.nfcore.base.cdo.CDOServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletException;
import java.util.logging.Logger;

@Service
public class CDOSvc {
    private final static Logger logger = Logger.getLogger(CDOSvc.class.getName());
    private final static String name = "nfcdoserver";
    @Value("${database.url:jdbc:postgresql://localhost:5432/cdo}")
    String url;
    @Value("${database.user:postgres}")
    String user;
    @Value("${database.password:postgres}")
    String password;

    private CDOServer server;

    @PostConstruct
    public void init() throws ServletException {
        server = new CDOServer(name, url, user, password);
        server.start();
        logger.info(String.format("CDO server '%s' started", name));
    }

    @PreDestroy
    public void fini() {
        server.stop();
    }

    public CDOServer getServer() {
        return server;
    }
}
