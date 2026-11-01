
public class grafo{
    private ArrayList<vertice> vertices;
    private ArrayList<ArrayList<Integer>> adjacencias;
    private ArrayList<aresta> arestas;

    public grafo(){
        this(null, null, null);
    }

    public grafo(ArrayList<vertice> vertices, ArrayList<ArrayList<Integer>> adjacencias, ArrayList<aresta> arestas) {

        this.vertices = (vertices == null) ? new ArrayList<>() : vertices;
        this.adjacencias = (adjacencias == null) ? new ArrayList<>() : adjacencias;
        this.arestas = (arestas == null) ? new ArrayList<>() : arestas;
    }

    public Iterator finalVertices(aresta e){
        ArrayList<vertice> retorno = new ArrayList<>();
        retorno.add(e.getOrigem());
        retorno.add(e.getDestino());
        return retorno.iterator();
    }

    public vertice oposto(vertice v, aresta e) throws erroGrafo{
        if(!e.verticeIncide(v)) throw new erroGrafo("A aresta não incide no vertice passado.");

        if(v.equals(e.getOrigem())) return e.getDestino();
        else return e.getOrigem();

    }
}