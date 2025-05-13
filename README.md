# Conversor de Moedas em Java (por [@DevHmsr](https://github.com/DevHmsr)) 💱

Este é um programa simples em Java que permite realizar conversões de diferentes moedas utilizando taxas de câmbio em tempo real. O programa oferece uma interface no terminal para que o usuário possa escolher entre diferentes opções de conversão e visualizar o valor convertido.

Além disso, ele também registra todas as conversões realizadas em arquivos de log, armazenados na pasta `logs`, com um timestamp único para cada conversão.

## 🟢 Funcionalidades

- Conversão entre diferentes moedas: Dólar, Peso Argentino, Real Brasileiro e Peso Colombiano.
- Utilização de taxas de câmbio em tempo real, obtidas da API [ExchangeRate](https://www.exchangerate-api.com).
- Armazenamento das conversões realizadas em arquivos JSON, dentro da pasta `logs`.
- Organização dos logs com timestamps únicos para cada conversão.

## ⚠ Requisitos

- Java 8 ou superior.
- Conexão com a internet para obter as taxas de câmbio em tempo real via API.

## ✅ Instalação

1. Clone o repositório:
```bash
git clone https://github.com/DevHmsr/Desafio-Conversor-de-Moedas-Alura.git
cd Desafio-Conversor-de-Moedas-Alura
```

2. Abra o projeto em seu ambiente de desenvolvimento preferido (ex.: IntelliJ, VS Code, etc.) ou utilize a linha de comando.
3. Compile o código: 
```
javac ConversorMoedas.java AssistenteInput.java ServicoConversao.java ApiTaxaConversao.java CriarJson.java
```
4. Execute o programa:
```
java ConversorMoedas
```
## ✍ Como usar
1. Ao executar o programa, será exibido um menu no terminal com as opções de conversão de moedas.

2. Escolha uma das opções digitando o número correspondente à conversão desejada.

3. Em seguida, o programa solicitará que você digite o valor a ser convertido.

4. O valor será convertido com base nas taxas de câmbio em tempo real, e o resultado será exibido no terminal.

5. O programa também registrará o resultado da conversão em um arquivo dentro da pasta logs.

## 💰 Exemplos de Conversões

1) Dólar => Peso Argentino

2) Peso Argentino => Dólar

3) Dólar => Real Brasileiro

4) Real Brasileiro => Dólar

5) Dólar => Peso Colombiano

6) Peso Colombiano => Dólar

## ⚙ Estrutura de Arquivos

`ConversorMoedas.java:` Classe principal que controla o fluxo de execução do programa.

`AssistenteInput.java:` Classe auxiliar para realizar a leitura segura das entradas do usuário.

`ServicoConversao.java:` Classe que realiza a conversão de moedas e salva os resultados em arquivos de log.

`ApiTaxaConversao.java:` Classe responsável por buscar as taxas de câmbio em tempo real via API externa.

`CriarJson.java:` Classe responsável pela criação do arquivo JSON contendo os detalhes da conversão.

## Exemplo de Arquivo de Log

Após a conversão, um arquivo de log será gerado com o seguinte formato JSON:

```
{
  "valorOriginal": 100.0,
  "moedaOrigem": "USD",
  "moedaDestino": "ARS",
  "taxa": 112.35,
  "valorConvertido": 11235.0
}
```
Esse arquivo será armazenado na pasta logs.

## Obrigado por ler até aqui! 
Espero que esse programa seja útil para você 😁
