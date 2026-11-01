
public class aresta {
    private vertice origem;
    private vertice destino;
    private Object valor;

    public aresta(vertice origem, vertice destino, Object valor) {
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
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

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
}