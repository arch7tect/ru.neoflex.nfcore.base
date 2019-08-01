package ru.neoflex.nfcore.base.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.emfjson.jackson.module.EMFModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class Workspace {
    private final static Log logger = LogFactory.getLog(Workspace.class);
    @Value("${workspace.root:${user.dir}/workspace}")
    String root;

    @PostConstruct
    void init() throws GitAPIException {
        File rootDir = getRootDir();
        rootDir.mkdirs();
        try {
            Repository repo = getRepository(rootDir);
            repo.close();
        }
        catch (Exception ex) {
            Git git = Git.init().setDirectory(rootDir).call();
            try {
            } finally {
                git.close();
            }
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


    public File getFile(String path) {
        return new File(getRootDir(), path);
    }

    public File getRootDir() {
        return new File(root);
    }

    public ClassLoader getClassLoader(ClassLoader parent) throws MalformedURLException {
        URL rootURL = getRootDir().toURI().toURL();
        return new URLClassLoader(new URL[] {rootURL}, parent);
    }

    public<R> R withClassLoader(Callable<R> f) throws Exception {
        ClassLoader parent = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(getClassLoader(parent));
        try {
            return f.call();
        }
        finally {
            Thread.currentThread().setContextClassLoader(parent);
        }
    }
}
