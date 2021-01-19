package com.example.simplificar.models;

public class Agendamento {
    private Integer _id;
    private String tipo, veiculo, data, hora;

    public Agendamento(){}

    public Agendamento(String tipo, String veiculo, String data, String hora) {
        this.tipo = tipo;
        this.veiculo = veiculo;
        this.data = data;
        this.hora = hora;
    }

    public Agendamento(Integer _id, String tipo, String veiculo, String data, String hora) {
        this._id = _id;
        this.tipo = tipo;
        this.veiculo = veiculo;
        this.data = data;
        this.hora = hora;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
