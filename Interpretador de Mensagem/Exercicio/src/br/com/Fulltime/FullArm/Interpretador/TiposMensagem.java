package br.com.Fulltime.FullArm.Interpretador;

public class TiposMensagem extends Central {

	public static void MensagemKeepAlive() {
		System.out.println("É um pacote de Keep-alive!");
		System.out.println("- " + byteArrayToHexString(new byte[] {header}) + " = " + "Header");
		System.out.println("- " + byteArrayToHexString(new byte[] {tamanho}) + " = " + "Tamanho do pacote");
		System.out.println("- " + byteArrayToHexString(new byte[] {sequencia}) + " = " + "Sequencia do pacote");
		System.out.println("- " + byteArrayToHexString(new byte[] {pacote}) + " = " + "Pacote de Keep-Alive");
		System.out.println("- " + byteArrayToHexString(new byte[] {check}) + " = " + "Checksum ou CRC");
	}

	public static void MensagemPacoteDeEvento () {

		System.out.println("É um pacote de Evento!");
		System.out.println("- " + byteArrayToHexString(new byte[] {header}) + " = " + "Header");
		System.out.println("- " + byteArrayToHexString(new byte[] {tamanho}) + " = " + "Tamanho do pacote");
		System.out.println("- " + byteArrayToHexString(new byte[] {sequencia}) + " = " + "Sequencia do pacote");
		System.out.println("- " + byteArrayToHexString(new byte[] {pacote}) + " = " + "Pacote de Evento");
		//Dados
		System.out.print("- ");	
		for (int i = 0; i < arrayStringDadosEvento.length; i++) {
			System.out.print(arrayStringDadosEvento[i] +  " ");
		}
		System.out.print("= Dados\n");
		System.out.println("- " + byteArrayToHexString(new byte[] {check}) + " = " + "Checksum ou CRC");



		//Inicio da interpretação de dados
		System.out.println("\nInterpretação dos DADOS\n");

		//Dados
		System.out.print("- ");	
		for (int i = 0; i < arrayStringDadosEvento.length; i++) {
			System.out.print(arrayStringDadosEvento[i] +  " ");
		}
		System.out.print("= Dados\n");


		//CONTA
		System.out.print("- ");	
		for (int i = 0; i < 4; i++) {
			System.out.print(hexToAscii(arrayStringDadosEvento[i]));
		}
		System.out.print(" = CONTA\n");

		//Qualificador
		System.out.println("- " + hexToAscii(arrayStringDadosEvento[4]) + " = Qualificador Evento");

		//Códgigo do evento
		System.out.print("- ");	
		for (int i = 5; i < 8; i++) {
			System.out.print(hexToAscii(arrayStringDadosEvento[i]));
		}
		System.out.print(" = Código do Evento\n");			

		//Partição
		System.out.print("- ");	
		for (int i = 8; i < 10; i++) {
			System.out.print(hexToAscii(arrayStringDadosEvento[i]));
		}
		System.out.print(" = Partição\n");

		//Argumento
		System.out.print("- ");	
		for (int i = 10; i < 13; i++) {
			System.out.print(hexToAscii(arrayStringDadosEvento[i]));
		}
		System.out.print(" = Argumento\n");

		//Contador de Evento
		System.out.print("- ");	
		for (int i = 13; i < 17; i++) {
			System.out.print(arrayStringDadosEvento[i] + " ");
		}
		System.out.print("= Contador do Evento\n");

		//Status da Partição
		System.out.println("- " + arrayStringDadosEvento[17] + " = Status da Partição");

		//Problemas
		System.out.print("- " + arrayStringDadosEvento[18] + " = ");

		if (Integer.parseInt(arrayStringDadosEvento[18]) == 1) {
			System.out.print("Há Problemas");
		} else {
			System.out.println("Não há Problemas");
		}
	}	

