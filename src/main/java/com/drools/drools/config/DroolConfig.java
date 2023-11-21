package com.drools.drools.config;
//package com.drools.drools.config;
//import org.kie.api.KieServices;
//import org.kie.api.builder.KieBuilder;
//import org.kie.api.builder.KieFileSystem;
//import org.kie.api.builder.KieModule;
//import org.kie.api.builder.ReleaseId;
//import org.kie.api.runtime.KieContainer;
//import org.kie.api.runtime.KieSession;
//import org.kie.internal.io.ResourceFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//
//@Configuration
//public class DroolConfig {
//    static final String drlFile = "rules/discount.drl";
////    private static final KieServices kieServices = KieServices.Factory.get();
//    @Bean
//    public KieContainer kieContainer() {
////        KieServices kieServices = KieServices.Factory.get(); // contains all the import methods e.g to create a KieContainer
//        KieFileSystem keyFileStem = kieServices.newKieFileSystem(); // create a new KieFileSystem
//        keyFileStem.write(ResourceFactory.newClassPathResource(drlFile)); // write the rules to the KieFileSystem
//        KieBuilder kieBuilder = kieServices.newKieBuilder(keyFileStem); // create a new KieBuilder
//        kieBuilder.buildAll(); // build all the rules
//        KieModule kieModule = kieBuilder.getKieModule(); // get the KieModule
//        return kieServices.newKieContainer(kieModule.getReleaseId()); // create a new KieContainer
//    }
//
//    private void getKieRepository() {
//        final KieRepository kieRepository = kieServices.getRepository();
//        kieRepository.addKieModule(new KieModule() {
//            public ReleaseId getReleaseId() {
//                return kieRepository.getDefaultReleaseId();
//            }
//        });
//    }
//    @Bean
//    public KieSession getKieSession() throws IOException {
//        System.out.println("session created...");
//        return kieContainer().newKieSession();
//    }
//}


import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class DroolConfig {
    private KieServices kieServices = KieServices.Factory.get();

    private KieFileSystem getKieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource("offer.drl"));
        return kieFileSystem;

    }
    @Bean
    public KieSession getKieSession() throws IOException {
        System.out.println("session created...");
        return getKieContainer().newKieSession();

    }

    @Bean
    public KieContainer getKieContainer() throws IOException {
        System.out.println("Container created...");
        getKieRepository();
        KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem());
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        return kContainer;

    }

    private void getKieRepository() {
        final KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(new KieModule() {
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });
    }



}
