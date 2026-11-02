import java.util.ArrayList;
import java.util.Iterator;

public class grafo{
    private ArrayList<vertice> vertices;
    private ArrayList<ArrayList<Integer>> adjacencias;
    private ArrayList<ArrayList<Integer>> adjacenciasDirecionado;
    private ArrayList<aresta> arestas;

    public grafo(){
        this(null, null, null, null);
    }

    public grafo(ArrayList<vertice> vertices, ArrayList<ArrayList<Integer>> adjacencias, ArrayList<ArrayList<Integer>> adjacenciasDirecionado, ArrayList<aresta> arestas) {

        this.vertices = (vertices == null) ? new ArrayList<>() : vertices;
        this.adjacencias = (adjacencias == null) ? new ArrayList<>() : adjacencias;
        this.adjacenciasDirecionado = (adjacenciasDirecionado == null) ? new ArrayList<>() : adjacenciasDirecionado;
        this.arestas = (arestas == null) ? new ArrayList<>() : arestas;
    }

    public Iterator<vertice> finalVertices(aresta e){
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

    public boolean ehAdjacente(aresta v, aresta w){

        if (v == w) return false;

        if(v.getOrigem().equals(w.getOrigem()) || v.getOrigem().equals(w.getDestino())) return true;
        if(v.getDestino().equals(w.getOrigem()) || v.getDestino().equals(w.getDestino())) return true;

        return false;
    }

    public void substituir(vertice v, Object x) throws erroGrafo{
        if (!vertices.contains(v)) throw new erroGrafo("Vértice não pertence ao grafo.");
        v.setValor(x);
    }

    public void substituir(aresta e, Object x) throws erroGrafo{
        if (!arestas.contains(e)) throw new erroGrafo("Aresta não pertence ao grafo.");
        e.setValor(x);
    }

    public vertice inserirVertice(Object o){
        vertice novo = new vertice(o);
        vertices.add(novo);

        expandir(adjacencias);
        expandir(adjacenciasDirecionado);

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

        for(ArrayList<Integer> linha : adjacenciasDirecionado){
            linha.remove(i);
        }

        adjacencias.remove(i);
        adjacenciasDirecionado.remove(i);

        arestas.removeIf(e -> e.getOrigem().equals(v) || e.getDestino().equals(v));

        return v.getValor();

    }

    public Object removeAresta(aresta e) throws erroGrafo{

        int i = arestas.indexOf(e);

        if (i==-1) throw new erroGrafo("Aresta não pertence ao grafo.");

        arestas.remove(i);

        i = vertices.indexOf(e.getOrigem());
        int j = vertices.indexOf(e.getDestino());

        ArrayList<ArrayList<Integer>> m = e.ehDirecionado() ? adjacenciasDirecionado : adjacencias;

        m.get(i).set(j, m.get(i).get(j) - 1);
        if (!e.ehDirecionado() && i != j) {
            m.get(j).set(i, m.get(j).get(i) - 1);
        }

        return e.getValor();
    }

    public Iterator<aresta> arestasIncidentes(vertice v) throws erroGrafo{
        if (!vertices.contains(v)) throw new erroGrafo("Vértice não pertence ao grafo.");

        ArrayList<aresta> incidentes = new ArrayList<>();
        for (aresta e : arestas) {
            if (e.getOrigem().equals(v) || e.getDestino().equals(v)) incidentes.add(e);
        }

        return incidentes.iterator();
    }

    public Iterator<vertice> vertices(){ return vertices.iterator();}

    public Iterator<aresta> arestas(){ return arestas.iterator();}

    private void expandir(ArrayList<ArrayList<Integer>> matriz) {
        for (ArrayList<Integer> linha : matriz) linha.add(0);

        ArrayList<Integer> nova = new ArrayList<>();
        for (int k = 0; k < vertices.size(); k++) nova.add(0);
        matriz.add(nova);
    }

    public boolean eDirecionado(aresta e){
        return e.ehDirecionado();
    }

    public aresta inserirArestaDirecionada(vertice v, vertice w, Object o) throws erroGrafo{
        int i = vertices.indexOf(v);
        int j = vertices.indexOf(w);

        if (i==-1 || j==-1) throw new erroGrafo("Vértice não pertence ao grafo.");

        aresta novo = new aresta(v, w, o, true);

        arestas.add(novo);
        adjacenciasDirecionado.get(i).set(j, adjacenciasDirecionado.get(i).get(j)+1);

        return novo;
    }

    public void imprimirMatrizND() {
        System.out.println("Não direcionadas: " + adjacencias);
    }

    public void imprimirMatrizD() {
        System.out.println("Direcionadas:     " + adjacenciasDirecionado);
    }
}