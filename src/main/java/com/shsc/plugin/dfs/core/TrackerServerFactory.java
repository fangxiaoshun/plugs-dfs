package com.shsc.plugin.dfs.core;


import com.shsc.plugin.dfs.fastdfs.TrackerClient;
import com.shsc.plugin.dfs.fastdfs.TrackerServer;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @author fangxs
 * @className TrackerServerFactory
 * @date 2019/6/27 14:34
 * @description
 **/
public class TrackerServerFactory  extends BasePooledObjectFactory<TrackerServer> {
    @Override
    public TrackerServer create() throws Exception {
        // TrackerClient
        TrackerClient trackerClient = new TrackerClient();
        // TrackerServer
        TrackerServer trackerServer = trackerClient.getConnection();

        return trackerServer;
    }

    @Override
    public PooledObject<TrackerServer> wrap(TrackerServer trackerServer) {
        return new DefaultPooledObject<TrackerServer>(trackerServer);
    }
}
