package nl.ypmania.demo.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * This class provides the configuration setup to Spring, which traditionally is
 * found in web.xml.
 * 
 * Since this class implements {@link WebApplicationInitializer}, it is
 * automatically picked up by spring's ServletContextInitializer to configure
 * the web application. ServletContextInitializer itself is found by Servlet
 * 3.0-containers picking up on
 * META-INF/services/javax.servlet.ServletContainerInitializer inside the
 * spring-web jar file.
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null; // our demo only has servlet config for the moment.
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        // All our webapp goes to Spring's dispatcher servlet
        // (this results in a web.xml equivalent servlet-mapping being created)
        return new String[] { "/" };
    }

}
