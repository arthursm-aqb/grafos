
public class aresta {
    private vertice origem;
    private vertice destino;

    public aresta(vertice origem, vertice destino) {
        this.origem = origem;
        this.destino = destino;
    }

    public vertice getOrigem() {
        return origem;
    }

    public void setOrigem(vertice origem) {
        this.origem = origem;
    }

    public vertice getDestino() {
        return destino;
    }

    public void setDestino(vertice destino) {
        this.destino = destino;
    }

    public boolean verticeIncide(vertice v){
        return (origem.equals(v)) || (destino.equals(v));
    }
}