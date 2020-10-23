package br.com.Fulltime.FullArm.Interpretador;

public class InterpretarTiposMensagens extends Central {

	//Transformando a mensagem recebida em byte
	static byte[] mensagemAInterpretar =  fromHexString(getMensagem());
	static byte[] arrayByteDados = fromHexStringDados(getMensagem());
	static byte auxiliarDados = 0;

	public static void DadosGerais() {

		header = mensagemAInterpretar[0];
		tamanho = mensagemAInterpretar[1];
		sequencia = mensagemAInterpretar[2];
		pacote = mensagemAInterpretar[3];

		if (header != 0x7b) {
			certo = false;
		}

		if (sequencia < 0x01 && sequencia > 0xFF) {
			certo = false;
		}
	}


	public static void mensagemkeepAlive() {

		DadosGerais();

		check = mensagemAInterpretar[4];

		if (pacote != 0x40) {
			certo = false;
		}

		if (tamanho != 0x05) {
			certo = false;
		}

		if (check < 0x00 && check > 0xFF) {
			certo = false;
		}

	}


	public static void mensagemEvento() {

		DadosGerais();		

		for (int i = 0; i < arrayByteDados.length; i++) {
			auxiliarDados = arrayByteDados[i];
			arrayStringDadosEvento[i] = byteArrayToHexString(new byte[] {auxiliarDados});
		}

		check = mensagemAInterpretar[23];

		if (pacote != 0x24) {
			certo = false;
		}

		if (tamanho != 0x18) {
			certo = false;
		}

		if (check < 0x00 && check > 0xFF) {
			certo = false;
		}
	}

	public static void mensagemComandoConexao() {

		DadosGerais();

		for (int i = 0; i < arrayByteDados.length; i++) {
			auxiliarDados = arrayByteDados[i];
			//			arrayStringConexao[i] = Integer.toHexString(auxiliarDados);
			arrayStringConexao[i] = byteArrayToHexString(new byte[] {auxiliarDados});
		}

		check = mensagemAInterpretar[101];

		if (tamanho != 0x66) {
			certo = false;
		}

		if (pacote != 0x21) {
			certo = false;
		}

		if (check < 0x00 && check > 0xFF) {
			certo = false;
		}
	}
}