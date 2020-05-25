package com.newly.vas.provider;

import com.google.common.collect.Lists;
import com.newly.vas.api.v1.common.VideoRestResult;
import com.newly.vas.api.v1.dto.ResListDTO;
import com.newly.vas.api.v1.remote.ResourcesRemoteService;
import com.newly.vas.api.v1.common.VideoRestResult;
import com.newly.vas.api.v1.dto.ResListDTO;
import com.newly.vas.api.v1.remote.ResourcesRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("resourcesRemoteService")
@Slf4j
public class ResourcesRemoteServiceImpl implements ResourcesRemoteService {

    @Override
    public Object resList(HttpServletRequest request) {
//        String authorization = request.getHeader("Authorization");
//        if (authorization == null)
//            return Response.status(401).entity(new VideoRestResult<>("鉴权失败", null)).build();

        String userId = "2";
        String userCode = "200";
        String type = "0";
        //获取用户关联的网关
        Map<String, Object> paramUser = new HashMap<>();
        paramUser.put("userId", userId);
        paramUser.put("userCode", userCode);
        paramUser.put("type", type);


        List<ResListDTO> resList = Lists.newArrayList();
        List<ResListDTO.DeviceBase> deviceInfoList = Lists.newArrayList();



        Map<String, List<ResListDTO.DeviceBase>> matchsListMap = deviceInfoList.stream().collect(Collectors.groupingBy(ResListDTO.DeviceBase::getGatewayId));

        return Response.status(200).entity(new VideoRestResult<>(null, resList)).build();
    }
}
