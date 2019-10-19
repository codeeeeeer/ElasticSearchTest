package com.lewjay.elapp;

import com.lewjay.elapp.constants.ELConstants;
import com.lewjay.elapp.utils.ELUtils;
import junit.framework.TestCase;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

/**
 * 〈〉
 *
 * @author liujie
 * @create 2019/10/17 21:37
 */
public class BaseELSampleTest extends TestCase {
    protected TransportClient client;


    @Override
    protected void setUp() throws Exception {
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(
                        new TransportAddress(
                                InetAddress.getByName(
                                        ELConstants.IP_ELASTIC_SEARCH),
                                ELConstants.PORT_ELASTIC_SEARCH_FOR_JAVA)
                );
    }

    @Override
    protected void tearDown(){
        ELUtils.close(client);
    }
}
