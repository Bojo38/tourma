/*
 * $Id: b49d3d171022df3b88995b9ff88e7a26a0cb47a0 $
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
package com.itextpdf.tool.xml.net;

import java.net.MalformedURLException;
import java.net.URL;

import com.itextpdf.text.Image;
import com.itextpdf.tool.xml.net.exc.NoImageException;
import com.itextpdf.tool.xml.pipeline.html.ImageProvider;
import com.itextpdf.tool.xml.pipeline.html.UrlLinkResolver;

/**
 * @author redlab_b
 *
 */
public class ImageRetrieve {
	private final ImageProvider imageProvider;
	private String resourcesRootPath;

	public ImageRetrieve(String resourcesRootPath, final ImageProvider imageProvider) {
		this.imageProvider = imageProvider;
		this.resourcesRootPath = resourcesRootPath;
	}

	public ImageRetrieve(String resourcesRootPath) {
		this.resourcesRootPath = resourcesRootPath;
		this.imageProvider = null;
	}

	public ImageRetrieve(final ImageProvider imageProvider) {
		this.imageProvider = imageProvider;
		this.resourcesRootPath = null;
	}

	public ImageRetrieve() {
		this.imageProvider = null;
		this.resourcesRootPath = null;
	}

	public com.itextpdf.text.Image retrieveImage(final String src) throws NoImageException {
		com.itextpdf.text.Image img;
		img = tryRetrieveImageWithImageProvider(src);

		if (img == null) {
			try {
				URL url = getImageUrl(src);
				img = Image.getInstance(url);
			} catch (Exception e) {
				throw new NoImageException(src, e);
			}
		}
		if (imageProvider != null && img != null) {
			imageProvider.store(src, img);
		}

		return img;
	}

	private Image tryRetrieveImageWithImageProvider(String src) {
		if (imageProvider != null) {
			return imageProvider.retrieve(src);
		}
		return null;
	}

	private URL getImageUrl(String src) throws MalformedURLException {
		UrlLinkResolver linkResolver = new UrlLinkResolver();
		URL url = null;
		if (imageProvider != null) {
			linkResolver.setLocalRootPath(imageProvider.getImageRootPath());
			url = linkResolver.resolveUrl(src);
		}
		if (url == null) {
			linkResolver.setLocalRootPath(resourcesRootPath);
			url = linkResolver.resolveUrl(src);
		}

		return url;
	}
}
