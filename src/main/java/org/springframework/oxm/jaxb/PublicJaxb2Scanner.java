package org.springframework.oxm.jaxb;

public class PublicJaxb2Scanner extends ClassPathJaxb2TypeScanner {

    public PublicJaxb2Scanner(ClassLoader classLoader, String[] packagesToScan) {
        super(classLoader, packagesToScan);
    }

}
