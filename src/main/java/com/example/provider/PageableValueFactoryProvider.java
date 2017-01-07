package com.example.provider;


import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.internal.inject.AbstractContainerRequestValueFactory;
import org.glassfish.jersey.server.model.Parameter;
import org.glassfish.jersey.server.spi.internal.ValueFactoryProvider;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;

public class PageableValueFactoryProvider implements ValueFactoryProvider {

    private final ServiceLocator locator;

    @Inject
    public PageableValueFactoryProvider(ServiceLocator locator) {
        this.locator = locator;
    }

    @Override
    public Factory<?> getValueFactory(Parameter parameter) {
        if (parameter.getRawType() == Pageable.class
                && parameter.isAnnotationPresent(Pagination.class)) {
            Factory<?> factory = new PageableValueFactory(locator);
            return factory;
        }
        return null;
    }

    @Override
    public PriorityType getPriority() {
        return Priority.NORMAL;
    }

    private static class PageableValueFactory
            extends AbstractContainerRequestValueFactory<Pageable> {

        @QueryParam("page") @DefaultValue("0") Integer page;
        @QueryParam("size") @DefaultValue("3") Integer size;
        @QueryParam("sort") List<String> sort;

        private final ServiceLocator locator;

        private PageableValueFactory(ServiceLocator locator) {
            this.locator = locator;
        }

        @Override
        public Pageable provide() {
            locator.inject(this);

            List<Sort.Order> orders = new ArrayList<>();
            for (String propOrder: sort) {
                String[] propOrderSplit = propOrder.split(",");
                String property = propOrderSplit[0];
                if (propOrderSplit.length == 1) {
                    orders.add(new Sort.Order(property));
                } else {
                    Sort.Direction direction
                            = Sort.Direction.fromStringOrNull(propOrderSplit[1]);
                    orders.add(new Sort.Order(direction, property));
                }
            }

            return new PageRequest(page, size,
                    orders.isEmpty() ? null : new Sort(orders));
        }
    }
}
