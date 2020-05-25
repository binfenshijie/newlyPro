package com.newly.vas.api.v1.remote;

import com.newly.vas.api.v1.dto.AlarmDTO;
import com.newly.vas.api.v1.dto.GoResult;
import com.newly.vas.api.v1.dto.AlarmDTO;
import com.newly.vas.api.v1.dto.GoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Api(value = "预警信息服务")
@Path("v1/alarmRemoteService")
@Produces(MediaType.APPLICATION_JSON)
public interface AlarmRemoteService{

    @POST
    @Path("/newAlarmMsg")
    @Produces("application/json")
    @ApiOperation(value = "新的预警信息")
    GoResult newAlarmMsg(AlarmDTO alarmDTO);
}
