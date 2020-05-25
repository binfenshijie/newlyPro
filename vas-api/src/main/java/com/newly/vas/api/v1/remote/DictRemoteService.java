package com.newly.vas.api.v1.remote;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Api(value = "字典管理对外服务")
@Produces(MediaType.APPLICATION_JSON)
@Path("v1/dictRemoteService")
public interface DictRemoteService {

    @GET
    @Path("/defineValue")
    @ApiOperation(value = "字典值查询")
    public String getDefineValue(@QueryParam("appCode") String appCode, @QueryParam("typeCode") String typeCode, @QueryParam("key") String key);

}
