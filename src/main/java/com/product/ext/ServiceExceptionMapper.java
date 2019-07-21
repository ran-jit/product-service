package com.product.ext;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.product.exception.ErrorCode;
import com.product.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.io.Serializable;
import java.time.LocalDateTime;

/** author: Ranjith Manickam @ 21 July' 2019 */
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Error implements Serializable {

        private HttpStatus httpStatus;

        @JsonFormat(shape = JsonFormat.Shape.STRING)
        private LocalDateTime timestamp;

        private ErrorCode error;
    }

    @Override
    public Response toResponse(ServiceException ex) {
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(Error.builder()
                        .error(ex.getErrorCode())
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .timestamp(LocalDateTime.now())
                        .build())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
