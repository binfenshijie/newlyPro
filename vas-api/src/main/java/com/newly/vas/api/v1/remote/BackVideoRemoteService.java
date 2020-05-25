package com.newly.vas.api.v1.remote;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Api(value = "历史视频对外服务接口")
@Produces(MediaType.APPLICATION_JSON)
@Path("v1/backvideo")
public interface BackVideoRemoteService {

    @GET
    @Path("/segments")
    @Produces("application/json")
    @ApiOperation(value = "历史视频检索")
    Object segments(
            @QueryParam(value = "deviceID") String deviceID,
            @QueryParam(value = "channelNum") String channelNum,
            @QueryParam(value = "start") Integer start,
            @QueryParam(value = "end") Integer end,
            @DefaultValue("") @QueryParam(value = "marker") String marker,
            @DefaultValue("1000") @QueryParam(value = "limit") Integer limit,
            @Context HttpServletRequest request);

    @GET
    @Path("/playback")
    @Produces("application/json")
    @ApiOperation(value = "历史视频回放")
    Object playback(
            @QueryParam(value = "deviceID") String deviceID,
            @QueryParam(value = "channelNum") String channelNum,
            @QueryParam(value = "start") Integer start,
            @QueryParam(value = "end") Integer end,
            @Context HttpServletRequest request);
}
