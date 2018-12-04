package binary.tree;

/**
 *
 * @author afonso
 */
public class BinaryTree {

    private Node root; //raiz da arvore, primeiro elemento

    public void inserir(int data) {
        Node novo = new Node(data, null, null);

        if (root == null) //arvore vazia
        {
            root = novo;
        } else {
            Node atual = root;
            Node anterior;
            while (true) {
                anterior = atual;
                if (data <= atual.getData()) { // indo para a esquerda
                    atual = atual.getLeft();
                    if (atual == null) {
                        anterior.setLeft(novo);
                        return;
                    }
                } else { //indo para a direita
                    atual = atual.getRight();
                    if (atual == null) {
                        anterior.setRight(novo);
                        return;
                    }
                }
            }
        }
    }

    public Node buscar(int item) {
        if (root == null) {
            return null; // se arvore vazia
        }
        Node atual = root;  // começa a procurar desde raiz
        while (atual.getData() != item) { // enquanto nao encontrou
            if (item < atual.getData()) {
                atual = atual.getLeft(); // caminha para esquerda
            } else {
                atual = atual.getRight(); // caminha para direita
            }
            if (atual == null) {
                return null; // encontrou uma folha -> sai
            }
        }
        return atual; //retorna o item encontrado
    }

    public boolean remover(int item) {
        if (root == null) {
            return false; // se arvore vazia
        }
        Node atual = root;
        Node pai = root;
        boolean filho_esq = true;

        while (atual.getData() != item) {
            pai = atual;
            if (item < atual.getData()) { // caminha para esquerda
                atual = atual.getLeft();
                filho_esq = true; // é filho a esquerda? sim
            } else { // caminha para direita
                atual = atual.getRight();
                filho_esq = false; // é filho a esquerda? NAO
            }
            if (atual == null) {
                return false; // encontrou uma folha
            }
        }

        // Se nao possui nenhum filho, deleta
        if (atual.getLeft() == null && atual.getRight() == null) {
            if (atual == root) {
                root = null;
            } else if (filho_esq) {
                pai.setLeft(null);
            } else {
                pai.setRight(null);
            }
        } // Se é pai e nao possui um filho a direita, substitui pela subarvore a direita
        else if (atual.getRight() == null) {
            if (atual == root) {
                root = atual.getLeft();
            } else if (filho_esq) {
                pai.setLeft(atual.getLeft());
            } else {
                pai.setRight(atual.getLeft());
            }
        } // Se é pai e nao possui um filho a esquerda, substitui pela subarvore a esquerda
        else if (atual.getLeft() == null) {
            if (atual == root) {
                root = atual.getRight();
            } else if (filho_esq) {
                pai.setLeft(atual.getRight());
            } else {
                pai.setRight(atual.getRight());
            }
        } // Se possui mais de um filho, se for um grau maior que pai
        else {
            Node sucessor = sucessor(atual);

            if (atual == root) {
                root = sucessor; // se raiz
            } else if (filho_esq) {
                pai.setLeft(sucessor);
            } else {
                pai.setRight(sucessor);
            }
            sucessor.setLeft(atual.getLeft());
        }

        return true;
    }

    private Node sucessor(Node del) {
        Node paisucessor = del;
        Node sucessor = del;
        Node atual = del.getRight(); // vai para a subarvore a direita

        while (atual != null) {
            paisucessor = sucessor;
            sucessor = atual;
            atual = atual.getLeft(); // caminha para a esquerda
        }
        if (sucessor != del.getRight()) { // se sucessor nao é o filho a direita do Nó que deverá ser eliminado
            paisucessor.setLeft(sucessor.getRight());
            sucessor.setRight(del.getRight()); // guardando a referencia a direita do sucessor 
        }
        return sucessor;
    }

}
