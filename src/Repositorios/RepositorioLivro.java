package Repositorios;

import Entidades.Titulo;

import Exception.JaCadastrado;
import Exception.CampoVazio;
import Exception.NegativoDetectado;
import Exception.LivroEsgotado;
import Exception.LivroNaoEncontrado;

import java.util.Collections;
import java.util.LinkedList;

public class RepositorioLivro {

    private LinkedList<Titulo> titulos;

    public RepositorioLivro(){
        this.titulos = new LinkedList<>();
    }

    public void adiciona(Titulo l) throws JaCadastrado, CampoVazio, NegativoDetectado{
        int i= buscaInt(l.getId());

        if(l.getTitulo().equals("") || l.getId().equals("") || l.getDescricao().equals("") || l.getTempoMax()==0 || l.getQtd() ==0){
            throw new CampoVazio();
        }

        if(l.getQtd() < 0 || l.getTempoMax() < 0){
            throw new NegativoDetectado();
        }

        if(i == -1){
            this.titulos.add(l);
            Collections.sort(titulos);
        }
        else{
            throw new JaCadastrado();
        }
    }

    public Titulo buscaId(String buscado) throws LivroNaoEncontrado {
        int i= buscaInt(buscado);
        if(i != -1){
            return titulos.get(i);
        }
        else{
            throw new LivroNaoEncontrado();
        }
    }

    public void aluga(String buscado) throws LivroNaoEncontrado, LivroEsgotado{
        int i= buscaInt(buscado);
        if(i != -1){
            if(titulos.get(i).getQtd() > 0){
                titulos.get(i).setQtd(titulos.get(i).getQtd() - 1);
            }
            else{
                throw new LivroEsgotado();
            }
        }
        else{
            throw new LivroNaoEncontrado();
        }
    }

    public void devolve(Titulo l) throws LivroNaoEncontrado{
        int i= buscaInt(l.getId());
        if(i != -1){
            titulos.get(i).setQtd(titulos.get(i).getQtd() + 1);
        }
        else{
            throw new LivroNaoEncontrado();
        }
    }

    private int buscaInt(String buscado){
        for(int i = 0; i < titulos.size(); i++){
            if(titulos.get(i).getId().equalsIgnoreCase(buscado)){
                return i;
            }
        }
        return -1;
    }


    @Override
    public String toString(){
        String aux="";
        for(int i = 0; i < titulos.size(); i++){
            aux= aux + titulos.get(i).toString()+"\n"+"\n";
        }
        return aux;
    }
}
