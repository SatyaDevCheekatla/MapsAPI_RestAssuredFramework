package org.satya.base;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.JSONParser;
import org.satya.driver.RestDriver;
import org.satya.utils.LoggerUtil;
import org.satya.utils.TestContext;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class BasePage {

    protected RestDriver driver;
    protected TestContext testContext;
    protected APIObject apiObject; // made protected so subclasses use same instance
    private  Properties prop ;
    private Response response;
    private Logger log;
    protected ExtentTest reportResult;

    // Public no-arg constructor required by Cucumber
    public BasePage() {
        apiObject = new APIObject();
    }

    public BasePage(RestDriver driver, TestContext test){
        this.driver = driver;
        this.testContext = test;
        this.log = test.logger;
        this.reportResult = test.reportResult;
        apiObject = new APIObject();
    }

    public Response sentRequest(){

        RequestSpecification request = driver.requestSpecification();

        // headers
        if(apiObject.getHeaders() != null && !apiObject.getHeaders().isEmpty()){
            request.headers(apiObject.getHeaders());
        }

        // body (string)
        if(apiObject.getBody() != null && !apiObject.getBody().isEmpty()){
            request.body(apiObject.getBody());
        }

        // basic auth
        if(apiObject.getBasicAuth() != null && !apiObject.getBasicAuth().isEmpty()){
            String username = apiObject.getBasicAuth().entrySet().iterator().next().getKey();
            String password = apiObject.getBasicAuth().entrySet().iterator().next().getValue();
            request.auth().basic(username,password);
        }

        // form params
        if(apiObject.getFormParams() != null && !apiObject.getFormParams().isEmpty()){
            request.formParams(apiObject.getFormParams());
        }

        // query params
        if(apiObject.getQueryParams() != null && !apiObject.getQueryParams().isEmpty()){
            request.queryParams(apiObject.getQueryParams());
        }

        // body params (map)
        if(apiObject.getBodyParams() != null && !apiObject.getBodyParams().isEmpty()){
            request.body(apiObject.getBodyParams());
        }

        // path params
        if(apiObject.getPathParameter() != null && !apiObject.getPathParameter().isEmpty()){
            request.pathParams(apiObject.getPathParameter());
        }

        // base URI from properties
        String base = readPropertyFile("baseUrl");
        if(base != null && !base.isEmpty()){
            request.baseUri(base);
        }


        if (apiObject.getMethod() != null && !apiObject.getMethod().isEmpty()) {
            switch (apiObject.getMethod().toUpperCase()) {
                case "GET":
                    response = request.get(apiObject.getUrl());
                    break;

                case "POST":
                    response = request.post(apiObject.getUrl());
                    break;
                case "PUT":
                    response = request.put(apiObject.getUrl());
                    break;
                case "DELETE":
                    response = request.delete(apiObject.getUrl());
                    break;
                default:
                    throw new RuntimeException("Invalid request method");
            }
        }

        return response;
    }

    public String fetchBody(String filename) {
        String path = "src/main/resources/body/"+filename;
        try (java.io.FileReader reader = new FileReader(path)) {
            Object localObj = new JSONParser().parse(reader);
            if (localObj != null) {
                return localObj.toString();
            } else {
                throw new RuntimeException("Parsed JSON object is null for file: " + filename);
            }

        } catch (Exception e) {
            throw new RuntimeException("Could not load JSON file: " + filename, e);
        }
    }

    public String readPropertyFile(String property) {

        try {
            prop = new Properties();
            File file = new File("src/test/resources/config.properties");
            prop.load(new FileReader(file));

        } catch (Exception e) {
            System.err.println("Could not load properties file: " + property + " - " + e.getMessage());
            return null;
        }
        return prop.getProperty(property);
    }

}
