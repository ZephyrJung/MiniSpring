/*
 * Copyright 2002-2012 the original author or authors.
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

package org.springframework.beans.factory.parsing;

import org.springframework.beans.BeanMetadataElement;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanReference;

public interface ComponentDefinition extends BeanMetadataElement {

    /**
     * Get the user-visible name of this {@code ComponentDefinition}.
     * <p>This should link back directly to the corresponding configuration data
     * for this component in a given context.
     */
    String getName();

    /**
     * Return a friendly description of the described component.
     * <p>Implementations are encouraged to return the same value from
     * {@code toString()}.
     */
    String getDescription();

    /**
     * Return the {@link BeanDefinition BeanDefinitions} that were registered
     * to form this {@code ComponentDefinition}.
     * <p>It should be noted that a {@code ComponentDefinition} may well be related with
     * other {@link BeanDefinition BeanDefinitions} via {@link BeanReference references},
     * however these are <strong>not</strong> included as they may be not available immediately.
     * Important {@link BeanReference BeanReferences} are available from {@link #getBeanReferences()}.
     * @return the array of BeanDefinitions, or an empty array if none
     */
    BeanDefinition[] getBeanDefinitions();

    /**
     * Return the {@link BeanDefinition BeanDefinitions} that represent all relevant
     * inner beans within this component.
     * <p>Other inner beans may exist within the associated {@link BeanDefinition BeanDefinitions},
     * however these are not considered to be needed for validation or for user visualization.
     * @return the array of BeanDefinitions, or an empty array if none
     */
    BeanDefinition[] getInnerBeanDefinitions();

    /**
     * Return the set of {@link BeanReference BeanReferences} that are considered
     * to be important to this {@code ComponentDefinition}.
     * <p>Other {@link BeanReference BeanReferences} may exist within the associated
     * {@link BeanDefinition BeanDefinitions}, however these are not considered
     * to be needed for validation or for user visualization.
     * @return the array of BeanReferences, or an empty array if none
     */
    BeanReference[] getBeanReferences();

}
