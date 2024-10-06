package io.formulate.common.ws.exception;

import io.formulate.common.ws.error.AppError;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class InternalServerErrorExceptionMapper implements ExceptionMapper<InternalServerErrorException> {
    @Override
    public Response toResponse(InternalServerErrorException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new AppError(e.getCause().getMessage())).type(MediaType.APPLICATION_JSON).build();
    }
}
