package com.newly.vas.api.v1.remote;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Api(value = "资源列表对外服务接口")
@Produces(MediaType.APPLICATION_JSON)
@Path("v1/resources")
public interface ResourcesRemoteService extends RemoteService {

    @GET
    @Path("/list")
    @Produces("application/json")
    @ApiOperation(value = "资源列表")
    Object resList(@Context HttpServletRequest request);
}
