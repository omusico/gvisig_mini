/* gvSIG Mini. A free mobile phone viewer of free maps.
 *
 * Copyright (C) 2011 Prodevelop.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307,USA.
 *
 * For more information, contact:
 *
 *   Prodevelop, S.L.
 *   Pza. Don Juan de Villarrasa, 14 - 5
 *   46001 Valencia
 *   Spain
 *
 *   +34 963 510 612
 *   +34 963 510 968
 *   prode@prodevelop.es
 *   http://www.prodevelop.es
 *
 *   gvSIG Mini has been partially funded by IMPIVA (Instituto de la PequeÔøΩa y
 *   Mediana Empresa de la Comunidad Valenciana) &
 *   European Union FEDER funds.
 *   
 *   2011.
 *   author Alberto Romeu aromeu@prodevelop.es
 */

package es.prodevelop.gvsig.mini.symbol;

import android.graphics.Canvas;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

import es.prodevelop.gvsig.mini.exceptions.BaseException;
import es.prodevelop.gvsig.mini.geom.impl.jts.JTSFeature;
import es.prodevelop.gvsig.mini.map.IMapView;

public class PointsAsLinesSymbol extends BasePathSymbol {

	private int i = 0;

	public PointsAsLinesSymbol(float pointWidth) {
		super(pointWidth);
		// TODO Auto-generated constructor stub
	}

	public PointsAsLinesSymbol(int color, float pointWidth) {
		super(color, pointWidth);
		// TODO Auto-generated constructor stub
	}

	public void resetPath() {
		super.resetPath();
		i = 0;
	}

	@Override
	public void draw(Canvas c, IMapView maps, JTSFeature feature)
			throws BaseException {
		final Geometry geometry = feature.getProjectedGeometry().getGeometry();

		if (geometry instanceof Point) {
			final double x = ((Point) geometry).getX();
			final double y = ((Point) geometry).getY();
			final int[] current = maps.getMRendererInfo().toPixels(
					new double[] { x, y });

			if (i == 0)
				getPath().moveTo(current[0], current[1]);
			else
				getPath().lineTo(current[0], current[1]);

			i++;
		}
	}

}
