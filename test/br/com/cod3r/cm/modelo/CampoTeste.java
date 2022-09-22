package br.com.cod3r.cm.modelo;

//import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.cod3r.cm.excecao.ExplosaoException;

public class CampoTeste {
    private Campo campo;

    @BeforeEach
    void iniciarCampo() {
        campo = new Campo(3, 3);
    }
    
    @Test
    void TesteVizinhoRealDistancia1Esquerda() {
        Campo vizinho = new Campo(3, 2);
        boolean resultado = campo.adcionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void TesteVizinhoRealDistancia1Direita() {
        Campo vizinho = new Campo(2, 3);
        boolean resultado = campo.adcionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void TesteVizinhoRealDistancia1EmCima() {
        Campo vizinho = new Campo(4, 3);
        boolean resultado = campo.adcionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void TesteVizinhoRealDistancia1EmBaixo() {
        Campo vizinho = new Campo(4, 3);
        boolean resultado = campo.adcionarVizinho(vizinho);
        assertTrue(resultado);
    }

    // Diagonal
    @Test
    void TesteVizinhoRealDistancia2() {
        Campo vizinho = new Campo(2, 2);
        boolean resultado = campo.adcionarVizinho(vizinho);
        assertTrue(resultado);
    }


    // Não é vizinho
    @Test
    void testeNaoVizinhoDistancia2() {
        Campo vizinho = new Campo(1, 1);
        boolean resultado = campo.adcionarVizinho(vizinho);
        assertFalse(resultado);
    }

    @Test
    void testeValorPadraoAtributoMarcado() {
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacao() {
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacaoDuasChamadas() {
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    // Teste de abertura
    @Test
    void testeAbrirNaoMinadoNaoMarcado() {
        assertTrue(campo.abrir());
    }

    @Test
    void testeAbrirNaoMinadoMarcado() {
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoMarcado() {
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoNaoMarcado() {
        campo.minar();
        assertThrows(ExplosaoException.class, () -> {
            campo.abrir();
        });
    }

    @Test
    void testeAbrirComVizinhos1() {
        Campo campo11 = new Campo(2, 2);

        Campo campo22 = new Campo(2, 3);
        campo22.adcionarVizinho(campo11);

        campo.adcionarVizinho(campo22);

        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
    void testeAbrirComVizinhos2() {
        Campo campo11 = new Campo(1, 1);
        Campo campo12 = new Campo(1, 1);
        campo12.minar();

        Campo campo22 = new Campo(2, 2);
        campo22.adcionarVizinho(campo11);
        campo22.adcionarVizinho(campo12);

        campo.adcionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isFechado());
    }
}

    
