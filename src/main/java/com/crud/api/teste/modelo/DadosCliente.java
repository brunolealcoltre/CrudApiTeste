package com.crud.api.teste.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.Api;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"codigoCliente",
	"nomeCliente",
	"numeroAgencia",
	"numeroDaConta",
	"bandeira"

})
@Entity
@Api(produces = "Objeto Cliente")
public class DadosCliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty ("codigoCliente")
	private Long codigoCliente;
	@JsonProperty("nomeCliente")
	@NotNull
	private String nomeCliente;
	@JsonProperty("numeroAgencia")
	@NotNull
	private Long numeroAgencia;
	@JsonProperty("numeroDaConta")
	private String numeroDaConta;
	@JsonProperty ("bandeira")
	private String bandeira;
	
	
    public DadosCliente() {
    }

    public DadosCliente(Long codigoCliente, String nomeCliente, Long numeroAgencia, String numeroDaConta,String bandeira) {
        super();
       
        this.codigoCliente = codigoCliente;
        this.nomeCliente = nomeCliente;
        this.numeroAgencia = numeroAgencia;
        this.numeroDaConta = numeroDaConta;
        this.bandeira = bandeira;
    }
    @JsonProperty("bandeira")
    public String getBandeira() {
		return bandeira;
	}
    @JsonProperty("bandeira")
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	@JsonProperty("codigoCliente")
    public Long getCodigoCliente() {
		return codigoCliente;
	}
    @JsonProperty("codigoCliente")
	public void setCodigoCliente(Long codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	@JsonProperty("nomeCliente")
	public String getNomeCliente() {
		return nomeCliente;
	}
    @JsonProperty("nomeCliente")
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
    @JsonProperty("numeroAgencia")
	public Long getNumeroAgencia() {
		return numeroAgencia;
	}
    @JsonProperty("numeroAgencia")
	public void setNumeroAgencia(Long numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}
    @JsonProperty("numeroDaConta")
	public String getNumeroDaConta() {
		return numeroDaConta;
	}
    @JsonProperty("numeroDaConta")
	public void setNumeroDaConta(String numeroDaConta) {
		this.numeroDaConta = numeroDaConta;
	}

}