package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.ItemPedido;
import io.github.heltonricardo.ingressoja.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemPedidoService {

  private final ItemPedidoRepository itemPedidoRepository;

  @Autowired
  public ItemPedidoService(
      ItemPedidoRepository itemPedidoRepository) {

    this.itemPedidoRepository = itemPedidoRepository;
  }

  /******************************* OBTER POR ID *******************************/

  public Optional<ItemPedido> obterPorId(Long id) {

    return itemPedidoRepository.findById(id);
  }

  /********************************* UTILIZAR *********************************/

  public Boolean utilizar(ItemPedido itemPedido) {

    if (itemPedido.getUtilizado())
      return false;

    itemPedido.utilizar();
    itemPedidoRepository.save(itemPedido);
    return true;
  }
}
