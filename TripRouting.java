import java.util.*;
import java.io.*;

public class TripRouting {
	static HashMap<String, Integer> nameToPos;
	static HashMap<Integer, String> posToName;
	static int[][] p;
	static edge[][] matriz;
	static StringBuilder retorno;
	static int sumMilles;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		 BufferedWriter esc=new BufferedWriter(new
		OutputStreamWriter(System.out));
		matriz = new edge[101][101];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (i != j) {
					edge act = new edge("", "", "", Integer.MAX_VALUE);
					matriz[i][j] = act;
				} else {
					edge act = new edge("", "", "", 0);
					matriz[i][j] = act;
				}
			}
		}

		BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
		HashSet<String> hm = new HashSet<String>();
		posToName = new HashMap<Integer, String>();
		nameToPos = new HashMap<String, Integer>();
		String linea;

		while (!(linea = lector.readLine()).equals("")) {

			String[] act = linea.split(",");
			String v1 = act[0];
			String v2 = act[1];
			String route = act[2];
			int miles = Integer.parseInt(act[3]);
			edge eddToAdd = new edge(v1, v2, route, miles);

			if (!hm.contains(v1)) {
				posToName.put(posToName.size(), v1);
				nameToPos.put(v1, nameToPos.size());
				hm.add(v1);
			}

			if (!hm.contains(v2)) {
				posToName.put(posToName.size(), v2);
				nameToPos.put(v2, nameToPos.size());
				hm.add(v2);
			}

			int pos1 = nameToPos.get(v1);
			int pos2 = nameToPos.get(v2);
			if (matriz[pos1][pos2].miles > eddToAdd.miles) {
				matriz[pos1][pos2] = eddToAdd;
				matriz[pos2][pos1] = eddToAdd;
			}
		}
		int tam = nameToPos.size();
		p = new int[tam][tam];
		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < p.length; j++) {
				// p[i][j] = new edge("", "", "", i);
				p[i][j] = i;
			}
		}

		for (int k = 0; k < nameToPos.size(); k++) {

			for (int i = 0; i < nameToPos.size(); i++) {
				for (int j = 0; j < nameToPos.size(); j++) {

					if ((matriz[i][k].miles != Integer.MAX_VALUE && matriz[k][j].miles != Integer.MAX_VALUE)
							&& matriz[i][j].miles > matriz[i][k].miles + matriz[k][j].miles) {
						matriz[i][j].miles = matriz[i][k].miles + matriz[k][j].miles;

						p[i][j] = p[k][j];
					}
				}
			}
		}
		
		while ((linea = lector.readLine()) != null) {
			esc.write("\n\n");
			String[] query = linea.split(",");

			String source = query[0];
			String destiny = query[1];

			sumMilles = 0;
			retorno = new StringBuilder();

			

			String encabezado = "From                 To                   Route      Miles";
			String lineaFrom = "-------------------- ";
			String lineaTo = "-------------------- ";
			String lineaRuta = "---------- ";
			String lineaMillas = "-----";

			String ret = encabezado + "\n" + lineaFrom + lineaTo + lineaRuta + lineaMillas;
			retorno.append(ret + "\n");
			imprimir(nameToPos.get(source), nameToPos.get(destiny), nameToPos.get(destiny));

			rellenarEspacios("", 53);
			retorno.append(lineaMillas + "\n");
			rellenarEspacios("", 42);
			retorno.append("Total");
			rellenarEspacios("Total"+sumMilles, 16);
			retorno.append(sumMilles+"\n");
			esc.write(retorno+"");
		}
esc.flush();
	}

	static void imprimir(int i, int j, int k) {
		if (i != j)
			imprimir(i, p[i][j], j);

		if (!posToName.get(j).equals(posToName.get(k))) {
			retorno.append(posToName.get(j));
			rellenarEspacios(posToName.get(j), 20);
			retorno.append(" " + posToName.get(k));
			rellenarEspacios(posToName.get(k), 20);
			retorno.append(" " + matriz[j][k].route);
			rellenarEspacios(matriz[j][k].route, 10);
			rellenarEspacios(matriz[j][k].miles + "", 5);
			retorno.append(" " + matriz[j][k].miles);
			// rellenarEspacios(matriz[j][k].miles+"", 10);
			retorno.append("\n");
			sumMilles += matriz[j][k].miles;
		}

	}

	static void rellenarEspacios(String cad, int n) {

		int dif = n - cad.length();
		for (int i = 0; i < dif; i++) {
			retorno.append(" ");
		}
	}
}

class edge {

	String v;
	String vAdj;
	String route;
	int miles;

	public edge(String v, String vAdj, String route, int miles) {

		this.v = v;
		this.vAdj = vAdj;
		this.vAdj = vAdj;
		this.route = route;
		this.miles = miles;
	}

	public void setMiles(int miles) {
		this.miles = miles;
	}

}
