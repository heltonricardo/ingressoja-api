package io.github.heltonricardo.ingressoja.utils;

import com.mercadopago.MercadoPago;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import io.github.heltonricardo.ingressoja.model.Pedido;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class Pagamento {

  /******************************** CONSTANTES ********************************/

  private static final String ACCESS_TOKEN =
      "TEST-7436017717495917-102123-f03839ba0f06c99772d08f139537fb65-599638185";

  private static final String URL_CONSULTA_PAGAMENTO =
      "https://api.mercadopago.com/v1/payments/search?external_reference=";

  /*************************** CONSULTAR PAGAMENTO ****************************/

  public static String consultarPagamento(Pedido pedido) {

    String idPedido = pedido.getId().toString();

    try {
      HttpGet request = new HttpGet(URL_CONSULTA_PAGAMENTO + idPedido);
      request.setHeader(HttpHeaders.AUTHORIZATION,
          "Bearer " + ACCESS_TOKEN);
      CloseableHttpClient client = HttpClients.createDefault();
      CloseableHttpResponse response = client.execute(request);
      HttpEntity entity = response.getEntity();
      String result = EntityUtils.toString(entity);

      if (result.contains(StatusPgto.PESQ_APPROVED)) {
        return StatusPgto.APROVADO;
      } //
      else if (result.contains(StatusPgto.PESQ_IN_PROGRESS)) {
        return StatusPgto.PENDENTE;
      }

      throw new Exception();
    } catch (Exception ignored) {
      return StatusPgto.RECUSADO;
    }
  }

  /************************** GERAR URL DE PAGAMENTO **************************/

  public static String gerarUrlPagamento(Pedido pedido) {

    try {
      MercadoPago.SDK.setAccessToken(ACCESS_TOKEN);

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