import java.util.ArrayList;

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

    public boolean eAdjacente(aresta v, aresta w){

        if (v == w) return false;

        if(v.getOrigem().equals(w.getOrigem()) || v.getOrigem().equals(w.getDestino())) return true;
        if(v.getDestino().equals(w.getOrigem()) || v.getDestino().equals(w.getDestino())) return true;

        return false;
    }

    public void substituir(vertice v, Object x){
        if (!vertices.contains(v)) throw new erroGrafo("Vértice não pertence ao grafo.");
        v.setValor(x);
    }

    public void substituir(aresta e, Object x){
        if (!arestas.contains(e)) throw new erroGrafo("Aresta não pertence ao grafo.");
        e.setValor(x);
    }

    public vertice inserirVertice(Object o){
        vertice novo = new vertice(o);
        vertices.add(novo);

        for (ArrayList<Integer> linha : adjacencias) {
            linha.add(0);
        }

        ArrayList<Integer> novaLinha = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            novaLinha.add(0);
        }

        adjacencias.add(novaLinha);

        return novo;
    }

    public aresta inserirAresta(vertice v, vertice w, Object o) throws erroGrafo{

        int i = vertices.indexOf(v);
        int j = vertices.indexOf(w);

        if (i==-1 || j==-1) throw new erroGrafo("Vértice não pertence ao grafo.");

        aresta novo = new aresta(v, w, o);
        arestas.add(novo);


        int valor = adjacencias.get(i).get(j);
        adjacencias.get(i).set(j, valor+1);
        valor = adjacencias.get(j).get(i);
        if(i!=j) adjacencias.get(j).set(i, valor+1);
        return novo;
    }

    public Object removeVertice(vertice v) throws erroGrafo{

        int i = vertices.indexOf(v);

        if (i==-1) throw new erroGrafo("Vértice não pertence ao grafo.");

        vertices.remove(i);

        for(ArrayList<Integer> linha : adjacencias){
            linha.remove(i);
        }

        adjacencias.remove(i);

        arestas.removeIf(e -> e.getOrigem().equals(v) || e.getDestino().equals(v));

        return v.getValor();

    }

    public Object removeAresta(aresta e) throws erroGrafo{

        int i = arestas.indexOf(e);

        if (i==-1) throw new erroGrafo("Aresta não pertence ao grafo.");

        arestas.remove(i);

        i = vertices.indexOf(e.getOrigem());
        int j = vertices.indexOf(e.getDestino());

        adjacencias.get(i).set(j, adjacencias.get(i).get(j)-1);
        if(i!=j) adjacencias.get(j).set(i, adjacencias.get(j).get(i)-1);

        return e.getValor();
    }
}