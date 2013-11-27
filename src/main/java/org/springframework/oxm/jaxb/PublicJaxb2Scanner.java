package org.springframework.oxm.jaxb;

/**
 * This exposes the package-private ClassPathJaxb2TypeScanner as public,
 * so we can work around https://jira.springsource.org/browse/SPR-9152
 * (which apparently is not quite fixed).
 */
public class PublicJaxb2Scanner extends ClassPathJaxb2TypeScanner {

    public PublicJaxb2Scanner(ClassLoader classLoader, String[] packagesToScan) {
        super(classLoader, packagesToScan);
    }

}
