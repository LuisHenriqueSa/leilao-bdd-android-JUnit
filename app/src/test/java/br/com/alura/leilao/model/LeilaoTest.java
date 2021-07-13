package br.com.alura.leilao.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario LUIS = new Usuario("Luis");

    @Test
    public void deve_DevolverDescricao_QuandoRecebeDescricao() {
        // executar acao esperada
        String descricaoDevolvida = CONSOLE.getDescricao();
        // testar resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeApenasUmLance() {
        CONSOLE.propoe(new Lance(LUIS, 200.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(200, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        CONSOLE.propoe(new Lance(new Usuario("Henrique"), 100.0));
        CONSOLE.propoe(new Lance(LUIS, 200.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(200, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {
        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(LUIS, 15900.0));
        carro.propoe(new Lance(new Usuario("Henrique"), 10000.0));

        double maiorLanceDevolvido = carro.getMaiorLance();

        assertEquals(15900.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeApenasUmLanc() {
        CONSOLE.propoe(new Lance(LUIS, 200.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(200.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        CONSOLE.propoe(new Lance(new Usuario("Henrique"), 100.0));
        CONSOLE.propoe(new Lance(LUIS, 200.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(100, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {
        CONSOLE.propoe(new Lance(LUIS, 15900.0));
        CONSOLE.propoe(new Lance(new Usuario("Henrique"), 10000.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(10000.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeExatosTresLances() {
        CONSOLE.propoe(new Lance(LUIS, 200));
        CONSOLE.propoe(new Lance(new Usuario("Vanecia"), 300));
        CONSOLE.propoe(new Lance(new Usuario("Henrique"), 400));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(400, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(200, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeLances() {
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(0, tresMaioresLancesDevolvidos.size());
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasUmLance() {
        CONSOLE.propoe(new Lance(LUIS, 200));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(1, tresMaioresLancesDevolvidos.size());
        assertEquals(200, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeDoisLances() {
        CONSOLE.propoe(new Lance(LUIS, 300));
        CONSOLE.propoe(new Lance(new Usuario("Henrique"), 400));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(2, tresMaioresLancesDevolvidos.size());
        assertEquals(400, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeMaisDeTresLances() {
        CONSOLE.propoe(new Lance(LUIS, 600));
        final Usuario HENRIQUE = new Usuario("Henrique");
        CONSOLE.propoe(new Lance(HENRIQUE, 500));
        CONSOLE.propoe(new Lance(LUIS, 400));
        CONSOLE.propoe(new Lance(HENRIQUE, 300));

        List<Lance> tresMaioresLancesDevolvidosParaQuatroLances = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidosParaQuatroLances.size());
        assertEquals(600, tresMaioresLancesDevolvidosParaQuatroLances.get(0).getValor(), DELTA);
        assertEquals(500, tresMaioresLancesDevolvidosParaQuatroLances.get(1).getValor(), DELTA);
        assertEquals(400, tresMaioresLancesDevolvidosParaQuatroLances.get(2).getValor(), DELTA);

        CONSOLE.propoe(new Lance(LUIS, 700.0));

        List<Lance> tresMaioresLancesParaCincoLances = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesParaCincoLances.size());
        assertEquals(700, tresMaioresLancesParaCincoLances.get(0).getValor(), DELTA);
        assertEquals(600, tresMaioresLancesParaCincoLances.get(1).getValor(), DELTA);
        assertEquals(500, tresMaioresLancesParaCincoLances.get(2).getValor(), DELTA);
    }

}