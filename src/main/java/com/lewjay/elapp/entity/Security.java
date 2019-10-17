package com.lewjay.elapp.entity;

import lombok.Data;

import java.util.Date;

/**
 * 〈〉
 *
 * @author liujie
 * @create 2019/10/12 16:22
 */
@Data
public class Security {
    private String tickerSymbol;
    private String security;
    private String secFiling;
    private String gicsSector;
    private String gicsSubIndustry;
    private String addressOfHeadQuarter;
    private Date dateFirstAdded;
    private String cik;
}
