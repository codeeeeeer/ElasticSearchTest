package com.lewjay.elapp;

import com.lewjay.elapp.constants.ELConstants;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * 〈demon application for elastic search〉
 *
 * @author liujie
 * @create 2019/10/12 15:57
 */
public class DemoApp {
    public static void main(String[] args) {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
                new HttpHost("172.20.10.13", ELConstants.PORT_ELASTIC_SEARCH)));
    }
}
