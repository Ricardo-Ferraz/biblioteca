package View;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

import Entidades.Aluno;
import Entidades.Titulo;
import Entidades.Pessoa;
import Entidades.Professor;
import Exception.SenhaIncorreta;
import Exception.UsuarioIncorreto;
import Repositorios.RepositorioLivro;
import Repositorios.RepositorioPessoa;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Admin extends JFrame implements ActionListener {
    private static final String USER= "admin";
    private static final String PASSWORD= "admin";

    private JTextField nome= new JTextField(20);
    private JTextField cpf= new JTextField(20);
    private JTextField idade= new JTextField(20);
    private JTextField matricula= new JTextField(20);
    private JTextField titulo= new JTextField(20);
    private JTextField descricao= new JTextField(20);
    private JTextField qtd= new JTextField(20);
    private JTextField tempoMax= new JTextField(20);
    private JTextField id= new JTextField(20);

    private Object[] entradaAluno= {"Nome: ", nome,"CPF: ",cpf, "Idade: ", idade, "Matricula: ", matricula};
    private Object[] entradaProfessor= {"Nome: ", nome,"CPF: ",cpf, "Idade: ", idade, "ID: ", matricula};
    private Object[] entradaPessoa= {"Nome: ", nome,"CPF: ",cpf, "Idade: ", idade};
    private Object[] entradaLivro= {"Titulo: ", titulo,"Descrição: ",descricao, "Quantidade em estoque: ",qtd, "Tempo máximo para devolução (Apenas o número): ", tempoMax, "ID: ", id};

    private JButton b1= new JButton("Cadastrar Aluno");
    private JButton b2= new JButton("Exibir Cadastrados");
    private JButton b3= new JButton("Cad. Professor");
    private JButton b4= new JButton("Cad. Pessoa");
    private JButton b5= new JButton("Cad. Livro");

    private Dimension dimension = new Dimension(300,300);


    private RepositorioPessoa repositorioPessoa;
    private RepositorioLivro repositorioLivro;

    public Admin(RepositorioPessoa repP, RepositorioLivro repL){
        this.repositorioPessoa= repP;
        this.repositorioLivro= repL;
    }

    public void checa(String usuario, String senha) throws SenhaIncorreta, UsuarioIncorreto{
        if(usuario.equals(this.USER)){
            if(senha.equals(this.PASSWORD)){
                criaJanela();
            }
            else{
                throw new SenhaIncorreta();
            }
        }
        else{
            throw new UsuarioIncorreto();
        }
    }

    public void criaJanela(){
        this.b1.setBounds(40, 30, 130,50);
        this.b1.addActionListener(this);
        this.b2.setBounds(40, 100, 130,50);
        this.b2.addActionListener(this);
        this.b3.setBounds(40, 170, 130,50);
        this.b3.addActionListener(this);
        this.b4.setBounds(40, 240, 130,50);
        this.b4.addActionListener(this);
        this.b5.setBounds(300, 30, 130,50);
        this.b5.addActionListener(this);


        this.getContentPane().add(b1);
        this.getContentPane().add(b2);
        this.getContentPane().add(b3);
        this.getContentPane().add(b4);
        this.getContentPane().add(b5);
        this.setLayout(null);
        this.setSize(500,500);
        this.setTitle("Menu Administrador");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        int i=0, j=0;
        if(e.getSource() == this.b1){
            int opcao = JOptionPane.showConfirmDialog(null, this.entradaAluno, "Informe os dados", JOptionPane.OK_CANCEL_OPTION);
            try {
                if (opcao == JOptionPane.OK_OPTION) {
                    if(!this.idade.getText().equals("")){
                        i= Integer.parseInt(this.idade.getText());
                    }
                    this.repositorioPessoa.adiciona(new Aluno(this.nome.getText(),this.cpf.getText(),i,this.matricula.getText()));
                    JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }
            this.nome.setText("");
            this.cpf.setText("");
            this.idade.setText("");
            this.matricula.setText("");
            i=0;
        }


        else if(e.getSource() == this.b2){
            JTextArea texto = new JTextArea(this.repositorioPessoa.toString());
            JScrollPane scroll = new JScrollPane(texto);
            texto.setEditable(false);
            scroll.setPreferredSize(this.dimension);
            JOptionPane.showMessageDialog(null, scroll, "Pessoas cadastrados",JOptionPane.UNDEFINED_CONDITION);
        }

        else if(e.getSource() == this.b3){
                int opcao = JOptionPane.showConfirmDialog(null, this.entradaProfessor, "Informe os dados", JOptionPane.OK_CANCEL_OPTION);
                try {
                    if (opcao == JOptionPane.OK_OPTION) {
                        if(!this.idade.getText().equals("")){
                            i= Integer.parseInt(this.idade.getText());
                        }
                        this.repositorioPessoa.adiciona(new Professor(this.nome.getText(),this.cpf.getText(),i,this.matricula.getText()));
                        JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                    }

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            this.nome.setText("");
            this.cpf.setText("");
            this.idade.setText("");
            this.matricula.setText("");
        }

        else if(e.getSource() == this.b4){
            int opcao = JOptionPane.showConfirmDialog(null, this.entradaPessoa, "Informe os dados", JOptionPane.OK_CANCEL_OPTION);
            try {
                if (opcao == JOptionPane.OK_OPTION) {
                    if(!this.idade.getText().equals("")){
                        i= Integer.parseInt(this.idade.getText());
                    }
                    this.repositorioPessoa.adiciona(new Pessoa(this.nome.getText(),this.cpf.getText(),i));
                    JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }
            this.nome.setText("");
            this.cpf.setText("");
            this.idade.setText("");
            this.matricula.setText("");
        }

        else if(e.getSource() == this.b5){
            int opcao = JOptionPane.showConfirmDialog(null, this.entradaLivro, "Informe os dados", JOptionPane.OK_CANCEL_OPTION);
            try{
                if(opcao == JOptionPane.OK_OPTION){
                    if(!this.qtd.getText().equals("")){
                        i= Integer.parseInt(this.qtd.getText());
                    }
                    if(!this.tempoMax.getText().equals("")){
                        j= Integer.parseInt(this.tempoMax.getText());
                    }
                    this.repositorioLivro.adiciona(new Titulo(this.titulo.getText(), this.descricao.getText(), i, j, this.id.getText()));
                    JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }

        }

    }

}
