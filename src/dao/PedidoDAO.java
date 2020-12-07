package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import entidades.Pedido;
import model.PedidoVO;
import utilitarios.FabricaConexao;
import utilitarios.GeradorID;

public class PedidoDAO {
	private EntityManager em = FabricaConexao.getConexao();

	/**
	 * Inclui um objeto Pedido na base de dados
	 */
	public boolean incluir(PedidoVO vo) {
		// utiliza um objeto de transferência (VO)
		Pedido p = new Pedido();
		p.setId(GeradorID.geraNumeroID());
		//p.setData(vo.getData());
		p.setNomeProduto(vo.getNomeProduto());
		p.setPreco(vo.getPreco());
		p.setQuantidade(vo.getQuantidade());
		p.setValorTotal(vo.getValorTotal());
		
		vo.setId(p.getId()); // repassa o ID para o VO
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		return true;
	}

	/**
	 * Pesquisa e retorna um Pedido baseado na chave "id"
	 */
	public Pedido findById(Integer id) {
		Pedido p = em.find(Pedido.class, id);
		if (p == null) {
			throw new EntityNotFoundException("Pedido não localizado pelo ID " + id);
		}
		return p;
	}

	/**
	 * Recupera todos os pedidos da base de dados 
	 * @return uma Lista de Pedidos
	 */
	public List<Pedido> getPedidos() {
		Query  query = em.createQuery("select p from Pedido p", Pedido.class);
		return query.getResultList();
	}

	/**
	 * Este método realiza o "update" dos dados do pedido.
	 * @param vo
	 */
	public boolean atualiza(PedidoVO vo) {
		// recupera o objeto persistente
		Pedido p = this.findById(vo.getId());
		p.setNomeProduto(vo.getNomeProduto());
		//p.setData(vo.getData());
		p.setQuantidade(vo.getQuantidade());
		p.setValorTotal(vo.getValorTotal());

		em.getTransaction().begin();
		em.merge(p); // merge -> faz o "update" no objeto persistente
		em.getTransaction().commit();
		return true;
	}

	/**
	 * Este método deleta um cliente já cadastrado.
	 * @param vo
	 */
	public boolean delete(PedidoVO vo) {
		// recupera o objeto persistente
		Pedido p = this.findById(vo.getId());
		if (p == null)
			return false;

		em.getTransaction().begin();
		em.remove(p);  // remove -> faz o "delete" no objeto persistente
		em.getTransaction().commit();
		return true;
	}

	// deleta usando o ID
	public boolean delete(int id) {
		// recupera o objeto persistente
		Pedido p = this.findById(id);
		if (p == null)
			return false;

		em.getTransaction().begin();
		em.remove(p);  // remove -> faz o "delete" no objeto persistente
		em.getTransaction().commit();
		return true;
	}

}
