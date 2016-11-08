package util;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by Максим on 11/8/2016.
 */
public final class RunIfRule implements TestRule {

    private boolean condition;

    public RunIfRule(boolean runCondition) {
        this.condition = runCondition;
    }

    @Override
    public Statement apply(Statement statement, Description description) {

        final RunIf annotation = description.getAnnotation(RunIf.class);

        if (annotation == null || condition == annotation.condition()) return statement;

        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                // skip
            }
        };
    }
}
