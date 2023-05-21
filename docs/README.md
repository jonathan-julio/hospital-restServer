# Hospital Mais Próximo - Servidor

## Estrutura de Diretórios

```bash
.
├── dist                      # programa compilado
├── docs                      # arquivos para documentação
└── src                       # código fonte
```

## Compilação

Tanto para compilar, quanto para executar é necessário possuir o Java 17.

```bash
make build                    # compila o programa usando o maven e move para a pasta dist/
```

## Execução

Após compilar o programas A execução pode ser feita com o comando a seguir.

```bash
java -jar ./dist/hospital-server.jar
```

O serviço será levantado na porta 8080.
