package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import dao.PedidoDAO;
import entidades.Pedido;
import model.PedidoVO;


@Named 
@SessionScoped
public class PedidoMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private PedidoVO pedido = new PedidoVO();	
	private List<PedidoVO> pedidos;
	 
	static PedidoDAO dao = new PedidoDAO();
	
	public PedidoMB() {}
 	
	
	public String salvar() {
		
        // se o "id" do objeto "pedidoVO" está NULL significa um "novo pedido"
		if (pedido.getId()==null) {
			cadastrarNovoPedido();
		} else
			atualizarPedido();
		return "";
	}
	
	public String novoPedido() {
		this.pedido = new PedidoVO();
		FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_INFO, 
	                		"Informe os dados do novo pedido", ""));	       
		return "";	
	}
	
    // método privado para incluir um novo pedido na base dados.
	
	private void cadastrarNovoPedido() {
		
		boolean incluiu = dao.incluir(pedido);
		if (incluiu)
		   FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Pedido <"+pedido.getNomeProduto() + "> "
					+ " cadastrado com ID="+pedido.getId(), null));
		else
		   FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na Operação!", null));
		// limpa o "VO" para incluir um novo
		this.pedido = new PedidoVO();			
	}
	
	// método privado para alterar os dados do cliente na base dados.
	private void atualizarPedido() {		
		boolean ok = dao.atualiza(this.pedido);
		if (ok)
		   FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, 
						"Pedido <" + this.pedido.getNomeProduto()
						+ "> atualizado com sucesso!", null));
		else
		   FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, 
						"Erro na Operação!", null));
		// limpa o "VO" para incluir um novo
		this.pedido = new PedidoVO();					
	}
	
	public void delete(String id) {
		int idPK = Integer.parseInt(id);	
		Pedido p = dao.findById(idPK);
		dao.delete(idPK);       
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, 
						"Pedido <" + p.getNomeProduto()
						+ "> Excluído com sucesso!", null));
    }
	
	public String update(String id) {
		int idPK = Integer.parseInt(id);		 
	    Pedido p = dao.findById(idPK);
	    this.pedido.setId(p.getId());
	    this.pedido.setNomeProduto(p.getNomeProduto());
	    this.pedido.setData(p.getData());
	    this.pedido.setQuantidade(p.getQuantidade());
	    this.pedido.setValorTotal(p.getValorTotal());
	    
	    return "";
	}
	
	// getters e setters
	public PedidoVO getPedido() {
		return this.pedido;
	}
	public void setPedido(PedidoVO pedido) {
		this.pedido = pedido;
	}

	public List<PedidoVO> getPedidos() {
		List<Pedido> pedidosEnt = dao.getPedidos();
		this.pedidos = new ArrayList<PedidoVO>();
		for (Pedido pedido : pedidosEnt) {			
			PedidoVO vo = new PedidoVO();
			vo.setId(pedido.getId());
			vo.setNomeProduto(pedido.getNomeProduto());
		    vo.setData(pedido.getData());
		    vo.setQuantidade(pedido.getQuantidade());
		    vo.setValorTotal(pedido.getValorTotal());
			
			this.pedidos.add(vo);	
		}		
		return this.pedidos;
	}

	public void setPedidos(List<PedidoVO> pedidos) {
		this.pedidos = pedidos;
	}
 
}
