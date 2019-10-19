package com.lewjay.elapp;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;

/**
 * 〈〉
 *
 * @author liujie
 * @create 2019/10/17 21:48
 */
public class DeteleDataTest extends BaseELSampleTest {

    public void testSimpleDelete() throws Exception{
        DeleteResponse deleteResponse = client
                .prepareDelete(ELTestConstants.INDEX_NAME, ELTestConstants.TYPE_NAME, "14")
                .execute()
                .actionGet();
        System.out.println(deleteResponse.status());
        System.out.println(deleteResponse.toString());
    }

    public void testByQuery() throws Exception{
        DeleteByQueryRequestBuilder builder = new DeleteByQueryRequestBuilder(client, DeleteByQueryAction.INSTANCE);
        builder.filter(QueryBuilders.matchQuery("security", "Inc"))
                .source(ELTestConstants.INDEX_NAME);
        BulkByScrollResponse response = builder.execute().actionGet();
        System.out.println(response.getDeleted());
        System.out.println(response.getStatus());
        System.out.println(response.getTotal());
        System.out.println(response.getUpdated());
    }
}
