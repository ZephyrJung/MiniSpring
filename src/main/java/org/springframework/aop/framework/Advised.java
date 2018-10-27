/*
 * Copyright 2002-2015 the original author or authors.
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

package org.springframework.aop.framework;

import org.aopalliance.aop.Advice;

import org.springframework.aop.Advisor;
import org.springframework.aop.TargetClassAware;
import org.springframework.aop.TargetSource;

/**
 * Interface to be implemented by classes that hold the configuration
 * of a factory of AOP proxies. This configuration includes the
 * Interceptors and other advice, Advisors, and the proxied interfaces.
 *
 * <p>Any AOP proxy obtained from Spring can be cast to this interface to
 * allow manipulation of its AOP advice.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @since 13.03.2003
 * @see org.springframework.aop.framework.AdvisedSupport
 */
public interface Advised extends TargetClassAware {

    /**
     * Return whether the Advised configuration is frozen,
     * in which case no advice changes can be made.
     */
    boolean isFrozen();

    /**
     * Are we proxying the full target class instead of specified interfaces?
     */
    boolean isProxyTargetClass();

    /**
     * Return the interfaces proxied by the AOP proxy.
     * <p>Will not include the target class, which may also be proxied.
     */
    Class<?>[] getProxiedInterfaces();

    /**
     * Determine whether the given interface is proxied.
     * @param intf the interface to check
     */
    boolean isInterfaceProxied(Class<?> intf);

    /**
     * Change the {@code TargetSource} used by this {@code Advised} object.
     * <p>Only works if the configuration isn't {@linkplain #isFrozen frozen}.
     * @param targetSource new TargetSource to use
     */
    void setTargetSource(TargetSource targetSource);

    /**
     * Return the {@code TargetSource} used by this {@code Advised} object.
     */
    TargetSource getTargetSource();

    void setExposeProxy(boolean exposeProxy);

    boolean isExposeProxy();

    void setPreFiltered(boolean preFiltered);

    /**
     * Return whether this proxy configuration is pre-filtered so that it only
     * contains applicable advisors (matching this proxy's target class).
     */
    boolean isPreFiltered();

    /**
     * Return the advisors applying to this proxy.
     * @return a list of Advisors applying to this proxy (never {@code null})
     */
    Advisor[] getAdvisors();

    void addAdvisor(Advisor advisor) throws AopConfigException;

    /**
     * Add an Advisor at the specified position in the chain.
     * @param advisor the advisor to add at the specified position in the chain
     * @param pos position in chain (0 is head). Must be valid.
     * @throws AopConfigException in case of invalid advice
     */
    void addAdvisor(int pos, Advisor advisor) throws AopConfigException;

    /**
     * Remove the given advisor.
     * @param advisor the advisor to remove
     * @return {@code true} if the advisor was removed; {@code false}
     * if the advisor was not found and hence could not be removed
     */
    boolean removeAdvisor(Advisor advisor);

    /**
     * Remove the advisor at the given index.
     * @param index index of advisor to remove
     * @throws AopConfigException if the index is invalid
     */
    void removeAdvisor(int index) throws AopConfigException;

    int indexOf(Advisor advisor);

    boolean replaceAdvisor(Advisor a, Advisor b) throws AopConfigException;

    void addAdvice(Advice advice) throws AopConfigException;

    void addAdvice(int pos, Advice advice) throws AopConfigException;

    /**
     * Remove the Advisor containing the given advice.
     * @param advice the advice to remove
     * @return {@code true} of the advice was found and removed;
     * {@code false} if there was no such advice
     */
    boolean removeAdvice(Advice advice);

    /**
     * Return the index (from 0) of the given AOP Alliance Advice,
     * or -1 if no such advice is an advice for this proxy.
     * <p>The return value of this method can be used to index into
     * the advisors array.
     * @param advice AOP Alliance advice to search for
     * @return index from 0 of this advice, or -1 if there's no such advice
     */
    int indexOf(Advice advice);

    /**
     * As {@code toString()} will normally be delegated to the target,
     * this returns the equivalent for the AOP proxy.
     * @return a string description of the proxy configuration
     */
    String toProxyConfigString();

}
