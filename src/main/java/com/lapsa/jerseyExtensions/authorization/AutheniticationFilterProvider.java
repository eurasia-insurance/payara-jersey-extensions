package com.lapsa.jerseyExtensions.authorization;

import java.io.IOException;
import java.security.Principal;

import javax.annotation.Priority;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AutheniticationFilterProvider implements ContainerRequestFilter {

    @Inject
    @QAuthenticatedUser
    private Event<Principal> event;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
	if (requestContext.getSecurityContext() == null)
	    return;
	if (requestContext.getSecurityContext().getUserPrincipal() == null)
	    return;
	event.fire(requestContext.getSecurityContext().getUserPrincipal());
    }

}