	public static void MensagemComandoDeConexao() {
		System.out.println("É um Comando de Conexão!");
		System.out.println("- " + byteArrayToHexString(new byte[] {header}) + " = " + "Header");
		System.out.println("- " + byteArrayToHexString(new byte[] {tamanho}) + " = " + "Tamanho do pacote");
		System.out.println("- " + byteArrayToHexString(new byte[] {sequencia}) + " = " + "Sequencia do pacote");
		System.out.println("- " + byteArrayToHexString(new byte[] {pacote}) + " = " + "Comando de Conexão");

		//NS
		System.out.print("- ");	
		for (int i = 0; i < 10; i++) {
			System.out.print(hexToAscii(arrayStringConexao[i]));
		}
		System.out.print(" = NS\n");	


		//IMEI
		int vazio = 0;
		System.out.print("- ");
		for (int i = 10; i < 25; i++) {
			if (arrayStringConexao[i].equalsIgnoreCase("ff")) {
				vazio++;
			}
			System.out.print(arrayStringConexao[i]);
		}
		if (vazio == 15) {
			System.out.println(" = IMEI Vazio");
		} else {
			System.out.print(" = IMEI\n");
		}
			


		//MAC
		System.out.print("- ");	
		for (int i = 25; i < 37; i++) {
			System.out.print(hexToAscii(arrayStringConexao[i]));
		}
		System.out.print(" = MAC\n");	

		//MOD
		String tipoModelo = arrayStringConexao[37];

		if (tipoModelo.equalsIgnoreCase("A0")) {
			System.out.println("- " + tipoModelo +  " = Active 32 DUO");
		} else if (tipoModelo.equalsIgnoreCase("A1")) {
			System.out.println("- " + tipoModelo +  " = Active 20 Ultra");
		} else if (tipoModelo.equalsIgnoreCase("A2")) {
			System.out.println("- " + tipoModelo +  " = Active 8 Ultra");
		} else if (tipoModelo.equalsIgnoreCase("A3")) {
			System.out.println("- " + tipoModelo +  " = Active 20 Ethernet");
		} else if (tipoModelo.equalsIgnoreCase("A4")) {
			System.out.println("- " + tipoModelo +  " = Active 100 Bus");
		} else if (tipoModelo.equalsIgnoreCase("A5")){
			System.out.println("- " + tipoModelo +  " = Active 20 Bus");
		} else {
			System.out.println("- " + tipoModelo + " = Modelo Inválido");
		}

		//VER
		System.out.print("- ");	
		String versao = hexToAscii(arrayStringConexao[38]) + "." + hexToAscii(arrayStringConexao[39]);
		int beta = Integer.parseInt(hexToAscii(arrayStringConexao[40]));
		if (beta == 0) {
			System.out.println(versao + " = Versão");
		} else {
			System.out.println(versao + "b" + beta + " = Versão");
		}

		//IP
		int ip = Integer.parseInt(arrayStringConexao[41]);
		System.out.print("- " + arrayStringConexao[41]);
		if (ip == 1) {
			System.out.println(" = IP 1");
		} else if (ip == 2){
			System.out.println(" = IP 2");
		} else {
			System.out.println(" = IP Inválido");
		}

		//SIMCARD
		int simcard = Integer.parseInt(arrayStringConexao[42]);
		System.out.print("- " + arrayStringConexao[42]);
		if (simcard == 1) {
			System.out.println(" = SIM CARD 1");
		} else if (simcard == 2){
			System.out.println(" = SIM CARD 2");
		} else if (simcard == 3){
			System.out.println(" = SIM CARD não existe");
		} else {
			System.out.println(" = Dado Inválido");
		}

		//VIA
		int via = Integer.parseInt(arrayStringConexao[43]);
		System.out.print("- " + arrayStringConexao[43]);
		if (via == 0) {
			System.out.println(" = GPRS");
		}
		else {
			System.out.println(" = Ethernet");
		}

		//OPERADORA
		int operadora = Integer.parseInt(arrayStringConexao[44]);
		System.out.print("- " + arrayStringConexao[44]);
		if (operadora == 0) {
			System.out.println(" = Vivo");
		} else if (operadora == 2) {
			System.out.println(" = Claro");
		} else if (operadora == 3) {
			System.out.println(" = Oi");
		} else if (operadora == 4) {
			System.out.println(" = Tim");
		} else if (operadora == 6) {
			System.out.println(" = Não Existe");
		} else if (operadora == 7){
			System.out.println(" = Personalizado");
		} else {
			System.out.println(" = Dado Inválido");
		}

		//STATUS
		System.out.print("- ");	
		for (int i = 45; i < 97; i++) {
			System.out.print(arrayStringConexao[i]);
		}
		System.out.print(" = Status\n");

		System.out.println("- " + byteArrayToHexString(new byte[] {check}) + " = " + "Checksum ou CRC");
	}
}
