package com.mycompany.arquivosemjava;

/**
 *
 * @author REUTER
 */

//Importa todas as bibliotecas
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;

//Importa bibliotecas nio
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ArquivosEmJava {
    public static void main(String[] args) {
            Scanner sc = new Scanner (System.in);
        
            System.out.println("Qual o nome do arquivo?");
            String nome = sc.nextLine();
            
            System.out.println("O que quer escrever no arquivo?");
            String conteudo = sc.nextLine();
            
            criarEscrever(conteudo, nome);
            
            ler (nome);
            
            
            
            System.out.println("Agora utilizando Nio, qual nome do arquivo?");
            String nomeNio = sc.nextLine();
            
            System.out.println("O que quer escrever no arquivo?");
            String conteudoNio = sc.nextLine();
            
            escreveNio(conteudoNio, nomeNio);
            lerNio(nomeNio);
    }
    
    //Utiliza bibliotecas io
    public static void criarEscrever (String content, String nome){
        //Pode-se usar tanto o file, que cria o arquivo na pasta inicial do projeto, quando o caminho
        File file = new File(nome);

        // Cria o arquivo se ele não existir
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
            System.out.println("Arquivo escrito com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void ler (String nome){
        // Especifica o caminho do arquivo,vai primeiro na pasta geral e especifica o nome do arquivo
        String caminhoPasta = "C:\\Users\\leore\\OneDrive\\Documents\\NetBeansProjects\\ArquivosEmJava\\";
        String caminhoArquivo = caminhoPasta.concat(nome);
        
        // Tenta ler o arquivo
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            // Lê cada linha do arquivo até o final
            while ((linha = br.readLine()) != null) {
                // Imprime a linha no console
                System.out.println(linha);
            }
        } catch (IOException e) {
            // Trata a exceção caso ocorra um erro de I/O
            e.printStackTrace();
        }
    }
    
    
    //Utiliza as bibliotecas nio
    public static void escreveNio (String content, String nome){
        //Caso não exista o arquivo, cria
        Path path = Paths.get(nome);

        try {
            Files.write(path, content.getBytes());
            System.out.println("Arquivo escrito com sucesso usando NIO.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void lerNio(String nome){
        //Identifica o arquivo desejado
        Path path = Paths.get(nome);

        try {
            //Cria uma lista das linhas do arquivo
            List<String> lines = Files.readAllLines(path);
            
            //Passa por caso linha e printa
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
