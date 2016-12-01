package net.sourceforge.lept4j;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * -------------------------------------------------------------*<br>
 *                       BMP info header                       *<br>
 * -------------------------------------------------------------<br>
 * <i>native declaration : bmp.h:36</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class BMP_InfoHeader extends Structure {
	/**
	 * size of the BMP_InfoHeader struct<br>
	 * C type : l_int32
	 */
	public int biSize;
	/**
	 * bitmap width in pixels<br>
	 * C type : l_int32
	 */
	public int biWidth;
	/**
	 * bitmap height in pixels<br>
	 * C type : l_int32
	 */
	public int biHeight;
	/**
	 * number of bitmap planes<br>
	 * C type : l_int16
	 */
	public short biPlanes;
	/**
	 * number of bits per pixel<br>
	 * C type : l_int16
	 */
	public short biBitCount;
	/**
	 * compression format (0 == uncompressed)<br>
	 * C type : l_int32
	 */
	public int biCompression;
	/**
	 * size of image in bytes<br>
	 * C type : l_int32
	 */
	public int biSizeImage;
	/**
	 * pixels per meter in x direction<br>
	 * C type : l_int32
	 */
	public int biXPelsPerMeter;
	/**
	 * pixels per meter in y direction<br>
	 * C type : l_int32
	 */
	public int biYPelsPerMeter;
	/**
	 * number of colors used<br>
	 * C type : l_int32
	 */
	public int biClrUsed;
	/**
	 * number of important colors used<br>
	 * C type : l_int32
	 */
	public int biClrImportant;
	public BMP_InfoHeader() {
		super();
	}
	protected List<? > getFieldOrder() {
		return Arrays.asList("biSize", "biWidth", "biHeight", "biPlanes", "biBitCount", "biCompression", "biSizeImage", "biXPelsPerMeter", "biYPelsPerMeter", "biClrUsed", "biClrImportant");
	}
	public BMP_InfoHeader(Pointer peer) {
		super(peer);
		read();
	}
	public static class ByReference extends BMP_InfoHeader implements Structure.ByReference {
		
	};
	public static class ByValue extends BMP_InfoHeader implements Structure.ByValue {
		
	};
}
