package estudos.gerador_cpf_cnpj;

import java.security.SecureRandom;
import java.util.ArrayList;

public class CpfCnpj
{
	public static void main( final String[] args )
	{
		CpfCnpj cliente = new CpfCnpj();
		cliente.geraCnpj();
	}

	private static final int[] PRIMEIRO_DIGITO_CNPJ = {5,4,3,2,9,8,7,6,5,4,3,2};

	private static final int[] SEGUNDO_DIGITO_CNPJ = {6,5,4,3,2,9,8,7,6,5,4,3,2};

	private static final int[] PRIMEIRO_DIGITO_CPF = {10,9,8,7,6,5,4,3,2};

	private static final int[] SEGUNDO_DIGITO_CPF = {11,10,9,8,7,6,5,4,3,2};

	SecureRandom geradorDeNumeros = new SecureRandom();

	private String cnpjCpf;

	private ArrayList<Integer> listaAleatoria = new ArrayList<>();
	private ArrayList<Integer> listaMultiplicada;

	public void geraCpf() {
		this.geraCpfParcial();
		this.geraDigitosCpf();
		this.geraCnpjCpfCli();
		System.out.println(this.cnpjCpf);
	}

	private void geraCpfParcial() {
		int[] arrayAleatorio = new int[9];
		for (int i = 0; i < arrayAleatorio.length; i++) {
			arrayAleatorio[i] = this.geradorDeNumeros.nextInt(10);
			this.listaAleatoria.add(arrayAleatorio[i]);
		}
	}

	private void geraDigitosCpf(){
		this.geraDigito(CpfCnpj.PRIMEIRO_DIGITO_CPF);
		this.geraDigito(CpfCnpj.SEGUNDO_DIGITO_CPF);
	}

	public void geraCnpj() {
		this.geraCnpjParcial();
		this.geraDigitosCnpj();
		this.geraCnpjCpfCli();
		System.out.println(this.cnpjCpf);
	}

	private void geraCnpjParcial() {
		int[] arrayAleatorio = new int[12];
		for (int i = 0; i < arrayAleatorio.length; i++) {
			arrayAleatorio[i] = this.geradorDeNumeros.nextInt(10);
			this.listaAleatoria.add(arrayAleatorio[i]);
		}
	}

	private void geraDigitosCnpj() {
		this.geraDigito(CpfCnpj.PRIMEIRO_DIGITO_CNPJ);
		this.geraDigito(CpfCnpj.SEGUNDO_DIGITO_CNPJ);
	}

	private void geraDigito(final int[] peso) {
		this.listaMultiplicada = new ArrayList<>();
		int primeiroDigito = 0;
		int somaTotal = 0;
		int resto;
		int cont = 0;
		for (int item : this.listaAleatoria) {
			this.listaMultiplicada.add(item * peso[cont]);
			cont++;
		}
		for (Integer item : this.listaMultiplicada) {
			somaTotal += item;
		}
		resto = somaTotal % 11;
		if (resto >= 2) {
			primeiroDigito = 11 - resto;
		}
		this.listaAleatoria.add(primeiroDigito);
	}

	private void geraCnpjCpfCli() {
		StringBuilder builder = new StringBuilder();
		for (int item : this.listaAleatoria) {
			builder.append(item);
		}
		this.cnpjCpf = builder.toString();
	}
}
