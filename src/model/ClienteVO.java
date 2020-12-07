package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import entidades.Pedido;

public class ClienteVO implements Serializable {
	private Integer id;
	private String nome;
	private String endereco;
	private BigDecimal valorTotal;
	private List<PedidoVO> pedidos = new ArrayList<PedidoVO>();
	 
	public ClienteVO() {}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer idCliente) {		
		this.id = idCliente;
		// this.idStr = this.id+"";
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<PedidoVO> getPedidos() {
		return pedidos;
	}
	
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setPedidos(List<PedidoVO> list) {
		this.pedidos = list;
	}
	 
	 
	@Override
	public String toString() {
		return "ClienteVO [id=" + id  + " ,"
				+ " nome=" + nome + ", endereco=" + endereco + ", pedidos=" + pedidos + "]";
	}	

}
