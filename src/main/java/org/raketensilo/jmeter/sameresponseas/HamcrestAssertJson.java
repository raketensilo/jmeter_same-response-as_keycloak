package org.raketensilo.jmeter.sameresponseas;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.Assert;

import org.hamcrest.CoreMatchers;
import org.raketensilo.jmeter.assertion.AssertionErrorLogger;
import uk.co.datumedge.hamcrest.json.SameJSONAs;

public class HamcrestAssertJson {

    private AssertionErrorLogger assertionErrorLogger = null;

    public HamcrestAssertJson setAssertionErrorLogger(AssertionErrorLogger ael) {
        assertionErrorLogger = ael;

        return this;
    }

    public void sameJsonAs(String actual, String expected) {

//        actual = StringEscapeUtils.unescapeJava(actual);
//        expected = StringEscapeUtils.unescapeJava(expected);

        if (assertionErrorLogger == null) {
            Assert.assertThat(
                    actual,
                    CoreMatchers.is(
                            SameJSONAs.sameJSONAs(expected)
                    )
            );
        } else {
            try {
                Assert.assertThat(
                        actual,
                        CoreMatchers.is(
                                SameJSONAs.sameJSONAs(expected)
                        )
                );
            } catch(AssertionError e) {
                String assertionErrorMessage =
                        StringEscapeUtils.unescapeJava(e.getMessage());
                assertionErrorLogger.log(assertionErrorMessage);
                throw new AssertionError(assertionErrorMessage);
            }
        }

    }

}
