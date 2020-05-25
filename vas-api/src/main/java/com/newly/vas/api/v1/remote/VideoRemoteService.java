package com.newly.vas.api.v1.remote;

import com.newly.vas.api.v1.dto.CreateStreamTO;
import com.newly.vas.api.v1.dto.DisabledStreamTO;
import com.newly.vas.api.v1.dto.LiveStreamsTO;
import com.newly.vas.api.v1.dto.CreateStreamTO;
import com.newly.vas.api.v1.dto.DisabledStreamTO;
import com.newly.vas.api.v1.dto.LiveStreamsTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.*;

@Api(value = "视频播放服务对外接口")
@Path("v1/videoRemoteService")
public interface VideoRemoteService extends RemoteService {

    @GET
    @Path("/publishRTMP")
    @Produces("application/json")
    @ApiOperation(value = "RTMP 推流地址")
    Object publishRTMP(
            @QueryParam(value = "streamTitle") String streamTitle);

    @GET
    @Path("/playRTMP")
    @Produces("application/json")
    @ApiOperation(value = "RTMP 播放地址")
    Object playRTMP(
            @QueryParam(value = "streamTitle") String streamTitle);

    @GET
    @Path("/playHLS")
    @Produces("application/json")
    @ApiOperation(value = "HLS 播放地址")
    Object playHLS(
            @QueryParam(value = "streamTitle") String streamTitle);

    @GET
    @Path("/playHDL")
    @Produces("application/json")
    @ApiOperation(value = "HDL (HTTP-FLV) 播放地址")
    Object playHDL(
            @QueryParam(value = "streamTitle") String streamTitle);


    @POST
    /*@Path("/{Hub}/streams")*/
    @Path("/streams")
    @Produces("application/json")
    @ApiOperation(value = "创建流")
    Object create(
            /*@PathParam(value = "Hub") String hubName,*/
            CreateStreamTO createStreamVO);

    @GET
    /*@Path("/{Hub}/streams/{EncodedStreamTitle}")*/
    @Path("/streams/{EncodedStreamTitle}")
    @Produces("application/json")
    @ApiOperation(value = "查询流")
    Object attribute(
            /*@PathParam(value = "Hub") String hub,*/
            @PathParam(value = "EncodedStreamTitle") String encodedStreamTitle);

    @GET
    /*@Path("/{Hub}/streams")*/
    @Path("/streams")
    @Produces("application/json")
    @ApiOperation(value = "流列表")
    Object listStreams(
            /*@PathParam(value = "Hub") String hubName,*/
            @DefaultValue("false") @QueryParam("liveonly") Boolean liveonly,
            @DefaultValue("") @QueryParam("prefix") String prefix,
            @DefaultValue("1000") @QueryParam("limit") Integer limit,
            @DefaultValue("") @QueryParam("marker") String marker);

    @POST
    /*@Path("/{Hub}/streams/{EncodedStreamTitle}/disabled")*/
    @Path("/streams/{EncodedStreamTitle}/disabled")
    @Produces("application/json")
    @ApiOperation(value = "禁播流")
    Object disableTill(
            /*@PathParam(value = "Hub") String hub,*/
            @PathParam(value = "EncodedStreamTitle") String encodedStreamTitle,
            DisabledStreamTO disabledStreamVO);

    @GET
    /*@Path("/{Hub}/streams/{EncodedStreamTitle}/live")*/
    @Path("/streams/{EncodedStreamTitle}/live")
    @Produces("application/json")
    @ApiOperation(value = "直播实时信息")
    Object liveStream(
            /*@PathParam(value = "Hub") String hubName,*/
            @PathParam(value = "EncodedStreamTitle") String encodedStreamTitle);

    @POST
    /*@Path("/{Hub}/livestreams")*/
    @Path("/livestreams")
    @Produces("application/json")
    @ApiOperation(value = "批量查询直播实时信息")
    Object liveStreams(
            /*@PathParam(value = "Hub") String hubName, */
            LiveStreamsTO liveStreamsTO);

    @GET
    /*@Path("/{Hub}/streams/{EncodedStreamTitle}/historyactivity")*/
    @Path("/streams/{EncodedStreamTitle}/historyactivity")
    @Produces("application/json")
    @ApiOperation(value = "直播历史记录")
    Object historyActivity(
            /*@PathParam(value = "Hub") String hubName,*/
            @PathParam(value = "EncodedStreamTitle") String encodedStreamTitle,
            @QueryParam("start") Integer start,
            @QueryParam("end") Integer end);
}
