public class Main {

	public static void main(String[] args) {
		
		Graph graph;
		int vi, vf, value;
		int op = 0;
		
		try {
			graph = new Graph();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		while (op != 15) {

			try {
				op = UI.menu();

				UI.printNewLine();

				switch (op) {
					case 1 -> {
	
					}
	
					case 2 -> {
	
					}
	
					case 3 -> {
						vi = Integer.parseInt(UI.readString("Vértice inicial: "));
						vf = Integer.parseInt(UI.readString("Vértice final: "));
						value = Integer.parseInt(UI.readString("Peso: "));
	
						graph.insertEdge(vi, vf, value);
					}
	
					case 4 -> {
						vi = Integer.parseInt(UI.readString("Vértice inicial: "));
						vf = Integer.parseInt(UI.readString("Vértice final: "));
	
						graph.removeEdge(vi, vf);
					}
	
					case 5 -> {
						vi = Integer.parseInt(UI.readString("Vértice inicial: "));
						vf = Integer.parseInt(UI.readString("Vértice final: "));
	
						if (graph.edgeExists(vi, vf)) {
							UI.printf("Os vértices %d e %d são adjacentes.", vi, vf);
						} else {
							UI.printf("Os vértices %d e %d não são adjacentes.", vi, vf);
						}
					}
	
					case 6 -> {
						graph.showDegrees();
					}
	
					case 7 -> {
						graph.showGraph();
					}
	
					case 8 -> {
	
					}
	
					case 9 -> {
	
					}
	
					case 10 -> {
	
					}
	
					case 11 -> {
	
					}
	
					case 12 -> {
	
					}
	
					case 13 -> {
						graph.load();
					}
	
					case 14 -> {
						graph.save();
					}
	
					case 15 -> {
						UI.println("Saindo da aplicação...");
						Thread.sleep(2 * 1000);
					}
				}

			} catch (Exception e) {
				UI.printf("\nTipo: %s\nErro: %s\n", e.getClass().getName(), e.getMessage());
			}
		}

	}

}
