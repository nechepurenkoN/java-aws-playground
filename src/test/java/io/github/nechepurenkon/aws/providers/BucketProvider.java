package io.github.nechepurenkon.aws.providers;

import java.util.List;
import org.testng.annotations.DataProvider;

public class BucketProvider {

    @DataProvider
    public static Object[][] simpleBucketNames() {
        return new Object[][] {
            {List.of(
                "nechn-testbucket-1",
                "nechn-testbucket-2",
                "nechn-testbucket-3"
            )}
        };
    }
}
