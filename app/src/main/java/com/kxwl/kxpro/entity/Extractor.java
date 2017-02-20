package com.kxwl.kxpro.entity;

import android.util.Log;

/**
 * Created by SEELE on 2017/2/20.
 */
public class Extractor {
    private static final String GOODS_ORIGIN_TAG="Att_Sys_zh-cn_171_G";//the goods origin tag
    private static final String GOODS_SPECIFICATION_TAG="Att_Sys_zh-cn_332_G";//the goods specification tag
    private static final String GOODS_BRAND_TAG="Att_Sys_zh-cn_304_G";//the goods brand tag
    private static final String GOODS_NAME_TAG="Att_Sys_zh-cn_141_G";//the goods brand tag
    private static final String GOODS_CLASSIFICATION_TAG="Att_Sys_zh-cn_22_G";//the goods classification tag

    private Extractor(){}

    /**
     * extract the useful info which we need from the content of the html
     * @param content
     * @return the goods object
     */
    public static Goods extractFromStr(String content){
        Goods goods=new Goods();
        String[] v= content.split("SetValue");
        goods.setBarCode(extractBarcodeFromStr(v[2]));
        for(int i=3;i<v.length;i++){
            extractSomeAttrFromStr(v[i],goods);
        }
        return goods;
    }

    /**
     * extract the barcode from the string in chaos
     * @param str
     * @return
     */
    private static String extractBarcodeFromStr(String str){
        String[] tmp=str.split("GTIN=");
        return tmp[1].substring(1,14);
    }

    private static void extractSomeAttrFromStr(String str,Goods goods){
        str=str.replaceAll("\\(","").replaceAll("\\)","").replaceAll(";","").replaceAll("'","");
        String[] tmp=str.split(",");
        switch(tmp[0]){
            case GOODS_BRAND_TAG:
                goods.setBrand(tmp[1].replaceAll("\\s",""));
                break;
            case GOODS_ORIGIN_TAG:
                goods.setOrigin(tmp[1].replaceAll("\\s",""));
                break;
            case GOODS_SPECIFICATION_TAG:
                goods.setSpecification(tmp[1].replaceAll("\\s",""));
                break;
            case GOODS_NAME_TAG:
                goods.setName(tmp[1].replaceAll("\\s",""));
                break;
            case GOODS_CLASSIFICATION_TAG:
                String s=tmp[1].replaceAll("&nbsp","").replaceAll("\\d","");
                String[] ss=s.split(">>");
                goods.setClassification(ss[1]);
                break;
            default:break;
        }

    }

}
