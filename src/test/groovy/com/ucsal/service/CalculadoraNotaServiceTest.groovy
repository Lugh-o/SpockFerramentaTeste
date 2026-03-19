package com.ucsal.service

import com.ucsal.model.Prova
import com.ucsal.repository.BonusRepository
import com.ucsal.repository.BonusRepositoryImplementado
import spock.lang.Specification
import spock.lang.Unroll

class CalculadoraNotaServiceTest extends Specification {
    def "calcula nota simples"() {
        given:
        BonusRepository repo = Stub(BonusRepository) {
            getBonus() >> 0
        }

        CalculadoraNotaService calculadora = new CalculadoraNotaService(repo)

        expect:
        calculadora.calcularNota(8, 2) == 7.5d
    }

    def "calcula nota com bonus"() {
        given:
        BonusRepository repo = Stub(BonusRepository) {
            getBonus() >> 2
        }

        CalculadoraNotaService calculadora = new CalculadoraNotaService(repo)

        expect:
        calculadora.calcularNota(8, 0) == 10d
    }

    def "deve lançar excecao para valores negativos"() {
        given:
        BonusRepository repo = Stub(BonusRepository) {
            getBonus() >> 0
        }

        CalculadoraNotaService calculadora = new CalculadoraNotaService(repo)

        when:
        calculadora.calcularNota(-1, 0)

        then:
        thrown(IllegalArgumentException)
    }

    def "nao deve lançar excecao para valores validos"() {
        given:
        BonusRepository repo = Stub(BonusRepository) {
            getBonus() >> 0
        }

        CalculadoraNotaService calculadora = new CalculadoraNotaService(repo)

        when:
        calculadora.calcularNota(5, 1)

        then:
        notThrown(Exception)
    }

    @Unroll
    def "calculo parametrizado acertos=#acertos erros=#erros"() {
        given:
        BonusRepository repo = Stub(BonusRepository) {
            getBonus() >> 0
        }

        def calculadora = new CalculadoraNotaService(repo)

        expect:
        calculadora.calcularNota(acertos, erros) == resultado

        where:
        acertos | erros || resultado
        10      | 0     || 10d
        5       | 0     || 5d
        5       | 4     || 4d
        0       | 4     || 0d
    }

    def "deve chamar bonusRepository"() {
        given:
        BonusRepository repo = Mock(BonusRepository)

        CalculadoraNotaService calculadora = new CalculadoraNotaService(repo)

        when:
        calculadora.calcularNota(5, 0)

        then:
        1 * repo.getBonus()
    }

    def "stub retorna bonus fixo"() {
        given:
        BonusRepository repo = Stub(BonusRepository) {
            getBonus() >> 5
        }

        CalculadoraNotaService calculadora = new CalculadoraNotaService(repo)

        expect:
        calculadora.calcularNota(5, 0) == 10d
    }

    def "spy usando implementacao real"() {
        given:
        BonusRepositoryImplementado real = new BonusRepositoryImplementado()
        BonusRepositoryImplementado repo = Spy(real)

        CalculadoraNotaService calculadora = new CalculadoraNotaService(repo)

        when:
        double nota = calculadora.calcularNota(5, 0)

        then:
        1 * repo.getBonus()
        nota == 6d
    }

    def "calcular usando objeto prova"() {
        given:
        BonusRepository repo = Stub(BonusRepository) {
            getBonus() >> 0
        }

        CalculadoraNotaService calculadora = new CalculadoraNotaService(repo)

        Prova prova = new Prova("Lucas", 8, 2)

        expect:
        calculadora.calcularNota(
                prova.getAcertos(),
                prova.getErros()
        ) == 7.5d
    }

    def "nota nao pode passar de 10"() {
        given:
        BonusRepository repo = Stub(BonusRepository) {
            getBonus() >> 10
        }

        CalculadoraNotaService calculadora = new CalculadoraNotaService(repo)

        expect:
        calculadora.calcularNota(10, 0) == 10d
    }

    def "nota nao pode ser negativa"() {
        given:
        BonusRepository repo = Stub(BonusRepository) {
            getBonus() >> 0
        }

        def calculadora = new CalculadoraNotaService(repo)

        expect:
        calculadora.calcularNota(0, 10) == 0d
    }
}