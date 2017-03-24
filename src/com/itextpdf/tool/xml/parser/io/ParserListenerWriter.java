/*
 * $Id: 8a5991de20cf143a9e2defe90919fd1cbd1322a9 $
 *
 * This file is part of the iText (R) project.
 * Copyright (c) 1998-2016 iText Group NV
 * Authors: Balder Van Camp, Emiel Ackermann, et al.
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
package com.itextpdf.tool.xml.parser.io;

import java.util.Map;
import java.util.Map.Entry;

import com.itextpdf.tool.xml.parser.XMLParserListener;

/**
 * Debugging util.
 * @author redlab_b
 *
 */
public class ParserListenerWriter implements XMLParserListener {
	/**
	 *
	 */
	private final Appender writer;
	private final boolean formatted;

	/**
	 * @param writer the appender
	 * @param formatted true if output should be formatted
	 */
	public ParserListenerWriter(final Appender writer, final boolean formatted) {
		this.writer = writer;
		this.formatted = formatted;
	}
	/**
	 * Construct a new ParserListenerWriter with the given appender and default formatted to true;
	 * @param writer the appender
	 */
	public ParserListenerWriter(final Appender writer) {
		this(writer, true);
	}

	public void unknownText(final String string) {
	}


	public void startElement(final String currentTag, final Map<String, String> attributes, final String ns) {
		String myns = (ns.length() > 0)?ns+":":ns;
		if( attributes.size() >0) {
			writer.append("<").append(myns ).append(currentTag).append(" ");
			for (Entry<String,String> e : attributes.entrySet()) {
				writer.append(e.getKey()).append("=\"").append(e.getValue()).append("\" ");
			}
			writer.append('>');
		} else {
			writer.append('<').append(myns).append(currentTag).append('>');
		}
	}

	public void endElement(final String curentTag, final String ns) {
		String myns = (ns.length() > 0)?ns+":":ns;
		writer.append("</").append(myns).append(curentTag).append('>');
		if (formatted) {
			writer.append((char) Character.LINE_SEPARATOR);
		}
	}

	/* (non-Javadoc)
	 * @see com.itextpdf.tool.xml.parser.ParserListener#comment(java.lang.String)
	 */
	public void comment(final String comment) {
		writer.append("<!--").append(comment).append("-->");
	}
	/* (non-Javadoc)
	 * @see com.itextpdf.tool.xml.parser.XMLParserListener#init()
	 */
	public void init() {
	}
	/* (non-Javadoc)
	 * @see com.itextpdf.tool.xml.parser.XMLParserListener#close()
	 */
	public void close() {
	}
	/* (non-Javadoc)
	 * @see com.itextpdf.tool.xml.parser.XMLParserListener#text(java.lang.String)
	 */
	public void text(final String text) {
		writer.append(text);

	}

}
