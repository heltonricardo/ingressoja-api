package io.github.heltonricardo.ingressoja.utils;

import com.mercadopago.MercadoPago;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.advancedpayment.Payment;
import com.mercadopago.resources.datastructures.preference.Item;
import io.github.heltonricardo.ingressoja.model.Pedido;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class Pagamento {

  /******************************** CONSTANTES ********************************/

  private static final String ACCESS_TOKEN =
      "TEST-ACCESS_TOKEN";

  private static final String URL_CONSULTA_PAGAMENTO =
      "https://api.mercadopago.com/v1/payments/search?external_reference=";

  /***************************** FAZER REQUISIÇÃO *****************************/

  public static String fazerRequisicao(String url) {

    try {
      HttpGet request = new HttpGet(url);
      request.setHeader(HttpHeaders.AUTHORIZATION,
          "Bearer " + ACCESS_TOKEN);
      CloseableHttpClient client = HttpClients.createDefault();
      CloseableHttpResponse response = client.execute(request);
      HttpEntity entity = response.getEntity();
      return EntityUtils.toString(entity);
    } catch (Exception e) {
      return null;
    }
  }

  /*************************** CONSULTAR PAGAMENTO ****************************/

  public static String consultarPagamento(Pedido pedido) {

    String idPedido = pedido.getId().toString();
    String resposta = fazerRequisicao(URL_CONSULTA_PAGAMENTO + idPedido);

    if (resposta == null)
      return null;
    if (resposta.contains(StatusPgto.PESQ_APPROVED))
      return StatusPgto.APROVADO;
    if (resposta.contains(StatusPgto.PESQ_IN_PROGRESS))
      return StatusPgto.PENDENTE;
    if (resposta.contains(StatusPgto.PESQ_RECUSADO))
      return StatusPgto.RECUSADO;

    return null;
  }

  /************************** GERAR URL DE PAGAMENTO **************************/

  public static String gerarUrlPagamento(Pedido pedido) {

    try {
      MercadoPago.SDK.setAccessToken(ACCESS_TOKEN);

      List<Item> itensPagamento = new ArrayList<>();

      pedido.getItensPedido().forEach(i -> itensPagamento.add(
          new Item()
              .setQuantity(1)
              .setTitle(i.getTipoDeIngresso().getEvento()
                  .getTitulo() + " - " + i.getIngressante())
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

  /************************** REALIZAR CANCELAMENTO ***************************/

  public static void realizarCancelamento(Pedido pedido) {

    String idPedido = pedido.getId().toString();
    String resposta = fazerRequisicao(URL_CONSULTA_PAGAMENTO + idPedido);

    try {
      JSONObject jsonObject = new JSONObject(resposta);
      JSONArray results = jsonObject.getJSONArray("results");
      int idUltimoPagamento =
          results.getJSONObject(results.length() - 1).getInt("id");

      Payment payment = new Payment();
      payment.setId(idUltimoPagamento);
      payment.getCapture();
    } //
    catch (Exception ignored) {
    }
  }
}