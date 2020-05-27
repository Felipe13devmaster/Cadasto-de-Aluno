package br.com.cadastroaluno.model;

public class Disciplina {
	private int rgm;
	private String nome;
	private String semestre;
	private String nota;
	private String faltas;
	
	public Disciplina() {
	}

	public Disciplina(int rgm, String nome, String semestre, String nota, String faltas) {
		super();
		this.rgm = rgm;
		this.nome = nome;
		this.semestre = semestre;
		this.nota = nota;
		this.faltas = faltas;
	}
	
	public int getRgm() {
		return rgm;
	}

	public void setRgm(int rgm) {
		this.rgm = rgm;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getFaltas() {
		return faltas;
	}

	public void setFaltas(String faltas) {
		this.faltas = faltas;
	}
	
	
}
