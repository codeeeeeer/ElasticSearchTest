package com.lewjay.elapp;

import com.lewjay.elapp.constants.ELType;
import com.lewjay.elapp.utils.ELUtils;
import junit.framework.TestCase;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.util.Map;

/**
 * 〈〉
 *
 * @author liujie
 * @create 2019/10/12 16:34
 */

public class ELTest extends TestCase {
    private RestHighLevelClient client = null;

    @Override
    protected void setUp() throws Exception {
        client = ELUtils.getHighLevalClient();
    }

    public void testInitIndex() throws Exception{
        CreateIndexRequest request = new CreateIndexRequest("security");
        Map<String, String> message = ELUtils.singletonMap("type", ELType.TEXT.getType());
        Map<String, Map<String, String>> properties = ELUtils.singletonMap("message", message);
        Map<String, Map<String, Map<String, String>>> mapping = ELUtils.singletonMap("properties", properties);
        request.mapping("security_01", mapping);
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    public void testDeleteIndex() throws Exception{
        DeleteIndexRequest request = new DeleteIndexRequest("security");
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
        boolean acknowledged = response.isAcknowledged();
        System.out.println(acknowledged);
    }

    public void testAddData() throws Exception{
        PutMappingRequest request = new PutMappingRequest("security");
        request.type("security_01");
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("message", "the is the test message content")
                .endObject();
        request.source(builder);
        AcknowledgedResponse acknowledgedResponse = client.indices().putMapping(request, RequestOptions.DEFAULT);
        System.out.println(acknowledgedResponse.isAcknowledged());
    }

    @Override
    protected void tearDown() throws Exception {
        client.close();
    }
}
