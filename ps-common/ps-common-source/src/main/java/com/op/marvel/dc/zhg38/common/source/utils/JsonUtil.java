package com.op.marvel.dc.zhg38.common.source.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 20:48 on 27/03/2018.
 */
public class JsonUtil {

    //定义JSON 对象
    private static final ObjectMapper MAPPER=new ObjectMapper();


    public static ObjectMapper obj2Json(){
        return  MAPPER;
    }

    public static JsonNode getJsonNode(String jsonData){
        JsonNode retValue=null;
        try {
            retValue=  MAPPER.readTree(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  retValue;
    }

    /**
     * 将对象转换成Json
     * @param obj
     * @return
     */
    public static String obj2Json(Object obj){
        String retValue=null;
        try {
            retValue=    MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return retValue;
    }

    /**
     * 将Json 转换成对象
     * @param jsonData
     * @param beanType
     * @param <T>
     * @return
     */
    public static <T> T json2Object(String jsonData,Class<T> beanType){
        T retValue=null;
        try {
            retValue = MAPPER.readValue(jsonData, beanType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retValue;
    }

    /**
     * 将json 数据转换成List
     * @param jsonData
     * @param beanType
     * @param <T>
     * @return
     */
    public static  <T>  List<T> json2List(String jsonData,Class<T> beanType){
        List<T> retValue=null;
        try {
            JavaType javaType = MAPPER.getTypeFactory().constructCollectionLikeType(List.class, beanType);
            retValue=MAPPER.readValue(jsonData,javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retValue;
    }

    /**
     * 将JSON 数据转换为Map
     * @param jsonData
     * @param keyType
     * @param valueType
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V>  Map<K,V> json2Map(String jsonData,  Class<K> keyType,Class<V> valueType){
        Map<K,V> retValue=null;
        try {
            JavaType javaType = MAPPER.getTypeFactory().constructParametricType(HashMap.class, keyType, valueType);
            retValue = MAPPER.readValue(jsonData, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retValue;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static  <T>  List<T> formatToList(String jsonData, Class<T> clazz) {
        List<T> retValue=null;
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            if (data.isArray() && data.size() > 0) {
                retValue = MAPPER.readValue(data.traverse(),  MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return retValue;
        } catch (Exception e) {
            return retValue;
        }
    }
}
