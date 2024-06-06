
package modelo;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Aluno {

    private int codigo;
    private String nome;
    private String cpf;
    private Date dataNasc;
    private float peso;
    private float altura;

    public Aluno(int codigo, String nome, String cpf, Date dataNasc, float peso, float altura) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.peso = peso;
        this.altura = altura;
    }
    
    public Aluno() {
        
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public static List<Integer> extractId(List<String> lista){
        List<Integer> listaIds = new ArrayList<>();
        
        for(String elemento : lista){
            int tagStart = elemento.indexOf(":");
            int tagFinal = elemento.indexOf(",");
            // System.out.println(tagStart);
            // System.out.println(tagFinal);
            if(tagStart != -1 & tagFinal != -1){
                tagStart += 2;
                int valor = Integer.valueOf(elemento.substring(tagStart, tagFinal));
                listaIds.add(valor);
            }
        }
        return listaIds;
    } 

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public boolean verificaCpf(String cpf){
        int tamanhoTotal = cpf.length();
        if(tamanhoTotal != 14){
            return false;
        }
        if(cpf.indexOf("-") == -1){
            return false;
        }
        String partesA[] = cpf.split("-");
        if(partesA.length != 2){
            return false;
        }
        if(partesA[1].length() != 2){
            return false;
        }
        if(partesA[0].indexOf(".") == -1){
            return false;
        }
        String partesB[] = (partesA[0]).split("\\.");
        if(partesB.length != 3){
            return false;
        }
        if(partesB[0].length() == 3 & partesB[1].length() == 3 & partesB[2].length() == 3){
            return true;
        }
        else{
            return false;
        }
        
    }
    
    public Date getDataNasc() {
        return dataNasc;
    }
    
    public String getDataNascFormatada(){
        SimpleDateFormat fmtDesejado = new SimpleDateFormat("dd/MM/yyyy");
        String dataNascFormatada = fmtDesejado.format(dataNasc);
        return dataNascFormatada;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }
    
    public boolean verificaDataNasc(String dataNasc){
        int tamanho = dataNasc.length();
        if(tamanho != 10){
            return false;
        }
        if(dataNasc.indexOf("/") == -1){
            return false;
        }
        String partes[] = dataNasc.split("/");
        if(partes.length != 3){
            return false;
        }
        if(partes[0].length() == 2 & partes[1].length() == 2 & partes[2].length() == 4){
            return true;
        }
        else{
            return false;
        }
    }
    
    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }
    
    @Override
    public String toString() {
        
        //SimpleDateFormat fmtOriginal = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat fmtDesejado = new SimpleDateFormat("dd/MM/yyyy");
        String dataNascFormatada = fmtDesejado.format(dataNasc);
           
        return "ID: " + codigo +
               ", CPF: " + cpf +
               ", Nome: " + nome +
               ", Data de Nascimento: " + dataNascFormatada +
               ", Peso: " + peso +
               ", Altura: " + altura;
    
    }
        
}
