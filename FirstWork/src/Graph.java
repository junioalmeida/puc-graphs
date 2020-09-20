import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Graph {
	
	private static final int MAX_SIZE = 10;
	private static final String FILE_NAME = "grafo.txt";
	
	private int[][] graph;
	private String[] vertex;
	private int size;
	
	public Graph() throws MaximumVextexException, IOException {
		load();
	}
	
	public void insertEdge(int initialVertex, int finalVertex, int value) throws EdgeException {
		if(edgeExists(initialVertex, finalVertex)) {
			throw new EdgeException(String.format("Os vértices \"%s\" e \"%s\" já são adjacentes.", 
					vertex[initialVertex], vertex[finalVertex]));
		}
		
		setValueAtPosition(initialVertex, finalVertex, value);
	}

	public void removeEdge(int initialVertex, int finalVertex) throws EdgeException {
		if(!edgeExists(initialVertex, finalVertex)) {
			throw new EdgeException(String.format("Os vértices \"%s\" e \"%s\" não são adjacentes.", 
					vertex[initialVertex], vertex[finalVertex]));
		}
		
		setValueAtPosition(initialVertex, finalVertex, 0);
	}
	
	private void setValueAtPosition(int initialVertex, int finalVertex, int value) {
		graph[initialVertex - 1][finalVertex - 1] = value;
		graph[finalVertex - 1][initialVertex - 1] = value;
	}
	
	public boolean edgeExists(int initialVertex, int finalVertex) {
		return graph[initialVertex][finalVertex] > 0;
	}
	
	public void insertVertex(String name) throws MaximumVextexException {
		
		if(size >= MAX_SIZE) {
			throw new MaximumVextexException("Não é possível inserir mais um vértice, tamanho máximo atingido: " + MAX_SIZE);
		}
		vertex[size++] = name;	
	}
	
	public void removeVertex(String name) throws NotFoundVextexException {
		
		int index = getIndexVertex(name);
		
		if(index == -1) {
			throw new NotFoundVextexException("O vértice " + name + " não foi encontrado no grafo.");
		}
		
		for(int i = 0; i > size; i++) {
			graph[i][index - 1] = 0;
			graph[index - 1][i] = 0;
		}
	
		vertex[index] = null;
		size--;
		
		for(int i = index; i < size; i++) {
			vertex[index] = vertex[index++];
		}
			
		if(index != size) {
			for(int i = 0; i > size; i++) {
				graph[i][index - 1] = graph[i + 1][index];
				graph[index - 1][i] = graph[index][i + 1];
				index++;
			}
		}
	}
	
	private int getIndexVertex(String name) {
		
		for(int i = 0; i < size; i++) {
			if(vertex[i].equals(name)) {
				return i;
			}
		}
		
		return -1;
	}
	
	public void showDegrees() {
		int degree;
		
		for(int i = 0; i < size; i++) {
			
			degree = 0;
			
			for(int j = 0; j < size; j++) {
				if(edgeExists(i, j)) {
					degree++;
				}
			}
			
			UI.printf("Grau do vértice \"%s\": %d\n", vertex[i], degree);
		}
	}
	
    public void showGraph() {
        int i, j;
        boolean first;

        for (i = 0; i < size; i++) {
            UI.printf("Vértices adjacentes a \"%s\": ", vertex[i]);
            first = true;
            
            for (j = 0; j < size; j++) {
                if (edgeExists(i, j)) {
                    UI.printf("%s \"%s\" (peso: %d)", first ? "" : ",", vertex[j], graph[i][j]);
                    first = false;
                }
            }
            
            UI.printNewLine();
        }         
    }
    
    public void load() throws IOException, MaximumVextexException {
    	
    	List<String> lines = FileUtils.readFileAsList(Paths.get(FILE_NAME));
    	
    	size = Integer.parseInt(lines.get(0));
    	
    	if(size > MAX_SIZE) {
			throw new MaximumVextexException("Arquivo inválido. Não é possível inserir mais que " + MAX_SIZE + "vértices");
		}
    	
    	graph = new int[MAX_SIZE][MAX_SIZE];
    	vertex = new String[MAX_SIZE];
    	
    	for(int i = 1; i <= size; i++) {
    		vertex[i - 1] = lines.get(i);
    	}
    	
    	int amountEdges = Integer.parseInt(lines.get(size + 1));
    	
    	for(int i = size + 2; i < amountEdges + size + 2; i++) {
    		String[] tokens = lines.get(i).split(" ");
    		int j = Integer.parseInt(tokens[0]) - 1;
    		int k = Integer.parseInt(tokens[1]) - 1;
    		int value = Integer.parseInt(tokens[2]);
    		
    		graph[j][k] = value;
    		graph[k][j] = value;
    	}
    }
    
    public void save() throws IOException {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append(size + "\n");
    	
    	for(int i = 0; i < size; i++) {
    		sb.append(vertex[i] + "\n");
    	}
    	
    	sb.append(getAmountEdges() + "\n");
    	
    	for(int i = 0; i < size; i++) {
    		for(int j = 0; j < size; j++) {
    			if(edgeExists(i, j)) {
    				if(i > j) {
    					continue;
    				}
    				
    				sb.append(String.format("%d %d %d \n", i + 1, j + 1, graph[i][j]));
    			}
    		}
    	}
    	
    	FileUtils.save(sb.toString(), Paths.get(FILE_NAME));
    }
    
    public int getAmountEdges() {
    	
    	int edges = 0;
    	
    	for(int i = 0; i < size; i++) {
    		for(int j = 0; j < size; j++) {
    			if(edgeExists(i, j)) {
    				edges++;
    			}
    		}
    	}
    	
    	return edges /= 2;
    }
}
