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

package org.springframework.jndi;

import javax.naming.Context;
import javax.naming.NamingException;

import org.springframework.lang.Nullable;

@FunctionalInterface
public interface JndiCallback<T> {

    /**
     * Do something with the given JNDI context.
     * <p>Implementations don't need to worry about error handling
     * or cleanup, as the JndiTemplate class will handle this.
     * @param ctx the current JNDI context
     * @throws NamingException if thrown by JNDI methods
     * @return a result object, or {@code null}
     */
    @Nullable
    T doInContext(Context ctx) throws NamingException;

}

