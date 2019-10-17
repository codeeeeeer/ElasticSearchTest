package com.lewjay.elapp.helper.file;

import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * 〈〉
 *
 * @author liujie
 * @create 2019/10/14 10:11
 */
public abstract class FileLineCallBack {
    private final Resource[] resources;

    public FileLineCallBack(Resource... resources) {
        this.resources = resources;
    }

    public void execute() throws Exception{
        Assert.notNull(resources, "resource can not be empty");
        for (Resource resource: resources) {
            try (InputStreamReader in = new InputStreamReader(resource.getInputStream(), "utf-8");
                 BufferedReader bufferedReader = new BufferedReader(in)) {
                String currentLineContent;
                Integer currentLineNumber = 0;
                while ((currentLineContent = bufferedReader.readLine()) != null){
                    currentLineNumber ++;
                    callBack(currentLineContent, currentLineNumber);
                }
            }
        }
    }

    public abstract void callBack(String source, Integer lineNumber) throws Exception;
}
