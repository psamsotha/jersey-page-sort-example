
package com.example;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

import com.example.provider.PageableValueFactoryProvider;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spi.internal.ValueFactoryProvider;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;


@ApplicationPath("/api")
public class AppConfig extends ResourceConfig {
    
    public AppConfig() {
        packages("com.example");
        register(RequestContextFilter.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(PageableValueFactoryProvider.class)
                        .to(ValueFactoryProvider.class)
                        .in(Singleton.class);
            }
        });
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
    }
}
