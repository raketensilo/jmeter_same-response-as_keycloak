package org.raketensilo.jmeter.assertion;

import org.raketensilo.jmeter.MyStringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class AssertionErrorLogger {

    private String assertionErrorPath;
    private String assertionErrorFileName;

    private String assertionError;

    private String testGroupName;
    private String testName;

    private static final String ASSERTIONERROR_FILENAME_DEFAULT = "AssertionErrors";
    private static final String ASSERTIONERROR_FILENAME_SUFFIX = ".txt";


    public AssertionErrorLogger init(String basePath, String testGroupName, String assertionErrorFileName) {

        if (basePath.endsWith("/")) {
            basePath = basePath.substring(0, basePath.length() - 1);
        }

//        this.testGroupName = testGroupName.replaceAll("\\s","");
        this.assertionErrorFileName = assertionErrorFileName.replaceAll("\\s","");

        assertionErrorPath = basePath + "/" + testGroupName;
        this.testGroupName = testGroupName;

        return this;
    }

    public AssertionErrorLogger clear() {

        // if assertionErrors file exists, delete it
        File assertionErrorFile = new File(getAssertionErrorFilePath());
        if (assertionErrorFile.exists()) {
            assertionErrorFile.delete();
        }

        return this;
    }

    public AssertionErrorLogger init(String basePath, String testGroupName) {
        init(basePath, testGroupName, ASSERTIONERROR_FILENAME_DEFAULT);

        return this;
    }

    public AssertionErrorLogger setTestName(String testName) {
        this.testName = testName;

        return this;
    }

    public String getAssertionErrorFilePath() {
        return assertionErrorPath + "/" + assertionErrorFileName + ASSERTIONERROR_FILENAME_SUFFIX;
    }

    private void logAssertionErrorToFile(String assertionErrorMessage) {
        try {
            FileWriter fw = new FileWriter(new File(getAssertionErrorFilePath()), true);

            BufferedWriter out = new BufferedWriter(fw);
            out.write(testGroupName + " : " + testName);
            out.newLine();
            out.write(MyStringUtils.useSystemLineSeparator(assertionErrorMessage));
            out.newLine();
            out.newLine();
            out.newLine();
            out.close();

            fw.close();
        } catch(IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void setAssertionError(String assertionErrorMessage) {

        assertionError =
                testGroupName + " : " + testName
                + MyStringUtils.useSystemLineSeparator(assertionErrorMessage);

    }

    public String getAssertionError() {
        return assertionError;
    }

    public void log(String assertionErrorMessage) {

        File dir = new File(assertionErrorPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        logAssertionErrorToFile(assertionErrorMessage);

        setAssertionError(assertionErrorMessage);

    }

}
