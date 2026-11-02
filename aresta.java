
public class aresta {
    private vertice origem;
    private vertice destino;
    private Object valor;
    private boolean direcionado;

    public aresta(vertice origem, vertice destino, Object valor) {
        this(origem, destino, valor, false);
    }

    public aresta(vertice origem, vertice destino, Object valor, boolean direcao) {
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
        this.direcionado = direcao;
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

    public boolean ehDirecionado() { return direcionado; }
}