package br.com.fuulltime.fullarm.painel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsercaoDadosPainel {

	public static void main(String[] args) throws SQLException {

		Conexao c = new Conexao();
		Connection connection = c.gerarConexao();
		Scanner s = new Scanner(System.in);

		try {
			PreparedStatement stm = connection
					.prepareStatement("INSERT INTO `fullarm`.painel (id_monitoramento, id_fabricante, id_modelo, id_acesso, descricao, "
							+ "imei, mac, ativo, id_servidor, conectado, gerou_falha_keepalive, utilizar_padrao, habilitar_entrega_evento, "
							+ "em_buffer, considerar_buffer_apos, tempo_de_keepalive, ultimo_keepalive, conta, conexao, prog_remota_permitida,"
							+ " tempo_acionamento_sirene) VALUES"
							+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); ");
			

			for (int i = 1; i <= 10; i++) {

				System.out.println("Monitoramento: "); int monitoramento = s.nextInt();
				System.out.println("Fabricante: ");	int fabricante = s.nextInt();
				System.out.println("Modelo: ");	int modelo = s.nextInt();
				System.out.println("Acesso: ");	int acesso = s.nextInt();
				s.nextLine();
				System.out.println("Descrição: "); String descricao = s.nextLine();
				System.out.println("Imei: "); String imei = s.nextLine();
				System.out.println("Mac: "); String mac = s.nextLine();
				System.out.println("Ativo: "); int ativo = s.nextInt();
				System.out.println("Servidor: "); int servidor = s.nextInt();
				System.out.println("Conectado: ");	int conectado = s.nextInt();
				System.out.println("Falha KeepAlive: "); int falha = s.nextInt();
				System.out.println("Padrão: ");	int padrao = s.nextInt();
				System.out.println("Entrega Evento: ");	int evento = s.nextInt();
				System.out.println("Buffer: ");	int buffer = s.nextInt();
				System.out.println("Buffer Apos: "); int bufferA = s.nextInt();
				System.out.println("Tempo KeepAlive: "); int tempo = s.nextInt();
				s.nextLine();
				System.out.println("Último KeepALive: "); String ultimo = s.nextLine();
				System.out.println("Conta: "); String conta = s.nextLine();
				System.out.println("Conexão: "); String con = s.nextLine();
				System.out.println("Programação Remota: ");	int prog = s.nextInt();
				System.out.println("Tempo Acionamento: "); int acionamento = s.nextInt();

				adicionarDados(monitoramento, fabricante, modelo, acesso, descricao, imei, mac, ativo, servidor,
						conectado, falha, padrao, evento, buffer, bufferA, tempo, ultimo, conta, con, prog, acionamento,
						stm);
			}

			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao adicionar");
		} finally {
			s.close();
			connection.close();
		}

	}

	private static void adicionarDados(int monitoramento, int fabricante, int modelo, int acesso, String descricao,
			String imei, String mac, int ativo, int servidor, int conectado, int falha, int padrao, int evento,
			int buffer, int bufferA, int tempo, String ultimo, String conta, String con, int prog, int acionamento,
			PreparedStatement stm) throws SQLException {

		stm.setInt(1, monitoramento);
		stm.setInt(2, fabricante);
		stm.setInt(3, modelo);
		stm.setInt(4, acesso);
		stm.setString(5, descricao);
		stm.setString(6, imei);
		stm.setString(7, mac);
		stm.setInt(8, ativo);
		stm.setInt(9, servidor);
		stm.setInt(10, conectado);
		stm.setInt(11, falha);
		stm.setInt(12, padrao);
		stm.setInt(13, evento);
		stm.setInt(14, buffer);
		stm.setInt(15, bufferA);
		stm.setInt(16, tempo);
		stm.setString(17, ultimo);
		stm.setString(18, conta);
		stm.setString(19, con);
		stm.setInt(20, prog);
		stm.setInt(21, acionamento);

		stm.execute();		
		
		System.out.println("\nCadastro feito com sucesso!\n");
		
	}
}
