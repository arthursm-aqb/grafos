import java.util.Iterator;

public class testeGrafo {
    static void mostrar(String titulo, Iterator<aresta> it) {
        StringBuilder sb = new StringBuilder(titulo + ": ");
        while (it.hasNext()) sb.append(it.next().getValor()).append(" | ");
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        grafo g = new grafo();

        vertice a = g.inserirVertice("A");
        vertice b = g.inserirVertice("B");
        vertice c = g.inserirVertice("C");

        aresta ab1 = g.inserirAresta(a, b, "AB1");
        aresta ab2 = g.inserirAresta(a, b, "AB2");

        aresta aParaB = g.inserirArestaDirecionada(a, b, "A->B");
        aresta bParaA = g.inserirArestaDirecionada(b, a, "B->A");

        aresta lacoA = g.inserirAresta(a, a, "laçoA");
        aresta lacoC = g.inserirArestaDirecionada(c, c, "laçoC");


        aresta bc = g.inserirAresta(b, c, "BC");

        System.out.println("--- depois de inserir tudo ---");
        mostrar("todas", g.arestas());
        mostrar("incidentes em A", g.arestasIncidentes(a));
        mostrar("incidentes em C", g.arestasIncidentes(c));

        System.out.println("AB1 e AB2 (paralelas) adjacentes? " + g.ehAdjacente(ab1, ab2));
        System.out.println("A->B e B->A adjacentes? " + g.ehAdjacente(aParaB, bParaA));
        System.out.println("AB1 direcionada? " + g.eDirecionado(ab1));
        System.out.println("A->B direcionada? " + g.eDirecionado(aParaB));
        System.out.println("oposto de A no laçoA: " + g.oposto(a, lacoA).getValor());
        System.out.println("oposto de B em B->A: " + g.oposto(b, bParaA).getValor());

        System.out.println("--- removendo uma paralela (AB1) ---");
        g.removeAresta(ab1);
        mostrar("todas", g.arestas());

        System.out.println("--- removendo uma direcionada (A->B) ---");
        g.removeAresta(aParaB);
        mostrar("todas", g.arestas());

        System.out.println("--- removendo o vértice A ---");
        System.out.println("removeu: " + g.removeVertice(a));
        mostrar("todas", g.arestas());

        System.out.println("--- inserindo de novo entre B e C ---");
        g.inserirAresta(b, c, "BC2");
        g.inserirArestaDirecionada(c, b, "C->B");
        mostrar("todas", g.arestas());
        mostrar("incidentes em B", g.arestasIncidentes(b));
    }
}