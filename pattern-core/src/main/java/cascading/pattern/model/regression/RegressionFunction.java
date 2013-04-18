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

package cascading.pattern.model.regression;

import java.util.Map;

import cascading.flow.FlowProcess;
import cascading.operation.FunctionCall;
import cascading.pattern.model.ClassifierFunction;
import cascading.pattern.model.regression.predictor.Predictor;
import cascading.tuple.Tuple;
import cascading.tuple.TupleEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class RegressionFunction extends ClassifierFunction<RegressionParam>
  {
  private static final Logger LOG = LoggerFactory.getLogger( RegressionFunction.class );

  public RegressionFunction( RegressionParam param )
    {
    super( param );
    }

  @Override
  public void operate( FlowProcess flowProcess, FunctionCall<Context> functionCall )
    {
    TupleEntry arguments = functionCall.getArguments();
    Tuple values = arguments.getTuple();

    Map<String, Object> param_map = getParam().getSchemaParam().getParamMap( values );
    double result = getParam().intercept;

    for( Predictor pred : getParam().predictors )
      result += pred.calcTerm( param_map );

    LOG.debug( "result: " + result );

    functionCall.getOutputCollector().add( functionCall.getContext().result( result ) );
    }
  }
