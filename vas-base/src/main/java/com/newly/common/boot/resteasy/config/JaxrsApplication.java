package com.newly.common.boot.resteasy.config;

import com.newly.common.boot.resteasy.config.ResteasyProperties;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Created by bingo on 2020/4/21.
 */
@ApplicationPath(ResteasyProperties.DEFAULT_APPLICATION_PATH_PATTERN)
public class JaxrsApplication extends Application {
}