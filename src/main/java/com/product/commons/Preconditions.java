package com.product.commons;

import com.product.exception.ServiceException;

/**
 * author: Ranjith Manickam @ 21 July' 2019
 */
public final class Preconditions {

    /** Ensures the passed data is not null */
    public static <T> void checkNotNull(T data, ServiceException ex) throws ServiceException {
        if (data == null) {
            throw ex;
        }
    }

}
