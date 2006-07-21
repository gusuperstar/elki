package de.lmu.ifi.dbs.parser;

import java.util.List;
import java.util.Map;

import de.lmu.ifi.dbs.data.DatabaseObject;
import de.lmu.ifi.dbs.distance.Distance;

/**
 * Provides a list of database objects and labels associated with these objects
 * and a cache of precomputed distances between the database objects.
 *
 * @author Elke Achtert (<a
 *         href="mailto:achtert@dbs.ifi.lmu.de">achtert@dbs.ifi.lmu.de</a>)
 */
public class DistanceParsingResult<O extends DatabaseObject, D extends Distance> extends ParsingResult<O> {
  /**
   * The cache of precomputed distances between the database objects.
   */
  private final Map<Integer, Map<Integer, D>> distanceCache;

  /**
   * Provides a list of database objects, a list of label obejcts associated
   * with these objects and cached distances between these objects.
   *
   * @param objectAndLabelList the list of database objects and labels associated with these
   *                           objects
   * @param distanceCache      the cache of precomputed distances between the database
   *                           objects
   */
  public DistanceParsingResult(List<ObjectAndLabels<O>> objectAndLabelList,
                               Map<Integer, Map<Integer, D>> distanceCache) {
    super(objectAndLabelList);
    this.distanceCache = distanceCache;
  }

  /**
   * Returns the cache of precomputed distances between the database objects.
   *
   * @return the cache of precomputed distances between the database objects
   */
  public Map<Integer, Map<Integer, D>> getDistanceCache() {
    return distanceCache;
  }
}
