import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class BISReader extends InputStreamReader {
	public BISReader(InputStream is) {
		super(is);
	}

	@Override
	public int read(char[] cbuf, int offset, int length) throws IOException {
		int c = read();
		if (c == -1)
			return -1;
		else {
			cbuf[offset] = (char) c;
			return 1;
		}
	}
}
