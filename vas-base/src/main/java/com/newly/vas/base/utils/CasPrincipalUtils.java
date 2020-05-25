package com.newly.vas.base.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by bingo on 2018/12/11 1:01 PM
 */
public class CasPrincipalUtils {
    
    /**
     * @return java.lang.String 
     * @author bingo 2018/12/11 1:03 PM
     * @modificationHistory ==========逻辑或功能重大变更记录
     * @modify by user   :{修改人} :{修改时间}
     * @modify by reason :{原因}
     **/
    public static String getCasUserName(HttpServletRequest request) {
        String userName = null;
        if(request.getUserPrincipal()!=null) {
            String userBody = request.getUserPrincipal().getName();
            if (userBody != null && !"".equals(userBody)) {
                userName = userBody.split("&&")[0];
            }
        }
        return userName;
    }


    /**
     * @return java.lang.String
     * @author bingo 2018/12/11 1:05 PM
     * @modificationHistory ========逻辑或功能重大变更记录
     * @modify by user   :{修改人} :{修改时间}
     * @modify by reason :{原因}
     **/
    public static String getCasUserId(HttpServletRequest request) {
        String userId = "1";
        if(request.getUserPrincipal()!=null) {
            String userBody = request.getUserPrincipal().getName();
            if (userBody != null && !"".equals(userBody)) {
                userId = userBody.split("&&")[1];
            }
        }
        return userId;
    }
    
}
