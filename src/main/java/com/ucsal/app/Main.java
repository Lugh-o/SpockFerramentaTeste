package com.ucsal.app;

import com.ucsal.model.Prova;
import com.ucsal.repository.BonusRepository;
import com.ucsal.repository.BonusRepositoryImplementado;
import com.ucsal.service.CalculadoraNotaService;

public class Main {
    public static void main(String[] args) {

        BonusRepository repo = new BonusRepositoryImplementado();

        CalculadoraNotaService calculadora = new CalculadoraNotaService(repo);

        Prova prova = new Prova("Lucas", 8, 2);

        double nota = calculadora.calcularNota(
                prova.getAcertos(),
                prova.getErros()
        );

        System.out.println("Aluno: " + prova.getAluno());
        System.out.println("Nota final: " + nota);
    }
}