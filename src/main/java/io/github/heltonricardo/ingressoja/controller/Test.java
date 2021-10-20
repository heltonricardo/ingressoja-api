package io.github.heltonricardo.ingressoja.controller;

import com.mercadopago.exceptions.MPConfException;
import io.github.heltonricardo.ingressoja.utils.Pagamento;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teste")
public class Test {

  @GetMapping
  public void pagseguro() throws Exception {

    //MercadoPago.autorizaaekkk();
    Pagamento.gerarLinkPagamento(null);
  }
}
