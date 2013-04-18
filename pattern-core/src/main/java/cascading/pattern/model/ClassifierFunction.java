/*
 * Copyright (c) 2007-2013 Concurrent, Inc. All Rights Reserved.
 *
 * Project and contact information: http://www.cascading.org/
 *
 * This file is part of the Cascading project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cascading.pattern.model;

import cascading.flow.FlowProcess;
import cascading.operation.BaseOperation;
import cascading.operation.Function;
import cascading.operation.OperationCall;
import cascading.tuple.Tuple;


public abstract class ClassifierFunction<P extends Param> extends BaseOperation<ClassifierFunction.Context> implements Function<ClassifierFunction.Context>
  {
  protected P param;

  /** Class Context is used to hold intermediate values. */
  protected static class Context
    {
    Tuple tuple = Tuple.size( 1 );

    public Tuple result( Object label )
      {
      tuple.set( 0, label );

      return tuple;
      }
    }

  protected ClassifierFunction( P param )
    {
    super( param.getSchemaParam().getInputFields().size(), param.getSchemaParam().getDeclaredFields() );
    this.param = param;
    }

  public P getParam()
    {
    return param;
    }

  @Override
  public void prepare( FlowProcess flowProcess, OperationCall<ClassifierFunction.Context> operationCall )
    {
    operationCall.setContext( new ClassifierFunction.Context() );
    }
  }
