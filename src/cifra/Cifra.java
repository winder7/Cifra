package cifra;

import java.text.Normalizer;
import java.util.Arrays;

/**
 * @Autor Winder Rezende
 * @Data 22/03/2019
 */
public class Cifra {

    public static void main(String[] args) {
        String mensagem = "teste";
        
        mensagem = cifraCesar(mensagem, "cifrar", "letras", "numeros", 3);
        
        System.out.println(mensagem);
        
        System.out.println(cifraCesar(mensagem, "decifrar", "numeros", "letras"));

        String chave = "CONA";
        mensagem = "SATURNOS";
        System.out.println(cifraVigenere(mensagem, "cifrar", chave, 0));
    }

    public static String cifraCesar(String mensagem, String modo, String entrada, String saida, int... args) {
        String[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        String[] numeros = "0001020304050607080910111213141516171819202122232425".split("(?<=\\G.{2})");
        String[] texto = entrada.equalsIgnoreCase("numeros") ? mensagem.replaceAll("\\D", "").split("(?<=\\G.{2})") : removeCaracteres(mensagem).split("");
        int k = args.length > 0 ? args[0] : 3;

        System.out.println(Arrays.toString(texto));

        String result = "";
        for (int i = 0; i < texto.length; i++) {
            for (int j = 0; j < letras.length; j++) {
                if (texto[i].equalsIgnoreCase(entrada.equalsIgnoreCase("numeros") ? numeros[j] : letras[j])) {
                    int m = modo.equalsIgnoreCase("cifrar") ? (j + k) % 26 : (j - k) < 0 ? (j - k) + 26 : (j - k);
                    result += saida.equalsIgnoreCase("numeros") ? numeros[m] : letras[m];
                }
            }
            if (addCaractere(texto[i])) {
                result += texto[i];
            }
        }

        return result;
    }

    public static String cifraVigenere(String mensagem, String modo, String chave, int... args) {
        String[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        String[] numeros = "0001020304050607080910111213141516171819202122232425".split("(?<=\\G.{2})");
        String[] msg = removeCaracteres(mensagem).split("");
        String[] ch = removeCaracteres(chave).split("");
        int k = args.length > 0 ? args[0] : 3;

        System.out.println(Arrays.toString(ch));

        int[] chaveNum = new int[ch.length];
        for (int i = 0; i < ch.length; i++) {
            for (int j = 0; j < letras.length; j++) {
                if (ch[i].equalsIgnoreCase(letras[j])) {
                    int m = (j + k) % 26;
                    chaveNum[i] = Integer.parseInt(numeros[m]);
                }
            }
        }

        System.out.println(Arrays.toString(chaveNum));

        System.out.println(Arrays.toString(msg));

        int[] msgNum = new int[msg.length];
        String result = "";

        for (int i = 0; i < msg.length; i++) {
            for (int j = 0; j < letras.length; j++) {
                if (msg[i].equalsIgnoreCase(letras[j])) {
                    int m = (j + k) % 26;
                    if (modo.equalsIgnoreCase("cifrar")) {
                        msgNum[i] = Integer.parseInt(numeros[m]) + chaveNum[i % ch.length];
                    } else {
                        if (Integer.parseInt(numeros[m]) - chaveNum[i % ch.length] > 0) {
                            msgNum[i] = Integer.parseInt(numeros[m]) - chaveNum[i % ch.length];
                        } else {
                            msgNum[i] = Integer.parseInt(numeros[m]) - chaveNum[i % ch.length] + 26;
                        }
                    }
                    result += letras[(msgNum[i] - k) % 26];
                }
            }
            if (addCaractere(msg[i])) {
                result += msg[i];
            }
        }

        return result;
    }

    public static String removeCaracteres(String texto) {
        CharSequence cs = new StringBuilder(texto);
        texto = Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        //texto = texto.replaceAll("\n", "").replaceAll("[^\\p{Alpha}\\p{Digit}]+", "");
        return texto;
    }

    public static boolean addCaractere(String caractere) {
        if (caractere.equals(" ") || caractere.equals(".") || caractere.equals(",") || caractere.equals("?") || caractere.equals("!") || caractere.equals(":") || caractere.equals(";")) {
            return true;
        }
        return false;
    }
}
