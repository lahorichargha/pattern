/*
 * Copyright (c) 2007-2012 Concurrent, Inc. All Rights Reserved.
 *
 * Project and contact information: http://www.concurrentinc.com/
 */

package pattern.rf;

import org.jgrapht.graph.DefaultEdge;


public class Edge extends DefaultEdge
  {
  public Integer predicate_id = null;

  /** @param predicate_id  */
  public void setPredicateId( Integer predicate_id )
    {
    this.predicate_id = predicate_id;
    }

  /** @return  */
  public Integer getPredicateId()
    {
    return predicate_id;
    }

  /** @return  */
  public String toString()
    {
    String base = super.toString();

    return base + ":" + predicate_id;
    }
  }