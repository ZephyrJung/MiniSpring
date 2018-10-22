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

package org.springframework.beans.factory.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.lang.Nullable;

public interface Scope {

    /**
     * Return the object with the given name from the underlying scope,
     * {@link org.springframework.beans.factory.ObjectFactory#getObject() creating it}
     * if not found in the underlying storage mechanism.
     * <p>This is the central operation of a Scope, and the only operation
     * that is absolutely required.
     * @param name the name of the object to retrieve
     * @param objectFactory the {@link ObjectFactory} to use to create the scoped
     * object if it is not present in the underlying storage mechanism
     * @return the desired object (never {@code null})
     * @throws IllegalStateException if the underlying scope is not currently active
     */
    Object get(String name, ObjectFactory<?> objectFactory);

    /**
     * Remove the object with the given {@code name} from the underlying scope.
     * <p>Returns {@code null} if no object was found; otherwise
     * returns the removed {@code Object}.
     * <p>Note that an implementation should also remove a registered destruction
     * callback for the specified object, if any. It does, however, <i>not</i>
     * need to <i>execute</i> a registered destruction callback in this case,
     * since the object will be destroyed by the caller (if appropriate).
     * <p><b>Note: This is an optional operation.</b> Implementations may throw
     * {@link UnsupportedOperationException} if they do not support explicitly
     * removing an object.
     * @param name the name of the object to remove
     * @return the removed object, or {@code null} if no object was present
     * @throws IllegalStateException if the underlying scope is not currently active
     * @see #registerDestructionCallback
     */
    @Nullable
    Object remove(String name);

    void registerDestructionCallback(String name, Runnable callback);

    /**
     * Resolve the contextual object for the given key, if any.
     * E.g. the HttpServletRequest object for key "request".
     * @param key the contextual key
     * @return the corresponding object, or {@code null} if none found
     * @throws IllegalStateException if the underlying scope is not currently active
     */
    @Nullable
    Object resolveContextualObject(String key);

    @Nullable
    String getConversationId();

}
