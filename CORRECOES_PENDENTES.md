# Correcoes Pendentes - RPG Terminal Java

Este arquivo lista as correcoes identificadas para serem implementadas depois.

## Alta prioridade (corrigir primeiro)

<!-- ### 1) Validar escolha de classe antes de criar personagem
- Problema: se o usuario escolher opcao fora de `1..3`, `classEscolhida` fica `null`.
- Impacto: pode gerar erro ao instanciar `Personagem`.
- Onde corrigir: `src/App.java:24`, `src/App.java:41`, `src/com/model/Personagem.java:18`.
- Sugestao:
  - Repetir o menu ate receber opcao valida.
  - Ou definir classe padrao segura + aviso. -->

<!-- ### 2) Corrigir leitura de input entre `nextInt()` e `nextLine()`
- Problema: `App` usa `scanner.nextInt()` e `CombateController` usa `nextLine()`.
- Impacto: primeira leitura no combate pode virar opcao invalida por causa de `\n` pendente.
- Onde corrigir: `src/App.java:24`, `src/com/service/CombateController.java:105`.
- Sugestao:
  - Padronizar tudo com `nextLine()` + `Integer.parseInt(...)`.
  - Ou consumir `\n` apos `nextInt()`. -->

### 3) Implementar efeito real para itens de ataque
- Problema: itens do tipo `Ataque` sao consumidos mas nao causam efeito.
- Impacto: jogador perde item sem beneficio.
- Onde corrigir: `src/com/model/Items.java:42`, `src/com/model/Bolsa.java:15`, `src/com/model/Bolsa.java:16`.
- Sugestao:
  - Criar retorno de dano bonus ao usar item ofensivo.
  - Aplicar esse dano no fluxo do turno de combate.

## Media prioridade

<!-- ### 4) Exibir arma escolhida no fluxo de criacao
- Problema: `getArma()` e chamado e descartado.
- Impacto: informacao nao aparece para o jogador.
- Onde corrigir: `src/App.java:43`. -->

### 5) Melhorar retorno de `usarItem`
- Problema: retorno atual e generico (`"Item utilizado"`).
- Impacto: feedback ruim para o jogador.
- Onde corrigir: `src/com/service/CombateService.java:24`, `src/com/service/CombateService.java:34`.
- Sugestao:
  - Retornar nome do item e efeito aplicado.

### 6) Ajustar regra de cura com vida maxima
- Problema: cura limita vida em 100 fixo, mas classes podem iniciar com vida > 100.
- Impacto: regra inconsistente para classes como Guerreiro.
- Onde corrigir: `src/com/model/Personagem.java:66`, `src/com/model/Personagem.java:68`.
- Sugestao:
  - Adicionar campo `vidaMaxima` no personagem.
  - Usar `vidaMaxima` em `curar` e em ganho de nivel.

### 7) Remover campo sem uso ou integrar na regra de nivel
- Problema: `xpNecessario` existe no `Personagem`, mas a logica usa apenas `NivelService`.
- Impacto: codigo redundante/confuso.
- Onde corrigir: `src/com/model/Personagem.java:13`, `src/com/service/NivelService.java:24`.

## Baixa prioridade / limpeza tecnica

### 8) Atualizar README para refletir arquitetura atual
- Problema: README cita classes que nao existem no projeto atual (`Combate.java`, `Classe.java`).
- Impacto: documentacao inconsistente.
- Onde corrigir: `README.md:10`, `README.md:12`.

<!-- ### 9) Remover import redundante/autorreferente
- Problema: `CombateController` importa ele mesmo.
- Impacto: ruido no codigo.
- Onde corrigir: `src/com/service/CombateController.java:3`. -->

### 10) Melhorar encapsulamento de `Inimigo`
- Problema: `vida` e `ataque` nao estao `private`.
- Impacto: quebra de encapsulamento.
- Onde corrigir: `src/com/model/Inimigo.java:5`, `src/com/model/Inimigo.java:6`.

## Extras recomendados (quando houver tempo)
- Criar testes basicos para combate, itens e subida de nivel.
- Revisar mensagens com acentuacao/encoding para evitar texto quebrado no terminal.
- Remover arquivos `.class` de `src/` (deixar compilados so em `bin/` ou em pasta de build).
