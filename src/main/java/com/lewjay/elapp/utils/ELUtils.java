package com.lewjay.elapp.utils;

import com.lewjay.elapp.constants.ELConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈〉
 *
 * @author liujie
 * @create 2019/10/12 16:32
 */
public class ELUtils {

    public static RestHighLevelClient getHighLevalClient(){
        return new RestHighLevelClient(RestClient.builder(
                new HttpHost("192.168.0.103", ELConstants.PORT_ELASTIC_SEARCH)));
    }

    public static <KT, VT> Map<KT, VT> singletonMap(KT key, VT value){
        Map<KT, VT> result = new HashMap<>();
        result.put(key, value);
        return result;
    }

    public static void close(AutoCloseable closeable){
        if (closeable != null){
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String trimQuotes(String source){
        return StringUtils.isEmpty(source) ? "" : source.replaceAll("\"$", "")
                .replaceAll("^\"", "").trim();
    }
}
