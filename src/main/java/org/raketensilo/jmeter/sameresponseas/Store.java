package org.raketensilo.jmeter.sameresponseas;

import java.io.*;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.raketensilo.jmeter.response.ResponseType;

public class Store {

    private String storePath;
    private String testName;

    public Store init(String basePath, String testGroupName, String testName) {
        if (basePath.endsWith("/")) {
            basePath = basePath.substring(0, basePath.length() - 1);
        }
        storePath = basePath + "/" + testGroupName;

        this.testName = testName.replaceAll("\\s","");
        return this;
    }

    public String getTestStorePath(ResponseType responseType) {
        return storePath + "/" + testName + responseType.asSuffix();
    }

    public Store logResponse(String response) {

        // if store directory does not exist
        File dir = new File(storePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File actualFile = new File(getTestStorePath(ResponseType.ACTUAL));
        File expectedFile = new File(getTestStorePath(ResponseType.EXPECTED));

        // write actual response to store
        writeResponseToFile(actualFile, response);

        // if expected doesnt exist, actual is considered expected
        if (actualFile.exists() && !expectedFile.exists()) {
            try {
                FileUtils.copyFile(actualFile, expectedFile);
            } catch(IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        return this;
    }

    private void writeResponseToFile(File file, String response) {
        try {
            FileUtils.writeStringToFile(
                    file,
                    response,
                    StandardCharsets.UTF_8.name());
        } catch(IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

//        try {
//            FileWriter fw = new FileWriter(file, false);
//            BufferedWriter out = new BufferedWriter(fw);
//            out.write(response);
//            out.close();
//            fw.close();
//        } catch(IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
    }

    private String loadResponseAsString(ResponseType responseType) {

        String response;
        try {
            response = FileUtils.readFileToString(
                    new File(getTestStorePath(responseType)),
                    StandardCharsets.UTF_8.name()
            );
        } catch(IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return response;
    }

    public String getActualResponseAsString() {
        return loadResponseAsString(ResponseType.ACTUAL);
    }

    public String getExpectedResponseAsString() {
        return loadResponseAsString(ResponseType.EXPECTED);
    }


}
