import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class RunShell {

    public static void addRodapeFolha(final String job, final String nomeArq, final String codigoLocalizacao, final String urlValidacao, final String folhaArq) {
        runShell(job, nomeArq, codigoLocalizacao, urlValidacao, folhaArq);
    }

    public static void runShell(final String ... args) {
        try {
            final Process process = new ProcessBuilder(args).start();

            final StringBuilder output = new StringBuilder();
            try (BufferedReader reader = new BufferedReader( new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }

            int exitVal = process.waitFor();
            if (exitVal != 0) {
                throw new RuntimeException(output.toString());
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[]args) {
        addRodapeFolha("/opt/eassina/docker/rede/scripts/add-rodape-folha.sh", "/home/marcelo/Documentos/ipva.pdf", "AA16.0421.16336.2002", "http://www.peixebinario.com.br/validacao", "/home/marcelo/projetos/eassina/rodape-script/folha-localizador.pdf");
    }
}
