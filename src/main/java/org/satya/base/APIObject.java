package org.satya.base;

import java.util.*;

public class APIObject {

    private Map<String,String> headers = new HashMap<>();
    private Map<String,String> queryParams = new HashMap<>();
    private Map<String,String> basicAuth = new HashMap<>();
    private Map<String,String> formParams = new HashMap<>();
    private Map<String,String> bodyParams = new HashMap<>();
    private Map<String, String> pathParams = new HashMap<>();

    private String body;
    private String method;
    private String url;

    public void addHeaders(String key,String value){
        headers.put(key,value);
    }
    public Map<String,String> getHeaders(){
        return headers;
    }

    public void addQueryParams(String key,String value){
        queryParams.put(key,value);
    }

    public Map<String,String> getQueryParams(){
        return queryParams;
    }

    public void addBasicAuth(String username,String password){basicAuth.put(username,password);}

    public Map<String,String> getBasicAuth(){return basicAuth;}

    public void addFormParams(String key,String value){formParams.put(key,value);}

    public Map<String,String> getFormParams(){return formParams;}

    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){return this.url;}

   public void setMethod(String method){
        this.method = method;
   }

   public String getMethod(){ return this.method;}

    public void setBodyParams(String key,String value){bodyParams.put(key,value);}

    public Map<String,String> getBodyParams(){return bodyParams;}



    public void setBody(String body){

       this.body = body;
    }

    public String getBody(){
        return this.body;
    }

    public void setPathParameter(String  key, String value){
       pathParams.put(key,value);
    }

    public Map<String,String> getPathParameter(){
        return this.pathParams;
    }
}
