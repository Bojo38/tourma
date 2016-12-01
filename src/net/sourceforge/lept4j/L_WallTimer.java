package net.sourceforge.lept4j;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * <i>native declaration : environ.h:52</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class L_WallTimer extends Structure {
	/** C type : l_int32 */
	public int start_sec;
	/** C type : l_int32 */
	public int start_usec;
	/** C type : l_int32 */
	public int stop_sec;
	/** C type : l_int32 */
	public int stop_usec;
	public L_WallTimer() {
		super();
	}
	protected List<? > getFieldOrder() {
		return Arrays.asList("start_sec", "start_usec", "stop_sec", "stop_usec");
	}
	/**
	 * @param start_sec C type : l_int32<br>
	 * @param start_usec C type : l_int32<br>
	 * @param stop_sec C type : l_int32<br>
	 * @param stop_usec C type : l_int32
	 */
	public L_WallTimer(int start_sec, int start_usec, int stop_sec, int stop_usec) {
		super();
		this.start_sec = start_sec;
		this.start_usec = start_usec;
		this.stop_sec = stop_sec;
		this.stop_usec = stop_usec;
	}
	public L_WallTimer(Pointer peer) {
		super(peer);
		read();
	}
	public static class ByReference extends L_WallTimer implements Structure.ByReference {
		
	};
	public static class ByValue extends L_WallTimer implements Structure.ByValue {
		
	};
}
