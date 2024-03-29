/*
 * $Id: be719f4f510fcd23980f2c877b22e25aa2b73c3c $
 *
 * This file is part of the iText (R) project.
 * Copyright (c) 1998-2016 iText Group NV
 * Authors: Bruno Lowagie, Balder Van Camp, et al.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License version 3
 * as published by the Free Software Foundation with the addition of the
 * following permission added to Section 15 as permitted in Section 7(a):
 * FOR ANY PART OF THE COVERED WORK IN WHICH THE COPYRIGHT IS OWNED BY
 * ITEXT GROUP. ITEXT GROUP DISCLAIMS THE WARRANTY OF NON INFRINGEMENT
 * OF THIRD PARTY RIGHTS
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, see http://www.gnu.org/licenses or write to
 * the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA, 02110-1301 USA, or download the license from the following URL:
 * http://itextpdf.com/terms-of-use/
 *
 * The interactive user interfaces in modified source and object code versions
 * of this program must display Appropriate Legal Notices, as required under
 * Section 5 of the GNU Affero General Public License.
 *
 * In accordance with Section 7(b) of the GNU Affero General Public License,
 * a covered work must retain the producer line in every PDF that is created
 * or manipulated using iText.
 *
 * You can be released from the requirements of the license by purchasing
 * a commercial license. Buying such a license is mandatory as soon as you
 * develop commercial activities involving the iText software without
 * disclosing the source code of your own applications.
 * These activities include: offering paid services to customers as an ASP,
 * serving PDFs on the fly in a web application, shipping iText with a closed
 * source product.
 *
 * For more information, please contact iText Software Corp. at this
 * address: sales@itextpdf.com
 */
package com.itextpdf.text.pdf.spatial;

import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.spatial.units.PtIdentifier;

/**
 * A Point Data dictionary.
 * @since 5.1.0
 */
public class PointData extends PdfDictionary {

	/**
	 * An array of names that identify the internal
	 * data elements of the individual point arrays in the XPTS array.
	 */
	protected PdfArray names = new PdfArray();
	/**
	 * An array with XPTS values.
	 */
	protected PdfArray xpts = new PdfArray();
	
	/**
	 * Creates a Point Data dictionary.
	 */
	public PointData() {
		super(PdfName.PTDATA);
		super.putEx(PdfName.SUBTYPE, PdfName.CLOUD);
		super.putEx(PdfName.NAMES, names);
		super.putEx(PdfName.XPTS, xpts);
	}

	/**
	 * Adds a point to the Point Data dictionary.
	 * @param value	an XPTS value
	 * @param identifier
	 */
	public void addXPTSValue(PdfNumber value, PtIdentifier identifier) {
		xpts.add(value);
		names.add(identifier.getPdfName());
	}
}
