package com.lewjay.elapp;

import junit.framework.TestCase;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetRequestBuilder;
import org.elasticsearch.action.get.MultiGetResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 〈〉
 *
 * @author liujie
 * @create 2019/10/17 22:30
 */
public class MutilGetTest extends BaseELSampleTest {
    public void testMGet() throws Exception{
        MultiGetRequestBuilder builder = client.prepareMultiGet();
        List<String> ids = new ArrayList<>();
        Collections.addAll(ids, "1", "2", "3");
        builder.add(ELTestConstants.INDEX_NAME, ELTestConstants.TYPE_NAME, ids)
                .add(ELTestConstants.INDEX_NAME, ELTestConstants.TYPE_NAME, "4", "5", "6");
        MultiGetResponse multiGetItemResponses = builder.execute().actionGet();
        for (MultiGetItemResponse response :multiGetItemResponses.getResponses()) {
            System.out.println(response.getResponse());
        }

    }
}
