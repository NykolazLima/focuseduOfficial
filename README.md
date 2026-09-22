# FocusEdu

Aplicação desktop para gerenciamento de **atividades e projetos escolares**, desenvolvida em Java com JavaFX.

## Descrição do sistema

O FocusEdu é um aplicativo voltado a estudantes que permite organizar as atividades escolares de forma simples e visual. A interface exibe as atividades em cards selecionáveis, com suporte a matérias, prioridades e prazos.

**Funcionalidades:**

- Criar, editar e excluir atividades
- Marcar atividades como concluídas ou reabri-las
- Classificação por matéria (Matemática, L. Portuguesa, História, Geografia, Ciências, Filosofia, Outra)
- Prioridades: Baixa, Média e Alta
- Controle de prazo com destaque para atividades vencidas
- Estados "A fazer" e "Concluída"

Os dados são mantidos em memória durante a execução (persistência em banco de dados ainda não implementada).

## Integrantes

- Nícolas Lima de Araújo
- Paulo Alves dos Reis Neto
- Ricardo Alves Silva

## Tecnologias

- Java 25
- JavaFX 21
- Maven

## Pré-requisitos

- JDK 25
- Maven 3.x

## Execução

Na raiz do projeto:

```bash
mvn clean javafx:run
```

Ou, no Eclipse: importe o projeto Maven e execute a classe `br.com.focusedu.focusedu.App` como Java Application.

## Estrutura do projeto

```
src/main/java/br/com/focusedu/focusedu/
├── App.java                    # Classe principal (entry point JavaFX)
├── model/                      # Modelo de domínio (Atividade, Materia, Status, Prioridade)
├── view/                       # Telas (TelaMain, AtividadeDialog)
│   └── components/             # Componentes da UI (Header, MenuLateral, ActionBar, cards)
└── repository/                 # Camada de persistência (a implementar)
src/main/resources/
└── focusedu/css/style.css      # Estilos da interface
```
