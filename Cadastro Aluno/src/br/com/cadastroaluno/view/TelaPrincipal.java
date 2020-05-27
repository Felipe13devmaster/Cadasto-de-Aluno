package br.com.cadastroaluno.view;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.cadastroaluno.dao.AlunoDAO;
import br.com.cadastroaluno.dao.DisciplinaDAO;
import br.com.cadastroaluno.model.Aluno;
import br.com.cadastroaluno.model.Disciplina;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField textRGM;
	private JTextField textNome;
	private JTextField textEmail;
	private JTextField textEndereco;
	private JTextField textMunicipio;
	private JTextField textRGMSearch;
	private JTextField textFaltas;
	private JTextField textBoletimRGMInputSearch;
	private JTextField textBoletimNomeAluno;
	private ButtonGroup buttongroup;
	private Aluno aluno;
	private AlunoDAO alunoDao;
	private Disciplina disciplina;
	private DisciplinaDAO disciplinaDao;
	private JTextField textNeFNomeAluno;
	private JTextField textNeFNomeCurso;
	private JTextField textBoletimCurso;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 401);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuAluno = new JMenu("Aluno");
		menuBar.add(menuAluno);
		
		JMenuItem menuItemAlunoSalvar = new JMenuItem("Salvar");
		
		menuItemAlunoSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuAluno.add(menuItemAlunoSalvar);
		
		JMenuItem menuItemAlunoAlterar = new JMenuItem("Alterar");
		menuAluno.add(menuItemAlunoAlterar);
		
		JMenuItem menuItemAlunoConsultar = new JMenuItem("Consultar");
		menuAluno.add(menuItemAlunoConsultar);
		
		JMenuItem menuItemAlunoExcluir = new JMenuItem("Excluir");
		menuAluno.add(menuItemAlunoExcluir);
		
		JMenuItem menuItemAlunoSair = new JMenuItem("Sair");
		menuItemAlunoSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_MASK));
		menuAluno.add(menuItemAlunoSair);
		
		JMenu menuNotaseFaltas = new JMenu("Notas e Faltas");
		menuBar.add(menuNotaseFaltas);
		
		JMenuItem menuItemNeFSalvar = new JMenuItem("Salvar");
		menuNotaseFaltas.add(menuItemNeFSalvar);
		
		JMenuItem menuItemNeFAlterar = new JMenuItem("Alterar");
		menuItemNeFAlterar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		menuNotaseFaltas.add(menuItemNeFAlterar);
		
		JMenuItem menuItemNeFExcluir = new JMenuItem("Excluir");
		menuNotaseFaltas.add(menuItemNeFExcluir);
		
		JMenuItem menuItemNeFConsultar = new JMenuItem("Consultar");
		menuNotaseFaltas.add(menuItemNeFConsultar);
		
		JMenu menuAjuda = new JMenu("Ajuda");
		menuBar.add(menuAjuda);
		
		JMenuItem menuItemAjudaSobre = new JMenuItem("Sobre");
		menuItemAjudaSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Desenvolvido por 쯊elipeRodrigues");
			}
		});
		menuAjuda.add(menuItemAjudaSobre);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.control);
		tabbedPane.setBounds(0, 11, 644, 327);
		contentPane.add(tabbedPane);
		
		JPanel panelDadosPessoais = new JPanel();
		tabbedPane.addTab("Dados Pessoais", null, panelDadosPessoais, null);
		panelDadosPessoais.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("RGM");
		lblNewLabel.setBounds(26, 54, 46, 14);
		panelDadosPessoais.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(295, 54, 46, 14);
		panelDadosPessoais.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nascimento");
		lblNewLabel_2.setBounds(26, 107, 116, 14);
		panelDadosPessoais.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("UF");
		lblNewLabel_3.setBounds(295, 242, 46, 14);
		panelDadosPessoais.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Celular");
		lblNewLabel_4.setBounds(417, 242, 46, 14);
		panelDadosPessoais.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Munic\u00EDpio");
		lblNewLabel_5.setBounds(26, 242, 62, 14);
		panelDadosPessoais.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("CPF");
		lblNewLabel_6.setBounds(295, 107, 46, 14);
		panelDadosPessoais.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Email");
		lblNewLabel_7.setBounds(26, 153, 46, 14);
		panelDadosPessoais.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("End.");
		lblNewLabel_8.setBounds(26, 199, 46, 14);
		panelDadosPessoais.add(lblNewLabel_8);
		
		textRGM = new JTextField();
		textRGM.setBounds(88, 51, 184, 20);
		panelDadosPessoais.add(textRGM);
		textRGM.setColumns(10);
		
		textNome = new JTextField();
		textNome.setBounds(351, 51, 257, 20);
		panelDadosPessoais.add(textNome);
		textNome.setColumns(10);
		
		JFormattedTextField formattedTextNascimento = new JFormattedTextField();
		formattedTextNascimento.setBounds(140, 104, 132, 20);
		panelDadosPessoais.add(formattedTextNascimento);
		try {
			MaskFormatter nascimentoFormat = new MaskFormatter("##/##/####");
			nascimentoFormat.install(formattedTextNascimento);
		} catch (ParseException e3) {
			e3.printStackTrace();
		} 
		
		JFormattedTextField formattedTextCPF = new JFormattedTextField();
		formattedTextCPF.setBounds(351, 101, 257, 20);
		panelDadosPessoais.add(formattedTextCPF);
		try {
			MaskFormatter cpfFormat = new MaskFormatter("###.###.###-##");
			cpfFormat.install(formattedTextCPF);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		
		JFormattedTextField formattedTextCelular = new JFormattedTextField();
		formattedTextCelular.setBounds(461, 239, 147, 20);
		panelDadosPessoais.add(formattedTextCelular);
		try {
			MaskFormatter celularFormat = new MaskFormatter("(##)####-####");
			celularFormat.install(formattedTextCelular);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		textEmail = new JTextField();
		textEmail.setBounds(88, 150, 520, 20);
		panelDadosPessoais.add(textEmail);
		textEmail.setColumns(10);
		
		textEndereco = new JTextField();
		textEndereco.setBounds(88, 196, 520, 20);
		panelDadosPessoais.add(textEndereco);
		textEndereco.setColumns(10);
		
		textMunicipio = new JTextField();
		textMunicipio.setBounds(88, 239, 197, 20);
		panelDadosPessoais.add(textMunicipio);
		textMunicipio.setColumns(10);
		
		JComboBox comboBoxUF = new JComboBox();
		comboBoxUF.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBoxUF.setModel(new DefaultComboBoxModel(new String[] {"Selecione", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		comboBoxUF.setBounds(323, 239, 77, 20);
		panelDadosPessoais.add(comboBoxUF);
		
		JLabel lblNewLabel_32 = new JLabel("Cadastro de Alunos");
		lblNewLabel_32.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_32.setBounds(26, 11, 582, 14);
		panelDadosPessoais.add(lblNewLabel_32);
		
		JPanel panelCurso = new JPanel();
		tabbedPane.addTab("Curso", null, panelCurso, null);
		panelCurso.setLayout(null);
		
		JLabel lblNewLabel_9 = new JLabel("Curso");
		lblNewLabel_9.setBounds(10, 42, 46, 14);
		panelCurso.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Campus");
		lblNewLabel_10.setBounds(10, 105, 46, 14);
		panelCurso.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Periodo");
		lblNewLabel_11.setBounds(10, 160, 46, 14);
		panelCurso.add(lblNewLabel_11);
		
		JRadioButton rdbtnMatutino = new JRadioButton("Matutino");
		rdbtnMatutino.setName("Periodo");
		rdbtnMatutino.setBounds(92, 156, 109, 23);
		panelCurso.add(rdbtnMatutino);
		
		JRadioButton rdbtnVespertino = new JRadioButton("Vespertino");
		rdbtnVespertino.setName("Periodo");
		rdbtnVespertino.setBounds(273, 156, 109, 23);
		panelCurso.add(rdbtnVespertino);
		
		JRadioButton rdbtnNoturno = new JRadioButton("Noturno");
		rdbtnNoturno.setName("Periodo");
		rdbtnNoturno.setBounds(474, 156, 109, 23);
		panelCurso.add(rdbtnNoturno);
		
		buttongroup = new ButtonGroup();
		buttongroup.add(rdbtnMatutino);
		buttongroup.add(rdbtnVespertino);
		buttongroup.add(rdbtnNoturno);
		
		JComboBox comboBoxCurso = new JComboBox();
		comboBoxCurso.setModel(new DefaultComboBoxModel(new String[] {"Selecione", "Administracao de Empresas", "Analise e Desenvolvimento de Sistemas"}));
		comboBoxCurso.setBounds(92, 39, 491, 20);
		panelCurso.add(comboBoxCurso);
		
		JComboBox comboBoxCampus = new JComboBox();
		comboBoxCampus.setModel(new DefaultComboBoxModel(new String[] {"Selecione", "Sao Paulo", "Minas Gerais"}));
		comboBoxCampus.setBounds(92, 102, 491, 20);
		panelCurso.add(comboBoxCampus);
		//Acoes do menu aluno
		menuItemAlunoSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aluno = new Aluno();
				aluno.setRgm(Integer.parseInt(textRGM.getText()));
				aluno.setNome(textNome.getText());
				//FORMAT플O DA DATA DE DD/MM/AAAA PARA O FORMATO SQL AAAA/MM/AA
				String dia = formattedTextNascimento.getText().substring(0, 2);
				String mes = formattedTextNascimento.getText().substring(3, 5);
				String ano = formattedTextNascimento.getText().substring(6);
				String dataSqlFormat = ano+mes+dia;
				aluno.setNascimento((String)(dataSqlFormat));
				aluno.setCpf(formattedTextCPF.getText());
				aluno.setEmail(textEmail.getText());
				aluno.setEndereco(textEndereco.getText());
				aluno.setMunicipio(textMunicipio.getText());
				aluno.setUf((String) comboBoxUF.getSelectedItem());
				aluno.setCelular(formattedTextCelular.getText());
				//=============Tela Curso============
				aluno.setCurso((String)comboBoxCurso.getSelectedItem());
				aluno.setCampus((String)comboBoxCampus.getSelectedItem());
				
				String opcao;
				if(rdbtnMatutino.isSelected()) 
					opcao = "Matutino";
				else if(rdbtnVespertino.isSelected()) 
					opcao = "Vespertino";
				else 
					opcao = "Noturno";
				
				aluno.setPeriodo(opcao);
				
				try {
					alunoDao = new AlunoDAO();//FAZ A CONEXAO COM O BANCO
					alunoDao.salvarAluno(aluno);//CHAMADA DA FUNCAO SALVAR
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Falha ao salvar aluno.");
					e.printStackTrace();
				}
				//limpa apos salvar
				textRGM.setText("");
				textNome.setText("");
				formattedTextNascimento.setText("");
				formattedTextCPF.setText("");
				textEmail.setText("");
				textEndereco.setText("");
				textMunicipio.setText("");
				comboBoxUF.setSelectedIndex(0);
				formattedTextCelular.setText("");
				comboBoxCurso.setSelectedIndex(0);
				comboBoxCampus.setSelectedIndex(0);
				buttongroup.clearSelection();
			}
			
		});
		
		menuItemAlunoAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aluno = new Aluno();
				aluno.setRgm(Integer.parseInt(textRGM.getText()));
				aluno.setNome(textNome.getText());
				//FORMAT플O DA DATA DE DD/MM/AAAA PARA O FORMATO SQL AAAA/MM/AA
				String dia = formattedTextNascimento.getText().substring(0, 2);
				String mes = formattedTextNascimento.getText().substring(3, 5);
				String ano = formattedTextNascimento.getText().substring(6);
				String dataSqlFormat = ano+mes+dia;
				aluno.setNascimento((String)(dataSqlFormat));
				aluno.setCpf(formattedTextCPF.getText());
				aluno.setEmail(textEmail.getText());
				aluno.setEndereco(textEndereco.getText());
				aluno.setMunicipio(textMunicipio.getText());
				aluno.setUf((String)comboBoxUF.getSelectedItem());
				aluno.setCelular(formattedTextCelular.getText());
				//=============Tela Curso============
				aluno.setCurso((String)comboBoxCurso.getSelectedItem());
				aluno.setCampus((String)comboBoxCampus.getSelectedItem());
				
				String opcao;
				if(rdbtnMatutino.isSelected()) 
					opcao = "Matutino";
				else if(rdbtnVespertino.isSelected()) 
					opcao = "Vespertino";
				else 
					opcao = "Noturno";
				
				aluno.setPeriodo(opcao);
				
				try {
					alunoDao = new AlunoDAO();//FAZ A CONEXAO COM O BANCO
					alunoDao.alterarAluno(aluno);//CHAMADA DA FUNCAO ALTERAR
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Falha ao alterar aluno.");
					e1.printStackTrace();
				}
			}
		});
		
		menuItemAlunoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					alunoDao = new AlunoDAO();
					disciplinaDao = new DisciplinaDAO();
					int rgm = Integer.parseInt(textRGM.getText());
					aluno = alunoDao.consultarAluno(rgm);
					disciplina = disciplinaDao.consultarDisciplina(rgm);
					textNome.setText(aluno.getNome());
		
					String ano = aluno.getNascimento().substring(0,4);
					String mes = aluno.getNascimento().substring(5,7);
					String dia = aluno.getNascimento().substring(8);
					String dataSystemFormat = dia+mes+ano;
					
					formattedTextNascimento.setText(dataSystemFormat);
					formattedTextCPF.setText(aluno.getCpf());
					textEmail.setText(aluno.getEmail());
					textEndereco.setText(aluno.getEndereco());
					textMunicipio.setText(aluno.getMunicipio());
					comboBoxUF.setSelectedItem(aluno.getUf());
					formattedTextCelular.setText(aluno.getCelular());
					comboBoxCurso.setSelectedItem(aluno.getCurso());
					comboBoxCampus.setSelectedItem(aluno.getCampus());
					
					String opcao = aluno.getPeriodo();
					if(opcao.equals("Matutino") ) {
						rdbtnMatutino.setSelected(true);
						rdbtnVespertino.setSelected(false);
						rdbtnNoturno.setSelected(false);
					}	
					else if(opcao.equals("Vespertino")) {
						rdbtnMatutino.setSelected(false);
						rdbtnVespertino.setSelected(true);
						rdbtnNoturno.setSelected(false);
					}	
					else if(opcao.equals("Noturno")) {
						rdbtnMatutino.setSelected(false);
						rdbtnVespertino.setSelected(false);
						rdbtnNoturno.setSelected(true);
					}
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Falha ao listar aluno no boletim.");
					e1.printStackTrace();
				}
			}
		});
		
		menuItemAlunoExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					alunoDao = new AlunoDAO();//FAZ A CONEXAO COM O BANCO
					int rgm = Integer.parseInt(textRGM.getText());
					System.out.println("RGM passado na funcao excluir"+rgm);
					alunoDao.excluirAluno(rgm);//CHAMADA DA FUNCAO EXCLUIR
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Falha ao excluir aluno.");
					e1.printStackTrace();
				}
			}
		});
		
		menuItemAlunoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Saindo do Sistema.");
				System.exit(0);
			}
		});
		
		JButton btnSalvarCursoAluno = new JButton("");
		btnSalvarCursoAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				aluno = new Aluno();
				aluno.setRgm(Integer.parseInt(textRGM.getText()));
				aluno.setNome(textNome.getText());
				//FORMAT플O DA DATA DE DD/MM/AAAA PARA O FORMATO SQL AAAA/MM/AA
				String dia = formattedTextNascimento.getText().substring(0, 2);
				String mes = formattedTextNascimento.getText().substring(3, 5);
				String ano = formattedTextNascimento.getText().substring(6);
				String dataSqlFormat = ano+mes+dia;
				aluno.setNascimento((String)(dataSqlFormat));
				aluno.setCpf(formattedTextCPF.getText());
				aluno.setEmail(textEmail.getText());
				aluno.setEndereco(textEndereco.getText());
				aluno.setMunicipio(textMunicipio.getText());
				aluno.setUf((String)comboBoxUF.getSelectedItem());
				aluno.setCelular(formattedTextCelular.getText());
				//=============Tela Curso============
				aluno.setCurso((String)comboBoxCurso.getSelectedItem());
				aluno.setCampus((String)comboBoxCampus.getSelectedItem());
				
				String opcao;
				if(rdbtnMatutino.isSelected()) 
					opcao = "Matutino";
				else if(rdbtnVespertino.isSelected()) 
					opcao = "Vespertino";
				else 
					opcao = "Noturno";
				
				aluno.setPeriodo(opcao);
				
				try {
					alunoDao = new AlunoDAO();//FAZ A CONEXAO COM O BANCO
					alunoDao.salvarAluno(aluno);//CHAMADA DA FUNCAO SALVAR
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Falha ao salvar aluno.");
					e.printStackTrace();
				}
				//limpa apos salvar
				textRGM.setText("");
				textNome.setText("");
				formattedTextNascimento.setText("");
				formattedTextCPF.setText("");
				textEmail.setText("");
				textEndereco.setText("");
				textMunicipio.setText("");
				comboBoxUF.setSelectedIndex(0);
				formattedTextCelular.setText("");
				comboBoxCurso.setSelectedIndex(0);
				comboBoxCampus.setSelectedIndex(0);
				buttongroup.clearSelection();
			}

		});
		btnSalvarCursoAluno.setIcon(new ImageIcon("C:\\Users\\Fabio\\eclipse-workspace\\Cadastro Aluno\\icones\\icons8-salvar-48.png"));
		btnSalvarCursoAluno.setBounds(290, 230, 38, 41);
		panelCurso.add(btnSalvarCursoAluno);
		
		JButton btnAlterarCurso = new JButton("");
		btnAlterarCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aluno = new Aluno();
				aluno.setRgm(Integer.parseInt(textRGM.getText()));
				aluno.setNome(textNome.getText());
				//FORMAT플O DA DATA DE DD/MM/AAAA PARA O FORMATO SQL AAAA/MM/AA
				String dia = formattedTextNascimento.getText().substring(0, 2);
				String mes = formattedTextNascimento.getText().substring(3, 5);
				String ano = formattedTextNascimento.getText().substring(6);
				String dataSqlFormat = ano+mes+dia;
				aluno.setNascimento((String)(dataSqlFormat));
				aluno.setCpf(formattedTextCPF.getText());
				aluno.setEmail(textEmail.getText());
				aluno.setEndereco(textEndereco.getText());
				aluno.setMunicipio(textMunicipio.getText());
				aluno.setUf((String)comboBoxUF.getSelectedItem());
				aluno.setCelular(formattedTextCelular.getText());
				//=============Tela Curso============
				aluno.setCurso((String)comboBoxCurso.getSelectedItem());
				aluno.setCampus((String)comboBoxCampus.getSelectedItem());
				
				String opcao;
				if(rdbtnMatutino.isSelected()) 
					opcao = "Matutino";
				else if(rdbtnVespertino.isSelected()) 
					opcao = "Vespertino";
				else 
					opcao = "Noturno";
				
				aluno.setPeriodo(opcao);
				
				try {
					alunoDao = new AlunoDAO();//FAZ A CONEXAO COM O BANCO
					alunoDao.alterarAluno(aluno);//CHAMADA DA FUNCAO ALTERAR
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Falha ao alterar aluno.");
					e1.printStackTrace();
				}
			}
		});
		btnAlterarCurso.setIcon(new ImageIcon("C:\\Users\\Fabio\\eclipse-workspace\\Cadastro Aluno\\icones\\icons8-alterar-40.png"));
		btnAlterarCurso.setBounds(149, 230, 38, 41);
		panelCurso.add(btnAlterarCurso);
		
		JButton btnListarCurso = new JButton("");
		btnListarCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					alunoDao = new AlunoDAO();
					disciplinaDao = new DisciplinaDAO();
					int rgm = Integer.parseInt(textRGM.getText());
					aluno = alunoDao.consultarAluno(rgm);
					disciplina = disciplinaDao.consultarDisciplina(rgm);
					textNome.setText(aluno.getNome());
		
					String ano = aluno.getNascimento().substring(0,4);
					String mes = aluno.getNascimento().substring(5,7);
					String dia = aluno.getNascimento().substring(8);
					String dataSystemFormat = dia+mes+ano;
					
					formattedTextNascimento.setText(dataSystemFormat);
					formattedTextCPF.setText(aluno.getCpf());
					textEmail.setText(aluno.getEmail());
					textEndereco.setText(aluno.getEndereco());
					textMunicipio.setText(aluno.getMunicipio());
					comboBoxUF.setSelectedItem(aluno.getUf());
					formattedTextCelular.setText(aluno.getCelular());
					comboBoxCurso.setSelectedItem(aluno.getCurso());
					comboBoxCampus.setSelectedItem(aluno.getCampus());
					
					String opcao = aluno.getPeriodo();
					if(opcao.equals("Matutino") ) {
						rdbtnMatutino.setSelected(true);
						rdbtnVespertino.setSelected(false);
						rdbtnNoturno.setSelected(false);
					}	
					else if(opcao.equals("Vespertino")) {
						rdbtnMatutino.setSelected(false);
						rdbtnVespertino.setSelected(true);
						rdbtnNoturno.setSelected(false);
					}	
					else if(opcao.equals("Noturno")) {
						rdbtnMatutino.setSelected(false);
						rdbtnVespertino.setSelected(false);
						rdbtnNoturno.setSelected(true);
					}
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Falha ao listar aluno no boletim.");
					e1.printStackTrace();
				}
			}
		});
		btnListarCurso.setIcon(new ImageIcon("C:\\Users\\Fabio\\eclipse-workspace\\Cadastro Aluno\\icones\\icons8-lista-48.png"));
		btnListarCurso.setBounds(10, 230, 46, 41);
		panelCurso.add(btnListarCurso);
		
		JButton btnExcluirCurso = new JButton("");
		btnExcluirCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					alunoDao = new AlunoDAO();//FAZ A CONEXAO COM O BANCO
					int rgm = Integer.parseInt(textRGM.getText());
					System.out.println("RGM passado na funcao excluir"+rgm);
					alunoDao.excluirAluno(rgm);//CHAMADA DA FUNCAO EXCLUIR
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Falha ao excluir aluno.");
					e1.printStackTrace();
				}
			}
		});
		btnExcluirCurso.setIcon(new ImageIcon("C:\\Users\\Fabio\\eclipse-workspace\\Cadastro Aluno\\icones\\icons8-excluir-40.png"));
		btnExcluirCurso.setBounds(545, 230, 38, 41);
		panelCurso.add(btnExcluirCurso);
		
		JButton btnLimparCurso = new JButton("");
		btnLimparCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textRGM.setText("");
				textNome.setText("");
				formattedTextNascimento.setText("");
				formattedTextCPF.setText("");
				textEmail.setText("");
				textEndereco.setText("");
				textMunicipio.setText("");
				comboBoxUF.setSelectedIndex(0);
				formattedTextCelular.setText("");
				comboBoxCurso.setSelectedIndex(0);
				comboBoxCampus.setSelectedIndex(0);
				buttongroup.clearSelection();
				
			}
		});
		btnLimparCurso.setIcon(new ImageIcon("C:\\Users\\Fabio\\eclipse-workspace\\Cadastro Aluno\\icones\\icons8-limpar1-s\u00EDmbolo-48.png"));
		btnLimparCurso.setBounds(433, 230, 46, 41);
		panelCurso.add(btnLimparCurso);
		
		JLabel lblNewLabel_22 = new JLabel("Consultar");
		lblNewLabel_22.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_22.setBounds(10, 215, 62, 14);
		panelCurso.add(lblNewLabel_22);
		
		JLabel lblNewLabel_23 = new JLabel("Alterar");
		lblNewLabel_23.setBounds(149, 215, 46, 14);
		panelCurso.add(lblNewLabel_23);
		
		JLabel lblNewLabel_24 = new JLabel("Salvar");
		lblNewLabel_24.setBounds(290, 215, 46, 14);
		panelCurso.add(lblNewLabel_24);
		
		JLabel lblNewLabel_25 = new JLabel("Limpar");
		lblNewLabel_25.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_25.setBounds(433, 215, 46, 14);
		panelCurso.add(lblNewLabel_25);
		
		JLabel lblNewLabel_26 = new JLabel("Excluir");
		lblNewLabel_26.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_26.setBounds(545, 215, 46, 14);
		panelCurso.add(lblNewLabel_26);
		
		JLabel lblNewLabel_33 = new JLabel("Cadastro de Alunos");
		lblNewLabel_33.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_33.setBounds(10, 11, 573, 14);
		panelCurso.add(lblNewLabel_33);
		
		JPanel panelNotasFaltas = new JPanel();
		tabbedPane.addTab("Notas e Faltas", null, panelNotasFaltas, null);
		panelNotasFaltas.setLayout(null);
		
		JLabel lblNewLabel_12 = new JLabel("RGM");
		lblNewLabel_12.setBounds(10, 38, 46, 14);
		panelNotasFaltas.add(lblNewLabel_12);
		
		textRGMSearch = new JTextField();
		textRGMSearch.setBounds(66, 35, 133, 20);
		panelNotasFaltas.add(textRGMSearch);
		textRGMSearch.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Aluno");
		lblNewLabel_13.setBounds(209, 38, 46, 14);
		panelNotasFaltas.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Curso");
		lblNewLabel_14.setBounds(10, 86, 46, 14);
		panelNotasFaltas.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("Disciplina");
		lblNewLabel_15.setBounds(10, 135, 59, 14);
		panelNotasFaltas.add(lblNewLabel_15);
		
		JComboBox comboBoxDisciplina = new JComboBox();
		comboBoxDisciplina.setModel(new DefaultComboBoxModel(new String[] {"Selecione", "Matematica", "Teoria Geral da Administracao", "Lingua Portuguesa", "Anatomia Humana", "Engenharia de Software"}));
		comboBoxDisciplina.setBounds(68, 132, 187, 20);
		panelNotasFaltas.add(comboBoxDisciplina);
		
		JLabel lblNewLabel_16 = new JLabel("Semestre");
		lblNewLabel_16.setBounds(10, 179, 46, 14);
		panelNotasFaltas.add(lblNewLabel_16);
		
		JComboBox comboBoxSemestre = new JComboBox();
		comboBoxSemestre.setModel(new DefaultComboBoxModel(new String[] {"Selecione", "1", "2", "3", "4", "5", "6", "7", "8"}));
		comboBoxSemestre.setBounds(66, 176, 81, 20);
		panelNotasFaltas.add(comboBoxSemestre);
		
		JLabel lblNewLabel_17 = new JLabel("Nota");
		lblNewLabel_17.setBounds(209, 179, 46, 14);
		panelNotasFaltas.add(lblNewLabel_17);
		
		JComboBox comboBoxNota = new JComboBox();
		comboBoxNota.setModel(new DefaultComboBoxModel(new String[] {"Selecione", "0,5", "1", "1,5", "2", "2,5", "3", "3,5", "4", "4,5", "5", "5,5", "6", "6,5", "7", "7,5", "8", "8,5", "9", "9,5", "10"}));
		comboBoxNota.setBounds(265, 176, 75, 20);
		panelNotasFaltas.add(comboBoxNota);
		
		JLabel lblNewLabel_18 = new JLabel("Faltas");
		lblNewLabel_18.setBounds(442, 179, 46, 14);
		panelNotasFaltas.add(lblNewLabel_18);
		
		textFaltas = new JTextField();
		textFaltas.setBounds(516, 176, 86, 20);
		panelNotasFaltas.add(textFaltas);
		textFaltas.setColumns(10);
		
		//acoes menu notas e faltas
		menuItemNeFSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disciplina = new Disciplina();
				disciplina.setRgm(Integer.parseUnsignedInt(textRGMSearch.getText()));
				disciplina.setNome((String)comboBoxDisciplina.getSelectedItem());
				disciplina.setSemestre((String)comboBoxSemestre.getSelectedItem());
				disciplina.setNota((String)comboBoxNota.getSelectedItem());
				disciplina.setFaltas(textFaltas.getText());
				try {
					disciplinaDao = new DisciplinaDAO();//FAZ A CONEXAO COM O BANCO
					disciplinaDao.salvarDisciplina(disciplina);//CHAMADA DA FUNCAO ALTERAR
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Falha ao salvar disciplina.");						e1.printStackTrace();
						}
					}
				});
				
		menuItemNeFAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disciplina = new Disciplina();
				disciplina.setRgm(Integer.parseUnsignedInt(textRGMSearch.getText()));
				disciplina.setNome((String)comboBoxDisciplina.getSelectedItem());
				disciplina.setSemestre((String)comboBoxSemestre.getSelectedItem());
				disciplina.setNota((String)comboBoxNota.getSelectedItem());
				disciplina.setFaltas(textFaltas.getText());
				try {
					disciplinaDao = new DisciplinaDAO();//FAZ A CONEXAO COM O BANCO
					disciplinaDao.alterarDisciplina(disciplina);//CHAMADA DA FUNCAO ALTERAR
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Falha ao alterar disciplina.");
					e1.printStackTrace();
				}
			}
		});
				
		menuItemNeFExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					disciplinaDao = new DisciplinaDAO();//FAZ A CONEXAO COM O BANCO
					int rgm = Integer.parseInt(textRGM.getText());
					System.out.println("RGM passado na funcao excluir disciplina"+rgm);
					disciplinaDao.excluirDisciplina(rgm);//CHAMADA DA FUNCAO EXCLUIR
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Falha ao excluir disciplina.");
					e1.printStackTrace();
				}
			}
		});
				
		menuItemNeFConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					alunoDao = new AlunoDAO();
					int rgm = Integer.parseInt(textRGMSearch.getText());
					aluno = alunoDao.consultarAluno(rgm);
					textNeFNomeAluno.setText(aluno.getNome());
					textNeFNomeCurso.setText(aluno.getCurso());
				
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				textNeFNomeAluno.setText(aluno.getNome());
				textNeFNomeCurso.setText(aluno.getCurso());
			}
		});
		
		JButton btnSalvarNeF = new JButton("");
		btnSalvarNeF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//salvar disciplina
				disciplina = new Disciplina();
				disciplina.setRgm(Integer.parseUnsignedInt(textRGMSearch.getText()));
				disciplina.setNome((String)comboBoxDisciplina.getSelectedItem());
				disciplina.setSemestre((String)comboBoxSemestre.getSelectedItem());
				disciplina.setNota((String)comboBoxNota.getSelectedItem());
				disciplina.setFaltas(textFaltas.getText());
				try {
					disciplinaDao = new DisciplinaDAO();//FAZ A CONEXAO COM O BANCO
					disciplinaDao.salvarDisciplina(disciplina);//CHAMADA DA FUNCAO ALTERAR
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Falha ao salvar disciplina.");
					e1.printStackTrace();
				}
			}
		});
		btnSalvarNeF.setIcon(new ImageIcon("C:\\Users\\Fabio\\eclipse-workspace\\Cadastro Aluno\\icones\\icons8-salvar-48.png"));
		btnSalvarNeF.setBounds(291, 231, 38, 43);
		panelNotasFaltas.add(btnSalvarNeF);
		
		JButton btnAlterarNeF = new JButton("");
		btnAlterarNeF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				disciplina = new Disciplina();
				disciplina.setRgm(Integer.parseUnsignedInt(textRGMSearch.getText()));
				disciplina.setNome((String)comboBoxDisciplina.getSelectedItem());
				disciplina.setSemestre((String)comboBoxSemestre.getSelectedItem());
				disciplina.setNota((String)comboBoxNota.getSelectedItem());
				disciplina.setFaltas(textFaltas.getText());
				try {
					disciplinaDao = new DisciplinaDAO();//FAZ A CONEXAO COM O BANCO
					disciplinaDao.alterarDisciplina(disciplina);//CHAMADA DA FUNCAO ALTERAR
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Falha ao alterar disciplina.");
					e1.printStackTrace();
				}
			}
		});
		btnAlterarNeF.setIcon(new ImageIcon("C:\\Users\\Fabio\\eclipse-workspace\\Cadastro Aluno\\icones\\icons8-alterar-40.png"));
		btnAlterarNeF.setBounds(161, 231, 38, 43);
		panelNotasFaltas.add(btnAlterarNeF);
		
		JButton btnListarNeF = new JButton("");
		btnListarNeF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					alunoDao = new AlunoDAO();
					int rgm = Integer.parseInt(textRGMSearch.getText());
					aluno = alunoDao.consultarAluno(rgm);
					textNeFNomeAluno.setText(aluno.getNome());
					textNeFNomeCurso.setText(aluno.getCurso());
				
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				textNeFNomeAluno.setText(aluno.getNome());
				textNeFNomeCurso.setText(aluno.getCurso());
			}
		});
		btnListarNeF.setIcon(new ImageIcon("C:\\Users\\Fabio\\eclipse-workspace\\Cadastro Aluno\\icones\\icons8-lista-48.png"));
		btnListarNeF.setBounds(10, 231, 46, 43);
		panelNotasFaltas.add(btnListarNeF);
		
		JButton btnExcluirNeF = new JButton("");
		btnExcluirNeF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					disciplinaDao = new DisciplinaDAO();//FAZ A CONEXAO COM O BANCO
					int rgm = Integer.parseInt(textRGM.getText());
					System.out.println("RGM passado na funcao excluir disciplina"+rgm);
					disciplinaDao.excluirDisciplina(rgm);//CHAMADA DA FUNCAO EXCLUIR
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Falha ao excluir disciplina.");
					e1.printStackTrace();
				}
			}
		});
		btnExcluirNeF.setIcon(new ImageIcon("C:\\Users\\Fabio\\eclipse-workspace\\Cadastro Aluno\\icones\\icons8-excluir-40.png"));
		btnExcluirNeF.setBounds(556, 231, 46, 43);
		panelNotasFaltas.add(btnExcluirNeF);
		
		JButton btnLimparNeF = new JButton("");
		btnLimparNeF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textRGMSearch.setText("");
				textNeFNomeAluno.setText("");
				textNeFNomeCurso.setText("");
			}
		});
		btnLimparNeF.setIcon(new ImageIcon("C:\\Users\\Fabio\\eclipse-workspace\\Cadastro Aluno\\icones\\icons8-limpar1-s\u00EDmbolo-48.png"));
		btnLimparNeF.setBounds(442, 231, 46, 43);
		panelNotasFaltas.add(btnLimparNeF);
		
		textNeFNomeAluno = new JTextField();
		textNeFNomeAluno.setEditable(false);
		textNeFNomeAluno.setBounds(265, 35, 337, 20);
		panelNotasFaltas.add(textNeFNomeAluno);
		textNeFNomeAluno.setColumns(10);
		
		textNeFNomeCurso = new JTextField();
		textNeFNomeCurso.setEditable(false);
		textNeFNomeCurso.setBounds(66, 83, 536, 20);
		panelNotasFaltas.add(textNeFNomeCurso);
		textNeFNomeCurso.setColumns(10);
		
		JLabel lblNewLabel_27 = new JLabel("Listar");
		lblNewLabel_27.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_27.setBounds(10, 215, 46, 14);
		panelNotasFaltas.add(lblNewLabel_27);
		
		JLabel lblNewLabel_28 = new JLabel("Alterar");
		lblNewLabel_28.setBounds(161, 215, 46, 14);
		panelNotasFaltas.add(lblNewLabel_28);
		
		JLabel lblNewLabel_29 = new JLabel("Salvar");
		lblNewLabel_29.setBounds(291, 215, 46, 14);
		panelNotasFaltas.add(lblNewLabel_29);
		
		JLabel lblNewLabel_30 = new JLabel("Limpar");
		lblNewLabel_30.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_30.setBounds(442, 215, 46, 14);
		panelNotasFaltas.add(lblNewLabel_30);
		
		JLabel lblNewLabel_31 = new JLabel("Excluir");
		lblNewLabel_31.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_31.setBounds(556, 215, 46, 14);
		panelNotasFaltas.add(lblNewLabel_31);
		
		JLabel lblNewLabel_34 = new JLabel("Cadastro de Disciplinas");
		lblNewLabel_34.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_34.setBounds(10, 11, 592, 14);
		panelNotasFaltas.add(lblNewLabel_34);
		
		JPanel panelBoletim = new JPanel();
		tabbedPane.addTab("Boletim", null, panelBoletim, null);
		panelBoletim.setLayout(null);
		
		JLabel lblNewLabel_19 = new JLabel("RGM");
		lblNewLabel_19.setBounds(21, 11, 46, 14);
		panelBoletim.add(lblNewLabel_19);
		
		textBoletimRGMInputSearch = new JTextField();
		textBoletimRGMInputSearch.setBounds(67, 8, 122, 20);
		panelBoletim.add(textBoletimRGMInputSearch);
		textBoletimRGMInputSearch.setColumns(10);
		
		JLabel lblNewLabel_20 = new JLabel("Nome");
		lblNewLabel_20.setBounds(21, 36, 46, 14);
		panelBoletim.add(lblNewLabel_20);
		
		textBoletimNomeAluno = new JTextField();
		textBoletimNomeAluno.setEditable(false);
		textBoletimNomeAluno.setBounds(67, 36, 244, 20);
		panelBoletim.add(textBoletimNomeAluno);
		textBoletimNomeAluno.setColumns(10);
		
		TextArea textAreaListaBoletim = new TextArea();
		textAreaListaBoletim.setEditable(false);
		textAreaListaBoletim.setBounds(66, 116, 244, 173);
		panelBoletim.add(textAreaListaBoletim);
		
		JLabel lblNewLabel_21 = new JLabel("Curso");
		lblNewLabel_21.setBounds(21, 82, 46, 14);
		panelBoletim.add(lblNewLabel_21);
		
		textBoletimCurso = new JTextField();
		textBoletimCurso.setEditable(false);
		textBoletimCurso.setBounds(67, 79, 244, 20);
		panelBoletim.add(textBoletimCurso);
		textBoletimCurso.setColumns(10);
		
		JButton btnListarBoletim = new JButton("Exibir Boletim");
		btnListarBoletim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Disciplina> listaD = new ArrayList<Disciplina>();
				try {
					alunoDao = new AlunoDAO();
					disciplinaDao = new DisciplinaDAO();
					int rgm = Integer.parseInt(textBoletimRGMInputSearch.getText());
					aluno = alunoDao.consultarAluno(rgm);
					textBoletimNomeAluno.setText(aluno.getNome());
					textBoletimCurso.setText(aluno.getCurso());
					textAreaListaBoletim.append("RGM:............."+ (Integer.toString(aluno.getRgm())+"\n\n"));
					listaD = disciplinaDao.ListarDisciplina(rgm);
				
					for(Disciplina disciplina : listaD) {
						textAreaListaBoletim.append("Disciplina:......"+ disciplina.getNome()+"\n");
						textAreaListaBoletim.append("Nota:............"+ disciplina.getNota()+"\n");
						textAreaListaBoletim.append("Faltas:.........."+ disciplina.getFaltas()+"\n");
						textAreaListaBoletim.append(".................................................................................................\n");
					}
				
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Falha ao listar aluno no boletim.");
					e1.printStackTrace();
				}
			}
		});
		btnListarBoletim.setBounds(199, 7, 114, 23);
		panelBoletim.add(btnListarBoletim);
		
		TextArea textAreaListaAlunos = new TextArea();
		textAreaListaAlunos.setEditable(false);
		textAreaListaAlunos.setBounds(338, 60, 277, 229);
		panelBoletim.add(textAreaListaAlunos);
		
		JButton btnLimparBoletim = new JButton("Limpar");
		btnLimparBoletim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textBoletimRGMInputSearch.setText("");
				textBoletimNomeAluno.setText("");
				textBoletimCurso.setText("");
				textAreaListaBoletim.setText("");
				textAreaListaAlunos.setText("");
			}
		});
		btnLimparBoletim.setBounds(501, 7, 114, 23);
		panelBoletim.add(btnLimparBoletim);
		
		JButton btnListaAlunos = new JButton("Listar Alunos");
		btnListaAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Aluno> listaA = new ArrayList<Aluno>();
				
				try {
					alunoDao = new AlunoDAO();
					listaA = alunoDao.ListarAlunos();
					
					for(Aluno aluno : listaA) {
						textAreaListaAlunos.append("RGM Aluno..."+ aluno.getRgm()+"\n");
						textAreaListaAlunos.append(aluno.getNome());
						textAreaListaAlunos.append("Curso Aluno..."+ aluno.getCurso()+"\n");
						
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Falha ao listar alunos.");
					e1.printStackTrace();
				}
			}
		});
		btnListaAlunos.setBounds(338, 7, 136, 23);
		panelBoletim.add(btnListaAlunos);
		
		JLabel lblNewLabel_35 = new JLabel("Lista de alunos matriculados");
		lblNewLabel_35.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_35.setBounds(338, 36, 277, 14);
		panelBoletim.add(lblNewLabel_35);
	}
}
