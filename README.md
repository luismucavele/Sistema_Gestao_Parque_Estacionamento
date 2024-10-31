Sistema de Gestão de Parque de Estacionamento de Viaturas
Autores: Julião Dombo, Luis Antonio, Marcelo Das Neves
Docente: João Metambo
Faculdade de Ciências (UEM)
Curso: Informática – Pós-laboral
Data: 23 de agosto de 2024

Descrição
Este projeto é um sistema automatizado de gestão de parques de estacionamento, desenvolvido para otimizar o uso de vagas, melhorar a transparência nas cobranças e reduzir erros no controle manual. O sistema foi desenvolvido em Java, com uso do banco de dados MySQL, e é destinado a solucionar problemas comuns em estacionamentos, como falta de visibilidade das vagas disponíveis e inconsistência nas tarifas cobradas.

Problema
Os parques de estacionamento atuais geralmente utilizam um controle manual para monitorar o tempo de permanência dos veículos, levando a possíveis erros, falta de transparência e má experiência do usuário. Esse sistema busca digitalizar e automatizar essas operações para garantir precisão e eficiência.

Objetivo Geral
Criar um sistema de gestão para parques de estacionamento que permita um controle eficiente das vagas, registro preciso das entradas e saídas de veículos e geração de relatórios financeiros.

Objetivos Específicos
Automatizar o registro de entrada e saída dos veículos.
Exibir o status das vagas em tempo real.
Gerar relatórios financeiros diários e mensais para acompanhamento da ocupação e da receita.
Funcionalidades
Reserva de Vagas: Permite a reserva de uma vaga com até 24 horas de antecedência.
Registro de Veículos: Registra a entrada e saída de veículos automaticamente.
Relatórios Financeiros: Gera relatórios sobre a receita e a ocupação diária do parque.
Visualização em Tempo Real: Exibe o status das vagas disponíveis, ocupadas e reservadas.
Estrutura do Sistema
O sistema é composto pelas seguintes classes:

Cliente: Armazena informações dos clientes, incluindo histórico de estacionamentos.
Funcionário: Modela os empregados do parque, responsáveis pelo controle de entrada e saída.
Estacionamento: Gerencia o parque, controlando as vagas e tarifas.
Parque: Representa as propriedades físicas do parque, como localização e número de vagas.
Veículo: Registra dados dos veículos, como placa e modelo.
Requisitos
Requisitos Funcionais:
Reserva de vaga com antecedência de até 24 horas.
Requisitos Não Funcionais:
Suporte a múltiplos usuários simultâneos em rede.
Escalabilidade para diferentes tamanhos de parque.
Tecnologias Utilizadas
Linguagem de Programação: Java
Banco de Dados: MySQL
