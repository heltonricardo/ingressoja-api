package io.github.heltonricardo.ingressoja.utils;

import com.mercadopago.MercadoPago;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import io.github.heltonricardo.ingressoja.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public abstract class Pagamento {

  private static final String accessToken =
      "TEST-7604221206220114-102022-fbe6e78ad40cf14327a0fd2c14083964-599638185";

  public static String gerarUrlPagamento(Pedido pedido) {

    try {
      MercadoPago.SDK.setAccessToken(accessToken);

      List<Item> itensPagamento = new ArrayList<>();

      pedido.getItensPedido().forEach(i -> itensPagamento.add(
          new Item()
              .setQuantity(1)
              .setTitle(i.getTipoDeIngresso().getEvento().getTitulo() + " - " +
                  i.getIngressante())
              .setUnitPrice(i.getTipoDeIngresso().getValor().floatValue())));

      Preference preference = new Preference();
      itensPagamento.forEach(preference::appendItem);
      preference.setExternalReference(pedido.getId().toString());
      preference.save();
      return preference.getSandboxInitPoint();
    } //
    catch (Exception e) {
      return null;
    }
  }
}