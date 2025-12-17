# KiwfyPet
Sistema web acadêmico para cadastro de tutores, pets e localizadores de animais perdidos. Inclui operação mestre/detalhe (tutor + pets), layout unificado com Bootstrap 5 e base de dados H2 populada com exemplos.

## Sumário
- Introdução
- Tecnologias
- Como rodar (Windows e Linux/macOS)
- Navegação e URLs
- Modelo de domínio
- CRUDs entregues
- Operação mestre/detalhe
- Relatório (exemplos)
- Prints das telas
- Dados de exemplo
- Desenvolvimento e hot reload
- Arquitetura do sistema
- Problemas comuns
- Cronograma/versão

## Tecnologias
- Java 21, Spring Boot 4.0, Spring MVC, Spring Data JPA, Thymeleaf.
- Banco H2 em memória (console em `/h2-console`).
- Bootstrap 5 + Bootstrap Icons; DevTools para reload.

## Como rodar
1) Pré-requisitos: Java 21 na máquina. Maven não é necessário (usa wrapper).
2) Dentro da pasta do projeto:
   - Windows: `cd kiwfy-pet && .\mvnw.cmd spring-boot:run`
   - Linux/macOS: `cd kiwfy-pet && ./mvnw spring-boot:run`
3) Acesse `http://localhost:8080/`.

Parar a aplicação: `Ctrl + C` no terminal.

## Navegação e URLs
- Home: `/` mostra destaque de animais sendo procurados (localizadores).
- Pets: `/pets` (CRUD).
- Tutores: `/tutores` (CRUD).
- Tutor + Pets (mestre/detalhe): `/tutores-md` para cadastrar; `/tutores-md/editar/{id}` para editar com pets na mesma tela.
- Localizadores: `/localizadores` (CRUD + marcar encontrado).
- H2 Console: `/h2-console` (JDBC URL `jdbc:h2:mem:testdb`, user `sa`, senha vazia).

## Modelo de domínio
- `Tutor` 1..* `Pet` (um tutor possui vários pets).
- `Pet` pertence a um `Tutor` (`@ManyToOne`).
- `Localizador` representa quem procura um animal perdido; inclui localização, foto e status encontrado.
- Entidades antigas de `Owner` permanecem apenas para legado, sem relação ativa com `Pet`.

## CRUDs entregues
- Tutores: listar, criar, editar, excluir.
- Pets: listar, criar, editar, excluir; seleção de tutor no formulário.
- Localizadores: listar, criar, editar, excluir; ação de “Marcar encontrado”.
- Owners (legado): listar/criar/editar/excluir; sem relação com pets (apenas para compatibilidade).

## Operação mestre/detalhe (Tutor + Pets)
- Tela dedicada em `/tutores-md/novo` para criar tutor e múltiplos pets na mesma página.
- Edição com itens em `/tutores-md/editar/{id}`; adiciona/remover pets dinamicamente.
- Cascade + orphanRemoval no `Tutor` para salvar/remover pets juntos.

## Relatório (exemplos)
- Top 5 pets mais cadastrados por tutor (contagem de pets agrupada por tutor).
- Tutores sem pets (lista de tutores com coleção vazia).
- Localizadores com status “perdido” (para priorizar atendimento).
- Adapte consultas no repositório ou via JPQL/SQL conforme necessidade do trabalho.

## Prints das telas
- Home com destaques de animais procurados.
- Listagens e formulários de Pets, Tutores, Localizadores.
- Tela mestre/detalhe de Tutor + Pets.
- Inclua as capturas no repositório (pasta `docs/prints`) ou no README usando links.

## Dados de exemplo
`src/main/resources/data.sql` insere:
- Tutores: Arthur (Bisteca), Brenda (Kiwi), Thiago (Crystal).
- Pets com bairro, coordenadas e foto.
- Localizadores: Leandra (Baby), Carlos (Caramelo), Marina (Catarina) com fotos, status e bairro.

## Desenvolvimento e hot reload
- DevTools e cache do Thymeleaf desabilitado para atualizar templates em tempo real.
- Propriedade `spring.jpa.defer-datasource-initialization=true` garante criação do schema antes de rodar `data.sql`.

## Arquitetura do sistema
- Camada de apresentação: Spring MVC + Thymeleaf + Bootstrap (templates em `src/main/resources/templates`).
- Camada de serviço: regras de negócio nos `*Service`.
- Persistência: Spring Data JPA (`*Repository`) com H2 em memória.
- Entidades: `Tutor`, `Pet`, `Localizador` (e `Owner` legado).
- Arquivos estáticos: `src/main/resources/static`.
- Configuração: `application.properties`, carga inicial `data.sql`.

## Problemas comuns
- Porta 8080 em uso: fechar outro processo (`netstat -ano | find "8080"` e `taskkill /PID <pid> /F` no Windows).
- Banco vazio: reinicie a aplicação para reexecutar o `data.sql`.
- Erros de template: verifique se acessos a campos removidos (ex.: `owner.pets`) foram limpos.

## Cronograma / versão
- Etapa 3 (CRUD 2 + CRUD 3 + Layout) atendida: CRUDs principais prontos, layout padronizado com Bootstrap 5.
- Etapa 4 (mestre/detalhe) atendida: tutor + pets na mesma tela com inclusão/remoção e persistência.
- Tag sugerida para este marco: `v0.3-crud3` (após confirmar no git).
