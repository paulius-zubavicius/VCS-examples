package com.owr.games.ships.client.runner.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.owr.games.ships.client.*" })
public class AppCtxConfig {

}

/*
 * @Configuration
 * 
 * @PropertySource("classpath:app.properties")
 * 
 * @ComponentScan({ "owr.tabule.sch.gui.*" })
 * 
 * @Profile({Profiles.LOCAL, Profiles.REMOTE, Profiles.TEST}) public class
 * AppCtxConfig {
 * 
 * @Bean public static PropertySourcesPlaceholderConfigurer
 * propertySourcesPlaceholderConfigurer() { return new
 * PropertySourcesPlaceholderConfigurer(); } }
 */
