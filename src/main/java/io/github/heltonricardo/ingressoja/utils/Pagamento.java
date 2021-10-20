package io.github.heltonricardo.ingressoja.utils;

import com.mercadopago.MercadoPago;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import io.github.heltonricardo.ingressoja.model.ItemPedido;

import java.util.List;

public abstract class Pagamento {


  private static final String accessToken =
      "TEST-7604221206220114-102022-fbe6e78ad40cf14327a0fd2c14083964-599638185";

  public static String gerarLinkPagamento(List<ItemPedido> itens) {

    try {
      MercadoPago.SDK.setAccessToken(accessToken);

      Item item = new Item()
          .setTitle("Meu produto")
          .setQuantity(1)
          .setUnitPrice((float) 75.56);

      Preference preference = new Preference();
      preference.appendItem(item);
      preference.save();

      return preference.getSandboxInitPoint();

    } catch (Exception e) {
      return null;
    }
  }
}