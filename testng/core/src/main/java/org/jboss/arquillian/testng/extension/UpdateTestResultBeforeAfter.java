/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009 Red Hat Inc. and/or its affiliates and other contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.arquillian.testng.extension;

import org.jboss.arquillian.core.api.annotation.Observes;
import org.jboss.arquillian.core.spi.EventContext;
import org.jboss.arquillian.test.spi.TestResult;
import org.jboss.arquillian.test.spi.TestResult.Status;
import org.jboss.arquillian.test.spi.event.suite.After;
import org.jboss.arquillian.testng.State;

/**
 * Update the TestResult based on result After going through the JUnit chain.
 * <p>
 * This will give the correct TestResult in After, even with validation outside
 * of Arquillians control, e.g. ExpectedExceptions.
 */
class UpdateTestResultBeforeAfter {
    public void update(@Observes(precedence = 99) EventContext<After> context, TestResult result) {
        if (State.caughtExceptionAfter() != null) {
            result.setStatus(TestResult.Status.FAILED);
            result.setThrowable(State.caughtExceptionAfter());
        } else {
            result.setStatus(Status.PASSED);
            result.setThrowable(null);
        }
        State.caughtExceptionAfter(null);
        context.proceed();
    }
}

