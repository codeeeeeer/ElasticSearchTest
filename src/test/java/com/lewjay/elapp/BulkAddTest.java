package com.lewjay.elapp;

import com.lewjay.elapp.constants.ELConstants;
import com.lewjay.elapp.entity.Security;
import com.lewjay.elapp.helper.DateFormatterHolder;
import com.lewjay.elapp.helper.file.FileLineCallBack;
import com.lewjay.elapp.utils.ELUtils;
import com.lewjay.elapp.utils.MappingUtils;
import junit.framework.TestCase;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.core.io.FileSystemResource;

import java.net.InetAddress;

/**
 * 〈〉
 *
 * @author liujie
 * @create 2019/10/13 19:51
 */
public class BulkAddTest extends BaseELSampleTest {
    private int count = 0;
    private final static Integer CHUNK = 3;
    private BulkRequestBuilder bulkRequestBuilder = null;

    public void testBulkAdd() throws Exception{
        FileSystemResource sourceFile = new FileSystemResource("D:\\GoogleDownload\\New+York+Stock+Exchange\\securities.csv");
        new FileLineCallBack(sourceFile) {
            @Override
            public void callBack(String source, Integer lineNumber) throws Exception {
                System.out.println(source);
                Security security = MappingUtils.str2Object(Security.class, source, ELConstants.SPLIT_STR);
                XContentBuilder builder = XContentFactory.jsonBuilder()
                        .startObject()
                        .field("tickerSymbol", security.getTickerSymbol())
                        .field("security", security.getSecurity())
                        .field("secFiling", security.getSecFiling())
                        .field("gicsSector", security.getGicsSector())
                        .field("gicsSubIndustry", security.getGicsSubIndustry())
                        .field("addressOfHeadQuarter", security.getAddressOfHeadQuarter())
                        .field("dateFirstAdded",
                                DateFormatterHolder.$1.format(security.getDateFirstAdded()))
                        .field("cik", security.getCik())
                        .endObject();
                bulkEL(builder);
            }
        }.execute();
    }

    private void bulkEL(XContentBuilder singleBuilder){
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        if (bulkRequestBuilder == null){
            bulkRequestBuilder = client.prepareBulk();
            count = 1;
        }else {
            count ++;
            bulkRequestBuilder.add(client.prepareIndex("security", "securities", System.currentTimeMillis() + "")
                    .setSource(singleBuilder));
        }
        if (count == CHUNK){
            BulkResponse bulkItemResponses = bulkRequestBuilder.execute().actionGet();
            for (BulkItemResponse responce: bulkItemResponses) {
                System.out.println(responce.status());
            }
            bulkRequestBuilder=null;
            count=0;
        }
    }

}
