/*
 * This file is part of ELKI:
 * Environment for Developing KDD-Applications Supported by Index-Structures
 *
 * Copyright (C) 2019
 * ELKI Development Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.lmu.ifi.dbs.elki.distance.distancefunction.minkowski;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import de.lmu.ifi.dbs.elki.distance.distancefunction.AbstractDistanceFunctionTest;
import de.lmu.ifi.dbs.elki.utilities.ELKIBuilder;
import net.jafama.FastMath;

/**
 * Unit test for Lp norms with integer p.
 *
 * @author Erich Schubert
 * @since 0.7.5
 */
public class LPIntegerNormDistanceFunctionTest extends AbstractDistanceFunctionTest {
  @Test
  public void testSpatialConsistency() {
    // Also test the builder - we could have just used .STATIC
    LPIntegerNormDistanceFunction dist = new ELKIBuilder<>(LPIntegerNormDistanceFunction.class) //
        .with(LPNormDistanceFunction.Parameterizer.P_ID, 3) //
        .build();
    assertSame("Subtyped", LPIntegerNormDistanceFunction.class, dist.getClass());
    basicChecks(dist);
    varyingLengthBasic(0, dist, 1, 0, 1, 1, FastMath.pow(2, 1. / 3), 1);
    spatialConsistency(dist);
    nonnegativeSpatialConsistency(dist);
  }
}
