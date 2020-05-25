package com.newly.vas.base.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * Created by bingo on 2018/8/28 下午4:26
 */
@Configuration
@PropertySource("classpath:video-gate.properties")
@Data
public class VideoGateProperties {

    @Value("${video.gate.auth}")
    private String auth;
    @Value("${video.gate.getLiveStream}")
    private String startLiveStream;
    @Value("${video.gate.controlPtz}")
    private String controlPtz;
    @Value("${video.gate.host}")
    private String host;
    @Value("${video.gate.user}")
    private String user;
    @Value("${video.gate.password}")
    private String password;
    @Value("${video.gate.stopLiveStream}")
    private String stopLiveStream;
    @Value("${video.gate.presetNumSet}")
    private String presetNum;
    @Value("${video.gate.heartbeat}")
    private String heartbeat;
    @Value("${video.gate.reg}")
    private String reg;

    @Value("${video.gate.device.add}")
    private String deviceAdd;
    @Value("${video.gate.device.delete}")
    private String deviceDelete;
    @Value("${video.gate.device.update}")
    private String deviceUpdate;
    @Value("${video.gate.channel.add}")
    private String channelAdd;
    @Value("${video.gate.channel.delete}")
    private String channelDelete;
    @Value("${video.gate.channel.update}")
    private String channelUpdate;

    @Value("${video.gate.strategy.rename}")
    private String strategyRename;
    @Value("${video.gate.strategy.delete}")
    private String strategyDelete;
    @Value("${video.gate.strategy.edit}")
    private String strategyEdit;

    /*直播云*/
    @Value("${video.gate.getPiliLiveStream}")
    private String piliLiveStream;

    /*扫描设备*/
    @Value("${video.gate.scanDev}")
    private String scanDev;

    /*鉴权 获取流地址*/
    @Value("${video.gate.devAuth}")
    private String devAuth;

    /*鉴权 获取流地址*/
    @Value("${video.gate.isOnline}")
    private String gateIsOnline;

    public String getGateIsOnline() {
        return this.getHost() + gateIsOnline;
    }

    public String getDevAuth() {
        return this.getHost() + devAuth;
    }

    public String getScanDev() {
        return this.getHost() + scanDev;
    }

    public String getPiliLiveStream() {
        return this.getHost() + piliLiveStream;
    }

    public String getStartLiveStreamUrl() {
        return this.getHost() + startLiveStream;
    }

    public String getPresetNumUrl() {
        return this.getHost() + presetNum;
    }

    public String getStopLiveStreamUrl() {
        return this.getHost() + stopLiveStream;
    }

    public String getAuthUrl() {
        return this.getHost() + auth;
    }

    public String getControlPtzUrl() {
        return this.getHost() + controlPtz;
    }

    public String getHeartbeatUrl() {
        return this.getHost() + heartbeat;
    }

    public String getRegUrl() {
        return this.getHost() + reg;
    }


    public String getDeviceAddUrl() {
        return this.getHost() + deviceAdd;
    }

    public String getDeviceDeleteUrl() {
        return this.getHost() + deviceDelete;
    }

    public String getDeviceUpdateUrl() {
        return this.getHost() + deviceUpdate;
    }

    public String getChannelAddUrl() {
        return this.getHost() + channelAdd;
    }

    public String getChannelDeleteUrl() {
        return this.getHost() + channelDelete;
    }

    public String getChannelUpdateUrl() {
        return this.getHost() + channelUpdate;
    }

    public String getStrategyRename() {
        return this.getHost() + strategyRename;
    }

    public String getStrategyDelete() {
        return this.getHost() + strategyDelete;
    }

    public String getStrategyEdit() {
        return this.getHost() + strategyEdit;
    }
}


