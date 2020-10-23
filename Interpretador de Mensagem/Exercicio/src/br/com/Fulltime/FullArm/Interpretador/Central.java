package br.com.Fulltime.FullArm.Interpretador;

public class Central {

	private static String Mensagem;
	static boolean certo = true; 
	static byte header = 0, tamanho = 0, sequencia = 0, pacote = 0, check = 0, teste = 0;

	//Array para os dados das mensagens
	static String [] arrayStringDadosEvento = new String[19];
	static String [] arrayStringConexao = new String[97];


	public static String getMensagem() {
		return Mensagem;
	}

	public static void setMensagem(String mensagem) {
		Mensagem = mensagem.replace(" ", "");
	}

	public static byte[] fromHexString(String rawData) {

		int len = rawData.length();
		byte[] data = new byte[len / 2];

		for (int i = 0; i < len; i += 2) {
			int nextPosition = i + 1;
			data[i / 2] = (byte) ((Character.digit(rawData.charAt(i), 16) << 4)
					+ Character.digit(rawData.charAt(nextPosition), 16));
		}

		return data;
	}

	public static byte[] fromHexStringDados(String rawData) {

		int len = rawData.length();
		byte[] dados = new byte[rawData.length()/2-5];
		int pos = 0;

		for (int i = 8; i < len-2; i += 2) {
			int nextPosition = i + 1;
			dados[pos] = (byte) ((Character.digit(rawData.charAt(i), 16) << 4)
					+ Character.digit(rawData.charAt(nextPosition), 16));
			pos++;          
		}

		return dados;
	}

	private static final char[] hexCode = "0123456789ABCDEF".toCharArray();
	public static String byteArrayToHexString(byte[] data) {
		StringBuilder r = new StringBuilder(data.length * 2);
		for (byte b : data) {
			r.append(hexCode[(b >> 4) & 0xF]);
			r.append(hexCode[(b & 0xF)]);
		}
		return r.toString();
	}


	protected static String hexToAscii(String hexStr) {
		StringBuilder output = new StringBuilder("");

		for (int i = 0; i < hexStr.length(); i += 2) {
			String str = hexStr.substring(i, i + 2);
			output.append((char) Integer.parseInt(str, 16));
		}

		return output.toString();
	}
}