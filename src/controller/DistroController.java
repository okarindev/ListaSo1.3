package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DistroController {
	public DistroController() {
		super();
	}

	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}

	public void exibeDistro() {
		String os = os();
		if (os.contains("Linux")) {
			String linux = "cat /etc/os-release";
			try {
				Process pLinux = Runtime.getRuntime().exec(linux);
				InputStream fluxoLinux = pLinux.getInputStream();
				InputStreamReader leitorLinux = new InputStreamReader(fluxoLinux);
				BufferedReader bufferLinux = new BufferedReader(leitorLinux);
				String linhaLinux = bufferLinux.readLine();
				while (linhaLinux != null) {
					if(linhaLinux.contains("PRETTY")) {
						String versaoNome[] = linhaLinux.split("=");
						System.out.println("O nome e a versao de distribuicao sao: " + versaoNome[1]);
					}
					linhaLinux = bufferLinux.readLine();
				}
				bufferLinux.close();
				leitorLinux.close();
				fluxoLinux.close();
			} catch (Exception e) {

			}
		} else {
			System.err.println("O SISTEMA OPERACIONAL NAO E LINUX!");
		}
	}
}