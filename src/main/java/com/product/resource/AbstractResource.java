package com.product.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.util.Optional;

/** author: Ranjith Manickam @ 21 July' 2019 */
interface AbstractResource {

    Logger LOGGER = LoggerFactory.getLogger(AbstractResource.class);

    default Response sendResponse(Optional data) {
        return Response.status(Response.Status.OK)
                .entity(com.product.entry.Response.builder()
                        .status(Boolean.TRUE)
                        .data(data.isPresent() ? data.get() : null)
                        .build())
                .build();
    }

    default void logException(String message, Object... objects) {
        LOGGER.error(message, objects);
    }
}
