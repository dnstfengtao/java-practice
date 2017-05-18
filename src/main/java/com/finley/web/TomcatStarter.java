package com.finley.web;

import java.io.File;
import java.io.IOException;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

/**
 * Tomcat starter.
 *
 * @author fengjiantao.
 * @since 5/18/17.
 */
@Getter
@Setter
public class TomcatStarter {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static final String DEFAULT_PROTOCOL = "org.apache.coyote.http11.Http11NioProtocol";
    private File baseDirectory;
    private int port;

    private File createTempDir(String prefix) {
        try {
            File tempDir = File.createTempFile(prefix + ".", "." + getPort());
            tempDir.delete();
            tempDir.mkdir();
            tempDir.deleteOnExit();
            return tempDir;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Tomcat createTomcat() {
        Tomcat tomcat = new Tomcat();

        File baseDir = (this.baseDirectory != null ? this.baseDirectory : createTempDir("tomcat"));
        tomcat.setBaseDir(baseDir.getAbsolutePath());
        Connector connector = new Connector(DEFAULT_PROTOCOL);
        tomcat.getService().addConnector(connector);
        tomcat.setConnector(connector);
        connector.setPort(port);
        tomcat.getHost().setAutoDeploy(false);

        return tomcat;
    }


    public static void main(String[] args) throws Exception {
        TomcatStarter tomcatStarter = new TomcatStarter();
        tomcatStarter.setPort(8080);
        Tomcat tomcat = tomcatStarter.createTomcat();
        tomcat.start();
        tomcat.getServer().await();
    }

}
