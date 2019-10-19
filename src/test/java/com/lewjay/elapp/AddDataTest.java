package com.lewjay.elapp;

import com.lewjay.elapp.constants.ELConstants;
import com.lewjay.elapp.entity.Security;
import com.lewjay.elapp.helper.DateFormatterHolder;
import com.lewjay.elapp.helper.file.FileLineCallBack;
import com.lewjay.elapp.utils.MappingUtils;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.core.io.FileSystemResource;

/**
 * 〈〉
 *
 * @author liujie
 * @create 2019/10/17 21:39
 */
public class AddDataTest extends BaseELSampleTest{


    public void testAddData() throws Exception{
        FileSystemResource sourceFile = new FileSystemResource("D:\\GoogleDownload\\New+York+Stock+Exchange\\securities_01.csv");
        new FileLineCallBack(sourceFile) {
            @Override
            public void callBack(String source, Integer lineNumber) throws Exception{
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
                                DateFormatterHolder.$1.getDateFormat().format(security.getDateFirstAdded()))
                        .field("cik", security.getCik())
                        .endObject();
                IndexResponse indexResponse = client.prepareIndex("security", "securities", lineNumber + "")
                        .setSource(builder)
                        .execute()
                        .actionGet();
                System.out.println(indexResponse.status());
            }
        }.execute();
    }
}
