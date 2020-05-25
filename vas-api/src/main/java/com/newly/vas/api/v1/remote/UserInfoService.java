package com.newly.vas.api.v1.remote;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by bingo on 2020/4/20 下午3:23.
 */
@Api(value = "用户查询对外服务")
@Path("v1/userService")
@Produces(MediaType.APPLICATION_JSON)
public interface UserInfoService extends RemoteService {

    @GET
    @Path("/userInfo")
    @Produces("application/xml")
    @ApiOperation(value = "用户信息查询")
    String findUserInfo();
}
