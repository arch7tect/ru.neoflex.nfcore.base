package ru.neoflex.nfcore.base.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.emfjson.jackson.module.EMFModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class Workspace {
    private final static Log logger = LogFactory.getLog(Workspace.class);
    @Value("${workspace.root:${user.dir}/workspace")
    String root;

    @PostConstruct
    void init() throws IOException {
        File rootDir = getRootDir();
        rootDir.mkdirs();
        try {
            Repository repo = getRepository(rootDir);
            repo.close();
        }
        catch (Exception ex) {

        }
    }

    private Repository getRepository() throws IOException {
        File rootDir = getRootDir();
        return getRepository(rootDir);
    }


    private Repository getRepository(File local) throws IOException {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        builder.findGitDir(local);
        builder.readEnvironment();
        builder.setMustExist(true);
        Repository repo = builder.build();
        return repo;
    }


    public File getRootDir() {
        return new File(root);
    }
}
