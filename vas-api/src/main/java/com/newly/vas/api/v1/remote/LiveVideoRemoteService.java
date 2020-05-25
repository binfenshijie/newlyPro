package com.newly.vas.api.v1.remote;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Api(value = "历史视频对外服务接口")
@Produces(MediaType.APPLICATION_JSON)
@Path("v1/livevideo")
public interface LiveVideoRemoteService {

    @GET
    @Path("/start")
    @Produces("application/json")
    @ApiOperation(value = "开始播放")
    Object start(
            @QueryParam(value = "gatewayID") String gatewayID,
            @QueryParam(value = "deviceID") String deviceID,
            @QueryParam(value = "channelNum") String channelNum,
            @QueryParam(value = "protocol") String protocol,
            @QueryParam(value = "streamId") String streamId,
            @Context HttpServletRequest request);

    @GET
    @Path("/stop")
    @Produces("application/json")
    @ApiOperation(value = "停止播放")
    Object stop(
            @QueryParam(value = "gatewayID") String gatewayID,
            @QueryParam(value = "deviceID") String deviceID,
            @QueryParam(value = "channelNum") String channelNum,
            @QueryParam(value = "streamId") String streamId,
            @Context HttpServletRequest request);
}
