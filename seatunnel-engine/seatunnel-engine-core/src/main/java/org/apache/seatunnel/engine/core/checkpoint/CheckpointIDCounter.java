/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.seatunnel.engine.core.checkpoint;

import org.apache.seatunnel.engine.core.job.PipelineState;

import java.util.concurrent.CompletableFuture;

/** A checkpoint ID counter. */
public interface CheckpointIDCounter {

    long INITIAL_CHECKPOINT_ID = 1;

    /** Starts the {@link CheckpointIDCounter} service down. */
    void start() throws Exception;

    /**
     * Shuts the {@link CheckpointIDCounter} service.
     *
     * <p>The job status is forwarded and used to decide whether state should actually be discarded
     * or kept.
     *
     * @return The {@code CompletableFuture} holding the result of the shutdown operation.
     */
    CompletableFuture<Void> shutdown(PipelineState jobStatus);

    /**
     * Atomically increments the current checkpoint ID.
     *
     * @return The previous checkpoint ID
     */
    long getAndIncrement() throws Exception;

    /**
     * Atomically gets the current checkpoint ID.
     *
     * @return The current checkpoint ID
     */
    long get();

    /**
     * Sets the current checkpoint ID.
     *
     * @param newId The new ID
     */
    void setCount(long newId) throws Exception;
}
