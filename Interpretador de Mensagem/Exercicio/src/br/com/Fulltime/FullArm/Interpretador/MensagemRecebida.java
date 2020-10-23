package br.com.Fulltime.FullArm.Interpretador;

import java.util.Scanner;

public class MensagemRecebida {

	public static void main(String args[]) {

		System.out.println("Mensagem:");
		Scanner scanner = new Scanner(System.in);

		//Lendo a mensagem
		String msg = scanner.nextLine();	
		Central.setMensagem(msg);		
		
		scanner.close();

		//Validando se a mensagemé par pra poder ser transformada em byte
		if (Central.getMensagem().length() % 2 != 0) {
			Central.certo = false;
		}

		else {

			if (InterpretarTiposMensagens.mensagemAInterpretar.length == 5) {
				InterpretarTiposMensagens.mensagemkeepAlive();
			}
			else if (InterpretarTiposMensagens.mensagemAInterpretar.length == 24) {
				InterpretarTiposMensagens.mensagemEvento();
			}
			else if (InterpretarTiposMensagens.mensagemAInterpretar.length == 102) {
				InterpretarTiposMensagens.mensagemComandoConexao();
			}
		}


		if (Central.certo && Central.pacote == 0x40) {
			TiposMensagem.MensagemKeepAlive();
		} else if (Central.certo && Central.pacote == 0x24) {
			TiposMensagem.MensagemPacoteDeEvento();
		} else if (Central.certo && Central.pacote == 0x21) {
			TiposMensagem.MensagemComandoDeConexao();
		} else {
			System.out.println("Mensagem Inválida");
		}	
	}
}