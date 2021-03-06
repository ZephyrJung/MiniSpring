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

package org.springframework.context.annotation;

import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.core.annotation.AliasFor;
import org.springframework.core.type.filter.TypeFilter;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Repeatable(ComponentScans.class)
public @interface ComponentScan {

    /**
     * Alias for {@link #basePackages}.
     * <p>Allows for more concise annotation declarations if no other attributes
     * are needed &mdash; for example, {@code @ComponentScan("org.my.pkg")}
     * instead of {@code @ComponentScan(basePackages = "org.my.pkg")}.
     */
    @AliasFor("basePackages")
    String[] value() default {};

    /**
     * Base packages to scan for annotated components.
     * <p>{@link #value} is an alias for (and mutually exclusive with) this
     * attribute.
     * <p>Use {@link #basePackageClasses} for a type-safe alternative to
     * String-based package names.
     */
    @AliasFor("value")
    String[] basePackages() default {};

    /**
     * Type-safe alternative to {@link #basePackages} for specifying the packages
     * to scan for annotated components. The package of each class specified will be scanned.
     * <p>Consider creating a special no-op marker class or interface in each package
     * that serves no purpose other than being referenced by this attribute.
     */
    Class<?>[] basePackageClasses() default {};

    Class<? extends BeanNameGenerator> nameGenerator() default BeanNameGenerator.class;

    /**
     * The {@link ScopeMetadataResolver} to be used for resolving the scope of detected components.
     */
    Class<? extends ScopeMetadataResolver> scopeResolver() default AnnotationScopeMetadataResolver.class;

    ScopedProxyMode scopedProxy() default ScopedProxyMode.DEFAULT;

    /**
     * Controls the class files eligible for component detection.
     * <p>Consider use of {@link #includeFilters} and {@link #excludeFilters}
     * for a more flexible approach.
     */
    String resourcePattern() default ClassPathScanningCandidateComponentProvider.DEFAULT_RESOURCE_PATTERN;

    /**
     * Indicates whether automatic detection of classes annotated with {@code @Component}
     * {@code @Repository}, {@code @Service}, or {@code @Controller} should be enabled.
     */
    boolean useDefaultFilters() default true;

    /**
     * Specifies which types are eligible for component scanning.
     * <p>Further narrows the set of candidate components from everything in {@link #basePackages}
     * to everything in the base packages that matches the given filter or filters.
     * <p>Note that these filters will be applied in addition to the default filters, if specified.
     * Any type under the specified base packages which matches a given filter will be included,
     * even if it does not match the default filters (i.e. is not annotated with {@code @Component}).
     *
     * @see #resourcePattern()
     * @see #useDefaultFilters()
     */
    Filter[] includeFilters() default {};

    /**
     * Specifies which types are not eligible for component scanning.
     *
     * @see #resourcePattern
     */
    Filter[] excludeFilters() default {};

    /**
     * Specify whether scanned beans should be registered for lazy initialization.
     * <p>Default is {@code false}; switch this to {@code true} when desired.
     *
     * @since 4.1
     */
    boolean lazyInit() default false;


    /**
     * Declares the type filter to be used as an {@linkplain ComponentScan#includeFilters
     * include filter} or {@linkplain ComponentScan#excludeFilters exclude filter}.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    @interface Filter {

        /**
         * The type of filter to use.
         * <p>Default is {@link FilterType#ANNOTATION}.
         *
         * @see #classes
         * @see #pattern
         */
        FilterType type() default FilterType.ANNOTATION;

        /**
         * Alias for {@link #classes}.
         *
         * @see #classes
         */
        @AliasFor("classes")
        Class<?>[] value() default {};

        /**
         * The class or classes to use as the filter.
         * <p>The following table explains how the classes will be interpreted
         * based on the configured value of the {@link #type} attribute.
         * <table border="1">
         * <tr><th>{@code FilterType}</th><th>Class Interpreted As</th></tr>
         * <tr><td>{@link FilterType#ANNOTATION ANNOTATION}</td>
         * <td>the annotation itself</td></tr>
         * <tr><td>{@link FilterType#ASSIGNABLE_TYPE ASSIGNABLE_TYPE}</td>
         * <td>the type that detected components should be assignable to</td></tr>
         * <tr><td>{@link FilterType#CUSTOM CUSTOM}</td>
         * <td>an implementation of {@link TypeFilter}</td></tr>
         * </table>
         * <p>When multiple classes are specified, <em>OR</em> logic is applied
         * &mdash; for example, "include types annotated with {@code @Foo} OR {@code @Bar}".
         * <p>Custom {@link TypeFilter TypeFilters} may optionally implement any of the
         * following {@link org.springframework.beans.factory.Aware Aware} interfaces, and
         * their respective methods will be called prior to {@link TypeFilter#match match}:
         * <ul>
         * <li>{@link org.springframework.context.EnvironmentAware EnvironmentAware}</li>
         * <li>{@link org.springframework.beans.factory.BeanFactoryAware BeanFactoryAware}
         * <li>{@link org.springframework.beans.factory.BeanClassLoaderAware BeanClassLoaderAware}
         * <li>{@link org.springframework.context.ResourceLoaderAware ResourceLoaderAware}
         * </ul>
         * <p>Specifying zero classes is permitted but will have no effect on component
         * scanning.
         *
         * @see #value
         * @see #type
         * @since 4.2
         */
        @AliasFor("value")
        Class<?>[] classes() default {};

        /**
         * The pattern (or patterns) to use for the filter, as an alternative
         * to specifying a Class {@link #value}.
         * <p>If {@link #type} is set to {@link FilterType#ASPECTJ ASPECTJ},
         * this is an AspectJ type pattern expression. If {@link #type} is
         * set to {@link FilterType#REGEX REGEX}, this is a regex pattern
         * for the fully-qualified class names to match.
         *
         * @see #type
         * @see #classes
         */
        String[] pattern() default {};

    }

}
