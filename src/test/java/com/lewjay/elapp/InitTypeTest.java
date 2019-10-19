package com.lewjay.elapp;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

/**
 * 〈〉
 *
 * @author liujie
 * @create 2019/10/17 21:40
 */
public class InitTypeTest extends BaseELSampleTest {


    public void testInitType() throws Exception{
        CreateIndexRequest request = new CreateIndexRequest("security");
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()

                .startObject("properties")

                .startObject("tickerSymbol")
                .field("type", "text")
                .endObject()

                .startObject("security")
                .field("type", "text")
                .endObject()

                .startObject("secFiling")
                .field("type", "text")
                .endObject()

                .startObject("gicsSector")
                .field("type", "text")
                .endObject()

                .startObject("gicsSubIndustry")
                .field("type", "text")
                .endObject()

                .startObject("addressOfHeadQuarter")
                .field("type", "text")
                .endObject()

                .startObject("dateFirstAdded")
                .field("type", "date")
                .field("format", "yyyy-MM-dd")
                .endObject()

                .startObject("cik")
                .field("type", "text")
                .endObject()

                .endObject()
                .endObject();



        request.mapping("securities", builder);

        CreateIndexResponse createIndexResponse = client.admin().indices().create(request).get();
        System.out.println(createIndexResponse.index());
    }
}
