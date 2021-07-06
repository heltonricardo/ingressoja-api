package io.github.heltonricardo.ingressoja.dto;

import io.github.heltonricardo.ingressoja.model.ItemPedido;
import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;
import io.github.heltonricardo.ingressoja.model.Usuario;
import io.github.heltonricardo.ingressoja.repository.ItemPedidoRepository;
import io.github.heltonricardo.ingressoja.repository.TipoDeIngressoRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Getter
public class ItemPedidoDTO {

  private String ingressante;
  private String cpf;
  private String email;
  private Long idTipoDeIngresso;

  public ItemPedido paraObjeto() {
    return new ItemPedido(ingressante, cpf, email, idTipoDeIngresso);
  }
}
