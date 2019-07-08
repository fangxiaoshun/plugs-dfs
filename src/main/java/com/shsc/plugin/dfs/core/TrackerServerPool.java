package com.shsc.plugin.dfs.core;

import com.shsc.plugin.dfs.common.MyException;
import com.shsc.plugin.dfs.exception.FastDfsException;
import com.shsc.plugin.dfs.fastdfs.ClientGlobal;
import com.shsc.plugin.dfs.fastdfs.TrackerServer;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

/**
 * @author fangxs
 * @className TrackerServerPool
 * @date 2019/6/27 14:33
 * @description
 **/
@Component
public class TrackerServerPool {
    /**
     * org.slf4j.Logger
     */
    private Logger logger = LoggerFactory.getLogger(TrackerServerPool.class);

    /**
     * 最大连接数 default 8.
     */
    @Value("${fastdfs.max_storage_connection}")
    private int maxStorageConnection;
    /**
     * tracker_servers 连接
     */
    @Value("${fastdfs.tracker_servers}")
    private String trackerServers;
    /**
     */
    @Value("${fastdfs.connect_timeout_in_seconds}")
    private String connectTimeoutInSeconds;
    /**
     */
    @Value("${fastdfs.network_timeout_in_seconds}")
    private String networkTimeoutInSeconds;
    /**
     */
    @Value("${fastdfs.charset}")
    private String charset;
    /**
     */
    @Value("${fastdfs.http_anti_steal_token}")
    private String httpAntiStealToken;
    /**
     */
    @Value("${fastdfs.http_secret_key}")
    private String httpSecretKey;

    @Value("${fastdfs.http_tracker_http_port}")
    private String httpTrackerHttpPort;


    /**
     * TrackerServer 对象池. GenericObjectPool 没有无参构造
     */
    private GenericObjectPool<TrackerServer> trackerServerPool;

    private TrackerServerPool() {
    };

    private synchronized GenericObjectPool<TrackerServer> getObjectPool() {


        if (trackerServerPool == null) {
            try {
                // 加载配置文件
                Properties pro = new Properties();
                pro.put(ClientGlobal.PROP_KEY_TRACKER_SERVERS, trackerServers);
                pro.put(ClientGlobal.PROP_KEY_CONNECT_TIMEOUT_IN_SECONDS, connectTimeoutInSeconds);
                pro.put(ClientGlobal.PROP_KEY_NETWORK_TIMEOUT_IN_SECONDS, networkTimeoutInSeconds);
                pro.put(ClientGlobal.PROP_KEY_CHARSET, charset);
                pro.put(ClientGlobal.PROP_KEY_HTTP_ANTI_STEAL_TOKEN, httpAntiStealToken);
                pro.put(ClientGlobal.PROP_KEY_HTTP_SECRET_KEY, httpSecretKey);
                pro.put(ClientGlobal.PROP_KEY_HTTP_TRACKER_HTTP_PORT, httpTrackerHttpPort);
                // 初始化配置文件
                ClientGlobal.initByProperties(pro);
            } catch (IOException | MyException e) {
                e.printStackTrace();
                logger.error("初始化文件失败,result{}",e.getMessage());
            }

            if (logger.isDebugEnabled()) {
                logger.debug("ClientGlobal configInfo: {}", ClientGlobal.configInfo());
            }

            // Pool配置
            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
            //设置最小空闲连接数
            poolConfig.setMinIdle(2);
            if (maxStorageConnection > 0) {
                //设置 storage最大连接数
                poolConfig.setMaxTotal(maxStorageConnection);
            }

            trackerServerPool = new GenericObjectPool<>(new TrackerServerFactory(), poolConfig);
        }
        return trackerServerPool;
    }

    /**
     * 获取 TrackerServer
     *
     * @return TrackerServer
     * @throws FastDfsException
     */
    public  TrackerServer borrowObject() throws FastDfsException {
        TrackerServer trackerServer = null;
        try {
            trackerServer = getObjectPool().borrowObject();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            if (e instanceof FastDfsException) {
                throw (FastDfsException) e;
            }
        }
        return trackerServer;
    }

    /**
     * 回收 TrackerServer
     *
     * @param trackerServer 需要回收的 TrackerServer
     */
    public  void returnObject(TrackerServer trackerServer) {

        getObjectPool().returnObject(trackerServer);
    }
}
