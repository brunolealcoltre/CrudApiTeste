package com.crud.api.teste.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.crud.api.teste.modelo.DadosCliente;


public interface ClienteRepository extends JpaRepository<DadosCliente, Long>{
	
	@Transactional(readOnly=true)
	@Query(value = "select * from dados_cliente d where LOWER(d.nome_cliente) like ?1% ",  nativeQuery = true)
	List<DadosCliente> findByNomeLike(@Param("nome")String nome);
	
	@Query (value = "select * from dados_cliente d where LOWER(d.nome_cliente) like %?1% ",  nativeQuery = true)
	List<DadosCliente> findByteste(String retornoDoSelect);
	
	@Transactional(readOnly=true)
	@Query(value = "select * FROM dados_cliente  ORDER BY LOWER(dados_cliente.nome_cliente) ASC ",nativeQuery = true)
	List<DadosCliente> ordenaCliente();
	
	@Query(value = "SELECT new com.crud.api.teste.modelo(u.nome_cliente) FROM dados_cliente u",nativeQuery = true)
	List<DadosCliente> findByColuna(String coluna);
		
}
	

