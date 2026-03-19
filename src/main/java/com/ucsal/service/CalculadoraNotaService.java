package com.ucsal.service;

import com.ucsal.repository.BonusRepository;

public class CalculadoraNotaService {
    private final BonusRepository bonusRepository;

    public CalculadoraNotaService(BonusRepository bonusRepository) {
        this.bonusRepository = bonusRepository;
    }

    public double calcularNota(int acertos, int erros) {
        if (acertos < 0 || erros < 0) {
            throw new IllegalArgumentException();
        }

        double nota = acertos * 1.0 - erros * 0.25;

        nota += bonusRepository.getBonus();

        if (nota > 10) {
            nota = 10;
        }

        if (nota < 0) {
            nota = 0;
        }

        return nota;
    }
}