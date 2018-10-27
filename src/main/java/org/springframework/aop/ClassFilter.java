/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.aop;

@FunctionalInterface
public interface ClassFilter {

    /**
     * Should the pointcut apply to the given interface or target class?
     *
     * @param clazz the candidate target class
     * @return whether the advice should apply to the given target class
     */
    boolean matches(Class<?> clazz);


    /**
     * Canonical instance of a ClassFilter that matches all classes.
     */
    ClassFilter TRUE = TrueClassFilter.INSTANCE;

}
