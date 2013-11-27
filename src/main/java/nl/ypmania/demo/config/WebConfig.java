package nl.ypmania.demo.config;

import java.util.List;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.oxm.jaxb.PublicJaxb2Scanner;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "nl.ypmania.demo.controller" })
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")   // Any request to /resources/...
                .addResourceLocations("/resources/");  // is served from src/main/webapp/resources/...
                                                       // (or the root of the packaged WAR)
    }
    
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(xmlConverter());
        converters.add(jsonConverter());
    }
    
    /** Creates a JSON converter for Jackson, reading both JAXB and Jackson annotations */
    public MappingJacksonHttpMessageConverter jsonConverter() {
        MappingJacksonHttpMessageConverter m = new MappingJacksonHttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
        AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
        AnnotationIntrospector pair = new AnnotationIntrospector.Pair(primary, secondary);
        objectMapper.setAnnotationIntrospector(pair);
        m.setObjectMapper(objectMapper );
        return m;
    }
    
    /** Creates an XML converter that knows about all JAXB-annotated classes in nl.ypmania */
    public MarshallingHttpMessageConverter xmlConverter() {
        // The "packagesToScan" feature of JaxbMarshaller seems to not work.
        // The JIRA below is apparently not quite resolved in 3.2.5.
        // https://jira.springsource.org/browse/SPR-9152 
        
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        PublicJaxb2Scanner scanner = new PublicJaxb2Scanner(
                Thread.currentThread().getContextClassLoader(), new String[] { "nl.ypmania" });
        Class<?>[] jaxb2Classes = scanner.scanPackages();
        marshaller.setClassesToBeBound(jaxb2Classes);
        return new MarshallingHttpMessageConverter(marshaller, marshaller);
    }
    
    @Bean
    public ViewResolver viewResolver() {
        // Our view resolver renders JSTL-based JSP pages
        InternalResourceViewResolver r = new InternalResourceViewResolver();
        r.setViewClass(JstlView.class);
        r.setPrefix("/WEB-INF/views");
        r.setSuffix(".jsp");
        return r;
    }
    
    @Bean
    public MessageSource messageSource() {
 
        ReloadableResourceBundleMessageSource m = new ReloadableResourceBundleMessageSource();
        m.setBasenames("classpath:messages/messages");
        // if true, the key of the message will be displayed if the key is not
        // found, instead of throwing a NoSuchMessageException
        m.setUseCodeAsDefaultMessage(true);
        m.setDefaultEncoding("UTF-8");
        // # -1 : never reload, 0 always reload
        m.setCacheSeconds(0);
        return m;
    }
}
