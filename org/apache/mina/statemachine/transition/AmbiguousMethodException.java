/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.apache.mina.statemachine.transition;

/**
 * Thrown by the constructors in {@link MethodTransition} if there are several 
 * methods with the specifed name in the target object's class. 
 *
 * @author The Apache MINA Project (dev@mina.apache.org)
 * @version $Rev: 588579 $, $Date: 2007-10-26 11:21:01 +0200 (ven, 26 oct 2007) $
 */
public class AmbiguousMethodException extends RuntimeException {

    private static final long serialVersionUID = -3926582218186692464L;

    /**
     * Creates a new instance using the specified method name as message.
     * 
     * @param methodName the name of the method.
     */
    public AmbiguousMethodException(String methodName) {
        super(methodName);
    }
    
}
