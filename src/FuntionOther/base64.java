package FuntionOther;
import org.apache.commons.codec.binary.Base64;
public class base64 {
	private String encodedBytes ="";
	private String decodedBytes ="";
	
	public String getEncodedBytes() {
		return encodedBytes;
	}
	public void setEncodedBytes(String encodedBytes) {
		this.encodedBytes = encodedBytes;
	}
	public String getDecodedBytes() {
		return decodedBytes;
	}
	public void setDecodedBytes(String decodedBytes) {
		this.decodedBytes = decodedBytes;
	}
	public void encodeBase64()
	{
		byte[] encodedBytes = Base64.encodeBase64("Test".getBytes());
		System.out.println("encodedBytes " + new String(encodedBytes));
		setEncodedBytes(new String(encodedBytes));
		
	}
	public void decodeBase64()
	{
		byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
		System.out.println("decodedBytes " + new String(decodedBytes));
		setDecodedBytes(new String(decodedBytes));
	}

}
