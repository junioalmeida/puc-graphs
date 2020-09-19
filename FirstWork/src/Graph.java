import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Graph {
	
	public static final int SIZE = 10;
	private static final String FILE_NAME = "grafo.txt";
	
	private int[][] graph;
	private String[] vertex;
	
	public Graph(String[] vertex) throws MaximumVextexException {
		
		if(vertex.length > SIZE) {
			throw new MaximumVextexException("Não é possível inserir mais que 10 vértices");
		}
		
		graph = new int[SIZE][SIZE];
		this.vertex = vertex;
	}
	
	public void insertEdge(int initialVertex, int finalVertex, int value) {
		setValueAtPosition(initialVertex, finalVertex, value);
	}

	public void removeEdge(int initialVertex, int finalVertex) {
		setValueAtPosition(initialVertex, finalVertex, 0);
	}
	
	private void setValueAtPosition(int initialVertex, int finalVertex, int value) {
		graph[initialVertex - 1][finalVertex - 1] = value;
		graph[finalVertex - 1][initialVertex - 1] = value;
	}
	
	public boolean edgeExists(int initialVertex, int finalVertex) {
		return graph[initialVertex][finalVertex] > 0;
	}
	
	public void showDegrees() {
		int degree;
		
		for(int i = 0; i < SIZE; i++) {
			
			degree = 0;
			
			for(int j = 0; j < SIZE; j++) {
				if(edgeExists(i, j)) {
					degree++;
				}
			}
			
			UI.printf("Grau do vértice \"%s\": $d\n", vertex[i], degree);
		}
	}
	
    public void showGraph() {
        int i, j;

        for (i = 1; i <= SIZE; i++) {
            UI.printf("Vértices adjacentes a \"%s\": ", vertex[i]);
    
            for (j = 1; j <= SIZE; j++) {
                if (edgeExists(i, j)) {
                    UI.printf("\"%s\" (peso: %d), ", vertex[j], graph[i-1][j-1]);
                }
            }
            
            UI.printNewLine();
        }         
    }
    
    public void load() throws IOException {
    	
    	List<String> lines = FileUtils.readFileAsList(Paths.get("", FILE_NAME));
    	
    	int amoutVertex = Integer.parseInt(lines.get(0));

    	vertex = new String[amoutVertex];
    	
    	for(int i = 1; i < amoutVertex; i++) {
    		vertex[i - 1] = lines.get(i);
    	}
    }
    
}
