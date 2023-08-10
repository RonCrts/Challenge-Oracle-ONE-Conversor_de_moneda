package conversorMonedas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

public class ConvertirMonedasAPesos {
	
	private static final String API_KEY = "YOUR_API_KEY"; // Agrega tu clave de API
	private static final String API_URL = "https://api.exchangeratesapi.io/latest?base=USD&access_key=" + API_KEY; // Agrega la clave de API a la URL

	public void ConvertirDolaresAPesos(double valor) {
		double tipoCambio = getTipoCambio("USD");
		double monedaDolar = valor * tipoCambio;
		monedaDolar = (double) Math.round(monedaDolar *100d)/100;
        JOptionPane.showMessageDialog(null, "Tienes $ " +monedaDolar+ " Pesos Colombianos");
	}
	
	public void ConvertirEurosAPesos(double valor) {
		double tipoCambio = getTipoCambio("EUR");
		double monedaEuro = valor * tipoCambio;
		monedaEuro = (double) Math.round(monedaEuro *100d)/100;
		JOptionPane.showMessageDialog(null, "Tienes $ " +monedaEuro+ " Pesos Colombianos");
	}
	
	public void ConvertirLibrasAPesos(double valor) {
		double tipoCambio = getTipoCambio("GBP");
		double monedaLibra = valor * tipoCambio;
        monedaLibra = (double) Math.round(monedaLibra *100d)/100;
        JOptionPane.showMessageDialog(null, "Tienes $ " +monedaLibra+ " Pesos Colombianos");
	}
	
	public void ConvertirYenAPesos(double valor) {
		double tipoCambio = getTipoCambio("JPY");
		double monedaYen = valor * tipoCambio;
        monedaYen = (double) Math.round(monedaYen *100d)/100;
        JOptionPane.showMessageDialog(null, "Tienes $ " +monedaYen+ " Pesos Colombianos");
	}
	
	public void ConvertirWonAPesos(double valor) {
		double tipoCambio = getTipoCambio("KRW");
		double monedaWon = valor * tipoCambio;
        monedaWon = (double) Math.round(monedaWon *100d)/100;
        JOptionPane.showMessageDialog(null, "Tienes $ " +monedaWon+ " Pesos Colombianos");
	}

	private double getTipoCambio(String moneda) {
		try {
			URL url = new URL(API_URL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			con.disconnect();
			JSONObject json = new JSONObject(content.toString());
			return json.getJSONObject("rates").getDouble(moneda);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al obtener el tipo de cambio");
			return 0;
		}
	}
}
